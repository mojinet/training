package fr.modji.test;

import java.time.LocalDate;
import java.time.LocalTime;

import fr.modji.bo.Adresse;
import fr.modji.bo.Creneau;
import fr.modji.bo.MedecinGeneraliste;
import fr.modji.bo.Patient;
import fr.modji.bo.RendezVous;

public class TestPersonnes {

	private static Adresse sh = new Adresse("ZAC du Moulin Neuf", 2, "B", "rue Benjamin Franklin", 44800,
			"Saint Herblain");
	private static MedecinGeneraliste melanie = new MedecinGeneraliste("Malalaniche", "Mélanie", "02.28.03.17.28", sh);
	private static Adresse nio = new Adresse(19, null, "avenue Léo Lagrange", 79000, "Niort");
	private static Patient adhemar = new Patient("Pamamobe", "Adhémar", "0753428619", 'M', 192112192020142l,
			LocalDate.of(1992, 11, 21), null, nio);

	public static void test() {
		System.out.println("__________________________ Médecins ______________________________");
		Creneau c1 = new Creneau(LocalTime.of(9, 0), 15, melanie);
		new Creneau(LocalTime.of(9, 15), 15, melanie);
		new Creneau(LocalTime.of(9, 30), 15, melanie);
		new Creneau(LocalTime.of(9, 45), 15, melanie);
		new Creneau(LocalTime.of(10, 30), 15, melanie);
		new Creneau(LocalTime.of(10, 45), 15, melanie);
		new Creneau(LocalTime.of(11, 15), 15, melanie);
		new Creneau(LocalTime.of(11, 30), 15, melanie);
		new Creneau(LocalTime.of(11, 45), 15, melanie);
		new Creneau(LocalTime.of(14, 0), 30, melanie);
		new Creneau(LocalTime.of(14, 30), 30, melanie);
		new Creneau(LocalTime.of(15, 0), 30, melanie);
		new Creneau(LocalTime.of(15, 30), 30, melanie);
		new Creneau(LocalTime.of(16, 0), 30, melanie);
		new Creneau(LocalTime.of(16, 30), 30, melanie);
		melanie.afficher();
		System.out.println("__________________________________________________________________");
		System.out.println("__________________________ Rendez-Vous ___________________________");
		RendezVous rdv = new RendezVous(c1, adhemar, LocalDate.of(2020, 5, 23));
		rdv.afficher();
	}
}
