package p004cn.jiguang.p015d.p026g;

import java.io.File;
import java.io.FileFilter;

/* renamed from: cn.jiguang.d.g.f */
final class C0485f implements FileFilter {

    /* renamed from: a */
    final /* synthetic */ boolean f520a;

    C0485f(boolean z) {
        this.f520a = z;
    }

    public final boolean accept(File file) {
        return this.f520a == file.isDirectory();
    }
}
