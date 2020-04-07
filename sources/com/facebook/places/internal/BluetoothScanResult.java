package com.facebook.places.internal;

public class BluetoothScanResult {
    public String payload;
    public int rssi;

    public BluetoothScanResult(String payload2, int rssi2) {
        this.payload = payload2;
        this.rssi = rssi2;
    }
}
