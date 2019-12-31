package com.example.libingzheng20191231.bean;

import java.util.List;

public class Bean {
    private String avatar;
    private String name;
    private String rank;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public Bean(String avatar, String name, String rank) {
        this.avatar = avatar;
        this.name = name;
        this.rank = rank;
    }
}
