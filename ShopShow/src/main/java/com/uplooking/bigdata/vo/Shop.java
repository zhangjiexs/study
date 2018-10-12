package com.uplooking.bigdata.vo;

public class Shop {
    private Integer id;
    private String type;
    private Integer count;

    public Shop() {
    }

    public Shop(Integer id, String type, Integer count) {
        this.id = id;
        this.type = type;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
