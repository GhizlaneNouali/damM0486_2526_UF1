

import java.util.Scanner;

public class LoginView {
    private Scanner sc = new Scanner(System.in);

    public String demanarOpcio() {
        return sc.nextLine();
    }

    public String demanarText(String missatge) {
        System.out.println(missatge);
        return sc.nextLine();
    }

    public void mostrarMissatge(String missatge) {
        System.out.println(missatge);
    }
}
