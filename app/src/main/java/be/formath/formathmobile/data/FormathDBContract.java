package be.formath.formathmobile.data;

import android.provider.BaseColumns;

public final class FormathDBContract {
    public static class TableGame implements BaseColumns {
        public static final String TABLE_NAME = "game";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_RESULT = "result";
        public static final String COLUMN_NAME_REMAINING_TIME = "remaining_time";
    }

    public static class TableOperation implements BaseColumns {
        public static final String TABLE_NAME = "operation";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_LABEL = "label";
        public static final String COLUMN_NAME_RESPONSE = "response";
        public static final String COLUMN_NAME_RAW_RESPONSE = "rawResponse";
        public static final String COLUMN_NAME_GIVEN_RESPONSE = "givenResponse";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_GAME_ID = "game_id";
    }

    public static class TableUser implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USER_NAME = "userName";
        public static final String COLUMN_NAME_FIRST_NAME = "firstName";
        public static final String COLUMN_NAME_LAST_NAME = "lastName";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_DISTANT_IDENTIFIER = "distantIdentifier";
        public static final String COLUMN_NAME_OFFLINE_USER = "offlineUser";
    }

    public static class TableMedal implements BaseColumns {
        public static final String TABLE_NAME = "medal";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_LEVEL = "LEVEL";
        public static final String COLUMN_NAME_OBTAINING_DATE = "obtainingDate";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_USER = "user";
    }
}
