package com.json.compare.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class GroupUserBean {

    private int groupId;

    private String groupName;

    private List<SimpleUserBean> userBeanList;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<SimpleUserBean> getUserBeanList() {
        return userBeanList;
    }

    public void setUserBeanList(List<SimpleUserBean> userBeanList) {
        this.userBeanList = userBeanList;
    }

    @Override
    public String toString() {
        return "GroupUserBean{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", userBeanList=" + userBeanList +
                '}';
    }
}
