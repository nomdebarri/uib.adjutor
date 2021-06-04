package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import resources.CSV;
import resources.HtmlBuilder;

public class Main {

	static double version = 3.5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("adjuTOr: ajudant per a l'anàlisi en Teoria de l'Optimitat\n"
				+ "Projecte creat en el marc de l'elaboració del Treball de Fi de Màster del Màster Universitari.\n"
				+ "Llengua i Literatura Catalanes: Coneixement i Anàlisi Crítica del Patrimoni Immaterial\n"
				+ "\"La Teoria de l'Optimitat aplicada a l'anàlisi diferencial del text oral i l'escrit: una proposta computacional\"\n"
				+ "\t\tPere Garau Borràs.\r\n" + "\t\t2019-2021\n");

		int k = 1;
		while (k > 0) {

			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print(">>\t");

			String in = input.nextLine();

			if (in.contains("llista") || in.contains("ls")) {
				MainFunctions.llista();
			}

			if (in.contains("compara") || in.contains("comp")) {

				String[] noms = in.split("\\s+");

				List<String> select = new ArrayList<>();

				select.add(noms[1]);
				select.add(noms[2]);

				HtmlBuilder.html(noms[2]);
				CSV.csv(noms[2]);

				System.out.println("Els arxius seleccionats són: \"" + noms[1] + "\" i \"" + noms[2]
						+ "\". Voleu continuar? (s/n)");
				String inSN = input.nextLine();

				MainFunctions.compara(inSN, select);

			}

			if (in.contains("ctot")) {

				long init = System.currentTimeMillis();

				String csv_path = "src/files/resultats/csv/tot.csv";
				FileWriter csv;
				try {

					csv = new FileWriter(csv_path);
					csv.append(";DE;DL;;F/MAX;F/DEP;F/ID;M/MAX\n");

					File f = new File("src/files/mostres/");
					String[] pathnames = f.list();

					List<String> noms = new ArrayList<>();
					for (String pathname : pathnames) {

						noms.add(pathname);

					}

					HashMap<String, Integer> valors = new HashMap<>();

					List<String> select = new ArrayList<>();
					for (int i = 0; i < noms.size(); i++) {

						if (noms.get(i).matches(".*_..._o.txt")) {
							System.out.println(":::::::::::::::::::" + noms.get(i));
							select.add(noms.get(i));

							i++;
						}

						System.out.println(":::::::::::::::::::" + noms.get(i));
						select.add(noms.get(i));

		
						
						valors = MainFunctions.compara("s", select);
						CSV.tocsvT(csv, valors, select.get(1));

						select.clear();
					}

					csv.close();

					long fi = System.currentTimeMillis();
					float time = (fi - init) / 1000;
					String a = String.valueOf(((float) time / 60));
					String[] b = a.split("\\.");
					System.out.println("\tEl programa s'ha executat durant " + b[0].toString() + " minuts i "
							+ (b[1]).toString().substring(0, 1) + " segons.");

					System.out.println("Feina feta no ha destorb.");

				} catch (IOException e) {

					e.printStackTrace();
				}

				break;

			}

			if (in.contains("surt") || in.contains("adeu")) {
				System.out.println("A reveure!");
				break;

			} else {
				MainFunctions.ajuda();
			}
		}
	}
}
