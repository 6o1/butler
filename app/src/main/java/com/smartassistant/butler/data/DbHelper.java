package com.smartassistant.butler.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.smartassistant.butler.data.DbContract.*;

/**
 * Created by emrekgn on 1/28/2017.
 */
public class DbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "butler.db";

    // If you change the database schmea, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold user data
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_SURNAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_ACTIVE + " INTEGER NOT NULL, " +
                UserEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);

        // Create a table to hold follow data
        final String SQL_CREATE_FOLLOW_TABLE = "CREATE TABLE " + FollowEntry.TABLE_NAME + " (" +
                FollowEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FollowEntry.COLUMN_FOLLOWER_ID + " INTEGER NOT NULL REFERENCES " + UserEntry.TABLE_NAME + "(" + UserEntry._ID + ") ON UPDATE CASCADE, " +
                FollowEntry.COLUMN_FOLLOWED_ID + " INTEGER NOT NULL REFERENCES " + UserEntry.TABLE_NAME + "(" + UserEntry._ID + ") ON UPDATE CASCADE, " +
                FollowEntry.COLUMN_NOTIFY_ON_EVENT_CREATION + " INTEGER NOT NULL, " +
                FollowEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_FOLLOW_TABLE);

        // Create a table to hold category data
        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + CategoryEntry.TABLE_NAME + " (" +
                CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CategoryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                CategoryEntry.COLUMN_DESC + " TEXT NOT NULL, " +
                CategoryEntry.COLUMN_USER_ID + " INTEGER REFERENCES " + UserEntry.TABLE_NAME + "(" + UserEntry._ID + ") ON UPDATE CASCADE, " +
                CategoryEntry.COLUMN_PUBLIC + " INTEGER NOT NULL, " +
                CategoryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY_TABLE);

        // Create a table to hold tag data
        final String SQL_CREATE_TAG_TABLE = "CREATE TABLE " + TagEntry.TABLE_NAME + " (" +
                TagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TagEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                TagEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_TAG_TABLE);

        // Create a table to hold category-tag data
        final String SQL_CREATE_CATEGORY_TAG_TABLE = "CREATE TABLE " + CategoryTagEntry.TABLE_NAME + " (" +
                CategoryTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CategoryTagEntry.COLUMN_CATEGORY_ID + " INTEGER NOT NULL REFERENCES " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry._ID + ") ON UPDATE CASCADE, " +
                CategoryTagEntry.COLUMN_TAG_ID + " INTEGER NOT NULL REFERENCES " + TagEntry.TABLE_NAME + "(" + TagEntry._ID + ") ON UPDATE CASCADE, " +
                CategoryTagEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY_TAG_TABLE);

        // Create a table to hold subscription data
        final String SQL_CREATE_SUBSCRIPTION_TABLE = "CREATE TABLE " + SubscriptionEntry.TABLE_NAME + " (" +
                SubscriptionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SubscriptionEntry.COLUMN_USER_ID + " INTEGER NOT NULL REFERENCES " + UserEntry.TABLE_NAME + "(" + UserEntry._ID + ") ON UPDATE CASCADE, " +
                SubscriptionEntry.COLUMN_CATEGORY_ID + " INTEGER NOT NULL REFERENCES " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry._ID + ") ON UPDATE CASCADE, " +
                SubscriptionEntry.COLUMN_NOTIFICATION_TYPE + " INTEGER, " +
                SubscriptionEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_SUBSCRIPTION_TABLE);

        // Create a table to hold subscription-tag data
        final String SQL_CREATE_SUBSCRIPTION_TAG_TABLE = "CREATE TABLE " + SubscriptionTagEntry.TABLE_NAME + " (" +
                SubscriptionTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SubscriptionTagEntry.COLUMN_SUBSCRIPTION_ID + " INTEGER NOT NULL REFERENCES " + SubscriptionEntry.TABLE_NAME + "(" + SubscriptionEntry._ID + ") ON UPDATE CASCADE, " +
                SubscriptionTagEntry.COLUMN_CATEGORY_TAG_ID + " INTEGER NOT NULL REFERENCES " + CategoryTagEntry.TABLE_NAME + "(" + CategoryTagEntry._ID + ") ON UPDATE CASCADE, " +
                SubscriptionTagEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_SUBSCRIPTION_TAG_TABLE);

        // Create a table to hold event data
        final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
                EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EventEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                EventEntry.COLUMN_DESC + " TEXT, " +
                EventEntry.COLUMN_CATEGORY_ID + " INTEGER NOT NULL REFERENCES " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry._ID + ") ON UPDATE CASCADE, " +
                EventEntry.COLUMN_USER_ID + " INTEGER REFERENCES " + UserEntry.TABLE_NAME + "(" + UserEntry._ID + ") ON UPDATE CASCADE, " +
                EventEntry.COLUMN_START_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                EventEntry.COLUMN_END_DATE + " TIMESTAMP NOT NULL, " +
                EventEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_EVENT_TABLE);

        // Create a table to hold event-tag data
        final String SQL_CREATE_EVENT_TAG_TABLE = "CREATE TABLE " + EventTagEntry.TABLE_NAME + " (" +
                EventTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EventTagEntry.COLUMN_EVENT_ID + " INTEGER NOT NULL REFERENCES " + EventEntry.TABLE_NAME + "(" + EventEntry._ID + ") ON UPDATE CASCADE, " +
                EventTagEntry.COLUMN_CATEGORY_TAG_ID + " INTEGER NOT NULL REFERENCES " + CategoryTagEntry.TABLE_NAME + "(" + CategoryTagEntry._ID + ") ON UPDATE CASCADE, " +
                EventTagEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_EVENT_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the tables and create new tables. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the tables
        // instead of dropping, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FollowEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CategoryTagEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SubscriptionEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SubscriptionTagEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TagEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EventTagEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
