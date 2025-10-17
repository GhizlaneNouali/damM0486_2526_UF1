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
        boolean sortir = false;

        while (!sortir) {
            view.mostrarMissatge("\n--- MENÚ PRINCIPAL ---");
            view.mostrarMissatge("1 - Afegir paraules (només admin)");
            view.mostrarMissatge("2 - Jugar");
            view.mostrarMissatge("3 - Sortir");

            String opcio = view.demanarText("Tria una opció:");

            switch (opcio) {
                case "1":
                    if (usuari.admin) {
                        afegirParaules();
                    } else {
                        view.mostrarMissatge("Només els administradors poden afegir paraules!");
                    }
                    break;
                case "2":
                    jugar();
                    break;
                case "3":
                    sortir = true;
                    break;
                default:
                    view.mostrarMissatge("Opció no vàlida.");
            }
        }
    }

    // Afegim paraules
    private void afegirParaules() {
        File fitxer = new File("paraules.txt");
        List<String> paraules = new ArrayList<>();

        if (fitxer.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String linia;
                while ((linia = br.readLine()) != null) {
                    paraules.add(linia);
                }
            } catch (IOException e) {
                view.mostrarMissatge(e.getMessage());
                return;
            }
        }

        view.mostrarMissatge("\nParaules actuals:");
        for (String p : paraules) {
            view.mostrarMissatge("- " + p);
        }

        String nova = view.demanarText("\nIntrodueix una nova paraula (o escriu 'sortir' per acabar):");
        while (!nova.equalsIgnoreCase("sortir")) {
            paraules.add(nova);
            nova = view.demanarText("Introdueix una altra paraula (o 'sortir'):");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxer))) {
            for (String p : paraules) {
                bw.write(p);
                bw.newLine();
            }
            view.mostrarMissatge("Fitxer actualitzat correctament!");
        } catch (IOException e) {
            view.mostrarMissatge( e.getMessage());
        }
    }

    // jugar
    private void jugar() {
        File fitxer = new File("paraules.txt");
        List<String> paraules = new ArrayList<>();

        // 1. Llegeix les paraules
        if (fitxer.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String linia;
                while ((linia = br.readLine()) != null) {
                    if (!linia.trim().isEmpty()) {
                        paraules.add(linia.trim().toLowerCase());
                    }
                }
            } catch (IOException e) {
                view.mostrarMissatge(e.getMessage());
                return;
            }
        } else {
            view.mostrarMissatge("Err. No existeix el fitxer");
            return;
        }

        if (paraules.isEmpty()) {
            view.mostrarMissatge("Err. No hi ha paraules per jugar");
            return;
        }

        // 2. Escollim una paraula aleatòria
        Random random = new Random();
        String paraula = paraules.get(random.nextInt(paraules.size()));
        char[] paraulaArray = paraula.toCharArray();

        // 3. Inicialitzem el joc
        Set<Character> lletresEncertades = new HashSet<>();
        Set<Character> lletresErronies = new HashSet<>();
        int intentsRestants = 10;
        boolean guanyat = false;

        view.mostrarMissatge("\nComença el joc del PENJAT!");
        view.mostrarMissatge("La paraula té " + paraula.length() + " lletres.");

        // 4. Bucle del joc
        while (intentsRestants > 0 && !guanyat) {
            // Mostrem estat actual
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

            // Demanar una lletra
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

            // Comprovem si ha guanyat
            guanyat = true;
            for (char c : paraulaArray) {
                if (!lletresEncertades.contains(c)) {
                    guanyat = false;
                    break;
                }
            }
        }

        // 5. Resultat final
        if (guanyat) {
            view.mostrarMissatge("\nHas guanyat! La paraula era: " + paraula);
        } else {
            view.mostrarMissatge("\nHas perdut! La paraula era: " + paraula);
        }
    }





}