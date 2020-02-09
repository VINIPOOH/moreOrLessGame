import java.util.Scanner;

public class ConsoleController {
    private final String INVITATION_MASSAGE = "Try to guess a number";
    private final String RANGE_INFO = "Now number in range ";
    private final String ANSWER_ON_INCORRECT_INPUT_MASSAGE = "Your input is incorrect, try again";
    private final String INVITATION_TO_NEXT_MOVE_ASSUMPTION_MORE = "conceived number more than your offer";
    private final String INVITATION_TO_NEXT_MOVE_ASSUMPTION_LESS = "conceived number less than your offer";
    private final String YOUR_INPUT = "Your choose is ";
    private final String WIN_MASSAGE = "Congratulations you are correct";
    private final String WIN_AMOUNT_MOVES = "amount of your moves is";

    private GameModel model;
    private ConsoleView view;
    private int currentMinNumber;
    private int currentMaxNumber;

    public ConsoleController(GameModel model, ConsoleView view) {
        this.model = model;
        this.view = view;
        this.currentMinNumber = model.getMIN_VALUE();
        this.currentMaxNumber = model.getMAX_VALUE();
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
        WinStatus winStatus = null;

        showRangeForUserGuess(currentMinNumber, currentMaxNumber);
        if (scanner.hasNext()) {
            try {
                userInput = Integer.parseInt(scanner.nextLine());
                if (userInput >= currentMinNumber && userInput <= currentMaxNumber) {
                    view.showMassage(YOUR_INPUT + userInput);
                    winStatus = model.isBiggerLessOrCorrect(userInput);
                    showMoveResult(winStatus);
                    changeRangeGuessing(userInput, winStatus);

                } else {
                    view.showMassage(ANSWER_ON_INCORRECT_INPUT_MASSAGE);

                }
                if (!winStatus.equals(WinStatus.CORRECT_ANSWER)) {
                    waitForNumber();
                    return;
                }
            } catch (Exception e) {
                view.showMassage(ANSWER_ON_INCORRECT_INPUT_MASSAGE);
            }

        }
    }

    private void showInvitation() {
        System.out.println(INVITATION_MASSAGE);
    }

    private void showMoveResult(WinStatus winStatus) {
        if (winStatus.equals(WinStatus.LESS_ANSWER)) {
            view.showMassage(INVITATION_TO_NEXT_MOVE_ASSUMPTION_MORE);
        } else if (winStatus.equals(WinStatus.BIGGER_ANSWER)) {
            view.showMassage(INVITATION_TO_NEXT_MOVE_ASSUMPTION_LESS);
        } else {
            showGameResult();
        }
    }

    private void changeRangeGuessing(int userChoose, WinStatus winStatus) {
        if (winStatus.equals(WinStatus.LESS_ANSWER)) {
            currentMinNumber = userChoose;
        } else if (winStatus.equals(WinStatus.BIGGER_ANSWER)) {
            currentMaxNumber = userChoose;
        } else {
            currentMinNumber = userChoose;
            currentMaxNumber = userChoose;
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
