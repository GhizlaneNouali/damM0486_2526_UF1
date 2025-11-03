package Model;

import java.io.*;

public class UserModel {

    public void guardarUsuari(User u) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(u.user + ".usr"))) {
            oos.writeObject(u);
        }
    }

    public User carregarUsuari(String username) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username + ".usr"))) {
            return (User) ois.readObject();
        }
    }

    public boolean existeixUsuari(String username) {
        File f = new File(username + ".usr");
        return f.exists();
    }
}
