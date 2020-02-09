import java.util.Scanner;

class ConsoleController {
    private final String INVITATION_MASSAGE = "Try to guess a number";
    private final String RANGE_INFO = "Now number in range" + "not including range limits ";
    private final String ANSWER_ON_INCORRECT_INPUT_MASSAGE = "Your input is incorrect, try again";
    private final String INVITATION_TO_NEXT_MOVE_ASSUMPTION_MORE = "conceived number more than your offer";
    private final String INVITATION_TO_NEXT_MOVE_ASSUMPTION_LESS = "conceived number less than your offer";
    private final String YOUR_INPUT = "Your choose is ";
    private final String WIN_MASSAGE = "Congratulations you are correct";
    private final String WIN_AMOUNT_MOVES = "amount of your moves is";

    private final GameModel model;
    private final ConsoleView view;

    public ConsoleController(GameModel model, ConsoleView view) {
        this.model = model;
        this.view = view;
        model.setRememberedValue();

    }

    public void start() {
        showInvitation();
        waitForNumber();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForNumber() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        WinStatus winStatus;

        showRangeForUserGuess(model.getMIN_VALUE(), model.getMAX_VALUE());
        if (scanner.hasNext()) {
            try {
                userInput = Integer.parseInt(scanner.nextLine());
                if (userInput > model.getMIN_VALUE() && userInput < model.getMAX_VALUE()) {
                    view.showMassage(YOUR_INPUT + userInput);
                    winStatus = model.isBiggerLessOrCorrect(userInput);
//                    System.out.println("||||"+userInput+winStatus+model.getRememberedValue());
                    showMoveResult(winStatus);
                    changeRangeGuessing(userInput, winStatus);
                } else {
                    view.showMassage(ANSWER_ON_INCORRECT_INPUT_MASSAGE);
                    waitForNumber();
                    return;
                }
                if (!winStatus.equals(WinStatus.CORRECT_ANSWER)) {
                    waitForNumber();
                }
            } catch (Exception e) {
                view.showMassage(ANSWER_ON_INCORRECT_INPUT_MASSAGE);
                waitForNumber();
            }

        }
    }

    private void showInvitation() {
        System.out.println(INVITATION_MASSAGE);
    }

    private void showMoveResult(WinStatus winStatus) {
        if (winStatus.equals(WinStatus.ANSWER_IS_SMALLER)) {
            view.showMassage(INVITATION_TO_NEXT_MOVE_ASSUMPTION_MORE);
        } else if (winStatus.equals(WinStatus.ANSWER_IS_BIGGER)) {
            view.showMassage(INVITATION_TO_NEXT_MOVE_ASSUMPTION_LESS);
        } else {
            showGameResult();
        }
    }

    private void changeRangeGuessing(int userChoose, WinStatus winStatus) {
        if (winStatus.equals(WinStatus.ANSWER_IS_SMALLER)) {
            model.setMIN_VALUE(userChoose);
        } else if (winStatus.equals(WinStatus.ANSWER_IS_BIGGER)) {
            model.setMAX_VALUE(userChoose);
        } else {
            model.setMIN_VALUE(userChoose - 1);
            model.setMAX_VALUE(userChoose + 1);
        }
    }

    private void showRangeForUserGuess(int minValue, int maxValue) {
        view.showMassage(RANGE_INFO + minValue + "-" + maxValue);
    }

    private void showGameResult() {
        view.showMassage(WIN_MASSAGE);
        view.showMassage(WIN_AMOUNT_MOVES + " " + model.getAmountTry());
    }


}
