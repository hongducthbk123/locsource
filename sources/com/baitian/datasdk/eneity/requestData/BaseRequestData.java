package com.baitian.datasdk.eneity.requestData;

import com.baitian.datasdk.eneity.BaseDataField;

public class BaseRequestData {
    private BaseDataField data;
    private String sign;

    public BaseDataField getData() {
        return this.data;
    }

    public void setData(BaseDataField data2) {
        this.data = data2;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign2) {
        this.sign = sign2;
    }
}
