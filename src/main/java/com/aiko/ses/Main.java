package com.aiko.ses;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
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
                System.out.println("メニュー番号を選択してください");
                String input = scanner.nextLine();
                if (Objects.equals(input, "E")) {
                    System.out.println("プログラムを終了します");
                    return;
                }
                callMainProcess(input, scanner);
            }
        }
    }
    private static void callMainProcess(String input, Scanner scanner) {
        switch (input) {
            case "1": registerEngineer(scanner);
                break;
            case "2": searchEngineer(scanner);
                break;
            case "3": registerProject(scanner);
                break;
            case "4": searchProject(scanner);
                break;
            default:
                System.out.println("");
                System.out.println("***************************");
                System.out.println("1~4の整数か、Eを入力してください");
                System.out.println("***************************");
                System.out.println("");
                break;
        }
    }
    private static void registerEngineer(Scanner scanner) {
        System.out.println("エンジニア氏名, 単価, スキルを順番に聞いていきます");
        System.out.println("まずはエンジニア氏名を教えてください");
        // エンジニア名振り分け処理
        String engineerName;
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "":
                    // エラーメッセージ(使い方を表示)
                    System.out.println("");
                    System.out.println("***************************");
                    System.out.println("エンジニア氏名を入力してください");
                    System.out.println("***************************");
                    System.out.println("");
                    continue;
                case "M":
                    System.out.println("メインメニューに戻ります");
                    return;
                default:
                    engineerName = input;
                    break;
            }
            break; // 本当はエラーのときは無限ループにしたい
        }
        System.out.println(engineerName);
    }
    private static void searchEngineer(Scanner scanner) {
        System.out.println("エンジニア検索処理を関数で呼び出します");
    }
    private static void registerProject(Scanner scanner) {
        System.out.println("案件登録処理を関数で呼び出します");
    }
    private static void searchProject(Scanner scanner) {
        System.out.println("案件検索処理を関数で呼び出します");
    }
}