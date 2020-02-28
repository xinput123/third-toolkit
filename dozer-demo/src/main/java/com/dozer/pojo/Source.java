package com.dozer.pojo;

import java.util.Date;
import java.util.List;

/**
 * 原始类
 */
public class Source {

    private String name;

    private Integer age;

    private Date birthday;

    private List<String> habbies;

    public String getName() {
        return name;
    }

    public Source setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Source setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Source setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public List<String> getHabbies() {
        return habbies;
    }

    public Source setHabbies(List<String> habbies) {
        this.habbies = habbies;
        return this;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", habbies=" + habbies +
                '}';
    }
}
