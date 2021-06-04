package resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HtmlBuilder {

	public static File html(String in) {

		File html = new File("src/files/resultats/" + in + ".html");
		try {

			if (html.createNewFile()) {
				System.out.println("Arxiu creat: " + html.getName());
			} else {
				System.out.println("ATENCIÓ!!! L'arxiu >>\"" + html.getName() + "\"<< serà sobreescrit!");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return html;

	}

	public static HashMap<String, Integer> comparador(String linia_oral, String linia_escrit, Float temps, FileWriter html,
			int mostra, String nom) throws IOException {

		int dl = Levenshtein.lev(linia_oral, linia_escrit); // DISTÀNCIA levenhstein ENTRE LES DUES FRASES
		float de = DistanciaEdicio.de(linia_oral, linia_escrit); // distància d'edició entre les dues frases

		html.append("<section id=\"" + mostra + "\">");

		html.append("<table style=\"width: 100%; float: left;\" border=\"1\">\r\n" + "<tbody>\r\n" + "<tr>");
		html.append("<td style=\"width: 153.333px;\"><b>" + mostra + "</b><br />" + temps + "</td>");

		html.append("<td style=\"width: 152px;\">F/</td>\r\n" + "<td style=\"width: 152.667px;\">M/</td>\r\n"
				+ "</tr>\r\n" + "<tr>");

		html.append("<tr style=\"height: 17px;\">\r\n" + "<td style=\"width: 153.333px; height: 17px;\"><i>"
				+ linia_oral.replaceAll("#", "<br>") + "</i></td>");

		new ArrayList<>();

		int max_compt = 0; // COMPTADOR MAX
		int dep_compt = 0; // COMPTADOR DEP
		int id_compt = 0; // COMPTADOR ID
		int mmax = 0; // COMPTADOR M/MAX

		if (linia_oral.length() > linia_escrit.length()) {

			max_compt = linia_oral.length() - linia_escrit.length();

		}

		else if (linia_oral.length() < linia_escrit.length()) {
			dep_compt = linia_escrit.length() - linia_oral.length();
		}

		else if ((max_compt + dep_compt) < dl) {

			id_compt = dl - (max_compt + dep_compt);
		}

		html.append(
				"<td style=\"width: 152px; height: 34px;\" rowspan=\"2\"><span style=\"font-variant: small-caps; padding-left: 40px\";>");

		if (max_compt > 0) {

			html.append("*Max: </span>" + max_compt);

		}

		else if (dep_compt > 0) {

			html.append("*Dep: </span>" + dep_compt);
		}

		else if (id_compt > 0) {

			html.append("*Id: </span>" + id_compt);
		}

		html.append("</td>");
		String mmax_str = "";

		if (M_Max.max(temps, linia_escrit) > 0) {
			mmax = M_Max.max(temps, linia_escrit);
			mmax_str = M_Max.print(temps, linia_oral, linia_escrit);
			html.append("<td style=\"width: 152.667px; height: 34px;\" rowspan=\"2\">" + mmax_str + "</td>");
		} else {
			html.append("<td style=\"width: 152px; height: 34px;\" rowspan=\"2\"></td>");
		}

		html.append(
				"</tr>\r\n" + "<tr style=\"height: 17px;\">\r\n" + "<td style=\"width: 153.333px; height: 17px;\"><i>☞ "
						+ linia_escrit.replaceAll("#", "<br>") + "</i></td>\r\n" + "</tr>\r\n"
						+ "<tr style=\"height: 63px;\">\r\n" + "<td style=\"width: 153.333px; height: 63px;\">");

		html.append("DL: " + dl + "<br />DE: " + de);

		html.append("<td style=\"width: 25%; border-style: none; height: 17px;\">&nbsp;</td>\r\n"
				+ "<td style=\"width: 25%; border-style: none; height: 17px;\"></td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
				+ "</table><p></p>");

		html.append("</section>");

		HashMap<String, Integer> map_valors = new HashMap<>();

		map_valors.put("F/Max", max_compt);
		map_valors.put("F/Dep", dep_compt);
		map_valors.put("F/Id", id_compt);
		map_valors.put("M/Max", mmax);
		map_valors.put("oral.length", linia_oral.length());
		map_valors.put("escrit.length", linia_escrit.length());
		map_valors.put("DL", dl);
		map_valors.put("mostra", mostra);
		map_valors.put("M_total", (mmax));

		return map_valors;
	}

}
