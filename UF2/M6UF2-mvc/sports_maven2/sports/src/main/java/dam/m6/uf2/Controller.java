package dam.m6.uf2;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;

public class Controller {
	Connection conn;

	public static void main(String[] args) {
		System.out.println("Current directory database.xml: " + System.getProperty("user.dir"));
		Controller controller = new Controller();
		controller.init();
	}

	private void init() {

		this.conn = ConnectionManager.getConnection("database.xml");

		MainView mainView = new MainView();
		deportePgDAO deporteDAO = new deportePgDAO(conn);
		deportistaPgDAO deportistaDAO = new deportistaPgDAO(conn);

		int option;
		do {
			option = mainView.mainMenu();
			switch (option) {
				case 1:
					mainView.showdDeporte(deporteDAO.getAll());
					break;

				case 2:
					deporte nouDeporte = mainView.addDeporteForm();

					if (nouDeporte != null && nouDeporte.getNom() != null && !nouDeporte.getNom().isBlank()) {
						deporteDAO.add(nouDeporte);
						System.out.println("Esport afegit correctament!");
						// Opcional: mostrar llistat actualitzat
						mainView.showdDeporte(deporteDAO.getAll());
					} else {
						System.out.println("Nom invàlid, no s'ha afegit res.");
					}
					break;
				case 3:
					mainView.showdDeporte(deporteDAO.getAll());
					int esport_id = mainView.demanaEsport();
					mainView.showDeportistas(deportistaDAO.llista_deportistasPerID(esport_id));
					break;

				case 4:
					mainView.showdDeporte((deporteDAO.getAll()));
					int esport_id2 = mainView.demanaEsport();
					deportista nouAtleta = mainView.addDeportistaForm(esport_id2);
					deportistaDAO.add(nouAtleta);
					System.out.println("Atleta afegit correctament");
					mainView.showDeportistas(deportistaDAO.llista_deportistasPerID(esport_id2));
					break;

				case 5:
					String nom = mainView.demanarNomAtleta();
					List<deportista> llistaAtletes = deportistaDAO.llistaDeportistasPerNom(nom);
					mainView.showDeportistas(llistaAtletes);
					break;

				case 0:
					System.out.println("sortint...");
					break;

				default:
					System.out.println("Opció no valida");
			}
		} while (option != 0);
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.err.println("Error tencant");
		}
	}
}
