public class Main {

    public static void main(String[] args) {
        // write your code here
        ConsoleView view = new ConsoleView();
        GameModel model = new GameModel(0,100);
        ConsoleController controller = new ConsoleController(model, view);
        controller.start();
    }
}
