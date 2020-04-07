package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import java.lang.ref.WeakReference;
import org.cocos2dx.lib.Cocos2dxVideoView.OnVideoEventListener;

public class Cocos2dxVideoHelper {
    static final int KeyEventBack = 1000;
    private static final int VideoSkipVisible = 13;
    private static final int VideoTaskCreate = 0;
    private static final int VideoTaskFullScreen = 12;
    private static final int VideoTaskKeepRatio = 11;
    private static final int VideoTaskPause = 5;
    private static final int VideoTaskRemove = 1;
    private static final int VideoTaskRestart = 10;
    private static final int VideoTaskResume = 6;
    private static final int VideoTaskSeek = 8;
    private static final int VideoTaskSetRect = 3;
    private static final int VideoTaskSetSource = 2;
    private static final int VideoTaskSetVisible = 9;
    private static final int VideoTaskStart = 4;
    private static final int VideoTaskStop = 7;
    static VideoHandler mVideoHandler = null;
    private static int videoTag = 0;
    /* access modifiers changed from: private */
    public Cocos2dxActivity mActivity = null;
    private FrameLayout mLayout = null;
    private SparseArray<RelativeLayout> sSkipLayouts = null;
    private SparseArray<Cocos2dxVideoView> sVideoViews = null;
    OnVideoEventListener videoEventListener = new OnVideoEventListener() {
        public void onVideoEvent(int tag, int event) {
            Cocos2dxVideoHelper.this.mActivity.runOnGLThread(new VideoEventRunnable(tag, event));
        }
    };

    private class VideoEventRunnable implements Runnable {
        private int mVideoEvent;
        private int mVideoTag;

        public VideoEventRunnable(int tag, int event) {
            this.mVideoTag = tag;
            this.mVideoEvent = event;
        }

        public void run() {
            Cocos2dxVideoHelper.nativeExecuteVideoCallback(this.mVideoTag, this.mVideoEvent);
        }
    }

    static class VideoHandler extends Handler {
        WeakReference<Cocos2dxVideoHelper> mReference;

        VideoHandler(Cocos2dxVideoHelper helper) {
            this.mReference = new WeakReference<>(helper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ((Cocos2dxVideoHelper) this.mReference.get())._createVideoView(msg.arg1);
                    break;
                case 1:
                    ((Cocos2dxVideoHelper) this.mReference.get())._removeVideoView(msg.arg1);
                    break;
                case 2:
                    ((Cocos2dxVideoHelper) this.mReference.get())._setVideoURL(msg.arg1, msg.arg2, (String) msg.obj);
                    break;
                case 3:
                    Rect rect = (Rect) msg.obj;
                    ((Cocos2dxVideoHelper) this.mReference.get())._setVideoRect(msg.arg1, rect.left, rect.top, rect.right, rect.bottom);
                    break;
                case 4:
                    ((Cocos2dxVideoHelper) this.mReference.get())._startVideo(msg.arg1);
                    break;
                case 5:
                    ((Cocos2dxVideoHelper) this.mReference.get())._pauseVideo(msg.arg1);
                    break;
                case 6:
                    ((Cocos2dxVideoHelper) this.mReference.get())._resumeVideo(msg.arg1);
                    break;
                case 7:
                    ((Cocos2dxVideoHelper) this.mReference.get())._stopVideo(msg.arg1);
                    break;
                case 8:
                    ((Cocos2dxVideoHelper) this.mReference.get())._seekVideoTo(msg.arg1, msg.arg2);
                    break;
                case 9:
                    Cocos2dxVideoHelper helper = (Cocos2dxVideoHelper) this.mReference.get();
                    if (msg.arg2 != 1) {
                        helper._setVideoVisible(msg.arg1, false);
                        break;
                    } else {
                        helper._setVideoVisible(msg.arg1, true);
                        break;
                    }
                case 10:
                    ((Cocos2dxVideoHelper) this.mReference.get())._restartVideo(msg.arg1);
                    break;
                case 11:
                    Cocos2dxVideoHelper helper2 = (Cocos2dxVideoHelper) this.mReference.get();
                    if (msg.arg2 != 1) {
                        helper2._setVideoKeepRatio(msg.arg1, false);
                        break;
                    } else {
                        helper2._setVideoKeepRatio(msg.arg1, true);
                        break;
                    }
                case 12:
                    Cocos2dxVideoHelper helper3 = (Cocos2dxVideoHelper) this.mReference.get();
                    Rect rect2 = (Rect) msg.obj;
                    if (msg.arg2 != 1) {
                        helper3._setFullScreenEnabled(msg.arg1, false, rect2.right, rect2.bottom);
                        break;
                    } else {
                        helper3._setFullScreenEnabled(msg.arg1, true, rect2.right, rect2.bottom);
                        break;
                    }
                case 13:
                    Cocos2dxVideoHelper helper4 = (Cocos2dxVideoHelper) this.mReference.get();
                    if (msg.arg2 != 1) {
                        helper4._setVideoSkipVisible(msg.arg1, false);
                        break;
                    } else {
                        helper4._setVideoSkipVisible(msg.arg1, true);
                        break;
                    }
                case 1000:
                    ((Cocos2dxVideoHelper) this.mReference.get()).onBackKeyEvent();
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public static native void nativeExecuteVideoCallback(int i, int i2);

    Cocos2dxVideoHelper(Cocos2dxActivity activity, FrameLayout layout) {
        this.mActivity = activity;
        this.mLayout = layout;
        mVideoHandler = new VideoHandler(this);
        this.sVideoViews = new SparseArray<>();
        this.sSkipLayouts = new SparseArray<>();
    }

    public static int createVideoWidget() {
        Message msg = new Message();
        msg.what = 0;
        msg.arg1 = videoTag;
        mVideoHandler.sendMessage(msg);
        int i = videoTag;
        videoTag = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    public void _createVideoView(int index) {
        Cocos2dxVideoView videoView = new Cocos2dxVideoView(this.mActivity, index);
        this.sVideoViews.put(index, videoView);
        this.mLayout.addView(videoView, new LayoutParams(-2, -2));
        videoView.setZOrderMediaOverlay(true);
        videoView.setOnCompletionListener(this.videoEventListener);
        _createSkipButton(index);
    }

    private static StateListDrawable newSelector(Context context, int idNormal, int idPressed, int idFocused, int idUnable) {
        Drawable unable = null;
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
        Drawable pressed = idPressed == -1 ? null : context.getResources().getDrawable(idPressed);
        Drawable focused = idFocused == -1 ? null : context.getResources().getDrawable(idFocused);
        if (idUnable != -1) {
            unable = context.getResources().getDrawable(idUnable);
        }
        bg.addState(new int[]{16842919, 16842910}, pressed);
        bg.addState(new int[]{16842910, 16842908}, focused);
        bg.addState(new int[]{16842910}, normal);
        bg.addState(new int[]{16842908}, focused);
        bg.addState(new int[]{16842909}, unable);
        bg.addState(new int[0], normal);
        return bg;
    }

    private void _createSkipButton(int index) {
        RelativeLayout skipLayout = new RelativeLayout(this.mActivity);
        this.sSkipLayouts.put(index, skipLayout);
        skipLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.mLayout.addView(skipLayout);
        skipLayout.setVisibility(4);
        Button skipButton = new Button(this.mActivity);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(-2, -2);
        lParams.addRule(12);
        lParams.addRule(11);
        lParams.rightMargin = 100;
        lParams.bottomMargin = 100;
        skipLayout.addView(skipButton, lParams);
        skipButton.setText("");
        skipButton.setTextColor(Color.argb(180, 255, 255, 255));
        skipButton.setTextSize(20.0f);
        final int indexx = index;
        skipButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Cocos2dxVideoHelper.this.mActivity.runOnGLThread(new VideoEventRunnable(indexx, 4));
            }
        });
    }

    public static void removeVideoWidget(int index) {
        Message msg = new Message();
        msg.what = 1;
        msg.arg1 = index;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _removeVideoView(int index) {
        Cocos2dxVideoView view = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (view != null) {
            view.stopPlayback();
            this.sVideoViews.remove(index);
            this.mLayout.removeView(view);
        }
        _removeSkipButton(index);
    }

    private void _removeSkipButton(int index) {
        RelativeLayout view = (RelativeLayout) this.sSkipLayouts.get(index);
        if (view != null) {
            this.sSkipLayouts.remove(index);
            this.mLayout.removeView(view);
        }
    }

    public static void setVideoUrl(int index, int videoSource, String videoUrl) {
        Message msg = new Message();
        msg.what = 2;
        msg.arg1 = index;
        msg.arg2 = videoSource;
        msg.obj = videoUrl;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _setVideoURL(int index, int videoSource, String videoUrl) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            switch (videoSource) {
                case 0:
                    videoView.setVideoFileName(videoUrl);
                    return;
                case 1:
                    videoView.setVideoURL(videoUrl);
                    return;
                default:
                    return;
            }
        }
    }

    public static void setVideoRect(int index, int left, int top, int maxWidth, int maxHeight) {
        Message msg = new Message();
        msg.what = 3;
        msg.arg1 = index;
        msg.obj = new Rect(left, top, maxWidth, maxHeight);
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _setVideoRect(int index, int left, int top, int maxWidth, int maxHeight) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.setVideoRect(left, top, maxWidth, maxHeight);
        }
    }

    public static void setFullScreenEnabled(int index, boolean enabled, int width, int height) {
        Message msg = new Message();
        msg.what = 12;
        msg.arg1 = index;
        if (enabled) {
            msg.arg2 = 1;
        } else {
            msg.arg2 = 0;
        }
        msg.obj = new Rect(0, 0, width, height);
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _setFullScreenEnabled(int index, boolean enabled, int width, int height) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.setFullScreenEnabled(enabled, width, height);
        }
    }

    /* access modifiers changed from: private */
    public void onBackKeyEvent() {
        int viewCount = this.sVideoViews.size();
        for (int i = 0; i < viewCount; i++) {
            int key = this.sVideoViews.keyAt(i);
            if (((Cocos2dxVideoView) this.sVideoViews.get(key)) != null) {
                this.mActivity.runOnGLThread(new VideoEventRunnable(key, 1000));
            }
        }
    }

    public static void startVideo(int index) {
        Message msg = new Message();
        msg.what = 4;
        msg.arg1 = index;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _startVideo(int index) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.start();
        }
    }

    public static void pauseVideo(int index) {
        Message msg = new Message();
        msg.what = 5;
        msg.arg1 = index;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _pauseVideo(int index) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.pause();
        }
    }

    public static void resumeVideo(int index) {
        Message msg = new Message();
        msg.what = 6;
        msg.arg1 = index;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _resumeVideo(int index) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.resume();
        }
    }

    public static void stopVideo(int index) {
        Message msg = new Message();
        msg.what = 7;
        msg.arg1 = index;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _stopVideo(int index) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.stop();
        }
    }

    public static void restartVideo(int index) {
        Message msg = new Message();
        msg.what = 10;
        msg.arg1 = index;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _restartVideo(int index) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.restart();
        }
    }

    public static void seekVideoTo(int index, int msec) {
        Message msg = new Message();
        msg.what = 8;
        msg.arg1 = index;
        msg.arg2 = msec;
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _seekVideoTo(int index, int msec) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.seekTo(msec);
        }
    }

    public static void setVideoVisible(int index, boolean visible) {
        Message msg = new Message();
        msg.what = 9;
        msg.arg1 = index;
        if (visible) {
            msg.arg2 = 1;
        } else {
            msg.arg2 = 0;
        }
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _setVideoVisible(int index, boolean visible) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView == null) {
            return;
        }
        if (visible) {
            videoView.fixSize();
            videoView.setVisibility(0);
            return;
        }
        videoView.setVisibility(4);
    }

    public static void setVideoSkipVisible(int index, boolean visible) {
        Message msg = new Message();
        msg.what = 13;
        msg.arg1 = index;
        if (visible) {
            msg.arg2 = 1;
        } else {
            msg.arg2 = 0;
        }
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _setVideoSkipVisible(int index, boolean visible) {
        RelativeLayout view = (RelativeLayout) this.sSkipLayouts.get(index);
        if (view == null) {
            return;
        }
        if (visible) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    public static void setVideoKeepRatioEnabled(int index, boolean enable) {
        Message msg = new Message();
        msg.what = 11;
        msg.arg1 = index;
        if (enable) {
            msg.arg2 = 1;
        } else {
            msg.arg2 = 0;
        }
        mVideoHandler.sendMessage(msg);
    }

    /* access modifiers changed from: private */
    public void _setVideoKeepRatio(int index, boolean enable) {
        Cocos2dxVideoView videoView = (Cocos2dxVideoView) this.sVideoViews.get(index);
        if (videoView != null) {
            videoView.setKeepRatio(enable);
        }
    }
}
