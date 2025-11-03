package Model;
import java.io.Serializable;

public class User implements Serializable {
    public String nom;
    public String user;
    public String pass;
    public boolean admin;
    public Integer punts;

    public User(String user, String pass, String nom, boolean admin, Integer punts) {
        this.nom = nom;
        this.pass = pass;
        this.user = user;
        this.admin = admin;
        this.punts = punts;
    }
    public String getNom(){
        return nom;
    }


    

}
	
