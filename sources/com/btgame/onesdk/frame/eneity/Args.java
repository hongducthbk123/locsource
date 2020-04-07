package com.btgame.onesdk.frame.eneity;

import java.io.Serializable;

public class Args implements Serializable {
    public String arg1;
    public String arg10;
    public String arg2;
    public String arg3;
    public String arg4;
    public String arg5;
    public String arg6;
    public String arg7;
    public String arg8;
    public String arg9;
    public String operation;

    public String toString() {
        return "arg1=" + this.arg1 + " , arg2 = " + this.arg2 + " ,arg3 = " + this.arg3 + " , arg4 = " + this.arg4 + " , arg5 = " + this.arg5 + " , arg6 = " + this.arg6 + " , arg7 = " + this.arg7 + " , arg8 = " + this.arg8 + " , arg9 = " + this.arg9 + " , arg10 = " + this.arg10 + " , operation = " + this.operation;
    }
}
