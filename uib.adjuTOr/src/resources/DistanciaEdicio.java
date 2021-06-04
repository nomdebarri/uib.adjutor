package resources;

public class DistanciaEdicio {

	public static float de(String oral, String escrit) {

		int maxim = 0;

		if (oral.length() <= escrit.length()) {

			maxim = escrit.length();
		} else {
			maxim = oral.length();
		}

		float ld = Levenshtein.lev(oral, escrit);
		float de = ld / maxim;

		return de;

	}
}
