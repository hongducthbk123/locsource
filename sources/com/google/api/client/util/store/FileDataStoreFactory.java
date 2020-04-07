package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

public class FileDataStoreFactory extends AbstractDataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    private final File dataDirectory;

    static class FileDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        private final File dataFile;

        FileDataStore(FileDataStoreFactory dataStore, File dataDirectory, String id) throws IOException {
            super(dataStore, id);
            this.dataFile = new File(dataDirectory, id);
            if (IOUtils.isSymbolicLink(this.dataFile)) {
                throw new IOException("unable to use a symbolic link: " + this.dataFile);
            } else if (this.dataFile.createNewFile()) {
                this.keyValueMap = Maps.newHashMap();
                save();
            } else {
                this.keyValueMap = (HashMap) IOUtils.deserialize((InputStream) new FileInputStream(this.dataFile));
            }
        }

        /* access modifiers changed from: 0000 */
        public void save() throws IOException {
            IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
        }

        public FileDataStoreFactory getDataStoreFactory() {
            return (FileDataStoreFactory) super.getDataStoreFactory();
        }
    }

    public FileDataStoreFactory(File dataDirectory2) throws IOException {
        File dataDirectory3 = dataDirectory2.getCanonicalFile();
        this.dataDirectory = dataDirectory3;
        if (IOUtils.isSymbolicLink(dataDirectory3)) {
            throw new IOException("unable to use a symbolic link: " + dataDirectory3);
        } else if (dataDirectory3.exists() || dataDirectory3.mkdirs()) {
            setPermissionsToOwnerOnly(dataDirectory3);
        } else {
            throw new IOException("unable to create directory: " + dataDirectory3);
        }
    }

    public final File getDataDirectory() {
        return this.dataDirectory;
    }

    /* access modifiers changed from: protected */
    public <V extends Serializable> DataStore<V> createDataStore(String id) throws IOException {
        return new FileDataStore(this, this.dataDirectory, id);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x009a, code lost:
        if (((java.lang.Boolean) r2.invoke(r10, new java.lang.Object[]{java.lang.Boolean.valueOf(false), java.lang.Boolean.valueOf(false)})).booleanValue() == false) goto L_0x009c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void setPermissionsToOwnerOnly(java.io.File r10) throws java.io.IOException {
        /*
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.String r6 = "setReadable"
            r7 = 2
            java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r8 = 0
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r7[r8] = r9     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r8 = 1
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r7[r8] = r9     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.reflect.Method r3 = r5.getMethod(r6, r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.String r6 = "setWritable"
            r7 = 2
            java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r8 = 0
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r7[r8] = r9     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r8 = 1
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r7[r8] = r9     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.reflect.Method r4 = r5.getMethod(r6, r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.String r6 = "setExecutable"
            r7 = 2
            java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r8 = 0
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r7[r8] = r9     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r8 = 1
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r7[r8] = r9     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.reflect.Method r2 = r5.getMethod(r6, r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 0
            r7 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 1
            r7 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Object r5 = r3.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            boolean r5 = r5.booleanValue()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            if (r5 == 0) goto L_0x009c
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 0
            r7 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 1
            r7 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Object r5 = r4.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            boolean r5 = r5.booleanValue()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            if (r5 == 0) goto L_0x009c
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 0
            r7 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 1
            r7 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Object r5 = r2.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            boolean r5 = r5.booleanValue()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            if (r5 != 0) goto L_0x00b5
        L_0x009c:
            java.util.logging.Logger r5 = LOGGER     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6.<init>()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.String r7 = "unable to change permissions for everybody: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.StringBuilder r6 = r6.append(r10)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.String r6 = r6.toString()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5.warning(r6)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
        L_0x00b5:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 0
            r7 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 1
            r7 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Object r5 = r3.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            boolean r5 = r5.booleanValue()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            if (r5 == 0) goto L_0x0112
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 0
            r7 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 1
            r7 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Object r5 = r4.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            boolean r5 = r5.booleanValue()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            if (r5 == 0) goto L_0x0112
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 0
            r7 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6 = 1
            r7 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5[r6] = r7     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Object r5 = r2.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            boolean r5 = r5.booleanValue()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            if (r5 != 0) goto L_0x012b
        L_0x0112:
            java.util.logging.Logger r5 = LOGGER     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r6.<init>()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.String r7 = "unable to change permissions for owner: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.StringBuilder r6 = r6.append(r10)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            java.lang.String r6 = r6.toString()     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
            r5.warning(r6)     // Catch:{ InvocationTargetException -> 0x012c, NoSuchMethodException -> 0x013c, SecurityException -> 0x0160, IllegalAccessException -> 0x015e, IllegalArgumentException -> 0x015c }
        L_0x012b:
            return
        L_0x012c:
            r1 = move-exception
            java.lang.Throwable r0 = r1.getCause()
            java.lang.Class<java.io.IOException> r5 = java.io.IOException.class
            com.google.api.client.util.Throwables.propagateIfPossible(r0, r5)
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r5.<init>(r0)
            throw r5
        L_0x013c:
            r1 = move-exception
            java.util.logging.Logger r5 = LOGGER
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unable to set permissions for "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r10)
            java.lang.String r7 = ", likely because you are running a version of Java prior to 1.6"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.warning(r6)
            goto L_0x012b
        L_0x015c:
            r5 = move-exception
            goto L_0x012b
        L_0x015e:
            r5 = move-exception
            goto L_0x012b
        L_0x0160:
            r5 = move-exception
            goto L_0x012b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.util.store.FileDataStoreFactory.setPermissionsToOwnerOnly(java.io.File):void");
    }
}
