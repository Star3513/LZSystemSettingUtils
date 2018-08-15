package com.lz.setting.main.model;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class LZMainDemoListModel {
    private String content;
    private int type;

    public LZMainDemoListModel() {
    }

    public LZMainDemoListModel(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
