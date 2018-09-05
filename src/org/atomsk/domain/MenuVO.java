//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.atomsk.domain;

import java.util.Date;

public class MenuVO {
    private int mno;
    private int sno;
    private String mname;
    private int price;
    private Date regdate;

    public MenuVO() {
    }

    public String toString() {
        return "MenuVO{mno=" + this.mno + ", sno=" + this.sno + ", mname='" + this.mname + '\'' + ", price=" + this.price + ", regdate=" + this.regdate + '}';
    }

    public int getMno() {
        return this.mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public int getSno() {
        return this.sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getMname() {
        return this.mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getRegdate() {
        return this.regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
