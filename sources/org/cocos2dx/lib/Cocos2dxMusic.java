package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import com.chukong.cocosplay.client.CocosPlayClient;
import java.io.FileInputStream;
import p004cn.jiguang.net.HttpUtils;

public class Cocos2dxMusic {
    private static final String TAG = Cocos2dxMusic.class.getSimpleName();
    private MediaPlayer mBackgroundMediaPlayer;
    private final Context mContext;
    private String mCurrentPath;
    private boolean mIsLoop = false;
    private float mLeftVolume;
    private boolean mManualPaused = false;
    private boolean mPaused;
    private float mRightVolume;

    public Cocos2dxMusic(Context context) {
        this.mContext = context;
        initData();
    }

    public void preloadBackgroundMusic(String path) {
        if (this.mCurrentPath == null || !this.mCurrentPath.equals(path)) {
            if (this.mBackgroundMediaPlayer != null) {
                this.mBackgroundMediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(path);
            this.mCurrentPath = path;
        }
    }

    public void playBackgroundMusic(String path, boolean isLoop) {
        if (this.mCurrentPath == null) {
            this.mBackgroundMediaPlayer = createMediaplayer(path);
            this.mCurrentPath = path;
        } else if (!this.mCurrentPath.equals(path)) {
            if (this.mBackgroundMediaPlayer != null) {
                this.mBackgroundMediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(path);
            this.mCurrentPath = path;
        }
        if (this.mBackgroundMediaPlayer == null) {
            Log.e(TAG, "playBackgroundMusic: background media player is null");
            return;
        }
        try {
            if (this.mPaused) {
                this.mBackgroundMediaPlayer.seekTo(0);
                this.mBackgroundMediaPlayer.start();
            } else if (this.mBackgroundMediaPlayer.isPlaying()) {
                this.mBackgroundMediaPlayer.seekTo(0);
            } else {
                this.mBackgroundMediaPlayer.start();
            }
            this.mBackgroundMediaPlayer.setLooping(isLoop);
            this.mPaused = false;
            this.mIsLoop = isLoop;
        } catch (Exception e) {
            Log.e(TAG, "playBackgroundMusic: error state");
        }
    }

    public void stopBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.release();
            this.mBackgroundMediaPlayer = createMediaplayer(this.mCurrentPath);
            this.mPaused = false;
        }
    }

    public void pauseBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
            this.mBackgroundMediaPlayer.pause();
            this.mPaused = true;
            this.mManualPaused = true;
        }
    }

    public void resumeBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null && this.mPaused) {
            this.mBackgroundMediaPlayer.start();
            this.mPaused = false;
            this.mManualPaused = false;
        }
    }

    public void rewindBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            playBackgroundMusic(this.mCurrentPath, this.mIsLoop);
        }
    }

    public boolean isBackgroundMusicPlaying() {
        if (this.mBackgroundMediaPlayer == null) {
            return false;
        }
        return this.mBackgroundMediaPlayer.isPlaying();
    }

    public void end() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.release();
        }
        initData();
    }

    public float getBackgroundVolume() {
        if (this.mBackgroundMediaPlayer != null) {
            return (this.mLeftVolume + this.mRightVolume) / 2.0f;
        }
        return 0.0f;
    }

    public void setBackgroundVolume(float volume) {
        if (volume < 0.0f) {
            volume = 0.0f;
        }
        if (volume > 1.0f) {
            volume = 1.0f;
        }
        this.mRightVolume = volume;
        this.mLeftVolume = volume;
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void onEnterBackground() {
        if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
            this.mBackgroundMediaPlayer.pause();
            this.mPaused = true;
        }
    }

    public void onEnterForeground() {
        if (!this.mManualPaused && this.mBackgroundMediaPlayer != null && this.mPaused) {
            this.mBackgroundMediaPlayer.start();
            this.mPaused = false;
        }
    }

    private void initData() {
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mBackgroundMediaPlayer = null;
        this.mPaused = false;
        this.mCurrentPath = null;
    }

    private MediaPlayer createMediaplayer(String path) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            if (CocosPlayClient.isEnabled() && !CocosPlayClient.isDemo()) {
                CocosPlayClient.updateAssets(path);
            }
            CocosPlayClient.notifyFileLoaded(path);
            if (path.startsWith(HttpUtils.PATHS_SEPARATOR)) {
                FileInputStream fis = new FileInputStream(path);
                mediaPlayer.setDataSource(fis.getFD());
                fis.close();
            } else {
                AssetFileDescriptor assetFileDescritor = this.mContext.getAssets().openFd(path);
                mediaPlayer.setDataSource(assetFileDescritor.getFileDescriptor(), assetFileDescritor.getStartOffset(), assetFileDescritor.getLength());
            }
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            return mediaPlayer;
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            return null;
        }
    }
}
