package resources;

public class Noms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String getONom(String nom1) {

		String num = null;
		String serie = null;
		if (nom1.contains("01")) {

			num = "Temporada 1, episodi 1";

		} else if (nom1.contains("02")) {
			num = "Temporada 1, episodi 1";
		} else if (nom1.contains("03")) {
			num = "Temporada 1, episodi 3";
		} else if (nom1.contains("04")) {
			num = "Temporada 1, episodi 4";
		} else {
			num = "";
		}

		if (nom1.contains("MNAC") || nom1.contains("mnac")) {
			serie = "Mai neva a ciutat (IB3)";
		} else if (nom1.contains("ADC") || nom1.contains("adc")) {
			serie = "Amor de cans (IB3)";
		} else if (nom1.contains("P") || nom1.contains("p")) {
			serie = "Pep (IB3)";
		} else if (nom1.contains("M") || nom1.contains("m")) {
			serie = "Merlí (IB3)";
		} else {
			serie = nom1;
		}

		return serie + " " + num;

	}

	public static String getSNom(String nom1, String nom2) {

		String subt = null;
		String nom = getONom(nom1);

		if (nom2.contains("ib3")) {

			subt = "subtitulació d'IB3";

		} else if (nom2.contains("tv3")) {
			subt = "subtitulació de TV3";
		} else {
			subt = "";
		}

		return nom + " " + subt;
	}

	public static String getVideo(String nom1) {

		String src = null;

		if (nom1.contains("adc")) {

			src = "https:/www.youtube.com/embed/kU8Gwmbhvqs";

		} else if (nom1.contains("mnac")) {
			src = "https://www.youtube.com/embed/Mi900FATBaQ";
		} else if (nom1.contains("p")) {
			src = "https://www.youtube.com/embed/sPG5OiIl-wk";
		} else if (nom1.contains("m")) {
			src = "https://www.youtube.com/embed/nFJ4JqBMQSk";
		}

		String s = "<iframe width=\"560\" height=\"315\" src=\"" + src
				+ "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";

		return s;

	}

}
