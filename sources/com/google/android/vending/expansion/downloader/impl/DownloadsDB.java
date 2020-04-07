package com.google.android.vending.expansion.downloader.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import android.util.Log;
import com.android.vending.expansion.zipfile.APEZProvider;

public class DownloadsDB {
    private static final int CONTROL_IDX = 7;
    private static final int CURRENTBYTES_IDX = 4;
    private static final String DATABASE_NAME = "DownloadsDB";
    private static final int DATABASE_VERSION = 7;
    private static final String[] DC_PROJECTION = {DownloadColumns.FILENAME, DownloadColumns.URI, DownloadColumns.ETAG, DownloadColumns.TOTALBYTES, DownloadColumns.CURRENTBYTES, DownloadColumns.LASTMOD, DownloadColumns.STATUS, DownloadColumns.CONTROL, DownloadColumns.NUM_FAILED, DownloadColumns.RETRY_AFTER, DownloadColumns.REDIRECT_COUNT, DownloadColumns.INDEX};
    private static final int ETAG_IDX = 2;
    private static final int FILENAME_IDX = 0;
    private static final int INDEX_IDX = 11;
    private static final int LASTMOD_IDX = 5;
    public static final String LOG_TAG = DownloadsDB.class.getName();
    private static final int NUM_FAILED_IDX = 8;
    private static final int REDIRECT_COUNT_IDX = 10;
    private static final int RETRY_AFTER_IDX = 9;
    private static final int STATUS_IDX = 6;
    private static final int TOTALBYTES_IDX = 3;
    private static final int URI_IDX = 1;
    private static DownloadsDB mDownloadsDB;
    int mFlags;
    SQLiteStatement mGetDownloadByIndex;
    final SQLiteOpenHelper mHelper;
    long mMetadataRowID = -1;
    int mStatus = -1;
    SQLiteStatement mUpdateCurrentBytes;
    int mVersionCode = -1;

    public static class DownloadColumns implements BaseColumns {
        public static final String CONTROL = "CONTROL";
        public static final String CURRENTBYTES = "CURRENTBYTES";
        public static final String ETAG = "ETAG";
        public static final String FILENAME = "FN";
        public static final String INDEX = "FILEIDX";
        public static final String LASTMOD = "LASTMOD";
        public static final String NUM_FAILED = "FAILCOUNT";
        public static final String REDIRECT_COUNT = "REDIRECTCOUNT";
        public static final String RETRY_AFTER = "RETRYAFTER";
        public static final String[][] SCHEMA = {new String[]{APEZProvider.FILEID, "INTEGER PRIMARY KEY"}, new String[]{INDEX, "INTEGER UNIQUE"}, new String[]{URI, "TEXT"}, new String[]{FILENAME, "TEXT UNIQUE"}, new String[]{ETAG, "TEXT"}, new String[]{TOTALBYTES, "INTEGER"}, new String[]{CURRENTBYTES, "INTEGER"}, new String[]{LASTMOD, "INTEGER"}, new String[]{STATUS, "INTEGER"}, new String[]{CONTROL, "INTEGER"}, new String[]{NUM_FAILED, "INTEGER"}, new String[]{RETRY_AFTER, "INTEGER"}, new String[]{REDIRECT_COUNT, "INTEGER"}};
        public static final String STATUS = "STATUS";
        public static final String TABLE_NAME = "DownloadColumns";
        public static final String TOTALBYTES = "TOTALBYTES";
        public static final String URI = "URI";
        public static final String _ID = "DownloadColumns._id";
    }

    protected static class DownloadsContentDBHelper extends SQLiteOpenHelper {
        private static final String[][][] sSchemas = {DownloadColumns.SCHEMA, MetadataColumns.SCHEMA};
        private static final String[] sTables = {DownloadColumns.TABLE_NAME, MetadataColumns.TABLE_NAME};

        DownloadsContentDBHelper(Context paramContext) {
            super(paramContext, DownloadsDB.DATABASE_NAME, null, 7);
        }

        private String createTableQueryFromArray(String paramString, String[][] paramArrayOfString) {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("CREATE TABLE ");
            localStringBuilder.append(paramString);
            localStringBuilder.append(" (");
            for (String[] arrayOfString : paramArrayOfString) {
                localStringBuilder.append(' ');
                localStringBuilder.append(arrayOfString[0]);
                localStringBuilder.append(' ');
                localStringBuilder.append(arrayOfString[1]);
                localStringBuilder.append(',');
            }
            localStringBuilder.setLength(localStringBuilder.length() - 1);
            localStringBuilder.append(");");
            return localStringBuilder.toString();
        }

        private void dropTables(SQLiteDatabase paramSQLiteDatabase) {
            String[] strArr = sTables;
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                try {
                    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + strArr[i]);
                } catch (Exception localException) {
                    localException.printStackTrace();
                }
            }
        }

        public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
            int numSchemas = sSchemas.length;
            int i = 0;
            while (i < numSchemas) {
                try {
                    paramSQLiteDatabase.execSQL(createTableQueryFromArray(sTables[i], sSchemas[i]));
                    i++;
                } catch (Exception localException) {
                    while (true) {
                        localException.printStackTrace();
                    }
                }
            }
        }

        public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
            Log.w(DownloadsContentDBHelper.class.getName(), "Upgrading database from version " + paramInt1 + " to " + paramInt2 + ", which will destroy all old data");
            dropTables(paramSQLiteDatabase);
            onCreate(paramSQLiteDatabase);
        }
    }

    public static class MetadataColumns implements BaseColumns {
        public static final String APKVERSION = "APKVERSION";
        public static final String DOWNLOAD_STATUS = "DOWNLOADSTATUS";
        public static final String FLAGS = "DOWNLOADFLAGS";
        public static final String[][] SCHEMA = {new String[]{APEZProvider.FILEID, "INTEGER PRIMARY KEY"}, new String[]{APKVERSION, "INTEGER"}, new String[]{DOWNLOAD_STATUS, "INTEGER"}, new String[]{FLAGS, "INTEGER"}};
        public static final String TABLE_NAME = "MetadataColumns";
        public static final String _ID = "MetadataColumns._id";
    }

    public static synchronized DownloadsDB getDB(Context paramContext) {
        DownloadsDB downloadsDB;
        synchronized (DownloadsDB.class) {
            if (mDownloadsDB == null) {
                downloadsDB = new DownloadsDB(paramContext);
            } else {
                downloadsDB = mDownloadsDB;
            }
        }
        return downloadsDB;
    }

    private SQLiteStatement getDownloadByIndexStatement() {
        if (this.mGetDownloadByIndex == null) {
            this.mGetDownloadByIndex = this.mHelper.getReadableDatabase().compileStatement("SELECT _id FROM DownloadColumns WHERE FILEIDX = ?");
        }
        return this.mGetDownloadByIndex;
    }

    private SQLiteStatement getUpdateCurrentBytesStatement() {
        if (this.mUpdateCurrentBytes == null) {
            this.mUpdateCurrentBytes = this.mHelper.getReadableDatabase().compileStatement("UPDATE DownloadColumns SET CURRENTBYTES = ? WHERE FILEIDX = ?");
        }
        return this.mUpdateCurrentBytes;
    }

    private DownloadsDB(Context paramContext) {
        this.mHelper = new DownloadsContentDBHelper(paramContext);
        Cursor cur = this.mHelper.getReadableDatabase().rawQuery("SELECT APKVERSION,_id,DOWNLOADSTATUS,DOWNLOADFLAGS FROM MetadataColumns LIMIT 1", null);
        if (cur != null && cur.moveToFirst()) {
            this.mVersionCode = cur.getInt(0);
            this.mMetadataRowID = cur.getLong(1);
            this.mStatus = cur.getInt(2);
            this.mFlags = cur.getInt(3);
            cur.close();
        }
        mDownloadsDB = this;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public DownloadInfo getDownloadInfoByFileName(String fileName) {
        Cursor itemcur = null;
        try {
            Cursor itemcur2 = this.mHelper.getReadableDatabase().query(DownloadColumns.TABLE_NAME, DC_PROJECTION, "FN = ?", new String[]{fileName}, null, null, null);
            if (itemcur2 == null || !itemcur2.moveToFirst()) {
                if (itemcur2 != null) {
                    itemcur2.close();
                }
                return null;
            }
            DownloadInfo downloadInfoFromCursor = getDownloadInfoFromCursor(itemcur2);
            if (itemcur2 == null) {
                return downloadInfoFromCursor;
            }
            itemcur2.close();
            return downloadInfoFromCursor;
        } catch (Throwable th) {
            if (itemcur != null) {
                itemcur.close();
            }
            throw th;
        }
    }

    public long getIDForDownloadInfo(DownloadInfo di) {
        return getIDByIndex(di.mIndex);
    }

    public long getIDByIndex(int index) {
        SQLiteStatement downloadByIndex = getDownloadByIndexStatement();
        downloadByIndex.clearBindings();
        downloadByIndex.bindLong(1, (long) index);
        try {
            return downloadByIndex.simpleQueryForLong();
        } catch (SQLiteDoneException e) {
            return -1;
        }
    }

    public void updateDownloadCurrentBytes(DownloadInfo di) {
        SQLiteStatement downloadCurrentBytes = getUpdateCurrentBytesStatement();
        downloadCurrentBytes.clearBindings();
        downloadCurrentBytes.bindLong(1, di.mCurrentBytes);
        downloadCurrentBytes.bindLong(2, (long) di.mIndex);
        downloadCurrentBytes.execute();
    }

    public void close() {
        this.mHelper.close();
    }

    public boolean updateDownload(DownloadInfo di) {
        ContentValues cv = new ContentValues();
        cv.put(DownloadColumns.INDEX, Integer.valueOf(di.mIndex));
        cv.put(DownloadColumns.FILENAME, di.mFileName);
        cv.put(DownloadColumns.URI, di.mUri);
        cv.put(DownloadColumns.ETAG, di.mETag);
        cv.put(DownloadColumns.TOTALBYTES, Long.valueOf(di.mTotalBytes));
        cv.put(DownloadColumns.CURRENTBYTES, Long.valueOf(di.mCurrentBytes));
        cv.put(DownloadColumns.LASTMOD, Long.valueOf(di.mLastMod));
        cv.put(DownloadColumns.STATUS, Integer.valueOf(di.mStatus));
        cv.put(DownloadColumns.CONTROL, Integer.valueOf(di.mControl));
        cv.put(DownloadColumns.NUM_FAILED, Integer.valueOf(di.mNumFailed));
        cv.put(DownloadColumns.RETRY_AFTER, Integer.valueOf(di.mRetryAfter));
        cv.put(DownloadColumns.REDIRECT_COUNT, Integer.valueOf(di.mRedirectCount));
        return updateDownload(di, cv);
    }

    public boolean updateDownload(DownloadInfo di, ContentValues cv) {
        long id = di == null ? -1 : getIDForDownloadInfo(di);
        try {
            SQLiteDatabase sqldb = this.mHelper.getWritableDatabase();
            if (id != -1) {
                if (1 != sqldb.update(DownloadColumns.TABLE_NAME, cv, "DownloadColumns._id = " + id, null)) {
                }
                return false;
            }
            return -1 != sqldb.insert(DownloadColumns.TABLE_NAME, DownloadColumns.URI, cv);
        } catch (SQLiteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getLastCheckedVersionCode() {
        return this.mVersionCode;
    }

    /* JADX INFO: finally extract failed */
    public boolean isDownloadRequired() {
        boolean z = true;
        Cursor cur = this.mHelper.getReadableDatabase().rawQuery("SELECT Count(*) FROM DownloadColumns WHERE STATUS <> 0", null);
        if (cur != null) {
            try {
                if (cur.moveToFirst()) {
                    if (cur.getInt(0) != 0) {
                        z = false;
                    }
                    if (cur != null) {
                        cur.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                if (cur != null) {
                    cur.close();
                }
                throw th;
            }
        }
        if (cur != null) {
            cur.close();
        }
        return z;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean updateFlags(int flags) {
        if (this.mFlags == flags) {
            return true;
        }
        ContentValues cv = new ContentValues();
        cv.put(MetadataColumns.FLAGS, Integer.valueOf(flags));
        if (!updateMetadata(cv)) {
            return false;
        }
        this.mFlags = flags;
        return true;
    }

    public boolean updateStatus(int status) {
        if (this.mStatus == status) {
            return true;
        }
        ContentValues cv = new ContentValues();
        cv.put(MetadataColumns.DOWNLOAD_STATUS, Integer.valueOf(status));
        if (!updateMetadata(cv)) {
            return false;
        }
        this.mStatus = status;
        return true;
    }

    public boolean updateMetadata(ContentValues cv) {
        SQLiteDatabase sqldb = this.mHelper.getWritableDatabase();
        if (-1 == this.mMetadataRowID) {
            long newID = sqldb.insert(MetadataColumns.TABLE_NAME, MetadataColumns.APKVERSION, cv);
            if (-1 == newID) {
                return false;
            }
            this.mMetadataRowID = newID;
        } else if (sqldb.update(MetadataColumns.TABLE_NAME, cv, "_id = " + this.mMetadataRowID, null) == 0) {
            return false;
        }
        return true;
    }

    public boolean updateMetadata(int apkVersion, int downloadStatus) {
        ContentValues cv = new ContentValues();
        cv.put(MetadataColumns.APKVERSION, Integer.valueOf(apkVersion));
        cv.put(MetadataColumns.DOWNLOAD_STATUS, Integer.valueOf(downloadStatus));
        if (!updateMetadata(cv)) {
            return false;
        }
        this.mVersionCode = apkVersion;
        this.mStatus = downloadStatus;
        return true;
    }

    /* JADX INFO: finally extract failed */
    public boolean updateFromDb(DownloadInfo di) {
        Cursor cur = null;
        try {
            Cursor cur2 = this.mHelper.getReadableDatabase().query(DownloadColumns.TABLE_NAME, DC_PROJECTION, "FN= ?", new String[]{di.mFileName}, null, null, null);
            if (cur2 == null || !cur2.moveToFirst()) {
                if (cur2 != null) {
                    cur2.close();
                }
                return false;
            }
            setDownloadInfoFromCursor(di, cur2);
            if (cur2 != null) {
                cur2.close();
            }
            return true;
        } catch (Throwable th) {
            if (cur != null) {
                cur.close();
            }
            throw th;
        }
    }

    public void setDownloadInfoFromCursor(DownloadInfo di, Cursor cur) {
        di.mUri = cur.getString(1);
        di.mETag = cur.getString(2);
        di.mTotalBytes = cur.getLong(3);
        di.mCurrentBytes = cur.getLong(4);
        di.mLastMod = cur.getLong(5);
        di.mStatus = cur.getInt(6);
        di.mControl = cur.getInt(7);
        di.mNumFailed = cur.getInt(8);
        di.mRetryAfter = cur.getInt(9);
        di.mRedirectCount = cur.getInt(10);
    }

    public DownloadInfo getDownloadInfoFromCursor(Cursor cur) {
        DownloadInfo di = new DownloadInfo(cur.getInt(11), cur.getString(0), getClass().getPackage().getName());
        setDownloadInfoFromCursor(di, cur);
        return di;
    }

    /* JADX INFO: finally extract failed */
    public DownloadInfo[] getDownloads() {
        DownloadInfo[] retInfos = null;
        Cursor cur = null;
        try {
            Cursor cur2 = this.mHelper.getReadableDatabase().query(DownloadColumns.TABLE_NAME, DC_PROJECTION, null, null, null, null, null);
            if (cur2 != null && cur2.moveToFirst()) {
                retInfos = new DownloadInfo[cur2.getCount()];
                int idx = 0;
                while (true) {
                    int idx2 = idx;
                    idx = idx2 + 1;
                    retInfos[idx2] = getDownloadInfoFromCursor(cur2);
                    if (!cur2.moveToNext()) {
                        break;
                    }
                }
                if (cur2 != null) {
                    cur2.close();
                }
            } else if (cur2 != null) {
                cur2.close();
            }
            return retInfos;
        } catch (Throwable th) {
            if (cur != null) {
                cur.close();
            }
            throw th;
        }
    }
}
