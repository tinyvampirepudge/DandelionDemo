package com.tinytongtong.dandelion.biz.upload.bean;

/**
 * @Description: apk上传
 * @Author wangjianzhou@qding.me
 * @Date 2018/10/6 1:44 PM
 * @Version TODO
 */
public class UploadResultBean {
    private String buildKey;
    private int buildType;

    public String getBuildKey() {
        return buildKey;
    }

    public void setBuildKey(String buildKey) {
        this.buildKey = buildKey;
    }

    public int getBuildType() {
        return buildType;
    }

    public void setBuildType(int buildType) {
        this.buildType = buildType;
    }

    @Override
    public String toString() {
        return "UploadResultBean{" +
                "buildKey='" + buildKey + '\'' +
                ", buildType=" + buildType +
                '}';
    }
}
