package com.koba_masa.analyze.loto.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class ExecManageDao {

    private boolean activeflg = false;
    private int processid = 0;
    private boolean statusflg = false;
    private Date createdate = null;
    private Timestamp updatedate = null;

    public void setActiveFlg(boolean aParam) {
        this.activeflg = aParam;
    }
    public boolean getActiveFlg() {
        return this.activeflg;
    }

    public void setProcessId(int aParam) {
        this.processid = aParam;
    }
    public int getProcessId() {
        return this.processid;
    }

    public void setStatusFlg(boolean aParam) {
        this.statusflg = aParam;
    }
    public boolean getStatusFlg() {
        return this.statusflg;
    }

    public void setCreateDate(Date aParam) {
        this.createdate = aParam;
    }
    public Date getCreateDate() {
        return this.createdate;
    }

    public void setUpdateDate(Timestamp aParam) {
        this.updatedate = aParam;
    }
    public Timestamp getUpdateDate() {
        return this.updatedate;
    }

}
