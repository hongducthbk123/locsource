package org.cocos2dx.lib;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController.MediaPlayerControl;
import com.chukong.cocosplay.client.CocosPlayClient;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.Map;
import p004cn.jiguang.net.HttpUtils;

public class Cocos2dxVideoView extends SurfaceView implements MediaPlayerControl {
    private static final int EVENT_COMPLETED = 3;
    private static final int EVENT_PAUSED = 1;
    private static final int EVENT_PLAYING = 0;
    private static final int EVENT_STOPPED = 2;
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    /* access modifiers changed from: private */
    public String TAG = "VideoView";
    private String assetResourceRoot = "assets/";
    private String fileName = null;
    private boolean isAssetRouse = false;
    private OnBufferingUpdateListener mBufferingUpdateListener = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            Cocos2dxVideoView.this.mCurrentBufferPercentage = percent;
        }
    };
    protected Cocos2dxActivity mCocos2dxActivity = null;
    private OnCompletionListener mCompletionListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            Cocos2dxVideoView.this.mCurrentState = 5;
            Cocos2dxVideoView.this.mTargetState = 5;
            Cocos2dxVideoView.this.release(true);
            if (Cocos2dxVideoView.this.mOnVideoEventListener != null) {
                Cocos2dxVideoView.this.mOnVideoEventListener.onVideoEvent(Cocos2dxVideoView.this.mViewTag, 3);
            }
        }
    };
    /* access modifiers changed from: private */
    public int mCurrentBufferPercentage;
    /* access modifiers changed from: private */
    public int mCurrentState = 0;
    private int mDuration;
    private OnErrorListener mErrorListener = new OnErrorListener() {
        public boolean onError(MediaPlayer mp, int framework_err, int impl_err) {
            int messageId;
            Log.d(Cocos2dxVideoView.this.TAG, "Error: " + framework_err + "," + impl_err);
            Cocos2dxVideoView.this.mCurrentState = -1;
            Cocos2dxVideoView.this.mTargetState = -1;
            if ((Cocos2dxVideoView.this.mOnErrorListener == null || !Cocos2dxVideoView.this.mOnErrorListener.onError(Cocos2dxVideoView.this.mMediaPlayer, framework_err, impl_err)) && Cocos2dxVideoView.this.getWindowToken() != null) {
                Resources r = Cocos2dxVideoView.this.mCocos2dxActivity.getResources();
                if (framework_err == 200) {
                    messageId = r.getIdentifier("VideoView_error_text_invalid_progressive_playback", "string", "android");
                } else {
                    messageId = r.getIdentifier("VideoView_error_text_unknown", "string", "android");
                }
                new Builder(Cocos2dxVideoView.this.mCocos2dxActivity).setTitle(r.getString(r.getIdentifier("VideoView_error_title", "string", "android"))).setMessage(messageId).setPositiveButton(r.getString(r.getIdentifier("VideoView_error_button", "string", "android")), new OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (Cocos2dxVideoView.this.mOnVideoEventListener != null) {
                            Cocos2dxVideoView.this.mOnVideoEventListener.onVideoEvent(Cocos2dxVideoView.this.mViewTag, 3);
                        }
                    }
                }).setCancelable(false).show();
            }
            return true;
        }
    };
    protected boolean mFullScreenEnabled = false;
    protected int mFullScreenHeight = 0;
    protected int mFullScreenWidth = 0;
    private boolean mKeepRatio = false;
    /* access modifiers changed from: private */
    public MediaPlayer mMediaPlayer = null;
    private boolean mNeedResume = false;
    /* access modifiers changed from: private */
    public OnErrorListener mOnErrorListener;
    /* access modifiers changed from: private */
    public OnPreparedListener mOnPreparedListener;
    /* access modifiers changed from: private */
    public OnVideoEventListener mOnVideoEventListener;
    OnPreparedListener mPreparedListener = new OnPreparedListener() {
        public void onPrepared(MediaPlayer mp) {
            Cocos2dxVideoView.this.mCurrentState = 2;
            if (Cocos2dxVideoView.this.mOnPreparedListener != null) {
                Cocos2dxVideoView.this.mOnPreparedListener.onPrepared(Cocos2dxVideoView.this.mMediaPlayer);
            }
            Cocos2dxVideoView.this.mVideoWidth = mp.getVideoWidth();
            Cocos2dxVideoView.this.mVideoHeight = mp.getVideoHeight();
            int seekToPosition = Cocos2dxVideoView.this.mSeekWhenPrepared;
            if (seekToPosition != 0) {
                Cocos2dxVideoView.this.seekTo(seekToPosition);
            }
            if (!(Cocos2dxVideoView.this.mVideoWidth == 0 || Cocos2dxVideoView.this.mVideoHeight == 0)) {
                Cocos2dxVideoView.this.fixSize();
            }
            if (Cocos2dxVideoView.this.mTargetState == 3) {
                Cocos2dxVideoView.this.start();
            }
        }
    };
    Callback mSHCallback = new Callback() {
        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            boolean isValidState;
            boolean hasValidSize;
            if (Cocos2dxVideoView.this.mTargetState == 3) {
                isValidState = true;
            } else {
                isValidState = false;
            }
            if (Cocos2dxVideoView.this.mVideoWidth == w && Cocos2dxVideoView.this.mVideoHeight == h) {
                hasValidSize = true;
            } else {
                hasValidSize = false;
            }
            if (Cocos2dxVideoView.this.mMediaPlayer != null && isValidState && hasValidSize) {
                if (Cocos2dxVideoView.this.mSeekWhenPrepared != 0) {
                    Cocos2dxVideoView.this.seekTo(Cocos2dxVideoView.this.mSeekWhenPrepared);
                }
                Cocos2dxVideoView.this.start();
            }
        }

        public void surfaceCreated(SurfaceHolder holder) {
            Cocos2dxVideoView.this.mSurfaceHolder = holder;
            Cocos2dxVideoView.this.openVideo();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            Cocos2dxVideoView.this.mSurfaceHolder = null;
            Cocos2dxVideoView.this.release(true);
        }
    };
    /* access modifiers changed from: private */
    public int mSeekWhenPrepared;
    protected OnVideoSizeChangedListener mSizeChangedListener = new OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            Cocos2dxVideoView.this.mVideoWidth = mp.getVideoWidth();
            Cocos2dxVideoView.this.mVideoHeight = mp.getVideoHeight();
            if (Cocos2dxVideoView.this.mVideoWidth != 0 && Cocos2dxVideoView.this.mVideoHeight != 0) {
                Cocos2dxVideoView.this.getHolder().setFixedSize(Cocos2dxVideoView.this.mVideoWidth, Cocos2dxVideoView.this.mVideoHeight);
            }
        }
    };
    /* access modifiers changed from: private */
    public SurfaceHolder mSurfaceHolder = null;
    /* access modifiers changed from: private */
    public int mTargetState = 0;
    private Uri mUri;
    /* access modifiers changed from: private */
    public int mVideoHeight = 0;
    /* access modifiers changed from: private */
    public int mVideoWidth = 0;
    protected int mViewHeight = 0;
    protected int mViewLeft = 0;
    /* access modifiers changed from: private */
    public int mViewTag = 0;
    protected int mViewTop = 0;
    protected int mViewWidth = 0;
    protected int mVisibleHeight = 0;
    protected int mVisibleLeft = 0;
    protected int mVisibleTop = 0;
    protected int mVisibleWidth = 0;

    public interface OnVideoEventListener {
        void onVideoEvent(int i, int i2);
    }

    public Cocos2dxVideoView(Cocos2dxActivity activity, int tag) {
        super(activity);
        this.mViewTag = tag;
        this.mCocos2dxActivity = activity;
        initVideoView();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            setMeasuredDimension(this.mViewWidth, this.mViewHeight);
            Log.i(this.TAG, "" + this.mViewWidth + ":" + this.mViewHeight);
            return;
        }
        setMeasuredDimension(this.mVisibleWidth, this.mVisibleHeight);
        Log.i(this.TAG, "" + this.mVisibleWidth + ":" + this.mVisibleHeight);
    }

    public void setVideoRect(int left, int top, int maxWidth, int maxHeight) {
        this.mViewLeft = left;
        this.mViewTop = top;
        this.mViewWidth = maxWidth;
        this.mViewHeight = maxHeight;
        if (this.mVideoWidth != 0 && this.mVideoHeight != 0) {
            fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
        }
    }

    public void setFullScreenEnabled(boolean enabled, int width, int height) {
        if (this.mFullScreenEnabled != enabled) {
            this.mFullScreenEnabled = enabled;
            if (!(width == 0 || height == 0)) {
                this.mFullScreenWidth = width;
                this.mFullScreenHeight = height;
            }
            fixSize();
        }
    }

    public int resolveAdjustedSize(int desiredSize, int measureSpec) {
        int result = desiredSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case Integer.MIN_VALUE:
                return Math.min(desiredSize, specSize);
            case 0:
                return desiredSize;
            case Ints.MAX_POWER_OF_TWO /*1073741824*/:
                return specSize;
            default:
                return result;
        }
    }

    public void setVisibility(int visibility) {
        if (visibility == 4) {
            this.mNeedResume = isPlaying();
            if (this.mNeedResume) {
                this.mSeekWhenPrepared = getCurrentPosition();
            }
        } else if (this.mNeedResume) {
            start();
            this.mNeedResume = false;
        }
        super.setVisibility(visibility);
    }

    private void initVideoView() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        getHolder().addCallback(this.mSHCallback);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getAction() & 255) != 1 || isPlaying() || this.mCurrentState == 4) {
        }
        return true;
    }

    public void setVideoFileName(String path) {
        if (path.startsWith(this.assetResourceRoot)) {
            path = path.substring(this.assetResourceRoot.length());
        }
        if (CocosPlayClient.isEnabled() && !CocosPlayClient.isDemo()) {
            CocosPlayClient.updateAssets(path);
        }
        CocosPlayClient.notifyFileLoaded(path);
        if (path.startsWith(HttpUtils.PATHS_SEPARATOR)) {
            this.isAssetRouse = false;
            setVideoURI(Uri.parse(path), null);
            return;
        }
        this.fileName = path;
        this.isAssetRouse = true;
        setVideoURI(Uri.parse(path), null);
    }

    public void setVideoURL(String url) {
        this.isAssetRouse = false;
        setVideoURI(Uri.parse(url), null);
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mSeekWhenPrepared = 0;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
        }
    }

    /* access modifiers changed from: private */
    public void openVideo() {
        if (this.mSurfaceHolder != null) {
            if (this.isAssetRouse) {
                if (this.fileName == null) {
                    return;
                }
            } else if (this.mUri == null) {
                return;
            }
            Intent i = new Intent("com.android.music.musicservicecommand");
            i.putExtra("command", "pause");
            this.mCocos2dxActivity.sendBroadcast(i);
            release(false);
            try {
                this.mMediaPlayer = new MediaPlayer();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mDuration = -1;
                this.mCurrentBufferPercentage = 0;
                if (this.isAssetRouse) {
                    AssetFileDescriptor afd = this.mCocos2dxActivity.getAssets().openFd(this.fileName);
                    this.mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                } else {
                    this.mMediaPlayer.setDataSource(this.mCocos2dxActivity, this.mUri);
                }
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = 1;
            } catch (IOException ex) {
                Log.w(this.TAG, "Unable to open content: " + this.mUri, ex);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            } catch (IllegalArgumentException ex2) {
                Log.w(this.TAG, "Unable to open content: " + this.mUri, ex2);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        }
    }

    public void setKeepRatio(boolean enabled) {
        this.mKeepRatio = enabled;
        fixSize();
    }

    public void fixSize() {
        if (this.mFullScreenEnabled) {
            fixSize(0, 0, this.mFullScreenWidth, this.mFullScreenHeight);
        } else {
            fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
        }
    }

    public void fixSize(int left, int top, int width, int height) {
        if (width == 0 || height == 0) {
            this.mVisibleLeft = left;
            this.mVisibleTop = top;
            this.mVisibleWidth = this.mVideoWidth;
            this.mVisibleHeight = this.mVideoHeight;
        } else if (this.mKeepRatio) {
            if (this.mVideoWidth * height > this.mVideoHeight * width) {
                this.mVisibleWidth = width;
                this.mVisibleHeight = (this.mVideoHeight * width) / this.mVideoWidth;
            } else if (this.mVideoWidth * height < this.mVideoHeight * width) {
                this.mVisibleWidth = (this.mVideoWidth * height) / this.mVideoHeight;
                this.mVisibleHeight = height;
            }
            this.mVisibleLeft = ((width - this.mVisibleWidth) / 2) + left;
            this.mVisibleTop = ((height - this.mVisibleHeight) / 2) + top;
        } else {
            this.mVisibleLeft = left;
            this.mVisibleTop = top;
            this.mVisibleWidth = width;
            this.mVisibleHeight = height;
        }
        getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
        LayoutParams lParams = new LayoutParams(-2, -2);
        lParams.leftMargin = this.mVisibleLeft;
        lParams.topMargin = this.mVisibleTop;
        lParams.gravity = 51;
        setLayoutParams(lParams);
    }

    public void setOnPreparedListener(OnPreparedListener l) {
        this.mOnPreparedListener = l;
    }

    public void setOnCompletionListener(OnVideoEventListener l) {
        this.mOnVideoEventListener = l;
    }

    public void setOnErrorListener(OnErrorListener l) {
        this.mOnErrorListener = l;
    }

    /* access modifiers changed from: private */
    public void release(boolean cleartargetstate) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (cleartargetstate) {
                this.mTargetState = 0;
            }
        }
    }

    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, 0);
            }
        }
        this.mTargetState = 3;
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, 1);
            }
        }
        this.mTargetState = 4;
    }

    public void stop() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            stopPlayback();
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, 2);
            }
        }
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        if (isInPlaybackState() && this.mCurrentState == 4) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, 0);
            }
        }
    }

    public void restart() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(0);
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            this.mTargetState = 3;
        }
    }

    public int getDuration() {
        if (!isInPlaybackState()) {
            this.mDuration = -1;
            return this.mDuration;
        } else if (this.mDuration > 0) {
            return this.mDuration;
        } else {
            this.mDuration = this.mMediaPlayer.getDuration();
            return this.mDuration;
        }
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int msec) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(msec);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = msec;
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    public boolean isInPlaybackState() {
        return (this.mMediaPlayer == null || this.mCurrentState == -1 || this.mCurrentState == 0 || this.mCurrentState == 1) ? false : true;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public int getAudioSessionId() {
        return this.mMediaPlayer.getAudioSessionId();
    }
}
