package resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Jerarquies {

	public static String jerarquiaT(int max, int dep, int id, int mmax) {
		String out = null;
		HashMap<String, Integer> unSortedMap = new HashMap<>();

		unSortedMap.put("F/Max", max);
		unSortedMap.put("F/Dep", dep);
		unSortedMap.put("F/Id", id);
		unSortedMap.put("M/Max", mmax);

		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

		unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

		out = sortedMap.toString().replaceAll("\\{", "").replaceAll("=", "(").replaceAll(",", ") >> ").replaceAll("\\}",
				"") + ")";

		return out;
	}

	public static String jerarquiaXC(float max, float dep, float id, float mmax, float ftot, float mtot) {
		String out = null;

		HashMap<String, Float> unSortedMap = new HashMap<>();

		unSortedMap.put("F/Max", (max / (ftot + mtot)) * 100);
		unSortedMap.put("F/Dep", (dep / (ftot + mtot)) * 100);
		unSortedMap.put("F/Id", (id / (ftot + mtot)) * 100);
		unSortedMap.put("M/Max", (mmax / (ftot + mtot)) * 100);

		LinkedHashMap<String, Float> sortedMap = new LinkedHashMap<>();

		unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

		out = sortedMap.toString().replaceAll("\\{", "").replaceAll("=", "(").replaceAll(",", " %) >> ")
				.replaceAll("\\}", "") + " %)";

		return out;
	}

}
