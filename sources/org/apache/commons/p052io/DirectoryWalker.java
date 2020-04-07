package org.apache.commons.p052io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.p052io.filefilter.FileFilterUtils;
import org.apache.commons.p052io.filefilter.IOFileFilter;
import org.apache.commons.p052io.filefilter.TrueFileFilter;

/* renamed from: org.apache.commons.io.DirectoryWalker */
public abstract class DirectoryWalker<T> {
    private final int depthLimit;
    private final FileFilter filter;

    /* renamed from: org.apache.commons.io.DirectoryWalker$CancelException */
    public static class CancelException extends IOException {
        private static final long serialVersionUID = 1347339620135041008L;
        private final int depth;
        private final File file;

        public CancelException(File file2, int depth2) {
            this("Operation Cancelled", file2, depth2);
        }

        public CancelException(String message, File file2, int depth2) {
            super(message);
            this.file = file2;
            this.depth = depth2;
        }

        public File getFile() {
            return this.file;
        }

        public int getDepth() {
            return this.depth;
        }
    }

    protected DirectoryWalker() {
        this(null, -1);
    }

    protected DirectoryWalker(FileFilter filter2, int depthLimit2) {
        this.filter = filter2;
        this.depthLimit = depthLimit2;
    }

    protected DirectoryWalker(IOFileFilter directoryFilter, IOFileFilter fileFilter, int depthLimit2) {
        if (directoryFilter == null && fileFilter == null) {
            this.filter = null;
        } else {
            if (directoryFilter == null) {
                directoryFilter = TrueFileFilter.TRUE;
            }
            if (fileFilter == null) {
                fileFilter = TrueFileFilter.TRUE;
            }
            this.filter = FileFilterUtils.m2189or(FileFilterUtils.makeDirectoryOnly(directoryFilter), FileFilterUtils.makeFileOnly(fileFilter));
        }
        this.depthLimit = depthLimit2;
    }

    /* access modifiers changed from: protected */
    public final void walk(File startDirectory, Collection<T> results) throws IOException {
        if (startDirectory == null) {
            throw new NullPointerException("Start Directory is null");
        }
        try {
            handleStart(startDirectory, results);
            walk(startDirectory, 0, results);
            handleEnd(results);
        } catch (CancelException cancel) {
            handleCancelled(startDirectory, results, cancel);
        }
    }

    private void walk(File directory, int depth, Collection<T> results) throws IOException {
        File[] arr$;
        checkIfCancelled(directory, depth, results);
        if (handleDirectory(directory, depth, results)) {
            handleDirectoryStart(directory, depth, results);
            int childDepth = depth + 1;
            if (this.depthLimit < 0 || childDepth <= this.depthLimit) {
                checkIfCancelled(directory, depth, results);
                File[] childFiles = filterDirectoryContents(directory, depth, this.filter == null ? directory.listFiles() : directory.listFiles(this.filter));
                if (childFiles == null) {
                    handleRestricted(directory, childDepth, results);
                } else {
                    for (File childFile : childFiles) {
                        if (childFile.isDirectory()) {
                            walk(childFile, childDepth, results);
                        } else {
                            checkIfCancelled(childFile, childDepth, results);
                            handleFile(childFile, childDepth, results);
                            checkIfCancelled(childFile, childDepth, results);
                        }
                    }
                }
            }
            handleDirectoryEnd(directory, depth, results);
        }
        checkIfCancelled(directory, depth, results);
    }

    /* access modifiers changed from: protected */
    public final void checkIfCancelled(File file, int depth, Collection<T> results) throws IOException {
        if (handleIsCancelled(file, depth, results)) {
            throw new CancelException(file, depth);
        }
    }

    /* access modifiers changed from: protected */
    public boolean handleIsCancelled(File file, int depth, Collection<T> collection) throws IOException {
        return false;
    }

    /* access modifiers changed from: protected */
    public void handleCancelled(File startDirectory, Collection<T> collection, CancelException cancel) throws IOException {
        throw cancel;
    }

    /* access modifiers changed from: protected */
    public void handleStart(File startDirectory, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public boolean handleDirectory(File directory, int depth, Collection<T> collection) throws IOException {
        return true;
    }

    /* access modifiers changed from: protected */
    public void handleDirectoryStart(File directory, int depth, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public File[] filterDirectoryContents(File directory, int depth, File[] files) throws IOException {
        return files;
    }

    /* access modifiers changed from: protected */
    public void handleFile(File file, int depth, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleRestricted(File directory, int depth, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleDirectoryEnd(File directory, int depth, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleEnd(Collection<T> collection) throws IOException {
    }
}
