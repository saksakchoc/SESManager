package com.aiko.ses;
import com.aiko.ses.model.*;
import java.util.*;

public class Main {
    private enum NavigationResult {
        MAIN_MENU,
        REGISTER_ENGINEER,
        SEARCH_ENGINEER,
        ENGINEER_LIST,
        REGISTER_PROJECT,
        SEARCH_PROJECT,
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static final EngineerStore engineerStore = new EngineerStore();
    private static final ProjectStore projectStore = new ProjectStore();
    private static final ParticipationStore participationStore = new ParticipationStore();
    private static final AssignService assignService = new AssignService(participationStore);

    public static void main(String[] args) {
        while (true) {
            System.out.println("========================");
            System.out.println();
            System.out.println("=   エンジニア管理ツール   =");
            System.out.println();
            System.out.println("========================");
            System.out.println();
            System.out.println("- メインメニュー -");
            System.out.println();
            System.out.println("[1] エンジニア登録");
            System.out.println("[2] エンジニア検索");
            System.out.println("[3] 案件登録");
            System.out.println("[4] 案件検索");
            System.out.println("[E] プログラム終了");
            System.out.println();
            System.out.println("メニュー番号を選択してください");
            String input = scanner.nextLine();
            if (Objects.equals(input, "E")) {
                System.out.println("プログラムを終了します");
                return;
            }
            callMainProcess(input);
        }
    }
    private static void callMainProcess(String input) {
        switch (input) {
            case "1": mainFeatureController(NavigationResult.REGISTER_ENGINEER);
                break;
            case "2": mainFeatureController(NavigationResult.SEARCH_ENGINEER);
                break;
            case "3": mainFeatureController(NavigationResult.REGISTER_PROJECT);
                break;
            case "4": mainFeatureController(NavigationResult.SEARCH_PROJECT);
                break;
            default:
                System.out.println();
                System.out.println("***************************");
                System.out.println("1~4の整数か、Eを入力してください");
                System.out.println("***************************");
                System.out.println();
                return;
        }
        System.out.println("メインメニューに戻ります");
    }

    // 基本機能のコントローラー（実装途中；使うときに遷移先を追加）
    private static void mainFeatureController(NavigationResult navi) {
        NavigationResult nextFeaturePage = navi;
        while(true) {
            switch (nextFeaturePage) {
                case ENGINEER_LIST:
                    nextFeaturePage = displayEngineerListScreen(engineerStore.getEngineers());
                    break;
                case REGISTER_ENGINEER:
                    nextFeaturePage = registerEngineer();
                    break;
                case SEARCH_ENGINEER:
                    nextFeaturePage = searchEngineerScreen();
                    break;
                case REGISTER_PROJECT:
                    nextFeaturePage = registerProject();
                    break;
                case SEARCH_PROJECT:
                    nextFeaturePage = searchProject();
                    break;
                default:
                    return;
            }
        }
    }

    private static NavigationResult registerEngineer() {
        System.out.println("エンジニア氏名, 単価, スキルを順番に聞いていきます");
        System.out.println("まずはエンジニア氏名を教えてください");
        // エンジニア名振り分け処理
        String engineerName;
        while (true) {
            String engineerNameInput = scanner.nextLine();
            switch (engineerNameInput) {
                case "":
                    // エラーメッセージ(使い方を表示)
                    System.out.println();
                    System.out.println("***************************");
                    System.out.println("エンジニア氏名を入力してください");
                    System.out.println("***************************");
                    System.out.println();
                    continue; // 無限ループ
                case "M":
                case "B":
                    return NavigationResult.MAIN_MENU;
                default:
                    engineerName = engineerNameInput;
                    break;
            }
            // エンジニア名が入力されたときだけループを抜ける
            break;
        }
        System.out.println("エンジニア名が入力されました: " + engineerName);
        // 単価振り分け処理
        int price;
        while (true) {
            System.out.println("単価を教えてください(1以上の整数)");
            String priceInput = scanner.nextLine();
            if (Objects.equals(priceInput, "M") || Objects.equals(priceInput, "B")) {
                return NavigationResult.MAIN_MENU;
            }
            try {
                int inputNum = Integer.parseInt(priceInput);
                // 正の整数なら単価として処理する
                if (inputNum > 0) {
                    price = inputNum;
                    break;
                }
                System.out.println("1以上の数値を入力してください");
                // 0ならループに戻る
            } catch (NumberFormatException e) {
                // 整数に変換できない場合はループに戻る
                System.out.println("入力できる範囲の整数を入力してください");
            }
        }
        System.out.println("単価が入力されました: " + price + "です");
        // スキル振り分け処理
        List<String> skills = new ArrayList<>();
        System.out.println("スキルを教えてください(カンマ区切り)");
        System.out.println("スキルを登録しない場合は入力無しでOK");
        String skillsInput = scanner.nextLine();
        String skillsListByComma = "";
        if (Objects.equals(skillsInput, "M") || Objects.equals(skillsInput, "B")) {
            System.out.println("メインメニューに戻ります");
            return NavigationResult.MAIN_MENU;
        }
        if (!Objects.equals(skillsInput, "")) {
            // スキルが入力されたたら配列にいれる
            Collections.addAll(skills, skillsInput.split(","));
            if (!skills.isEmpty()) {
                System.out.println("スキルを登録します: ");
                skillsListByComma = String.join(", ", skills);
            } else {
                // カンマのみが入力されたらスキルを登録せず終了
                System.out.println("スキルを登録しません");
            }
        } else {
            // 空文字が入力されたらスキルを登録せず終了
            System.out.println("スキルを登録しません");
        }
        System.out.println("エンジニア登録内容を受け取りました。");
        System.out.println("エンジニア名: " + engineerName);
        System.out.println("目安単価: " + price);
        System.out.println("スキル: "
                + (skills.isEmpty() ? "なし" : skillsListByComma));
        System.out.println("こちらの内容で登録してよろしいですか？");
        if (askForConfirmation()) {
            // 登録メイン処理
            // skillsはStringに変換して渡す
            Engineer engineer = engineerStore.createEngineer(engineerName, price, skills.toArray(new String[0]));
            System.out.println("エンジニアを登録しました。エンジニア詳細画面に移動します");
            return displayEngineerDetails(engineer);
        } else {
            return NavigationResult.REGISTER_ENGINEER;
        }
    }
    private static NavigationResult searchEngineerScreen() {
        final NavigationResult thisScreenNavigationCode = NavigationResult.SEARCH_ENGINEER;
        System.out.println("======== エンジニア検索画面 ========");
        while(true) {
            List<Engineer> engineers = new ArrayList<>();
            System.out.println("検索方法を指定してください。");
            System.out.println("1-> 特殊検索条件指定 / 2-> ID検索 / 3-> 全件表示");
            System.out.println("M-> メインメニュー / B-> メインメニュー");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    engineers = filterEngineers();
                    break;
                case "2":
                    engineers = filterEngineerById();
                    break;
                case "3":
                    engineers = engineerStore.getEngineers();
                    break;
                case "M":
                case "B":
                    // MかBが入力されたらメインメニューに戻る
                    return NavigationResult.MAIN_MENU;
                default:
                    continue;
            }
            NavigationResult navigationResult = displayEngineerListScreen(engineers);
            if (navigationResult != thisScreenNavigationCode) {
                return navigationResult;
            }
        }
    }

    private static NavigationResult displayEngineerListScreen(List<Engineer> engineers) {
        final NavigationResult thisScreenNavigationCode = NavigationResult.ENGINEER_LIST;

        System.out.println("======== エンジニア一覧画面 ========");
        int engineerListSize = engineers.size();

        if (engineerListSize == 0) {
            System.out.println("該当するエンジニアが見つかりません");
            return NavigationResult.SEARCH_ENGINEER;
        }

        while(true) {
            System.out.println("閲覧したいエンジニアの行番号を選択してください。");
            System.out.println("M-> メインメニュー / B-> エンジニア検索画面");
            printEngineerList(engineers);
            String input = scanner.nextLine();
            switch (input) {
                case "M":
                    return NavigationResult.MAIN_MENU;
                case "B":
                    // MかBが入力されたらメインメニューに戻る
                    return NavigationResult.SEARCH_ENGINEER;
                default:
                    try {
                        int rowNumber = Integer.parseInt(input);
                        if (rowNumber <= 0 || rowNumber > engineerListSize) {
                            throw new IllegalArgumentException("入力された数値の範囲が、エンジニアの人数より多い、または0以下です");
                        }
                        Engineer selectedEngineer = engineers.get(rowNumber - 1);
                        NavigationResult navigationResult = displayEngineerDetails(selectedEngineer);
                        if (navigationResult != thisScreenNavigationCode) {
                            return navigationResult;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("行番号(1～" + engineerListSize + "の整数、またはM/Bを入力してください");
                    }
            }
        }
    }
    private static void printEngineerList(List<Engineer> engineers) {
        for (int i=0; i<engineers.size(); i++) {
            Engineer engineer = engineers.get(i);
            System.out.print(i+1 + " : ");
            System.out.print(engineer.getName());
            System.out.println();
        }
    }
    private static List<Engineer> filterEngineerById() {
        while(true) {
            System.out.println("エンジニアIDを入力してください。");
            String input = scanner.nextLine();
            try {
                int engineerId = Integer.parseInt(input);
                Engineer engineer = engineerStore.getEngineer(engineerId);
                if (engineer == null) {
                    throw new IllegalArgumentException("入力されたIDに該当するエンジニアがいません。");
                }
                List<Engineer> engineers = new ArrayList<>();
                engineers.add(engineer);
                return engineers;
            } catch (NumberFormatException e) {
                // 整数に変換できない場合はループに戻る
                System.out.println("入力できる範囲の整数を入力してください。");
            } catch (IllegalArgumentException e) {
                System.out.println("入力されたIDに該当するエンジニアがいません。");
            }
        }
    }
    private static List<Engineer> filterEngineers() {
        while(true) {
            System.out.println("検索方法を指定してください");
            System.out.println("M-> メインメニュー / B-> メインメニュー");
            String input = scanner.nextLine();
            switch (input) {
                case "M":
                case "B":
                    // MかBが入力されたらほんとうはメインメニューに戻るんだけど、どうやってNavigationResultを返すか相談したい。返り値を2つもたせる？
                default:
                    return engineerStore.getEngineers();
            }
        }
    }
    private static NavigationResult displayEngineerDetails(Engineer engineer) {
        System.out.println("========================");
        System.out.println();
        System.out.println("=    エンジニア詳細画面　  =");
        System.out.println();
        System.out.println("========================");
        System.out.println();

        List<String> skills = engineer.getSkills();
        String skillsListByComma = skills.isEmpty() ? "なし" : String.join(", ", skills);

        System.out.println("========================");
        System.out.println("　　　　エンジニア情報");
        System.out.println("========================");
        System.out.println("エンジニアID: " + engineer.getId());
        System.out.println("エンジニア名: " + engineer.getName());
        System.out.println("単価　　　　: " + engineer.getPrice());
        System.out.println("スキル　　　: " + skillsListByComma);
        System.out.println("========================");
        System.out.println("　　　参画中案件情報");
        System.out.println("========================");
        List<Participation> participations =
                assignService.getEngineerParticipation(engineer).stream()
                        .sorted(Comparator.comparing(Participation::getStartDate))
                        .toList();
        if (participations.isEmpty()) {
            System.out.println("参画中の案件がありません");
        }
        participations.forEach(participation -> {
            Project project = participation.getProject();
            System.out.println("【 案件名: " + project.getName() + " 】");
            System.out.println("　　案件ID: " + project.getId());
            System.out.println("　　契約単価: " + participation.getContractPrice());
            System.out.println("　　開始日　: " + participation.getStartDate());
            System.out.println("　　終了日　: " + participation.getEndDate());
            System.out.println();
        });
        // 入力を受け付ける
        while (true) {
            // 使い方を表示
            System.out.println();
            System.out.println("***************************");
            System.out.println("M: メインメニュー / B: エンジニア一覧画面");
            System.out.println("***************************");
            System.out.println();

            String input = scanner.nextLine();
            switch (input) {
                case "M":
                    return NavigationResult.MAIN_MENU;
                case "B":
                    return NavigationResult.ENGINEER_LIST;
                default:
            }
        }
    }
    private static NavigationResult registerProject() {
        System.out.println("======== 案件登録画面 ========");
        while(true) {
            System.out.println("M-> メインメニュー / B-> メインメニュー");
            String input = scanner.nextLine();
            switch (input) {
                case "M":
                case "B":
                    // MかBが入力されたらメインメニューに戻る
                    return NavigationResult.MAIN_MENU;
                default:
            }
        }
    }
    private static NavigationResult searchProject() {
        System.out.println("======== 案件検索画面 ========");
        while(true) {
            System.out.println("M-> メインメニュー / B-> メインメニュー");
            String input = scanner.nextLine();
            switch (input) {
                case "M":
                case "B":
                    // MかBが入力されたらメインメニューに戻る
                    return NavigationResult.MAIN_MENU;
                default:
            }
        }
    }
    private static boolean askForConfirmation() {
        while (true) {
            // 確認画面
            System.out.println();
            System.out.println("***************************");
            System.out.println("YまたはNを入力してください(Y: 登録する / N: 登録しない");
            System.out.println("***************************");
            System.out.println();

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
            }
        }
    }
}