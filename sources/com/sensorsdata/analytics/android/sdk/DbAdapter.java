package com.sensorsdata.analytics.android.sdk;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.android.vending.expansion.zipfile.APEZProvider;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class DbAdapter {
    public static final int DB_OUT_OF_MEMORY_ERROR = -2;
    public static final int DB_UPDATE_ERROR = -1;
    public static final String KEY_CREATED_AT = "created_at";
    public static final String KEY_DATA = "data";
    private static final String TAG = "SA.DbAdapter";
    private ContentResolver contentResolver = this.mContext.getContentResolver();
    private final Context mContext;
    private final File mDatabaseFile;
    private Uri mUri;

    public enum Table {
        EVENTS("events");
        
        private final String mTableName;

        private Table(String name) {
            this.mTableName = name;
        }

        public String getName() {
            return this.mTableName;
        }
    }

    private long getMaxCacheSize(Context context) {
        try {
            return SensorsDataAPI.sharedInstance(context).getMaxCacheSize();
        } catch (Exception e) {
            e.printStackTrace();
            return 33554432;
        }
    }

    private boolean belowMemThreshold() {
        if (!this.mDatabaseFile.exists() || Math.max(this.mDatabaseFile.getUsableSpace(), getMaxCacheSize(this.mContext)) >= this.mDatabaseFile.length()) {
            return true;
        }
        return false;
    }

    public DbAdapter(Context context, String dbName) {
        this.mContext = context;
        this.mDatabaseFile = context.getDatabasePath(dbName);
        this.mUri = Uri.parse("content://" + dbName + ".SensorsDataContentProvider/" + Table.EVENTS.getName());
    }

    public int addJSON(JSONObject j, Table table) {
        int count = -1;
        Cursor c = null;
        try {
            if (!belowMemThreshold()) {
                SALog.m1608i(TAG, "There is not enough space left on the device to store events, so will delete some old events");
                String[] eventsData = generateDataString(Table.EVENTS, 100);
                if (eventsData != null) {
                    count = cleanupEvents(eventsData[0], Table.EVENTS);
                    if (count <= 0) {
                        if (c == null) {
                            return -2;
                        }
                        c.close();
                        return -2;
                    }
                } else if (c == null) {
                    return -2;
                } else {
                    c.close();
                    return -2;
                }
            }
            ContentValues cv = new ContentValues();
            cv.put("data", j.toString() + "\t" + j.toString().hashCode());
            cv.put(KEY_CREATED_AT, Long.valueOf(System.currentTimeMillis()));
            this.contentResolver.insert(this.mUri, cv);
            Cursor c2 = this.contentResolver.query(this.mUri, null, null, null, null);
            if (c2 != null) {
                count = c2.getCount();
            }
            if (c2 != null) {
                c2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (c != null) {
                c.close();
            }
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
        return count;
    }

    public int cleanupEvents(String last_id, Table table) {
        Cursor c = null;
        int count = -1;
        try {
            this.contentResolver.delete(this.mUri, "_id <= ?", new String[]{last_id});
            Cursor c2 = this.contentResolver.query(this.mUri, null, null, null, null);
            if (c2 != null) {
                count = c2.getCount();
            }
            if (c2 != null) {
                c2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (c != null) {
                c.close();
            }
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
        return count;
    }

    public String[] generateDataString(Table table, int limit) {
        Cursor c = null;
        String data = null;
        String last_id = null;
        String tableName = table.getName();
        try {
            c = this.contentResolver.query(this.mUri, null, null, null, "created_at ASC LIMIT " + String.valueOf(limit));
            JSONArray arr = new JSONArray();
            if (c != null) {
                while (c.moveToNext()) {
                    if (c.isLast()) {
                        last_id = c.getString(c.getColumnIndex(APEZProvider.FILEID));
                    }
                    try {
                        String keyData = c.getString(c.getColumnIndex("data"));
                        if (!TextUtils.isEmpty(keyData)) {
                            JSONObject j = null;
                            int index = keyData.lastIndexOf("\t");
                            if (index > -1) {
                                String crc = keyData.substring(index).replaceFirst("\t", "");
                                String content = keyData.substring(0, index);
                                if (!TextUtils.isEmpty(content) && !TextUtils.isEmpty(crc) && crc.equals(String.valueOf(content.hashCode()))) {
                                    j = new JSONObject(content);
                                }
                            } else {
                                j = new JSONObject(keyData);
                            }
                            if (j != null) {
                                j.put("_flush_time", System.currentTimeMillis());
                                arr.put(j);
                            }
                        }
                    } catch (JSONException e) {
                    }
                }
                if (arr.length() > 0) {
                    data = arr.toString();
                }
            }
            if (c != null) {
                c.close();
            }
        } catch (SQLiteException e2) {
            SALog.m1609i(TAG, "Could not pull records for SensorsData out of database " + tableName + ". Waiting to send.", e2);
            last_id = null;
            data = null;
            if (c != null) {
                c.close();
            }
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
        if (last_id == null || data == null) {
            return null;
        }
        return new String[]{last_id, data};
    }
}
