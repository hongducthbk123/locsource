package com.sensorsdata.analytics.android.sdk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sensorsdata.analytics.android.sdk.DbAdapter.Table;

class SensorsDataDBHelper extends SQLiteOpenHelper {
    private static final String CREATE_EVENTS_TABLE = ("CREATE TABLE " + Table.EVENTS.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "data" + " STRING NOT NULL, " + "created_at" + " INTEGER NOT NULL);");
    private static final int DATABASE_VERSION = 4;
    private static final String EVENTS_TIME_INDEX = ("CREATE INDEX IF NOT EXISTS time_idx ON " + Table.EVENTS.getName() + " (" + "created_at" + ");");
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_DATA = "data";
    private static final String TAG = "SA.SQLiteOpenHelper";

    SensorsDataDBHelper(Context context, String dbName) {
        super(context, dbName, null, 4);
    }

    public void onCreate(SQLiteDatabase db) {
        SALog.m1608i(TAG, "Creating a new Sensors Analytics DB");
        db.execSQL(CREATE_EVENTS_TABLE);
        db.execSQL(EVENTS_TIME_INDEX);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SALog.m1608i(TAG, "Upgrading app, replacing Sensors Analytics DB");
        db.execSQL("DROP TABLE IF EXISTS " + Table.EVENTS.getName());
        db.execSQL(CREATE_EVENTS_TABLE);
        db.execSQL(EVENTS_TIME_INDEX);
    }
}
