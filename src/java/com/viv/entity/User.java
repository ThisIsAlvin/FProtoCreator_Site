package com.viv.entity;

/**
 * Created by viv on 16-3-28.
 */
public class User {

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", home='" + home + '\'' +
                ", tel=" + tel +

                ", email='" + email + '\'' +
                '}';
    }

    public User(){

    }

    public User(String name,String sex,String home, Integer tel,String email ){
        this.name = name;
        this.sex = sex;
        this.home = home;
        this.tel = tel;
        this.email = email;
    }

    public String name;
    public String sex;
    public String home;
    public Integer tel;
    public String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
