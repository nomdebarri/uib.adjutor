package resources;

public class M_Max {

	public static boolean mmaxbool(Float temps, String linia) {

		boolean check = false;
		int chars = linia.length();

		if (temps <= 1) {
			if (chars > 12) {
				check = true;
			}
		} else if (temps <= 1.5) {
			if (chars > 18) {
				check = true;
			}
		} else if (temps <= 2) {
			if (chars > 24) {
				check = true;
			}
		} else if (temps <= 2.5) {
			if (chars > 30) {
				check = true;
			}

		} else if (temps <= 3) {
			if (chars > 36) {
				check = true;
			}

		} else if (temps <= 3.5) {
			if (chars > 42) {
				check = true;
			}
		} else if (temps <= 4) {
			if (chars > 48) {
				check = true;
			}
		} else if (temps <= 4.5) {
			if (chars > 54) {
				check = true;
			}
		} else if (temps <= 5) {
			if (chars > 60) {

			}
		} else if (temps <= 5.5) {
			if (chars > 66) {
				check = true;
			}
		} else if (temps <= 6) {
			if (chars > 72) {
				check = true;
			}
		}
		return check;
	}

	public static String print(Float temps, String linia_oral, String linia_escrit) {

		String mmax = "";

		if (mmaxbool(temps, linia_escrit)) {

			mmax = "<p style=\"padding-left: 40px;\"><span style=\"font-variant: small-caps;\">*M/Max</span>{<i>in</i>("
					+ temps + " s),  <i>out</i>(" + linia_escrit.length() + " c)}: " + (M_Max.max(temps, linia_escrit))
					+ "</p>";

		}

		return mmax;

	}

	public static int max(float temps, String linia_escrit) {

		int rec = linia_escrit.length();

		int max = 0;

		if (temps <= 1) {
			max = rec - 12;
		} else if (temps <= 1.5) {
			max = rec - 18;
		} else if (temps <= 2) {
			max = rec - 24;
		} else if (temps <= 2.5) {
			max = rec - 30;
		} else if (temps <= 3) {
			max = rec - 36;
		} else if (temps <= 3.5) {
			max = rec - 42;
		} else if (temps <= 4) {
			max = rec - 48;
		} else if (temps <= 4.5) {
			max = rec - 54;
		} else if (temps <= 5) {
			max = rec - 60;
		} else if (temps <= 5.5) {
			max = rec - 66;
		} else if (temps <= 6) {
			max = rec - 72;
		}

	if (max > 0) {
		int charblanc = Espais.charblanc(linia_escrit);
		if (max > charblanc) {
			
			max = max-charblanc;
			
		} else {
			max = charblanc-max;
		}
	}
		
		return max;
	}

}
