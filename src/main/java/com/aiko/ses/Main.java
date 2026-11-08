package com.aiko.ses;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("========================");
            System.out.println("");
            System.out.println("=   エンジニア管理ツール   =");
            System.out.println("");
            System.out.println("========================");
            System.out.println("");
            System.out.println("- メインメニュー -");
            System.out.println("");
            System.out.println("[1] エンジニア登録");
            System.out.println("[2] エンジニア検索");
            System.out.println("[3] 案件登録");
            System.out.println("[4] 案件検索");
            System.out.println("[E] プログラム終了");
            System.out.println("");
            System.out.println("メニュー番号を選択してください。");
            input = scanner.nextLine();
            if (input.equals("E")) {
                break;
            }
            int menuId;
            try {
                menuId = Integer.parseInt(input);
                if (menuId > 0 && menuId <= 4) {
                    break;
                }
            } catch(NumberFormatException e) {

            }
            System.out.println("");
            System.out.println("***************************");
            System.out.println("1~4の整数か、Eを入力してください");
            System.out.println("***************************");
            System.out.println("");

        }
        System.out.println("メニュー" + input + "が選択されました" );

    }
}