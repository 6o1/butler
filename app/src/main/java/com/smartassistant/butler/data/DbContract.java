package com.smartassistant.butler.data;

import android.provider.BaseColumns;

/**
 * Created by emrekgn on 1/28/2017.
 */
public class DbContract {

    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_ACTIVE = "active";
    }

    public static final class FollowEntry implements BaseColumns {
        public static final String TABLE_NAME = "follow";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_FOLLOWER_ID = "followerId";
        public static final String COLUMN_FOLLOWED_ID = "followedId";
        public static final String COLUMN_NOTIFY_ON_EVENT_CREATION = "notifyOnEvent";
    }

    public static final class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_USER_ID = "userId";
        public static final String COLUMN_PUBLIC = "public";
    }

    public static final class TagEntry implements BaseColumns {
        public static final String TABLE_NAME = "tag";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME = "name";
    }

    public static final class CategoryTagEntry implements BaseColumns {
        public static final String TABLE_NAME = "categoryTag";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_CATEGORY_ID = "categoryId";
        public static final String COLUMN_TAG_ID = "tagId";
    }

    public static final class SubscriptionEntry implements BaseColumns {
        public static final String TABLE_NAME = "subscription";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_USER_ID = "userId";
        public static final String COLUMN_CATEGORY_ID = "categoryId";
        public static final String COLUMN_NOTIFICATION_TYPE = "notificationType";
    }

    public static final class SubscriptionTagEntry implements BaseColumns {
        public static final String TABLE_NAME = "subscriptionTag";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_SUBSCRIPTION_ID = "subscriptionId";
        public static final String COLUMN_CATEGORY_TAG_ID = "categoryTagId";
    }

    public static final class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_CATEGORY_ID = "categoryId";
        public static final String COLUMN_USER_ID = "userId";
        public static final String COLUMN_START_DATE = "startDate";
        public static final String COLUMN_END_DATE = "endDate";
    }

    public static final class EventTagEntry implements BaseColumns {
        public static final String TABLE_NAME = "eventTag";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_EVENT_ID = "eventId";
        public static final String COLUMN_CATEGORY_TAG_ID = "categoryTagId";
    }

}
