package com.dozer.pojo;

import java.util.Date;
import java.util.List;

/**
 * 原始类
 */
public class Target01 {

    private String name;

    private Integer age;

    private Date birthday;

    private List<String> habbies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getHabbies() {
        return habbies;
    }

    public void setHabbies(List<String> habbies) {
        this.habbies = habbies;
    }

    @Override
    public String toString() {
        return "Target01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", habbies=" + habbies +
                '}';
    }
}
