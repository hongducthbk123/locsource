package com.btgame.onesdk.frame.eneity.onesdk;

import java.util.List;

public class LoginReusltInfo {
    public String btSessionId;
    public String desc;
    public List<Object> obs;
    public int platfromId;
    public int statusCode;

    public String toString() {
        return "LoginCallbackInfo [statusCode=" + this.statusCode + ", btSession=" + this.btSessionId + " , platfromId = " + this.platfromId + "]";
    }
}
