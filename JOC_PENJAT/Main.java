
public class Main {
    public static void main(String[] args) {
        LoginView view = new LoginView();
        UserController controller = new UserController(view);
        controller.start();
    }
}
