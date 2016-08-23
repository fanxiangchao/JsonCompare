package com.json.compare.bean;

/**
 * Created by Administrator on 2016/8/23.
 */
public class NotGenericBean {

    private int id;

    private String name;

    private SimpleUserBean data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleUserBean getData() {
        return data;
    }

    public void setData(SimpleUserBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NotGenericBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
