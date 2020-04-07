package org.cocos2dx.lib;

import android.content.Context;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import com.chukong.cocosplay.client.CocosPlayClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;
import p004cn.jiguang.net.HttpUtils;

public class Cocos2dxSound {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
    private static final int MAX_SIMULTANEOUS_STREAMS_I9100 = 3;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "Cocos2dxSound";
    private final Context mContext;
    /* access modifiers changed from: private */
    public final ArrayList<SoundInfoForLoadedCompleted> mEffecToPlayWhenLoadedArray = new ArrayList<>();
    private float mLeftVolume;
    private final HashMap<String, Integer> mPathSoundIDMap = new HashMap<>();
    private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap<>();
    private float mRightVolume;
    /* access modifiers changed from: private */
    public Semaphore mSemaphore;
    private SoundPool mSoundPool;
    /* access modifiers changed from: private */
    public int mStreamIdSyn;

    public class OnLoadCompletedListener implements OnLoadCompleteListener {
        public OnLoadCompletedListener() {
        }

        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            if (status == 0) {
                Iterator it = Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SoundInfoForLoadedCompleted info = (SoundInfoForLoadedCompleted) it.next();
                    if (sampleId == info.soundID) {
                        Cocos2dxSound.this.mStreamIdSyn = Cocos2dxSound.this.doPlayEffect(info.path, info.soundID, info.isLoop, info.pitch, info.pan, info.gain);
                        Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.remove(info);
                        break;
                    }
                }
            } else {
                Cocos2dxSound.this.mStreamIdSyn = -1;
            }
            Cocos2dxSound.this.mSemaphore.release();
        }
    }

    public class SoundInfoForLoadedCompleted {
        public float gain;
        public boolean isLoop;
        public float pan;
        public String path;
        public float pitch;
        public int soundID;

        public SoundInfoForLoadedCompleted(String path2, int soundId, boolean isLoop2, float pitch2, float pan2, float gain2) {
            this.path = path2;
            this.soundID = soundId;
            this.isLoop = isLoop2;
            this.pitch = pitch2;
            this.pan = pan2;
            this.gain = gain2;
        }
    }

    public Cocos2dxSound(Context context) {
        this.mContext = context;
        initData();
    }

    private void initData() {
        if (Cocos2dxHelper.getDeviceModel().indexOf("GT-I9100") != -1) {
            this.mSoundPool = new SoundPool(3, 3, 5);
        } else {
            this.mSoundPool = new SoundPool(5, 3, 5);
        }
        this.mSoundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mSemaphore = new Semaphore(0, true);
    }

    public int preloadEffect(String path) {
        if (CocosPlayClient.isEnabled() && !CocosPlayClient.isDemo()) {
            CocosPlayClient.updateAssets(path);
        }
        CocosPlayClient.notifyFileLoaded(path);
        Integer soundID = (Integer) this.mPathSoundIDMap.get(path);
        if (soundID == null) {
            soundID = Integer.valueOf(createSoundIDFromAsset(path));
            if (soundID.intValue() != -1) {
                this.mPathSoundIDMap.put(path, soundID);
            }
        }
        return soundID.intValue();
    }

    public void unloadEffect(String path) {
        ArrayList<Integer> streamIDs = (ArrayList) this.mPathStreamIDsMap.get(path);
        if (streamIDs != null) {
            Iterator it = streamIDs.iterator();
            while (it.hasNext()) {
                this.mSoundPool.stop(((Integer) it.next()).intValue());
            }
        }
        this.mPathStreamIDsMap.remove(path);
        Integer soundID = (Integer) this.mPathSoundIDMap.get(path);
        if (soundID != null) {
            this.mSoundPool.unload(soundID.intValue());
            this.mPathSoundIDMap.remove(path);
        }
    }

    public int playEffect(String path, boolean loop, float pitch, float pan, float gain) {
        int streamID;
        Integer soundID = (Integer) this.mPathSoundIDMap.get(path);
        if (soundID != null) {
            streamID = doPlayEffect(path, soundID.intValue(), loop, pitch, pan, gain);
        } else {
            Integer soundID2 = Integer.valueOf(preloadEffect(path));
            if (soundID2.intValue() == -1) {
                return -1;
            }
            synchronized (this.mSoundPool) {
                this.mEffecToPlayWhenLoadedArray.add(new SoundInfoForLoadedCompleted(path, soundID2.intValue(), loop, pitch, pan, gain));
                try {
                    this.mSemaphore.acquire();
                    streamID = this.mStreamIdSyn;
                } catch (Exception e) {
                    return -1;
                }
            }
        }
        return streamID;
    }

    public void stopEffect(int steamID) {
        this.mSoundPool.stop(steamID);
        for (String pPath : this.mPathStreamIDsMap.keySet()) {
            if (((ArrayList) this.mPathStreamIDsMap.get(pPath)).contains(Integer.valueOf(steamID))) {
                ((ArrayList) this.mPathStreamIDsMap.get(pPath)).remove(((ArrayList) this.mPathStreamIDsMap.get(pPath)).indexOf(Integer.valueOf(steamID)));
                return;
            }
        }
    }

    public void pauseEffect(int steamID) {
        this.mSoundPool.pause(steamID);
    }

    public void resumeEffect(int steamID) {
        this.mSoundPool.resume(steamID);
    }

    public void pauseAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry<String, ArrayList<Integer>> entry : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) entry.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.pause(((Integer) it.next()).intValue());
                }
            }
        }
    }

    public void resumeAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry<String, ArrayList<Integer>> entry : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) entry.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.resume(((Integer) it.next()).intValue());
                }
            }
        }
    }

    public void stopAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry<String, ArrayList<Integer>> entry : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) entry.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.stop(((Integer) it.next()).intValue());
                }
            }
        }
        this.mPathStreamIDsMap.clear();
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void setEffectsVolume(float volume) {
        if (volume < 0.0f) {
            volume = 0.0f;
        }
        if (volume > SOUND_RATE) {
            volume = SOUND_RATE;
        }
        this.mRightVolume = volume;
        this.mLeftVolume = volume;
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry<String, ArrayList<Integer>> entry : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) entry.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.setVolume(((Integer) it.next()).intValue(), this.mLeftVolume, this.mRightVolume);
                }
            }
        }
    }

    public void end() {
        this.mSoundPool.release();
        this.mPathStreamIDsMap.clear();
        this.mPathSoundIDMap.clear();
        this.mEffecToPlayWhenLoadedArray.clear();
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        initData();
    }

    public int createSoundIDFromAsset(String path) {
        int soundID;
        try {
            if (path.startsWith(HttpUtils.PATHS_SEPARATOR)) {
                soundID = this.mSoundPool.load(path, 0);
            } else {
                soundID = this.mSoundPool.load(this.mContext.getAssets().openFd(path), 0);
            }
        } catch (Exception e) {
            soundID = -1;
            Log.e(TAG, "error: " + e.getMessage(), e);
        }
        if (soundID == 0) {
            return -1;
        }
        return soundID;
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    /* access modifiers changed from: private */
    public int doPlayEffect(String path, int soundId, boolean loop, float pitch, float pan, float gain) {
        float leftVolume = this.mLeftVolume * gain * (SOUND_RATE - clamp(pan, 0.0f, SOUND_RATE));
        float rightVolume = this.mRightVolume * gain * (SOUND_RATE - clamp(-pan, 0.0f, SOUND_RATE));
        int streamID = this.mSoundPool.play(soundId, clamp(leftVolume, 0.0f, SOUND_RATE), clamp(rightVolume, 0.0f, SOUND_RATE), 1, loop ? -1 : 0, clamp(SOUND_RATE * pitch, 0.5f, 2.0f));
        ArrayList<Integer> streamIDs = (ArrayList) this.mPathStreamIDsMap.get(path);
        if (streamIDs == null) {
            streamIDs = new ArrayList<>();
            this.mPathStreamIDsMap.put(path, streamIDs);
        }
        streamIDs.add(Integer.valueOf(streamID));
        return streamID;
    }

    public void onEnterBackground() {
        this.mSoundPool.autoPause();
    }

    public void onEnterForeground() {
        this.mSoundPool.autoResume();
    }
}
