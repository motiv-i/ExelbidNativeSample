package com.motivi.native_sample;

public class ActivityInfo {
    public Class<?> activityClass; // Activity 클래스
    public String data; // Activity에 전달할 데이터 (선택 사항)

    public ActivityInfo(Class<?> activityClass, String data) {
        this.activityClass = activityClass;
        this.data = data;
    }
}