package com.smartassistant.butler.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emrekgn on 1/28/2017.
 */
public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        // Create a list of fake users
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(DbContract.UserEntry.COLUMN_NAME, "Volkan");
        cv.put(DbContract.UserEntry.COLUMN_SURNAME, "Şahin");
        cv.put(DbContract.UserEntry.COLUMN_ACTIVE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.UserEntry.COLUMN_NAME, "Caner");
        cv.put(DbContract.UserEntry.COLUMN_SURNAME, "Feyzullahoğlu");
        cv.put(DbContract.UserEntry.COLUMN_ACTIVE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.UserEntry.COLUMN_NAME, "Cemre");
        cv.put(DbContract.UserEntry.COLUMN_SURNAME, "Alpsoy");
        cv.put(DbContract.UserEntry.COLUMN_ACTIVE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.UserEntry.COLUMN_NAME, "Emre");
        cv.put(DbContract.UserEntry.COLUMN_SURNAME, "Akkaya");
        cv.put(DbContract.UserEntry.COLUMN_ACTIVE, 0);
        list.add(cv);

        // Insert everything in one transaction
        try
        {
            db.beginTransaction();
            // Clear the table first
            db.delete (DbContract.UserEntry.TABLE_NAME, null, null);
            // Go through the list and add one by one
            for(ContentValues c:list){
                db.insert(DbContract.UserEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

        // Create a list of fake tags
        list.clear();

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "OSYM");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "ALES");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "YDS");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "Sınav");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "TFF");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "Galatasaray");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "Fenerbahçe");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.TagEntry.COLUMN_NAME, "Trabzonspor");
        list.add(cv);

        try
        {
            db.beginTransaction();
            // Clear the table first
            db.delete (DbContract.TagEntry.TABLE_NAME, null, null);
            // Go through the list and add one by one
            for(ContentValues c:list){
                db.insert(DbContract.TagEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

        // Create a list of fake categories
        list.clear();

        cv = new ContentValues();
        cv.put(DbContract.CategoryEntry.COLUMN_NAME, "OSYM Sınav Takvimi");
        cv.put(DbContract.CategoryEntry.COLUMN_DESC, "OSYM tarafından düzenlenen tüm sınavlar, duyurular ve sınav sonuçları.");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.CategoryEntry.COLUMN_NAME, "TFF Fikstürü");
        cv.put(DbContract.CategoryEntry.COLUMN_DESC, "TFF liglerinin fikstürü ve maç sonuçları.");
        list.add(cv);

        try
        {
            db.beginTransaction();
            // Clear the table first
            db.delete (DbContract.CategoryEntry.TABLE_NAME, null, null);
            // Go through the list and add one by one
            for(ContentValues c:list){
                db.insert(DbContract.CategoryEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

        // Create a list of fake events
        list.clear();
        try
        {
            db.beginTransaction();
            // Clear the table first
            db.delete (DbContract.EventEntry.TABLE_NAME, null, null);
            // Go through the list and add one by one
            for(ContentValues c:list){
                db.insert(DbContract.EventEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }

}
