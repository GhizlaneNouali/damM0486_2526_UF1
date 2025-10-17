import java.io.*;

import Controller.PenjatController;
import Model.User;
import View.LoginView;


public class UserController {
    private LoginView view;

    public UserController(LoginView view) {
        this.view = view;
    }

    // Inici
    public void start() {
        view.mostrarMissatge("\n--- MENÚ PRINCIPAL ---");
        view.mostrarMissatge("1 - Login");
        view.mostrarMissatge("2 - Registre");
        String opcio = view.demanarOpcio();
        String user = view.demanarText("Introdueix el nom d'usuari:");
        String nom_arxiu = user + ".usr";
        File arxiu = new File(nom_arxiu);

        // Login
        if (opcio.equals("1")) {
            if (arxiu.exists()) {
                loginUser(arxiu);
            } else {
                String resposta = view.demanarText("No s'ha trobat l'usuari, vols registrar-te? (si/no)");
                if (resposta.equalsIgnoreCase("si")) {
                    registerUser(arxiu, user);
                } else {
                    view.mostrarMissatge("Operació cancel·lada.");
                }
            }
        } else if (opcio.equals("2")) {
            registerUser(arxiu, user);
        } else {
            view.mostrarMissatge("Opció no vàlida.");
        }
    }

    private void loginUser(File arxiu) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arxiu))) {
            User u = (User) ois.readObject();
            String password = view.demanarText("Hola, " + u.nom + ". Introdueix la teva contrasenya:");

            if (u.pass.equals(password)) {
                view.mostrarMissatge("Accés correcte al sistema");
                PenjatController joc = new PenjatController(view, u);
                joc.start();
            } else {
                view.mostrarMissatge("Contrasenya incorrecta");
            }
        } catch (IOException | ClassNotFoundException e) {
            view.mostrarMissatge("Error en llegir l'arxiu d'usuari: " + e.getMessage());
        }
    }

    // Register
    private void registerUser(File arxiu, String user) {
        String nom = view.demanarText("Introdueix el teu nom");
        Integer punts = 0;
        Boolean admin = false;
        String password = view.demanarText("Introdueix la contrasenya:");


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arxiu))) {
            User usr = new User(user, password, nom, admin, punts);
            oos.writeObject(usr);
            view.mostrarMissatge("Usuari creat correctament!\nBenvingut, " + usr.nom);
        } catch (IOException e) {
            view.mostrarMissatge("Error en crear l'usuari: " + e.getMessage());
        }
    }
}