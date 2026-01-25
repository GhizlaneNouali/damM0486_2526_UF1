package activitat2;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();

        boolean sortir = false;
        while (!sortir) {
            int opcio = view.mostrarMenu();
            switch (opcio) {
                case 1: { 
                    Cotxe cotxe = view.llegirCotxe();
                    model.inserirCotxe(cotxe);
                    view.mostrarMissatge("Cotxe afegit correctament!");
                    break;
                }
                case 2: { 
                    String matricula = view.llegirMatricula();
                    model.deleteCotxe(matricula);
                    view.mostrarMissatge("Cotxe eliminat correctament!");
                    break;
                }
                case 3: { 
                    Cotxe cotxe = view.llegirCotxe();
                    model.updateCotxe(cotxe);
                    view.mostrarMissatge("Cotxe modificat correctament!");
                    break;
                }
                case 4: { 
                    List<Cotxe> cotxes = model.getAllCotxes();
                    view.mostrarCotxes(cotxes);
                    break;
                }
                case 5: { 
                    Date[] dates = view.llegirDates();
                    List<Cotxe> cotxes = model.getCotxesByDate(dates[0], dates[1]);
                    view.mostrarCotxes(cotxes);
                    break;
                }
                case 6: { 
                    String filtre = view.llegirFiltre();
                    List<Cotxe> cotxes = model.getFilteredCotxes(filtre);
                    view.mostrarCotxes(cotxes);
                    break;
                }
                case 0: sortir = true;
                default: view.mostrarMissatge("Opció no vàlida!");
            }
        }
    }
}
