package com.zhangxin.bean;


import org.hibernate.validator.constraints.NotEmpty;

/** Created by zhangxin.zhang on 2015/4/13.
 */
public class TempBean {

    private int id;

    private String tempstr;

    public TempBean() {
    }

    public TempBean(String tempstr) {
        this.tempstr = tempstr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempstr() {
        return tempstr;
    }

    public void setTempstr(String tempstr) {
        this.tempstr = tempstr;
    }

    @Override
    public String toString() {
        return "TempBean{" +
                "id=" + id +
                ", tempstr='" + tempstr + '\'' +
                '}';
    }
}
