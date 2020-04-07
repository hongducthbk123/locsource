package org.fmod;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaDataSource;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;

public class MediaCodec {
    private int mChannelCount = 0;
    /* access modifiers changed from: private */
    public long mCodecPtr = 0;
    private int mCurrentOutputBufferIndex = -1;
    private Object mDataSourceProxy = null;
    private android.media.MediaCodec mDecoder = null;
    private MediaExtractor mExtractor = null;
    private ByteBuffer[] mInputBuffers = null;
    private boolean mInputFinished = false;
    private long mLength = 0;
    private ByteBuffer[] mOutputBuffers = null;
    private boolean mOutputFinished = false;
    private int mSampleRate = 0;

    /* access modifiers changed from: private */
    public static native long fmodGetSize(long j);

    /* access modifiers changed from: private */
    public static native int fmodReadAt(long j, long j2, byte[] bArr, int i, int i2);

    public long getLength() {
        return this.mLength;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getChannelCount() {
        return this.mChannelCount;
    }

    public boolean init(long j) {
        int i;
        int i2 = 0;
        this.mCodecPtr = j;
        if (VERSION.SDK_INT < 17) {
            Log.w("fmod", "MediaCodec::init : MediaCodec unavailable, ensure device is running at least 4.2 (JellyBean).\n");
            return false;
        }
        if (VERSION.SDK_INT < 23) {
            try {
                Class cls = Class.forName("android.media.DataSource");
                Method method = Class.forName("android.media.MediaExtractor").getMethod("setDataSource", new Class[]{cls});
                this.mExtractor = new MediaExtractor();
                this.mDataSourceProxy = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
                    public Object invoke(Object obj, Method method, Object[] objArr) {
                        if (method.getName().equals("readAt")) {
                            return Integer.valueOf(MediaCodec.fmodReadAt(MediaCodec.this.mCodecPtr, objArr[0].longValue(), objArr[1], 0, objArr[2].intValue()));
                        }
                        if (method.getName().equals("getSize")) {
                            return Long.valueOf(MediaCodec.fmodGetSize(MediaCodec.this.mCodecPtr));
                        }
                        if (method.getName().equals("close")) {
                            return null;
                        }
                        Log.w("fmod", "MediaCodec::DataSource::invoke : Unrecognised method found: " + method.getName());
                        return null;
                    }
                });
                method.invoke(this.mExtractor, new Object[]{this.mDataSourceProxy});
            } catch (ClassNotFoundException e) {
                Log.w("fmod", "MediaCodec::init : " + e.toString());
                return false;
            } catch (NoSuchMethodException e2) {
                Log.w("fmod", "MediaCodec::init : " + e2.toString());
                return false;
            } catch (IllegalAccessException e3) {
                Log.e("fmod", "MediaCodec::init : " + e3.toString());
                return false;
            } catch (InvocationTargetException e4) {
                Log.e("fmod", "MediaCodec::init : " + e4.toString());
                return false;
            }
        } else {
            try {
                this.mExtractor = new MediaExtractor();
                this.mExtractor.setDataSource(new MediaDataSource() {
                    public int readAt(long j, byte[] bArr, int i, int i2) {
                        return MediaCodec.fmodReadAt(MediaCodec.this.mCodecPtr, j, bArr, i, i2);
                    }

                    public long getSize() {
                        return MediaCodec.fmodGetSize(MediaCodec.this.mCodecPtr);
                    }

                    public void close() {
                    }
                });
            } catch (IOException e5) {
                Log.w("fmod", "MediaCodec::init : " + e5.toString());
                return false;
            }
        }
        int trackCount = this.mExtractor.getTrackCount();
        int i3 = 0;
        while (i3 < trackCount) {
            MediaFormat trackFormat = this.mExtractor.getTrackFormat(i3);
            String string = trackFormat.getString("mime");
            Log.d("fmod", "MediaCodec::init : Format " + i3 + " / " + trackCount + " -- " + trackFormat);
            if (string.equals("audio/mp4a-latm")) {
                try {
                    this.mDecoder = android.media.MediaCodec.createDecoderByType(string);
                    this.mExtractor.selectTrack(i3);
                    this.mDecoder.configure(trackFormat, null, null, 0);
                    this.mDecoder.start();
                    this.mInputBuffers = this.mDecoder.getInputBuffers();
                    this.mOutputBuffers = this.mDecoder.getOutputBuffers();
                    if (trackFormat.containsKey("encoder-delay")) {
                        i = trackFormat.getInteger("encoder-delay");
                    } else {
                        i = 0;
                    }
                    if (trackFormat.containsKey("encoder-padding")) {
                        i2 = trackFormat.getInteger("encoder-padding");
                    }
                    long j2 = trackFormat.getLong("durationUs");
                    this.mChannelCount = trackFormat.getInteger("channel-count");
                    this.mSampleRate = trackFormat.getInteger("sample-rate");
                    this.mLength = (long) ((((int) (((j2 * ((long) this.mSampleRate)) + (1000000 - 1)) / 1000000)) - i) - i2);
                    return true;
                } catch (IOException e6) {
                    Log.e("fmod", "MediaCodec::init : " + e6.toString());
                    return false;
                }
            } else {
                i3++;
            }
        }
        return false;
    }

    public void release() {
        if (this.mDecoder != null) {
            this.mDecoder.stop();
            this.mDecoder.release();
            this.mDecoder = null;
        }
        if (this.mExtractor != null) {
            this.mExtractor.release();
            this.mExtractor = null;
        }
    }

    public int read(byte[] bArr, int i) {
        int i2;
        if (!this.mInputFinished || !this.mOutputFinished || this.mCurrentOutputBufferIndex != -1) {
            i2 = 0;
        } else {
            i2 = -1;
        }
        while (!this.mInputFinished) {
            int dequeueInputBuffer = this.mDecoder.dequeueInputBuffer(0);
            if (dequeueInputBuffer < 0) {
                break;
            }
            int readSampleData = this.mExtractor.readSampleData(this.mInputBuffers[dequeueInputBuffer], 0);
            if (readSampleData >= 0) {
                this.mDecoder.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, this.mExtractor.getSampleTime(), 0);
                this.mExtractor.advance();
            } else {
                this.mDecoder.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                this.mInputFinished = true;
            }
        }
        if (!this.mOutputFinished && this.mCurrentOutputBufferIndex == -1) {
            BufferInfo bufferInfo = new BufferInfo();
            int dequeueOutputBuffer = this.mDecoder.dequeueOutputBuffer(bufferInfo, 10000);
            if (dequeueOutputBuffer >= 0) {
                this.mCurrentOutputBufferIndex = dequeueOutputBuffer;
                this.mOutputBuffers[dequeueOutputBuffer].limit(bufferInfo.size);
                this.mOutputBuffers[dequeueOutputBuffer].position(bufferInfo.offset);
            } else if (dequeueOutputBuffer == -3) {
                this.mOutputBuffers = this.mDecoder.getOutputBuffers();
            } else if (dequeueOutputBuffer == -2) {
                Log.d("fmod", "MediaCodec::read : MediaCodec::dequeueOutputBuffer returned MediaCodec.INFO_OUTPUT_FORMAT_CHANGED " + this.mDecoder.getOutputFormat());
            } else if (dequeueOutputBuffer == -1) {
                Log.d("fmod", "MediaCodec::read : MediaCodec::dequeueOutputBuffer returned MediaCodec.INFO_TRY_AGAIN_LATER.");
            } else {
                Log.w("fmod", "MediaCodec::read : MediaCodec::dequeueOutputBuffer returned " + dequeueOutputBuffer);
            }
            if ((bufferInfo.flags & 4) != 0) {
                this.mOutputFinished = true;
            }
        }
        if (this.mCurrentOutputBufferIndex != -1) {
            ByteBuffer byteBuffer = this.mOutputBuffers[this.mCurrentOutputBufferIndex];
            i2 = Math.min(byteBuffer.remaining(), i);
            byteBuffer.get(bArr, 0, i2);
            if (!byteBuffer.hasRemaining()) {
                byteBuffer.clear();
                this.mDecoder.releaseOutputBuffer(this.mCurrentOutputBufferIndex, false);
                this.mCurrentOutputBufferIndex = -1;
            }
        }
        return i2;
    }

    public void seek(int i) {
        if (this.mCurrentOutputBufferIndex != -1) {
            this.mOutputBuffers[this.mCurrentOutputBufferIndex].clear();
            this.mCurrentOutputBufferIndex = -1;
        }
        this.mInputFinished = false;
        this.mOutputFinished = false;
        this.mDecoder.flush();
        this.mExtractor.seekTo((((long) i) * 1000000) / ((long) this.mSampleRate), 0);
        long sampleTime = ((this.mExtractor.getSampleTime() * ((long) this.mSampleRate)) + (1000000 - 1)) / 1000000;
        int i2 = (int) ((((long) i) - sampleTime) * ((long) this.mChannelCount) * 2);
        if (i2 < 0) {
            Log.w("fmod", "MediaCodec::seek : Seek to " + i + " resulted in position " + sampleTime);
            return;
        }
        byte[] bArr = new byte[1024];
        while (i2 > 0) {
            i2 -= read(bArr, Math.min(bArr.length, i2));
        }
    }
}
