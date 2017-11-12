package com.koba_masa.analyze.loto.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import com.koba_masa.analyze.loto.dao.Loto6Dao;

public class DbAccess6 extends DbAccess {

    private final String INSERT = "INSERT INTO LOTO6_RESULT_INFO_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
    private final String SELECT = "SELECT times FROM LOTO6_RESULT_INFO_TBL";

    // カラムヘッダー(開催回)
    private final String COL_HEADER_TIMES  = "times";

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
        Statement select = null;
        // 検索結果
        ResultSet resSelect = null;
        // 登録済み開催回
        HashSet<Integer> registTimes = new HashSet<Integer>();

        // ステートメント
        PreparedStatement insert = null;
        // 登録結果
        int resInsert = -1;

        try {
            // ステートメント文生成
            select = conn.createStatement();
            // プリペアード文生成
            insert = conn.prepareStatement(INSERT);

            // 登録済み開催回情報取得
            resSelect = select.executeQuery(SELECT);
            while ( resSelect.next() ) {
                if ( !registTimes.contains(resSelect.getInt(COL_HEADER_TIMES)) ) {
                    registTimes.add(resSelect.getInt(COL_HEADER_TIMES));
                }
            }

            // 新規開催回情報登録
            for (Loto6Dao dao : aDao) {
                if ( !registTimes.contains(dao.getTimes()) ) {
                    // プリペアードセット
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
                    // Insert文実行
                    resInsert = insert.executeUpdate();
                    System.out.println("第" + dao.getTimes() + "回の登録が完了しました。");
                    // Insert文クリア
                    insert.clearParameters();
                }
            }
        } catch ( SQLException se ) {
            throw se;
        }
    }
}
