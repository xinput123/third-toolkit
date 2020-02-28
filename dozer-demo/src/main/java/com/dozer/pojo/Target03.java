package com.dozer.pojo;

import com.github.dozermapper.core.Mapping;

import java.util.Date;
import java.util.List;

/**
 * 原始类
 */
public class Target03 {

    @Mapping("name")
    private String userName;

    private Integer age;

    private Date birthday;

    private List<String> habbies;

    public String getUserName() {
        return userName;
    }

    public Target03 setUserName(String userName) {
        this.userName = userName;
        return this;
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
        return "Target03{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", habbies=" + habbies +
                '}';
    }
}
