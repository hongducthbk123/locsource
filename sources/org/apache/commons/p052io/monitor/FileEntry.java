package org.apache.commons.p052io.monitor;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.monitor.FileEntry */
public class FileEntry implements Serializable {
    static final FileEntry[] EMPTY_ENTRIES = new FileEntry[0];
    private static final long serialVersionUID = -2505664948818681153L;
    private FileEntry[] children;
    private boolean directory;
    private boolean exists;
    private final File file;
    private long lastModified;
    private long length;
    private String name;
    private final FileEntry parent;

    public FileEntry(File file2) {
        this(null, file2);
    }

    public FileEntry(FileEntry parent2, File file2) {
        if (file2 == null) {
            throw new IllegalArgumentException("File is missing");
        }
        this.file = file2;
        this.parent = parent2;
        this.name = file2.getName();
    }

    public boolean refresh(File file2) {
        long j;
        long j2 = 0;
        boolean origExists = this.exists;
        long origLastModified = this.lastModified;
        boolean origDirectory = this.directory;
        long origLength = this.length;
        this.name = file2.getName();
        this.exists = file2.exists();
        this.directory = this.exists && file2.isDirectory();
        if (this.exists) {
            j = file2.lastModified();
        } else {
            j = 0;
        }
        this.lastModified = j;
        if (this.exists && !this.directory) {
            j2 = file2.length();
        }
        this.length = j2;
        if (this.exists == origExists && this.lastModified == origLastModified && this.directory == origDirectory && this.length == origLength) {
            return false;
        }
        return true;
    }

    public FileEntry newChildInstance(File file2) {
        return new FileEntry(this, file2);
    }

    public FileEntry getParent() {
        return this.parent;
    }

    public int getLevel() {
        if (this.parent == null) {
            return 0;
        }
        return this.parent.getLevel() + 1;
    }

    public FileEntry[] getChildren() {
        return this.children != null ? this.children : EMPTY_ENTRIES;
    }

    public void setChildren(FileEntry[] children2) {
        this.children = children2;
    }

    public File getFile() {
        return this.file;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public long getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(long lastModified2) {
        this.lastModified = lastModified2;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long length2) {
        this.length = length2;
    }

    public boolean isExists() {
        return this.exists;
    }

    public void setExists(boolean exists2) {
        this.exists = exists2;
    }

    public boolean isDirectory() {
        return this.directory;
    }

    public void setDirectory(boolean directory2) {
        this.directory = directory2;
    }
}
