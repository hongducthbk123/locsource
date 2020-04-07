package p004cn.jiguang.p015d.p020c;

import java.io.EOFException;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/* renamed from: cn.jiguang.d.c.r */
public final class C0441r extends C0424a {
    private C0441r(long j) {
        super(SocketChannel.open(), j);
    }

    /* renamed from: a */
    private byte[] m616a(int i) {
        SocketChannel socketChannel = (SocketChannel) this.f335b.channel();
        byte[] bArr = new byte[i];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.f335b.interestOps(1);
        int i2 = 0;
        while (i2 < i) {
            try {
                if (this.f335b.isReadable()) {
                    long read = (long) socketChannel.read(wrap);
                    if (read < 0) {
                        throw new EOFException();
                    }
                    i2 += (int) read;
                    if (i2 >= i) {
                        continue;
                    } else if (System.currentTimeMillis() > this.f334a) {
                        throw new SocketTimeoutException();
                    }
                } else {
                    m513a(this.f335b, this.f334a);
                }
            } catch (Throwable th) {
                if (this.f335b.isValid()) {
                    this.f335b.interestOps(0);
                }
                throw th;
            }
        }
        if (this.f335b.isValid()) {
            this.f335b.interestOps(0);
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m617a(SocketAddress socketAddress, SocketAddress socketAddress2, byte[] bArr, long j) {
        int i = 0;
        C0441r rVar = new C0441r(j);
        try {
            SocketChannel socketChannel = (SocketChannel) rVar.f335b.channel();
            if (!socketChannel.connect(socketAddress2)) {
                rVar.f335b.interestOps(8);
                while (!socketChannel.finishConnect()) {
                    if (!rVar.f335b.isConnectable()) {
                        m513a(rVar.f335b, rVar.f334a);
                    }
                }
                if (rVar.f335b.isValid()) {
                    rVar.f335b.interestOps(0);
                }
            }
            SocketChannel socketChannel2 = (SocketChannel) rVar.f335b.channel();
            ByteBuffer[] byteBufferArr = {ByteBuffer.wrap(new byte[]{(byte) (bArr.length >>> 8), (byte) (bArr.length & 255)}), ByteBuffer.wrap(bArr)};
            rVar.f335b.interestOps(4);
            while (i < bArr.length + 2) {
                if (rVar.f335b.isWritable()) {
                    long write = socketChannel2.write(byteBufferArr);
                    if (write < 0) {
                        throw new EOFException();
                    }
                    i += (int) write;
                    if (i < bArr.length + 2 && System.currentTimeMillis() > rVar.f334a) {
                        throw new SocketTimeoutException();
                    }
                } else {
                    m513a(rVar.f335b, rVar.f334a);
                }
            }
            if (rVar.f335b.isValid()) {
                rVar.f335b.interestOps(0);
            }
            byte[] a = rVar.m616a(2);
            byte[] a2 = rVar.m616a((a[1] & 255) + ((a[0] & 255) << 8));
            rVar.f335b.channel();
            rVar.mo6481a();
            return a2;
        } catch (Throwable th) {
            rVar.mo6481a();
            throw th;
        }
    }
}
