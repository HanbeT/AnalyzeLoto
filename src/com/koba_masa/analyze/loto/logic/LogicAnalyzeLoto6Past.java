package com.koba_masa.analyze.loto.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.koba_masa.analyze.loto.dao.Loto6Dao;
import com.koba_masa.analyze.loto.sg.ComConst;
import com.koba_masa.analyze.loto.util.ComUtil;

public class LogicAnalyzeLoto6Past {
    /** 過去結果ページ名パターン */
    private final String URL_PAST_PAGE = "loto6[0-9]{4}.html";
    /** リンク記載要素取得セレクタ */
    private final String TAG_URL_LIST = "ul li a";
    /** href属性 */
    private final String TAG_ATTR_HREF = "href";
    /** テーブル行取得セレクタ */
    private final String TAG_PASTPAGE_TABLE = "table tbody tr";
    /** テーブルセル要素 */
    private final String TAG_ELE_TD = "td";
    /**  */
    private final int ELE_TD_COUNT = 20;

    /** 共通処理クラス */
    private ComUtil cu = null;

    /** コンストラクタ */
    public LogicAnalyzeLoto6Past() {
        cu = new ComUtil();
    }

    /**
     * 過去結果取得メソッド
     * @return
     * @throws IOException
     */
    public ArrayList<Loto6Dao> mainLogic() throws IOException {
        // 戻り値(結果)
        ArrayList<Loto6Dao> res = new ArrayList<Loto6Dao>();
        // 過去ページ
        ArrayList<String> pastPage = null;
        // DAO
        Loto6Dao dao = null;
        // 解析対象ドキュメント
        Document htmlDoc = null;
        // HTMLソース
        Elements source = null;
        // HTMLソース(tr)
        Elements td = null;

        // 過去ページリスト取得
        pastPage = getPastPage();
        for ( String url : pastPage ) {
            // 一覧ページ取得
            htmlDoc = Jsoup.connect(ComConst.URL_PAST_LOTO6 + url).userAgent("Mozilla").get();
            // HTMLソース取得
            source = htmlDoc.select(TAG_PASTPAGE_TABLE);
            for ( Element ele : source ) {
                td = ele.select(TAG_ELE_TD);
                if ( td.size() == ELE_TD_COUNT ) {
                    dao = getTableData(td);
                    if ( dao != null ) {
                        res.add(dao);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 過去結果一覧取得メソッド
     * @return
     * @throws IOException
     */
    private ArrayList<String> getPastPage() throws IOException {
        // 戻り値(過去ページ)
        ArrayList<String> res = new ArrayList<String>();
        // 解析対象ドキュメント
        Document htmlDoc = null;
        // HTMLソース
        Elements source = null;
        // パターンマッチャー
        Pattern p = Pattern.compile(URL_PAST_PAGE);

        // 一覧ページ取得
        htmlDoc = Jsoup.connect(ComConst.URL_PAST_LOTO6).userAgent("Mozilla").get();
        // HTMLソース取得
        source = htmlDoc.select(TAG_URL_LIST);
        // HTML解析処理
        for ( Element ele : source ) {
            for ( Attribute attr : ele.attributes() ) {
                if ( attr.getKey().equals(TAG_ATTR_HREF) && p.matcher(attr.getValue()).find() ) {
                    res.add(attr.getValue());
                }
            }
        }
        return res;
    }

    /**
     * TR要素取得メソッド
     * @param aTd
     * @return
     */
    private Loto6Dao getTableData(Elements aTd) {
        Loto6Dao res = null;
        try {
            if ( !cu.isEmpty(aTd.get(1).text()) ) {
                res = new Loto6Dao();
                res.setTimes(Integer.valueOf(aTd.get(0).text().replaceAll("ロト6 第", "").replaceAll("回", "")));
                res.setDay(excludeUnnecessaryChar(aTd.get(1).text()));
                res.setNum1(excludeUnnecessaryChar(aTd.get(2).text()));
                res.setNum2(excludeUnnecessaryChar(aTd.get(3).text()));
                res.setNum3(excludeUnnecessaryChar(aTd.get(4).text()));
                res.setNum4(excludeUnnecessaryChar(aTd.get(5).text()));
                res.setNum5(excludeUnnecessaryChar(aTd.get(6).text()));
                res.setNum6(excludeUnnecessaryChar(aTd.get(7).text()));
                res.setBonus(excludeUnnecessaryChar(aTd.get(8).text()));
                res.setClass1(Integer.valueOf(excludeUnnecessaryChar(aTd.get(9).text())));
                res.setMoney1(Long.valueOf(excludeUnnecessaryChar(aTd.get(10).text())));
                res.setClass2(Integer.valueOf(excludeUnnecessaryChar(aTd.get(11).text())));
                res.setMoney2(Long.valueOf(excludeUnnecessaryChar(aTd.get(12).text())));
                res.setClass3(Integer.valueOf(excludeUnnecessaryChar(aTd.get(13).text())));
                res.setMoney3(Long.valueOf(excludeUnnecessaryChar(aTd.get(14).text())));
                res.setClass4(Integer.valueOf(excludeUnnecessaryChar(aTd.get(15).text())));
                res.setMoney4(Long.valueOf(excludeUnnecessaryChar(aTd.get(16).text())));
                res.setClass5(Integer.valueOf(excludeUnnecessaryChar(aTd.get(17).text())));
                res.setMoney5(Long.valueOf(excludeUnnecessaryChar(aTd.get(18).text())));
                res.setCarryOver(Long.valueOf(excludeUnnecessaryChar(aTd.get(19).text())));
            }
        } catch (Exception e) {
            System.out.println(res.getTimes());
            throw e;
        }
        return res;
    }

    /**
     * 不要記号除外メソッド
     * @param aText
     * @return
     */
    private String excludeUnnecessaryChar(String aText) {
        String tmpText = aText;
        tmpText = tmpText.replaceAll(",", "");
        tmpText = tmpText.replaceAll("\\.", "");
        return tmpText;
    }
}

