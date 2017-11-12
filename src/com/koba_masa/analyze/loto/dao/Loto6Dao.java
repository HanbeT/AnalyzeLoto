package com.koba_masa.analyze.loto.dao;

import java.sql.Date;

public class Loto6Dao {
    private boolean activeflg = true;
    private int times = 0;
    private String day = null;
    private String num1 = null;
    private String num2 = null;
    private String num3 = null;
    private String num4 = null;
    private String num5 = null;
    private String num6 = null;
    private String bonus = null;
    private int class1 = 0;
    private long money1 = 0L;
    private int class2 = 0;
    private long money2 = 0L;
    private int class3 = 0;
    private long money3 = 0L;
    private int class4 = 0;
    private long money4 = 0L;
    private int class5 = 0;
    private long money5 = 0L;
    private long sales = 0L;
    private long carryover = 0L;
    private Date createdate = null;
    private Date updatedate = null;

    public void setActiveflg(boolean aParam) {
        this.activeflg = aParam;
    }
    public boolean getActiveflg() {
        return this.activeflg;
    }
    public void setTimes(int aParam) {
        this.times = aParam;
    }
    public int getTimes() {
        return this.times;
    }
    public void setDay(String aParam) {
        this.day = aParam;
    }
    public String getDay() {
        return this.day;
    }
    public void setNum1(String aParam) {
        this.num1 = aParam;
    }
    public String getNum1() {
        return this.num1;
    }
    public void setNum2(String aParam) {
        this.num2 = aParam;
    }
    public String getNum2() {
        return this.num2;
    }
    public void setNum3(String aParam) {
        this.num3 = aParam;
    }
    public String getNum3() {
        return this.num3;
    }
    public void setNum4(String aParam) {
        this.num4 = aParam;
    }
    public String getNum4() {
        return this.num4;
    }
    public void setNum5(String aParam) {
        this.num5 = aParam;
    }
    public String getNum5() {
        return this.num5;
    }
    public void setNum6(String aParam) {
        this.num6 = aParam;
    }
    public String getNum6() {
        return this.num6;
    }
    public void setBonus(String aParam) {
        this.bonus = aParam;
    }
    public String getBonus() {
        return this.bonus;
    }
    public void setClass1(int aParam) {
        this.class1 = aParam;
    }
    public int getClass1() {
        return this.class1;
    }
    public void setMoney1(long aParam) {
        this.money1 = aParam;
    }
    public long getMoney1() {
        return this.money1;
    }
    public void setClass2(int aParam) {
        this.class2 = aParam;
    }
    public int getClass2() {
        return this.class2;
    }
    public void setMoney2(long aParam) {
        this.money2 = aParam;
    }
    public long getMoney2() {
        return this.money2;
    }
    public void setClass3(int aParam) {
        this.class3 = aParam;
    }
    public int getClass3() {
        return this.class3;
    }
    public void setMoney3(long aParam) {
        this.money3 = aParam;
    }
    public long getMoney3() {
        return this.money3;
    }
    public void setClass4(int aParam) {
        this.class4 = aParam;
    }
    public int getClass4() {
        return this.class4;
    }
    public void setMoney4(long aParam) {
        this.money4 = aParam;
    }
    public long getMoney4() {
        return this.money4;
    }
    public void setClass5(int aParam) {
        this.class5 = aParam;
    }
    public int getClass5() {
        return this.class5;
    }
    public void setMoney5(long aParam) {
        this.money5 = aParam;
    }
    public long getMoney5() {
        return this.money5;
    }
    public void setSales(long aParam) {
        this.sales = aParam;
    }
    public long getSales() {
        return this.sales;
    }
    public void setCarryOver(long aParam) {
        this.carryover = aParam;
    }
    public long getCarryOver() {
        return this.carryover;
    }
    public void setCreateDate(Date aParam) {
        this.createdate = aParam;
    }
    public Date getCreateDate() {
        return this.createdate;
    }
    public void setUpdateDate(Date aParam) {
        this.updatedate = aParam;
    }
    public Date getUpdateDate() {
        return this.updatedate;
    }
}
