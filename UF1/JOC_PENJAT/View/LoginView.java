

import java.util.Scanner;

public class LoginView {
    private Scanner sc = new Scanner(System.in);

    public String gestionarMenuPrincipal(){
        System.out.println("\n--- MENÚ PRINCIPAL ---\n1 - Login\n2 - Registre\n0 - Sortir");
        String opcio = sc.nextLine();
        return opcio;   
        
    }

    public String gestionarMenuSecundari(){
        System.out.println("\n--- PENJAT ---\n1 - Afegir paraules (només admin)\n2 - Editar paraula (només admin)\n3 - Llistar paraules (només admin)\n4 - Jugar\n0 - Sortir");
        String opcio = sc.nextLine();
        return opcio;   
        
    }

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
