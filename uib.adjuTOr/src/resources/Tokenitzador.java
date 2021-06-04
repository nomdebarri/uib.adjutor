package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Tokenitzador {

	public static List<String> tok_mots(String txt_path) {

		List<String> text = new ArrayList<>();

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(txt_path, StandardCharsets.UTF_8));

			String linia = br.readLine();
			String l1 = "";
			String l2 = "";
			String l = "";

			while (linia != null) {

				if (Pattern.matches("\\[.*\\]", linia)) {

					linia = br.readLine();

					if (Pattern.matches("\\[\\d\\d:\\d\\d:\\d\\d([\\.\\:]\\d\\d)?\\]", linia) == false
							&& linia.length() != 0) {

						linia = linia.toLowerCase().replaceAll("\\(.*\\)", "").replaceAll("[,.;:\\[\\]()\"@]", "")
								.replaceAll("\\A | \\Z", "").replaceAll("\\{.*\\}", "").replaceAll("^\\-", "");

						if (!linia.matches("^\\s*$")) {

							l1 = linia;
							l = l1;
							linia = br.readLine();

							if (Pattern.matches("\\[\\d\\d:\\d\\d:\\d\\d([\\.\\:]\\d\\d)?\\]", linia) == false
									&& linia.length() != 0) {

								linia = linia.toLowerCase().replaceAll("\\(.*\\)", "").replaceAll("[,.;:\\[\\]()\"@]", "")
										.replaceAll("\\A | \\Z", "").replaceAll("\\{.*\\}", "").replaceAll("^\\-", "");

								if (!linia.matches("^\\s*$")) {

									l2 = linia;
									l = l+"#"+l2;
									linia = br.readLine();

								}
							}
							text.add(l);
						}

					}
				}
				

				linia = br.readLine();

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return text;

	}

	public static List<Float> tok_temps(String txt_path) {

		BufferedReader br;
		List<String> l = new ArrayList<>();

		try {
			br = new BufferedReader(new FileReader(txt_path, StandardCharsets.UTF_8));

			String linia = br.readLine();

			while (linia != null) {

				if (linia.contains("[")) {

					l.add(linia);

				}

				linia = br.readLine();

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		String[] arr_a = null;
		String[] arr_b = null;

		List<Float> floats = new ArrayList<>();
		float flot = 0;
		int j = 0;

		float h = 0;
		float min = 0;
		float sec = 0;
		float init = 0;
		float hb = 0;
		float minb = 0;
		float secb = 0;
		float fi = 0;

		for (int i = 1; i < l.size(); i = i + 2) {

			j = i - 1;

			String a = l.get(j).replaceAll("[\\[\\]]", "");
			arr_a = a.split(":");
			h = Float.parseFloat(arr_a[0]);
			min = Float.parseFloat(arr_a[1]);
			sec = Float.parseFloat(arr_a[2]);

			h = h * 3600;
			min = min * 60;
			init = h + min + sec;

			String b = l.get(i).replaceAll("[\\[\\]]", "");
			arr_b = b.split(":");
			hb = Float.parseFloat(arr_b[0]);
			minb = Float.parseFloat(arr_b[1]);
			secb = Float.parseFloat(arr_b[2]);

			hb = hb * 3600;
			minb = minb * 60;
			fi = hb + minb + secb;

			flot = (fi - init);
			floats.add(flot);
			flot = 0;

		}

		return floats;

	}

}
