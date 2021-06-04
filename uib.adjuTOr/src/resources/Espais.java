package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Espais {

	public static int charblanc(String linia) {

		int valor = 0;
		int i = 0;

		for (i = 0; i < linia.length(); i++) {

			if (linia.charAt(i) == ' ') {
				valor++;
			}

		}

		return valor;
	}

}
