package com.baitian.datasdk.eneity;

import android.content.Context;

public class AccountInfoData extends DeviceBaseData {
    private String accountId;

    public AccountInfoData(Context ctx) {
        super(ctx);
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId2) {
        this.accountId = accountId2;
    }
}
