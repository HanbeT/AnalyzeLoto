package com.koba_masa.analyze.loto.util;

public class ComUtil {

    /**
    * 空文字チェックメソッド
    * @param aParam：文字列
    * @return 処理結果(true：空/false：!空)
    */
    public boolean isEmpty(String aParam) {
        return "".equals(aParam) || aParam == null;
    }

}
