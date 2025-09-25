package ex41;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ex41 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String user;
        String password;
        String resposta;

        System.out.println("Introdueix el username: ");
        user = sc.nextLine();

        String nom_arxiu = user + ".usr";
        File arxiu = new File(nom_arxiu);
        if (arxiu.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nom_arxiu)));
                User u = (User) ois.readObject();
                System.out.println("Hola, " + u.nom + " introdueix la teva contrasenya: ");
                password = sc.nextLine();

                if (u.pass.equals(password)){
                    System.out.println("Accés correcte al sistema");
                } else {
                    System.out.println("Accés no concedit: La contrasenya no és correcte");
                }       
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No s'ha trobat l'usuari, vols registrar-te? ");
            resposta = sc.nextLine();

            if (resposta.equals("si")){
                System.out.println("Introdueix la contrasenya ");
                password = sc.nextLine();

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(nom_arxiu)));
                    User usr = new User(user, password);
                    oos.writeObject(usr);
                    System.out.println("Usuari creat correctament!\nBenvingut! " + usr.nom);
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("--");
            }
        }
    }
}
