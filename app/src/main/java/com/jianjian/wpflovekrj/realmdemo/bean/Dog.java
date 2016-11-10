package com.jianjian.wpflovekrj.realmdemo.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wyl_fight on 2016/11/8.
 */

public class Dog extends RealmObject {
    private String name;
    private int age;

    @PrimaryKey
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
