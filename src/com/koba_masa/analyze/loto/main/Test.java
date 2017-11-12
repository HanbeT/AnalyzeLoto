package com.koba_masa.analyze.loto.main;

import java.io.IOException;

import com.koba_masa.analyze.loto.logic.LogicAnalyzeLoto6Past;

public class Test {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        LogicAnalyzeLoto6Past a6p = new LogicAnalyzeLoto6Past();
        try {
            a6p.mainLogic();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

}
