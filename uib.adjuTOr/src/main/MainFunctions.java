package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import resources.CSV;
import resources.DistanciaEdicio;
import resources.HtmlBuilder;
import resources.Jerarquies;
import resources.Levenshtein;
import resources.Noms;
import resources.Tokenitzador;

public class MainFunctions {

	public static void ajuda() {

		System.out.println("Aquesta és la llista de funcions disponibles a ScripTOr:\n"
				+ "\t--ajuda: proporciona el llistat d'ajuda.\n"
				+ "\t--llista: mostra els arxius disponibles per a l'anàlisi.\n"
				+ "\t--compara <ARXIU1.TXT> <ARXIU2.TXT>: executa l'anàlisi per als dos arxius seleccionats i en crea un arxiu HTML amb les taules disposades.\n"
				+ "\t--ctot: executa l'anàlisi per a tots els arxius disponibles al directori de treball.\n"
				+ "\t--surt: atura l'execució del programa.\n");
	}

	public static void llista() {

		System.out.println("Aquests són els arxius disponibles per a analitzar: ");
		String[] pathnames;
		File f = new File("src/files/mostres/");
		pathnames = f.list();
		for (String pathname : pathnames) {
			System.out.println("\t" + pathname + "\t\t--[" + Noms.getONom(pathname) + "]");
		}
	}

	public static HashMap<String, Integer> compara(String inSN2, List<String> noms) {
		HashMap<String, Integer> map_mostra = new HashMap<>();
		if (inSN2.matches("[sS]")) {

			long init = System.currentTimeMillis();

			String oral_path = "src/files/mostres/" + noms.get(0);
			String escrit_path = "src/files/mostres/" + noms.get(1);

			List<String> oral = Tokenitzador.tok_mots(oral_path);
			List<String> escrit = Tokenitzador.tok_mots(escrit_path);
			List<Float> temps = Tokenitzador.tok_temps(escrit_path);

			int maxima = 0;

			if (oral.size() < escrit.size()) {
				maxima = escrit.size();
			} else {
				maxima = oral.size();
			}

			String html_path = "src/files/resultats/" + noms.get(1) + ".html";
			FileWriter html;
			String csv_path = "src/files/resultats/csv/" + noms.get(1) + ".csv";
			FileWriter csv;
			try {
				html = new FileWriter(html_path);
				csv = new FileWriter(csv_path);
				csv.append("SUBMOSTRA;DL;DE;F/MAX;F/DEP;F/ID;M/MAX\n");

				html.append("<!DOCTYPE html>"
						+ "<html lang=\"ca\">"
						+ "<head>"
						+ "<title>"+noms.get(1)+"</title>"
						+ "<meta charset=\"utf-8\"/>"
						+"<body>");
				
				
				html.append(
						"<img src=\"https://adjutor.neocities.org/scriptor_github.png\" align=\"right\" width=\"200\" height=\"80\" alt=\"ScripTOr\">");

				html.append("<h2 style=\"text-align: center;\">Informe de comparació de les mostres <i>"
						+ Noms.getONom(noms.get(0)) + "</i> amb <i>" + Noms.getSNom(noms.get(0), noms.get(0)) + "</i> </h2>");

				html.append("<p></p><p></p><section id=\"init\">");
				html.append("<h2>Accessos directes:</h2>" + "<ul>" + "<li><a href=\"#tableaux\">Tableaux</a></li>"
						+ "<li><a href=\"#recompte\">Recompte de violacions</a></li>"
						+ "<li><a href=\"#recomptes\">Recomptes globals</a></li>"
						+ "<li><a href=\"#llistat\">Llistat de violacions de les restriccions</a></li>"
						+ "<li><a href=\"#video\">Vídeo de la mostra</a></li>"
						+ "<li><a href=\"#comparable\">Text comparable</a></li>"
						+ "<li><a href=\"#credits\">Crèdits</a></li>" + "</ul>");

				html.append("</section>");
				html.append("<hr size=\"8px\" color=\"black\" />");

				html.append("<section id=\"tableaux\">");
				html.append("<h2>Tableaux</h2>");

				HashMap<String, Integer> valors = new HashMap<>();

				int max_compt = 0;
				int dep_compt = 0;

				int id_compt = 0;
				int mid_compt = 0;
			
				int mmax_compt = 0;
				
				int len_oral = 0;
				int len_escrit = 0;
				int idsyn_compt = 0;
				int dl = 0;

				String llistat = "";
				String llistaf = "";
				int M_tot;

				int dl_suma = 0;
				
				for (int i = 0; i < maxima; i++) {

					valors = HtmlBuilder.comparador(oral.get(i), escrit.get(i), temps.get(i), html, i, noms.get(1));

					CSV.tocsv(csv, valors);

					dl = valors.get("DL");
					M_tot = valors.get("M_total");

					if (dl != 0 || M_tot != 0) {
						html.append(
								"<br><a href=\"#a" + i + "\"><button>⬐Torna al llistat de violacions de restriccions⬎</button><a/>"
										+ "     <a href=\"#init\"><button>Puja🠕</button><a/>"
										
										+ "<br>");
						llistat = "<tr>" + "<td style=\"width: 16.6667%;\"><section id=\"a" + i + "\"><a href=\"#" + i
								+ "\">" + i + "</a></td>" + "<td style=\"width: 16.6667%;\">" + valors.get("F/Max")
								+ "</td>" + "<td style=\"width: 16.6667%;\">" + valors.get("F/Dep") + "</td>"
								+ "<td style=\"width: 16.6667%;\">" + valors.get("F/Id") + "</td>"
								+ "<td style=\"width: 0.520835%;\">" + valors.get("M/Max") + "</td>";
								

						llistaf = llistaf + llistat;
					}

					html.append("<p></p>");
					max_compt = max_compt + valors.get("F/Max");
					dep_compt = dep_compt + valors.get("F/Dep");
					id_compt = id_compt + valors.get("F/Id");

					mmax_compt = mmax_compt + valors.get("M/Max");


					len_oral = len_oral + valors.get("oral.length");
					len_escrit = len_escrit + valors.get("escrit.length");
					
					dl_suma = dl_suma+valors.get("DL");

				}

				csv.close();
				
				int F_total = max_compt + dep_compt + id_compt;
				int M_total = mmax_compt;

			
				html.append("</section>");

				html.append("<hr size=\"4px\" color=\"black\" />");
				html.append("<hr size=\"2px\" color=\"black\" />");
				html.append("<section id=\"recompte\">");
				html.append("<p></p><p></p><h2>Recompte de violacions</h2>");

				html.append("<table style=\"width: 70%; border-style: groove;\" border=\"1\">\r\n"
						+ "<tbody>\r\n"
						+ "<tr>\r\n"
						+ "<td style=\"width: 50%; text-align: center;\">F/</td>\r\n"
						+ "<td style=\"width: 50%; text-align: center;\">M/</td>\r\n"
						+ "</tr>\r\n"
						+ "<tr>");
				html.append("<td style=\"width: 50%;\">Max: "+max_compt+"</td>\r\n"
						+ "<td style=\"width: 50%;\">Max: "+mmax_compt+"</td>\r\n"
						+ "</tr>\r\n"
						+ "<tr>\r\n"
						+ "<td style=\"width: 50%;\">Dep: "+dep_compt+"</td>\r\n"
						+ "<td style=\"width: 50%;\">&nbsp;</td>\r\n"
						+ "</tr>\r\n"
						+ "<tr>\r\n"
						+ "<td style=\"width: 50%;\">Id: "+id_compt+"</td>\r\n"
						+ "<td style=\"width: 50%;\">&nbsp;</td>\r\n"
						+ "</tr>\r\n"
						+ "<tr>\r\n"
						+ "<td style=\"width: 50%;\">Total F/: "+F_total+"</td>\r\n"
						+ "<td style=\"width: 50%;\">Total M/: "+M_total+"</td>\r\n"
						+ "</tr>");
				
				html.append("<tr>\r\n"
						+ "<td style=\"width: 50%;\" colspan=\"2\">Jerarquia: "+Jerarquies.jerarquiaT(max_compt, dep_compt, id_compt, mmax_compt)+"</td>\r\n"
						+ "</tr>\r\n"
						+ "<tr>\r\n"
						+ "<td style=\"width: 50%;\" colspan=\"2\">Jeraquia %: "+Jerarquies.jerarquiaXC(max_compt, dep_compt, id_compt, mmax_compt, F_total, M_total)+"</td>\r\n"
						+ "</tr>\r\n"
						+ "<tr>\r\n"
						+ "<td style=\"width: 50%;\" colspan=\"2\">Total violacions: "+(F_total+M_total)+"</td>\r\n"
						+ "</tr>\r\n"
						+ "</tbody>\r\n"
						+ "</table>");
				
				html.append("</section>");

				html.append("<hr size=\"2px\" color=\"grey\" />");
				html.append("<section id = \"recomptes\">");
				int lev_glob = Levenshtein.lev(oral.toString().replaceAll("[\\[\\]]", ""),
						escrit.toString().replaceAll("[\\[\\]]", ""));

				html.append("<h2>Recomptes</h2>");
				html.append("<blockquote>");
				html.append("<p><li>La transcripicó oral conté un total de <b>" + len_oral + "</b> caràcters.</li>");
				html.append("<li>Els subtítols contenen un total de <b>" + len_escrit + "</b> caràcters.</li></p>");
				html.append(
						"<p><li>La distància de Levenshtein global de la mostra és de <b>" + lev_glob + "</b></li>");
				html.append("<li>La distància d'edició global de la mostra és de <b>" + DistanciaEdicio
						.de(oral.toString().replaceAll("[\\[\\]]", ""), escrit.toString().replaceAll("[\\[\\]]", ""))
						+ "</b></li></p>");

				map_mostra.put("DL", lev_glob);
				map_mostra.put("F/Max", max_compt);
				map_mostra.put("F/Dep", dep_compt);
				map_mostra.put("F/Id", id_compt);
				map_mostra.put("F/IdSin", idsyn_compt);
				map_mostra.put("M/Max", mmax_compt);
				map_mostra.put("M/Id", mid_compt);
				map_mostra.put("oral.length", len_oral);
				map_mostra.put("escrit.length", len_escrit);

				long fi = System.currentTimeMillis();
				System.out.println("Arxiu creat amb èxit: " + noms.get(1) + ".html");
				float time = (fi - init) / 1000;
				html.append("<p><li>Aquesta anàlisi s'ha realitzat en <b>" + time + "</b> segons.</li></p>");
				html.append("</blockquote>");
				html.append("<p style=\"text-align: right; padding-right: 10em\"><a href=\"#init\"><button>Puja🠕</button><a/></p>");
				html.append("</section>");

				html.append("<hr size=\"2px\" color=\"black\" />");
				html.append("<hr size=\"4px\" color=\"black\" />");
			

				html.append("<section id=\"llistat\">");
				html.append("<hr size=\"4px\" color=\"black\" />");
				html.append("<h2>Llistat de restriccions violades</h2>");
				html.append("<table style=\"width: 50%;\" border=\"2\">" + "<tbody>" + "<tr>"
						+ "<td style=\"width: 16.6667%;\">&nbsp;</td>"
						+ "<td style=\"width: 16.6667%;\" colspan=\"3\">F/</td>"
						+ "<td style=\"width: 0.520835%;\" colspan=\"1\">M/</td>" + "</tr>" + "<tr>"
						+ "<td style=\"width: 16.6667%;\">&nbsp;</td>" + "<td style=\"width: 16.6667%;\">Max</td>"
						+ "<td style=\"width: 16.6667%;\">Dep</td>" + "<td style=\"width: 16.6667%;\">Id</td>"
					
						+ "<td style=\"width: 0.520835%;\">Max</td>"
						+ "</tr>");
				html.append(llistaf);
				html.append("</tbody></table>");
				html.append("</section>");

				html.append("<p style=\"text-align: right; padding-right: 10em\"><a href=\"#init\"><button>Puja🠕</button><a/></p>");
				html.append("</section>");
				html.append("<p></p><p></p>");
				html.append("<section id=\"video\">");
				html.append("<hr size=\"4px\" color=\"black\" />");
				html.append("<h2>Vídeo de la mostra</h2>");
				html.append(Noms.getVideo(noms.get(0)));
				html.append("</section>");

				html.append("<section id = \"comparable\">");

				html.append("<h2>Text comparable</h2>" + "<table style=\"width: 100%; height: 34px;\" border=\"2\">"
						+ "<tbody>" + "<tr style=\"height: 17px;\">"
						+ "<td style=\"width: 50%\"><h3>Text oral</h3></td>"
						+ "<td style=\"width: 50%\"><h3>Text subt&iacute;tols</h3></td>" + "</tr>"
						+ "<tr style=\"height: 17px;\">" + "<td style=\"width: 50%\">"
						+ oral.toString().replaceAll("[\\[\\]]", "") + "</td>" + "<td style=\"width: 50%\">"
						+ escrit.toString().replaceAll("[\\[\\]]", "") + "</td>" + "</tr>" + "</tbody>" + "</table>");
				html.append("<p style=\"text-align: right; padding-right: 10em\"><a href=\"#init\"><button>Puja🠕</button><a/></p>");
				html.append("</section>");

				html.append("<hr size=\"4px\" color=\"black\" />");
				html.append("<section id=\"credits\">");

				html.append(
						"<img src=\"https://adjutor.neocities.org/scriptor_github.png\" align=\"center\" width=\"200\" height=\"80\" alt=\"ScripTOr\">");

				html.append("<address style=\"text-align: right; padding-right: 5em\">");
				html.append("<b>adjuTOr v. " + Main.version
						+ "</b><br>Pere Garau Borràs<br>Universitat de les Illes Balears<br></address>");
				html.append("<hr size=\"4px\" color=\"black\" />");
				html.append("<p style=\"text-align: right; padding-right: 10em\"><a href=\"#init\"><button>Puja🠕</button><a/></p>");
				html.append("</section>");
				
				html.append("</body></html>");
				html.close();

				System.out.println("Temps d'execució: " + time + " segons.");

			} catch (IOException e) {
				System.out.println("Error! No s'han trobat els fitxers indicats: " + noms.get(0) + " i " + noms.get(1));

			}

		} else {
			System.out.println("D'acord, adeu!");
		}
		return map_mostra;
	}

}
