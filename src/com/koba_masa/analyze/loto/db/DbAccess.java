package com.koba_masa.analyze.loto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.koba_masa.analyze.loto.dao.ExecManageDao;
import com.koba_masa.analyze.loto.sg.ComConst;
import com.koba_masa.analyze.loto.sg.ComSg;


public class DbAccess {

    /** コネクション */
    protected Connection conn = null;

    /** 起動確認SQL */
    private final String SQL_CHECK_DUPLICATION = "SELECT * FROM EXEC_MANAGE_TBL WHERE processid = ? AND activeflg = true";
    /** 起動状態更新SQL */
    private final String SQL_UPDATE_EXECTIME = "UPDATE EXEC_MANAGE_TBL SET(statusflg, updatedate) = (? ,now()) WHERE processid = ? AND activeflg = true";

    /** コンストラクタ */
    protected DbAccess() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception();
        }
    }

    /**
     * DB接続メソッド
     * @throws SQLException
     */
    public void connect() throws SQLException {
        // コネクション生成
        conn = DriverManager.getConnection(ComSg.DbUrl, ComSg.DbUser, ComSg.DbPass);
    }

    /**
     * DB切断メソッド
     * @throws SQLException
     */
    public void close() throws SQLException {
        // コネクション破棄
        if ( conn != null ) {
            conn.close();
        }
    }

    /**
     * 重複起動チェックメソッド
     * @param aPId プロセスID
     * @return 処理結果(true：起動中/false：停止中)
     */
    public boolean checkDuplication(int aPId) {
        // 戻り値
        boolean res = false;
        // プリペアードステートメントオブジェクト
        PreparedStatement pstate = null;
        // 結果オブジェクト
        ResultSet result = null;
        // 実行管理テーブルDao
        ExecManageDao emo = null;

        try {
            // ステートメント生成
            pstate = conn.prepareStatement(SQL_CHECK_DUPLICATION);
            pstate.setInt(1, aPId);

            // 検索
            result = pstate.executeQuery();
            while( result.next() ) {
                if ( emo != null ) {
                    // システム異常
                }
                emo = new ExecManageDao();
                emo.setActiveFlg(result.getBoolean("activeflg"));
                emo.setProcessId(result.getInt("processid"));
                emo.setStatusFlg(result.getBoolean("statusflg"));
                emo.setCreateDate(result.getDate("createdate"));
                emo.setUpdateDate(result.getTimestamp("updatedate"));
            }
            if ( emo == null ) {
                // システム異常
                System.out.println("対象の処理が存在しません。");
            }

            // 重複起動チェック
            if ( emo.getStatusFlg() && checkTimeout(emo.getUpdateDate()) ) {
                // タイムアウト
                System.out.println("タイムアウトしました。");

            } else if ( emo.getStatusFlg() ) {
                // 起動中
                res = true;
            }

            // 停止中またはタイムアウトの場合、時間を更新
            if ( !res ) {
                if ( pstate != null ) {
                    pstate.close();
                }
                pstate = conn.prepareStatement(SQL_UPDATE_EXECTIME);
                pstate.setBoolean(1, true);
                pstate.setInt(2, aPId);
                // SQL実行
                pstate.executeUpdate();
            }

        } catch ( SQLException se ) {
            se.printStackTrace();
        } finally {
            try {
                if ( result != null ) {
                    result.close();
                }
                if ( pstate != null ) {
                    pstate.close();
                }
            } catch ( SQLException se) {

            }
        }

        return res;
    }

    /**
     *
     * @param aParam
     * @return 処理結果(true：タイムアウト時間経過/false：起動中)
     */
    private boolean checkTimeout( Timestamp aParam ) {
        long nowTime = new java.util.Date().getTime();
        return nowTime - aParam.getTime() > ComConst.DUPLICATE_TIME_OUT ;
    }

    public void exitExec(int aPId) {
        // プリペアードステートメントオブジェクト
        PreparedStatement pstate = null;

        try {
            pstate = conn.prepareStatement(SQL_UPDATE_EXECTIME);
            pstate.setBoolean(1, false);
            pstate.setInt(2, aPId);
            // SQL実行
            pstate.executeUpdate();
        } catch ( SQLException se ) {
            se.printStackTrace();
        } finally {
            try {
                if ( pstate != null ) {
                    pstate.close();
                }
            } catch ( SQLException se) {

            }
        }
    }



}
