import Model.User;
import Model.UserModel;
import View.LoginView;

public class UserController {
    private LoginView view;
    private UserModel model;

    public UserController(LoginView view) {
        this.view = view;
        this.model = new UserModel();
    }

    public void start() {
        boolean sortir = false;

        while (!sortir) {
           String opcio = view.gestionarMenuPrincipal();

            switch (opcio) {
                case "1": login(); break;
                case "2": register(); break;
                case "0":
                    sortir = true;
                    view.mostrarMissatge("Sortint del programa...");
                    break;
                default:
                    view.mostrarMissatge("Opció no vàlida.");
            }
        }
    }

    private void login() {
        String username = view.demanarText("Nom d'usuari:");
        if (!model.existeixUsuari(username)) {
            view.mostrarMissatge("Usuari no trobat.");
            return;
        }

        try {
            User u = model.carregarUsuari(username);
            String pass = view.demanarText("Contrasenya:");

            if (u.pass.equals(pass)) {
                PenjatController joc = new PenjatController(view, u);
                joc.start();
            } else {
                view.mostrarMissatge("Contrasenya incorrecta.");
            }
        } catch (Exception e) {
            view.mostrarMissatge("Error en el login: " + e.getMessage());
        }
    }

    private void register() {
        String username = view.demanarText("Nom d'usuari:");
        if (model.existeixUsuari(username)) {
            view.mostrarMissatge("Aquest usuari ja existeix.");
            return;
        }

        String nom = view.demanarText("Nom complet:");
        String pass = view.demanarText("Contrasenya:");

        User nou = new User(username, pass, nom, false, 0);

        try {
            model.guardarUsuari(nou);
            view.mostrarMissatge("Usuari creat correctament!");
            PenjatController joc = new PenjatController(view, nou);
            joc.start();
        } catch (Exception e) {
            view.mostrarMissatge("Error en el registre: " + e.getMessage());
        }
    }
}
