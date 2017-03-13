package com.synaptik.soundwave;

public interface OnUpdateListener {
    int ERROR_CODE_MICROPHONE_LOCKED = 1;

    void update(short[] bytes, int length, float sampleLength);
    void quit(int errorCode);
}
