package com.uplooking.bigdata.vo;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.vo
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer provice_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProvice_id() {
        return provice_id;
    }

    public void setProvice_id(Integer provice_id) {
        this.provice_id = provice_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", provice_id=" + provice_id +
                '}';
    }
}
