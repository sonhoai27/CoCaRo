package com.sonhoai.sonho.tiktak;

public interface CallBack {
    public void onTaskCompleted(String result);
    public void onFailure(Exception e);
}
