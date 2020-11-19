package com.example.casestudy.database;

import android.provider.BaseColumns;

public class FeedReaderContract {
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "city_table";
        public static final String COLUMN_CITY_NAME = "city_name";
        public static final String COLUMN_CITY_CODE = "city_code";
    }
}
