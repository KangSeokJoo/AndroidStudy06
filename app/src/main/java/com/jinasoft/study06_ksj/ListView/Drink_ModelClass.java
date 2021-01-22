package com.jinasoft.study06_ksj.ListView;

import com.jinasoft.study06_ksj.R;

class Drink_ModelClass {
    private String name;
    private String decription;
    private int imageResourceId;

    public static final Drink_ModelClass[] drinks = { // 클래스 배열?
            new Drink_ModelClass("라때", "에스프레소 샷 에 스팀우유을 섞는다", R.drawable.latte),
            new Drink_ModelClass("카푸치노", "에스프레스에 우유 넣고 스팀 우유 넣는다", R.drawable.capucino),
            new Drink_ModelClass("카페모카", "에스프레소에 초코시럽 넣고 스팀 우유 넣는다", R.drawable.cafemoca)
    };

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public Drink_ModelClass(String name, String decription, int imageResourceId) {
        this.name = name;
        this.decription = decription;
        this.imageResourceId = imageResourceId;
    }
}
