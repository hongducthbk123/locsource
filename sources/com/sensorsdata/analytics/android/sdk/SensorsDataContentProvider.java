package com.sensorsdata.analytics.android.sdk;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.sensorsdata.analytics.android.sdk.DbAdapter.Table;

public class SensorsDataContentProvider extends ContentProvider {
    private static final int EVENTS = 1;
    private static UriMatcher uriMatcher = new UriMatcher(-1);
    private ContentResolver contentResolver;
    private SensorsDataDBHelper dbHelper;

    public boolean onCreate() {
        Context context = getContext();
        if (context != null) {
            this.contentResolver = context.getContentResolver();
            uriMatcher.addURI(context.getApplicationContext().getPackageName() + ".SensorsDataContentProvider", "events", 1);
            this.dbHelper = new SensorsDataDBHelper(context, context.getApplicationContext().getPackageName());
        }
        return true;
    }

    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int id = 0;
        try {
            return this.dbHelper.getWritableDatabase().delete(Table.EVENTS.getName(), selection, selectionArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return id;
        }
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        try {
            if (uriMatcher.match(uri) == 1) {
                return ContentUris.withAppendedId(uri, this.dbHelper.getWritableDatabase().insert(Table.EVENTS.getName(), APEZProvider.FILEID, values));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        try {
            if (uriMatcher.match(uri) == 1) {
                return this.dbHelper.getReadableDatabase().query(Table.EVENTS.getName(), projection, selection, selectionArgs, null, null, sortOrder);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
