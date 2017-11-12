package com.koba_masa.analyze.loto.main;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.koba_masa.analyze.loto.sg.ComConst;
import com.koba_masa.analyze.loto.sg.ComSg;
import com.koba_masa.analyze.loto.util.ComUtil;

public abstract class AbstractAnalyzeLoto {

    /** 共通処理クラス */
    protected ComUtil cu = null;

    /**
     * コンストラクタ
     */
    public AbstractAnalyzeLoto() {
        cu = new ComUtil();

    }

    /**
     * 設定ファイル読み込みメソッド
     * @throws Exception
     */
    protected void readConfig() throws Exception {
        String dbHost = null;
        String dbPort = null;
        String dbName = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(ComConst.CONF_FILE_NAME));
        Element element = doc.getDocumentElement();
        NodeList parents = element.getElementsByTagName("datebase");

        for ( int i = 0; i < parents.getLength(); i++ ) {
            NodeList children = parents.item(i).getChildNodes();
            for ( int j = 0; j < children.getLength(); j++ ) {
                String name = children.item(j).getNodeName();
                // DBホスト取得
                if ( ComConst.CONF_DB_HOST.equals(name) ) {
                    dbHost = children.item(j).getTextContent().trim();
                // DBポート取得
                } else if ( ComConst.CONF_DB_PORT.equals(name) ) {
                    dbPort = children.item(j).getTextContent().trim();
                // DB名取得
                } else if ( ComConst.CONF_DB_NAME.equals(name) ) {
                    dbName = children.item(j).getTextContent().trim();
                // DBユーザ取得
                } else if ( ComConst.CONF_DB_USER.equals(name) ) {
                    ComSg.DbUser = children.item(j).getTextContent().trim();
                // DBパスワード取得
                } else if ( ComConst.CONF_DB_PASS.equals(name) ) {
                    ComSg.DbPass = children.item(j).getTextContent().trim();
                }
            }
        }

        // DBホストチェック
        if ( cu.isEmpty(dbHost) ) {
            throw new Exception("ホスト名");
        }

        // DBポートチェック
        if ( cu.isEmpty(dbPort) ) {
            throw new Exception("ポート");
        }

        // DB名チェック
        if ( cu.isEmpty(dbName) ) {
            throw new Exception("DB名");
        }

        // URL生成
        ComSg.DbUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

        // DBユーザチェック
        if ( cu.isEmpty(ComSg.DbUser) ) {
            throw new Exception("ユーザ名");
        }

        // DBパスワードチェック
        if ( cu.isEmpty(ComSg.DbPass) ) {
            throw new Exception("パスワード");
        }
    }


}
