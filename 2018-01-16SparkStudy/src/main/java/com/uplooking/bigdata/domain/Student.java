package com.uplooking.bigdata.domain;



import java.io.Serializable;

/**
 * @ Title: 2018-01-16SparkStudy
 * @ Package:com.uplooking.bigdata.domain
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/1/24
 * @ version V1.0
 */

public class Student  implements Serializable {
    private String name;
    private int age;
    private int gender;
    private int achievement;

    public Student() {
    }

    public Student(String name, int age, int achievement, int elective) {
        this.name = name;
        this.age = age;
        this.achievement = achievement;
        this.gender = elective;
    }

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }
}

