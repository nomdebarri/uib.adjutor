package resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CSV {

	public static File csv(String nom) {

		File csv = new File("src/files/resultats/csv/" + nom + ".csv");
		try {

			if (csv.createNewFile()) {
				System.out.println("Arxiu creat: " + csv.getName());
			} else {
				System.out.println("ATENCIÓ!!! L'arxiu >>\"" + csv.getName() + "\"<< serà sobreescrit!");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return csv;

	}

	public static void tocsv(FileWriter csv, HashMap<String, Integer> valors) {

		int lenmax = 0;
		if (valors.get("oral.length") < valors.get("escrit.length")) {
			lenmax = valors.get("escrit.length");
		} else {
			lenmax = valors.get("oral.length");
		}

		try {

			csv.append(valors.get("mostra").toString() + ";");
			csv.append(valors.get("DL").toString() + ";");
			csv.append(String.valueOf((float) valors.get("DL") / lenmax).replaceAll("\\.", ",") + ";");
			csv.append(valors.get("F/Max").toString() + ";");
			csv.append(valors.get("F/Dep").toString() + ";");
			csv.append(valors.get("F/Id").toString() + ";");
			csv.append(valors.get("M/Max").toString() + ";");

			csv.append("\n");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	public static void tocsvT(FileWriter csv, HashMap<String, Integer> valors, String nom) {
		
		int lenmax = 0;
		if (valors.get("oral.length") < valors.get("escrit.length")) {
			lenmax = valors.get("escrit.length");
		} else {
			lenmax = valors.get("oral.length");
		}

		try {

			csv.append(nom + ";");
			csv.append(String.valueOf((float) valors.get("DL") / lenmax).replaceAll("\\.", ",") + ";");
			csv.append(valors.get("DL").toString() + ";");
			csv.append(nom + ";");
			csv.append(valors.get("F/Max").toString() + ";");
			csv.append(valors.get("F/Dep").toString() + ";");
			csv.append(valors.get("F/Id").toString() + ";");
			csv.append(valors.get("M/Max").toString() + ";");


			csv.append("\n");

		} catch (IOException e) {

			e.printStackTrace();
		}

		
	}
}
