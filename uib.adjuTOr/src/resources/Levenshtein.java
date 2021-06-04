package resources;

public class Levenshtein {
	public static int lev(String oral, String escrit) {
		oral = oral.toLowerCase();
		escrit = escrit.toLowerCase();
		// i == 0
		int[] costs = new int[escrit.length() + 1];
		for (int j = 0; j < costs.length; j++)
			costs[j] = j;
		for (int i = 1; i <= oral.length(); i++) {

			costs[0] = i;
			int nw = i - 1;
			for (int j = 1; j <= escrit.length(); j++) {
				int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
						oral.charAt(i - 1) == escrit.charAt(j - 1) ? nw : nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		return costs[escrit.length()];
	}
}
