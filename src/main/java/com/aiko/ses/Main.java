package com.aiko.ses;
import com.aiko.ses.model.EngineerStore;
import com.aiko.ses.model.ParticipationStore;
import com.aiko.ses.model.ProjectStore;

import java.util.*;

public class Main {
    private static final EngineerStore engineerStore = new EngineerStore();
    private static final ProjectStore projectStore = new ProjectStore();
    private static final ParticipationStore participationStore = new ParticipationStore();

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
            String engineerNameInput = scanner.nextLine();
            switch (engineerNameInput) {
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
                    engineerName = engineerNameInput;
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
            String priceInput = scanner.nextLine();
            if (Objects.equals(priceInput, "M")) {
                    System.out.println("メインメニューに戻ります");
                    return;
            }
            try {
                int inputNum = Integer.parseInt(priceInput);
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
        System.out.println("スキルを教えてください(カンマ区切り)");
        System.out.println("スキルを登録しない場合は入力無しでOK");
        String skillsInput = scanner.nextLine();
        if (Objects.equals(skillsInput, "M")) {
            System.out.println("メインメニューに戻ります");
            return;
        }
        if (!Objects.equals(skillsInput, "")) {
            // スキルが入力されたたら配列にいれる
            Collections.addAll(skills, skillsInput.split(","));
            if (!skills.isEmpty()) {
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
        }
        // 空文字が入力された、もしくはカンマのみが入力されたらスキルを登録せず終了
        System.out.println("スキルを登録しません");

        System.out.println("エンジニア登録内容を受け取りました。");
        System.out.println("エンジニア名: " + engineerName);
        System.out.println("目安単価: " + price);

        StringBuilder outputSkillString = new StringBuilder();
        if (skills.isEmpty()) {
            outputSkillString = new StringBuilder("なし");
        } else {
            for (String skill : skills) {
                outputSkillString.append(skill);
            }
        }

        System.out.println("スキル: " + outputSkillString);
        System.out.println("こちらの内容で登録してよろしいですか？");
        if (askForConfirmation(scanner)) {
            // 登録メイン処理
            // skillsはStringに変換して渡す
            engineerStore.createEngineer(engineerName, price, skills.toString());
            System.out.println("エンジニアを登録しました。エンジニア詳細画面に移動します");
        } else {
            // ここでもう一度エンジニア登録処理を呼び出したい。全体をループで囲うべき？
        }
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
    private static boolean askForConfirmation(Scanner scanner) {
        System.out.println("Y/Nを入力してください(Y: OK / N: キャンセル");
        // 確認画面
        while (true) {
            String input = scanner.nextLine(); // skillの登録時にinputっていう変数定義しちゃってるからここで使えないのなんか気持ち悪い
            switch (input) {
                case "Y":
                    // Yが入力されたらtrueを返す
                    return true;
                case "N":
                    System.out.println("登録をキャンセルしました");
                    // Nが入力されたらfalseを返す
                    return false;
                default:
                    // エラーメッセージ(使い方を表示)
                    System.out.println("");
                    System.out.println("***************************");
                    System.out.println("YまたはNを入力してください(Y: 登録する / N: 登録しない");
                    System.out.println("***************************");
                    System.out.println("");
            }
        }
    }
}