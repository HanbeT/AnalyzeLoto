package com.koba_masa.analyze.loto.sg;

public class ComConst {

    /** 設定ファイル */
    public static final String CONF_FILE_NAME = "config/config.xml";
    /** タグ(DBホスト) */
    public static final String CONF_DB_HOST = "host";
    /** タグ(DBポート) */
    public static final String CONF_DB_PORT = "port";
    /** タグ(DB名) */
    public static final String CONF_DB_NAME = "name";
    /** タグ(DBユーザ) */
    public static final String CONF_DB_USER = "user";
    /** タグ(DBパスワード) */
    public static final String CONF_DB_PASS = "pass";

    /** 重複起動タイムアウト時間 */
    public static final long DUPLICATE_TIME_OUT = 600000L;

    /** URL(ロト6最新結果) */
    public static final String URL_TOP_LOTO6 = "http://www.takarakuji-loto.jp/tousenp.html";

    /** URL(ロト6過去結果) */
    public static final String URL_PAST_LOTO6 = "http://www.takarakuji-loto.jp/loto6-mini/";







}
