package hibernate;

import org.hibernate.Session;
import java.util.List;

public class Controller {

    public static void main(String[] args) {
        System.out.println("Iniciant aplicació amb Hibernate...");
        Controller controller = new Controller();
        controller.init();
    }

    private void init() {
        MainView mainView = new MainView();

        int option;
        do {
            option = mainView.mainMenu();
            switch (option) {
                case 1: 
                    mostrarEsports(mainView);
                    break;

                case 2: 
                    afegirEsport(mainView);
                    break;

                case 3: 
                    mostrarEsportistes(mainView);
                    break;

                case 4: 
                    afegirEsportista(mainView);
                    break;

                case 0:
                    System.out.println("Sortint...");
                    break;

                default:
                    System.out.println("Opció no vàlida");
            }
        } while (option != 0);

        HibernateUtil.getSessionFactory().close();
    }

    private void mostrarEsports(MainView mainView) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<deporte> deportes = session.createQuery("from deporte", deporte.class).list();
            mainView.showdDeporte(deportes);
        }
    }

    private void afegirEsport(MainView mainView) {
        deporte nouDeporte = mainView.addDeporteForm();
        if (nouDeporte != null && nouDeporte.getNom() != null && !nouDeporte.getNom().isBlank()) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(nouDeporte);
                session.getTransaction().commit();
                System.out.println("Esport afegit correctament!");
            }
        } else {
            System.out.println("Nom invàlid, no s'ha afegit res.");
        }
        mostrarEsports(mainView);
    }

    private void mostrarEsportistes(MainView mainView) {
        mostrarEsports(mainView);
        int esport_id = mainView.demanaEsport();
        if (esport_id > 0) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                deporte d = session.find(deporte.class, esport_id);
                if (d != null && d.getDeportistas() != null) {
                    mainView.showDeportistas(d.getDeportistas());
                } else {
                    System.out.println("No hi ha esportistes per aquest esport.");
                }
            }
        }
    }

    private void afegirEsportista(MainView mainView) {
        mostrarEsports(mainView);
        int esport_id = mainView.demanaEsport();
        if (esport_id > 0) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                deporte d = session.find(deporte.class, esport_id);
                if (d != null) {
                    deportista nouAtleta = mainView.addDeportistaForm(d);
                    if (nouAtleta != null) {
                        session.beginTransaction();
                        String codi = generarCodi(session);
                        nouAtleta.setCodi(codi);
                        session.persist(nouAtleta);
                        session.getTransaction().commit();
                        System.out.println("Atleta afegit correctament!");
                    }
                    mainView.showDeportistas(d.getDeportistas());
                } else {
                    System.out.println("Esport no trobat");
                }
            }
        }
    }

    private String generarCodi(Session session) {
        try {
            String maxCodi = session.createQuery(
                    "SELECT MAX(d.codi) FROM deportista d", String.class)
                    .uniqueResult();
            if (maxCodi == null) return "A001";

            String lletra = maxCodi.substring(0,1);
            int num = Integer.parseInt(maxCodi.substring(1)) + 1;
            return lletra + String.format("%03d", num);
        } catch (Exception e) {
            return "A001";
        }
    }
}
