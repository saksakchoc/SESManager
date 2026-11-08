package com.aiko.ses;
import com.aiko.ses.model.EngineerStore;

import java.util.*;

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
                    continue; // 無限ループ
                case "M":
                    System.out.println("メインメニューに戻ります");
                    return;
                default:
                    engineerName = input;
                    break;
            }
            // エンジニア名が入力されたときだけループを抜ける
            break;
        }
        System.out.println("エンジニア名が入力されました: " + engineerName + "です");
        // 単価振り分け処理
        int price;
        while (true) {
            System.out.println("単価を教えてください(1以上の整数)");
            String input = scanner.nextLine();
            if (Objects.equals(input, "M")) {
                    System.out.println("メインメニューに戻ります");
                    return;
            }
            try {
                int inputNum = Integer.parseInt(input);
                // 正の整数なら単価として処理する
                if (inputNum > 0) {
                    price = inputNum;
                    break;
                }
                // 0ならループに戻る
            } catch (NumberFormatException e) {
                // 整数に変換できない場合はループに戻る
            }
        }
        System.out.println("単価が入力されました: " + price + "です");
        // スキル振り分け処理
        List<String> skills = new ArrayList<>();
        while (true) {
            System.out.println("スキルを教えてください(カンマ区切り)");
            System.out.println("スキルを登録しない場合は入力無しでOK");
            String input = scanner.nextLine();
            if (Objects.equals(input, "M")) {
                System.out.println("メインメニューに戻ります");
                return;
            }
            if (!Objects.equals(input, "")) {
                // スキルが入力されたたら配列にいれる
                Collections.addAll(skills, input.split("\\s+"));
                if (skills.size() > 1) {
                    System.out.println("スキルを登録します: ");
                    for (int i = 0; i < skills.size(); i++) {
                        System.out.print(skills.get(i));
                        if (i < skills.size() - 1) {
                            System.out.print(",");
                        } else {
                            System.out.println("");
                        }
                    }
                }
                break;
            }
            // 空文字が入力された、もしくはカンマのみが入力されたらスキルを登録せず終了
            System.out.println("スキルを登録しません");
        }
        EngineerStore engineerStore = new EngineerStore();
        // いまさらだが、skillsはListで渡したほうがよかったか？
        engineerStore.createEngineer(engineerName, price, String.valueOf(skills));
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