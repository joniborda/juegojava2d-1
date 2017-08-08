package principal.mapas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {

	private String[] partes;

	private final int ancho;
	private final int alto;

	private final Sprite[] paleta;
	private final int[] sprites;
	private final String[] hojasSeparadas;

	private final boolean[] colisiones;

	public Mapa(final String ruta) {
		String contenido = CargadorRecursos.leerArchivoTexto(ruta);

		partes = contenido.split("\\*");

		ancho = Integer.parseInt(partes[0]);
		alto = Integer.parseInt(partes[1]);

		String hojasUtilizadas = partes[2];

		hojasSeparadas = hojasUtilizadas.split(",");

		String paletaEntera = partes[3];
		String[] partesPaleta = paletaEntera.split("#");

		paleta = asignarSprites(partesPaleta, hojasSeparadas);

		String colisionesEnteras = partes[4];

		colisiones = extraerColisiones(colisionesEnteras);

		String spriteEnteros = partes[5];
		String[] cadenasSprites = spriteEnteros.split(" ");

		sprites = extraerSprites(cadenasSprites);
	}

	private Sprite[] asignarSprites(final String[] partesPaleta, final String[] hojasSeparadas) {
		System.out.println("tamaña que carga la paleta " + partesPaleta.length);
		Sprite[] paleta = new Sprite[partesPaleta.length];

		HojaSprites hoja = new HojaSprites("/imagenes/hojasTexturas/" + hojasSeparadas[0] + ".png", 32, false);

		for (int i = 0; i < partesPaleta.length; i++) {
			String spriteTemporal = partesPaleta[i];
			String[] partesSprite = spriteTemporal.split("-");

			int indicePaleta = Integer.parseInt(partesSprite[0]);
			int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);

			paleta[indicePaleta] = hoja.getSprite(indiceSpriteHoja);
		}

		return paleta;
	}
	private boolean[] extraerColisiones(final String cadenaColisiones) {

		boolean[] colisiones = new boolean[cadenaColisiones.length()];

		for (int i = 0; i < cadenaColisiones.length(); i++) {
			if (cadenaColisiones.charAt(i) == '0') {
				colisiones[i] = false;
			} else {
				colisiones[i] = true;
			}
		}

		return colisiones;
	}

	private int[] extraerSprites(final String[] cadenaSprites) {
		ArrayList<Integer> sprites = new ArrayList<Integer>();

		for (int i = 0; i < cadenaSprites.length; i++) {
			if (cadenaSprites[i].length() == 2) {
				sprites.add(Integer.parseInt(cadenaSprites[i]));
			} else {
				String uno = "";
				String dos = "";

				String error = cadenaSprites[i];
				uno += error.charAt(0);
				uno += error.charAt(1);

				dos += error.charAt(2);
				dos += error.charAt(3);

				sprites.add(Integer.parseInt(uno));
				sprites.add(Integer.parseInt(dos));
			}
		}

		int[] vectorSprites = new int[sprites.size()];

		for (int i = 0; i < sprites.size(); i++) {
			vectorSprites[i] = sprites.get(i);
		}

		return vectorSprites;
	}

	public void dibujar(Graphics g, int posicionX, int posicionY) {
		int anchoSprite = Constantes.LADO_SPRITE;
		int altoSprite = Constantes.LADO_SPRITE;

		for (int y = 0; y < this.alto; y++) {
			for (int x = 0; x < this.ancho; x++) {
				BufferedImage imagen = this.paleta[this.sprites[x + y * this.ancho]].getImagen();
				g.drawImage(imagen, x * anchoSprite - posicionX, y * altoSprite - posicionY, null);
			}
		}
	}
}
