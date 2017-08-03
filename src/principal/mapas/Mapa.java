package principal.mapas;

import principal.herramientas.CargadorRecursos;
import principal.sprites.Sprite;

public class Mapa {

	private String[] partes;

	private int ancho;
	private int alto;

	private Sprite[] sprites;

	public Mapa(final String ruta) {
		String contenido = CargadorRecursos.leerArchivoTexto(ruta);

		partes = contenido.split("\\*");

		ancho = Integer.parseInt(partes[0]);
		alto = Integer.parseInt(partes[0]);

		escribirArray();
	}

	public void escribirArray() {
		for (int i = 0; i < partes.length; i++) {
			System.out.println(partes[i]);
		}
	}

}
