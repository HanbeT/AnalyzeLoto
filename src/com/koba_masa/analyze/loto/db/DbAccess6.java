package com.koba_masa.analyze.loto.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.koba_masa.analyze.loto.dao.Loto6Dao;

public class DbAccess6 extends DbAccess {

    private final String INSERT = "INSERT INTO LOTO6_RESULT_INFO_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
    private final String SELECT = "SELECT * FROM LOTO6_RESULT_INFO_TBL WHERE times = ?";

    /** コンストラクタ */
    public DbAccess6() throws Exception {
        super();
    }

    /**
     *
     * @param aDao
     * @throws SQLException
     */
    public void insert(ArrayList<Loto6Dao> aDao) throws SQLException {
        // ステートメント
        PreparedStatement insert = null;
        // 登録結果
        int resInsert = -1;
        // ステートメント
        PreparedStatement select = null;
        // 検索結果
        ResultSet resSelect = null;

        select = conn.prepareStatement(SELECT);
        insert = conn.prepareStatement(INSERT);
        try {
            for (Loto6Dao dao : aDao) {
                select.setInt(1, dao.getTimes());
                resSelect = select.executeQuery();
                if ( !resSelect.next() ) {
                    insert.setBoolean(1, dao.getActiveflg());
                    insert.setInt(2, dao.getTimes());
                    insert.setString(3, dao.getDay());
                    insert.setString(4, dao.getNum1());
                    insert.setString(5, dao.getNum2());
                    insert.setString(6, dao.getNum3());
                    insert.setString(7, dao.getNum4());
                    insert.setString(8, dao.getNum5());
                    insert.setString(9, dao.getNum6());
                    insert.setString(10, dao.getBonus());
                    insert.setInt(11, dao.getClass1());
                    insert.setLong(12, dao.getMoney1());
                    insert.setInt(13, dao.getClass2());
                    insert.setLong(14, dao.getMoney2());
                    insert.setInt(15, dao.getClass3());
                    insert.setLong(16, dao.getMoney3());
                    insert.setInt(17, dao.getClass4());
                    insert.setLong(18, dao.getMoney4());
                    insert.setInt(19, dao.getClass5());
                    insert.setLong(20, dao.getMoney5());
                    insert.setLong(21, dao.getSales());
                    insert.setLong(22, dao.getCarryOver());

                    resInsert = insert.executeUpdate();
                    insert.clearParameters();
                }
            }
        } catch ( SQLException se ) {
            throw se;
        }
    }



}
