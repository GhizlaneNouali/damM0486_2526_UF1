import Model.EscripuraParaula;
import Model.LecturaParaula;
import Model.ModificarParaula;
import Model.Paraula;
import Model.ParaulaModel;
import Model.User;
import View.LoginView;
import java.io.*;
import java.util.*;

public class PenjatController {
    private LoginView view;
    private User usuari;

    public PenjatController(LoginView view, User usuari) {
        this.view = view;
        this.usuari = usuari;
    }

    // Iniciem 
    public void start() {
        int punts = llegirPuntuacio();
        view.mostrarMissatge("Benvingut, " + usuari.getNom() + "! Tens " + punts + " punts guardats.");

        boolean sortir = false;

        while (!sortir) {
            String opcio = view.gestionarMenuSecundari();

            switch (opcio) {
                case "1":
                    if (usuari.admin) {
                        afegirParaules();
                    } else {
                        view.mostrarMissatge("Només els administradors poden afegir paraules!");
                    }
                    break;
                case "2":
                    if (usuari.admin) {
                        editarParaula();
                    } else {
                        view.mostrarMissatge("Només els administradors poden editar paraules!");
                    }
                    break;
                case "3":
                    if (usuari.admin) {
                        llistarParaula();
                    } else {
                        view.mostrarMissatge("Només els administradors poden llistar paraules!");
                    }
                    break;
                case "4":
                    jugar();
                    break;
                case "0":
                    sortir = true;
                    break;
                default:
                    view.mostrarMissatge("Opció no vàlida.");
            }
        }
    }

    // Afegim paraules
    private void afegirParaules() {
        List<Paraula> llista = new ArrayList<>();

        String text = view.demanarText("Introdueix una paraula (o 'sortir' per acabar):");
        while (!text.equalsIgnoreCase("sortir")) {
            int puntuacio = Integer.parseInt(view.demanarText("Introdueix la puntuació d’aquesta paraula:"));
            llista.add(new Paraula(text, puntuacio));
            text = view.demanarText("Introdueix una altra paraula (o 'sortir'):");
        }
    
        if (llista.isEmpty()) {
            view.mostrarMissatge("No s’han afegit paraules.");
            return;
        }
    
        // Convertim la llista a array per passar-la al mètode
        Paraula[] array = llista.toArray(new Paraula[0]);
    
        // Cridem la classe d’escriptura binària
        EscripuraParaula escriptor = new EscripuraParaula();
        escriptor.escriureParaules(array);
    
        view.mostrarMissatge("Les paraules s’han guardat correctament ");
    }

    private void guardarPuntuacio(int punts) {
        try (RandomAccessFile fitxer = new RandomAccessFile("config.bin", "rw")) {
            fitxer.setLength(0);           
            fitxer.writeInt(100);           
            fitxer.writeInt(punts);
            view.mostrarMissatge("S’han guardat " + punts + " punts");
        } catch (IOException e) {
            view.mostrarMissatge("Error guardant la puntuació: " + e.getMessage());
        }
    }

    private int llegirPuntuacio() {
        try (RandomAccessFile fitxer = new RandomAccessFile("config.bin", "r")) {
            int versio = fitxer.readInt();
            int punts = fitxer.readInt();
            System.out.println("Versió: " + (versio / 100.0)); 
            return punts;
        } catch (IOException e) {
            System.out.println("No hi ha puntuació guardada, començant amb 0");
            return 0;
        }
    }
    
    // jugar
    private void jugar() {
        LecturaParaula lector = new LecturaParaula();
        List<Paraula> llista = lector.llegirParaules();

        if (llista.isEmpty()) {
            view.mostrarMissatge("No hi ha paraules disponibles per jugar.");
            return;
        }

        // Escollim una paraula aleatòria
        Random random = new Random();
        Paraula p = llista.get(random.nextInt(llista.size()));
        String paraula = p.getParaula();

        char[] paraulaArray = paraula.toCharArray();
        Set<Character> lletresEncertades = new HashSet<>();
        Set<Character> lletresErronies = new HashSet<>();
        int intentsRestants = 10;
        boolean guanyat = false;

        view.mostrarMissatge("\nComença el joc del PENJAT!");
        view.mostrarMissatge("La paraula té " + paraula.length() + " lletres.");

        while (intentsRestants > 0 && !guanyat) {
            StringBuilder estat = new StringBuilder();
            for (char c : paraulaArray) {
                if (lletresEncertades.contains(c)) {
                    estat.append(c).append(" ");
                } else {
                    estat.append("_ ");
                }
            }

            view.mostrarMissatge("\nParaula: " + estat.toString());
            view.mostrarMissatge("Lletres errònies: " + lletresErronies);
            view.mostrarMissatge("Intents restants: " + intentsRestants);

            String entrada = view.demanarText("Introdueix una lletra: ").toLowerCase();
            if (entrada.length() != 1) {
                view.mostrarMissatge("Has d’introduir una lletra");
                continue;
            }

            char lletra = entrada.charAt(0);

            if (lletresEncertades.contains(lletra) || lletresErronies.contains(lletra)) {
                view.mostrarMissatge("Ja has provat aquesta lletra");
                continue;
            }

            if (paraula.indexOf(lletra) >= 0) {
                lletresEncertades.add(lletra);
                view.mostrarMissatge("Correcte!");
            } else {
                lletresErronies.add(lletra);
                intentsRestants--;
                view.mostrarMissatge("Incorrecte!");
            }

            guanyat = true;
            for (char c : paraulaArray) {
                if (!lletresEncertades.contains(c)) {
                    guanyat = false;
                    break;
                }
            }
        }

        if (guanyat) {
            view.mostrarMissatge("\nHas guanyat! La paraula era: " + paraula);
            guardarPuntuacio(p.getPuntuacio());
        } else {
            view.mostrarMissatge("\nHas perdut! La paraula era: " + paraula);
        }
    }

    // editar paraula
    private void editarParaula() {
        ModificarParaula mp = new ModificarParaula();
        List<Paraula> llista = mp.llegirParaules();

        if (llista.isEmpty()) {
            view.mostrarMissatge("No hi ha paraules per editar");
            return;
        }

        for (int i = 0; i < llista.size(); i++) {
            Paraula p = llista.get(i);
            view.mostrarMissatge(i + ": " + p.toStrings());
        }

        try {
            int index = Integer.parseInt(view.demanarText("Introdueix l'índex de la paraula a modificar:"));
            if (index < 0 || index >= llista.size()) {
                view.mostrarMissatge("Err. Índex invàlid");
                return;
            }
    
            String novaParaula = view.demanarText("Nova paraula:");
            String novaPuntuacio = view.demanarText("Nova puntuació:");

            ParaulaModel.validarParaula(novaParaula);

            int punts;
            try {
                punts = Integer.parseInt(novaPuntuacio);
            } catch (NumberFormatException e) {
                view.mostrarMissatge("La puntuació ha de ser un número enter");
                return;
            }

            ParaulaModel.validarPuntuacio(punts);
            mp.modificarParaula(index, novaParaula, punts);
            view.mostrarMissatge("Paraula modificada correctament!");
            
        } catch (NumberFormatException e) {
            view.mostrarMissatge("Has d'introduir un nombre vàlid per l'índex");
        } catch (IllegalArgumentException e) {
            view.mostrarMissatge(e.getMessage());
        }
    }

    // llistar paraula
    private void llistarParaula(){
        LecturaParaula lector = new LecturaParaula();
        List<Paraula> llista = lector.llegirParaules();

        if(llista.isEmpty()){
            view.mostrarMissatge("No hi ha paraules registrades");
            return;
        }

        view.mostrarMissatge("\nLlistat de paraules guardades");
        for (Paraula p: llista){
            view.mostrarMissatge(p.toStrings());
        }

    }


}