package com.koba_masa.analyze.loto.main;

import java.sql.SQLException;
import java.util.ArrayList;

import com.koba_masa.analyze.loto.dao.Loto6Dao;
import com.koba_masa.analyze.loto.db.DbAccess6;
import com.koba_masa.analyze.loto.logic.LogicAnalyzeLoto6Past;
import com.koba_masa.analyze.loto.sg.ComSg;

public class MainAnalyzeLoto6 extends AbstractAnalyzeLoto {

    private static MainAnalyzeLoto6 ml6 = null;

    /** プロセスID */
    private final int ProcessId = 1;

    public static void main(String[] args) {

        // インスタンス化
        ml6 = new MainAnalyzeLoto6();
        // DBアクセスクラス
        DbAccess6 db = null;
        // 過去結果取得クラス
        LogicAnalyzeLoto6Past past = new LogicAnalyzeLoto6Past();
        // DBDao
        ArrayList<Loto6Dao> dao6 = null;
        // 解析対象
        int target = -1;

        if ( args.length != 1 ) {
            System.out.println("パラメータ数が間違っています。");
            System.exit(1);
        } else if ( "0".equals(args[0]) ) {
            System.out.println("対象：最新回");
            target = 0;
        } else if ( "1".equals(args[0]) ) {
            System.out.println("対象：過去回");
            target = 1;
        } else {
            System.out.println("パラメータが間違っています。");
            System.exit(1);
        }

        try {
            // 設定ファイルチェック
            ml6.readConfig();
            System.out.println(ComSg.DbUrl);

            db = new DbAccess6();

            // DB接続
            db.connect();

            // 重複起動チェック
            if ( db.checkDuplication(ml6.ProcessId) ) {
                // 重複起動
                System.out.println("既に処理が起動中です。");
                // DB切断
                db.close();
                // 処理終了
                System.exit(0);
            }

            // HTML解析
            if ( target == 1 ) {
                dao6 = past.mainLogic();
            } else {
                dao6 = null;
            }

            // データ登録
            db.insert(dao6);

            // 起動フラグOFF
            db.exitExec(ml6.ProcessId);

            // DB切断
            db.close();

            // 処理終了
            System.out.println("処理が正常に完了しました。");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
