package com.btgame.google;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.btgame.google.constant.GpConfig;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.EventsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class BtPlayGamesManager {
    private volatile AchievementsClient mAchievementsClient;
    private volatile EventsClient mEventsClient;
    private volatile GoogleSignInAccount mGamesSignInAccount;
    private volatile LeaderboardsClient mLeaderboardsClient;
    private volatile PlayersClient mPlayersClient;

    private static class BtPlayGamesManagerHolder {
        /* access modifiers changed from: private */
        public static final BtPlayGamesManager btPlayGamesManager = new BtPlayGamesManager();

        private BtPlayGamesManagerHolder() {
        }
    }

    private BtPlayGamesManager() {
    }

    public static BtPlayGamesManager getInstance() {
        return BtPlayGamesManagerHolder.btPlayGamesManager;
    }

    public AchievementsClient getAchievementsClient(Activity activity) {
        if (this.mAchievementsClient == null && this.mGamesSignInAccount != null) {
            this.mAchievementsClient = Games.getAchievementsClient(activity, this.mGamesSignInAccount);
        }
        return this.mAchievementsClient;
    }

    public LeaderboardsClient getLeaderboardsClient(Activity activity) {
        if (this.mLeaderboardsClient == null && this.mGamesSignInAccount != null) {
            this.mLeaderboardsClient = Games.getLeaderboardsClient(activity, this.mGamesSignInAccount);
        }
        return this.mLeaderboardsClient;
    }

    public EventsClient getEventsClient(Activity activity) {
        if (this.mEventsClient == null && this.mGamesSignInAccount != null) {
            this.mEventsClient = Games.getEventsClient(activity, this.mGamesSignInAccount);
        }
        return this.mEventsClient;
    }

    public PlayersClient getPlayersClient(Activity activity) {
        if (this.mPlayersClient == null && this.mGamesSignInAccount != null) {
            this.mPlayersClient = Games.getPlayersClient(activity, this.mGamesSignInAccount);
        }
        return this.mPlayersClient;
    }

    public void showAchievements(final Activity activity) {
        if (getAchievementsClient(activity) != null) {
            getAchievementsClient(activity).getAchievementsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                public void onSuccess(Intent intent) {
                    activity.startActivityForResult(intent, GpConfig.RC_UNUSED);
                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(@NonNull Exception e) {
                    BtToast.showShortTimeToast(activity, BTResourceUtil.findStringByName("achievements_exception"));
                    BtsdkLog.m1430e(e.getLocalizedMessage());
                }
            });
        }
    }

    public void showLeaderboards(final Activity activity) {
        if (getLeaderboardsClient(activity) != null) {
            getLeaderboardsClient(activity).getAllLeaderboardsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                public void onSuccess(Intent intent) {
                    activity.startActivityForResult(intent, GpConfig.RC_UNUSED);
                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(@NonNull Exception e) {
                    BtToast.showShortTimeToast(activity, BTResourceUtil.findStringByName("leaderboards_exception"));
                    BtsdkLog.m1430e(e.getLocalizedMessage());
                }
            });
        }
    }

    public void incrementEvents(Activity activity, String id, int times) {
        if (getEventsClient(activity) != null) {
            getEventsClient(activity).increment(id, times);
        }
    }

    public void incrementAchievement(Activity activity, String id, int steps) {
        if (getAchievementsClient(activity) != null) {
            getAchievementsClient(activity).increment(id, steps);
        }
    }

    public void unlockAchievement(Activity activity, String id) {
        if (getAchievementsClient(activity) != null) {
            getAchievementsClient(activity).unlock(id);
        }
    }

    public void submitScore(Activity activity, String id, long score) {
        if (getLeaderboardsClient(activity) != null) {
            getLeaderboardsClient(activity).submitScore(id, score);
        }
    }

    public void setGamesSignInAccount(GoogleSignInAccount account) {
        clean();
        this.mGamesSignInAccount = account;
    }

    public void clean() {
        this.mGamesSignInAccount = null;
        this.mAchievementsClient = null;
        this.mLeaderboardsClient = null;
        this.mEventsClient = null;
        this.mPlayersClient = null;
    }
}
