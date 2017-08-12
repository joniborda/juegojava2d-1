package principal.mapas;

import java.awt.Graphics;
import java.awt.Rectangle;
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

	private final int MARGEN_X = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
	private final int MARGEN_Y = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

	private final boolean[] colisiones;

	public ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();

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

	public void actualizar(final int posicionX, final int posicionY) {
		actualizarAreasColision(posicionX, posicionY);
	}

	private void actualizarAreasColision(final int posicionX, final int posicionY) {
		if (!areasColision.isEmpty()) {
			areasColision.clear();
		}

		for (int y = 0; y < this.alto; y++) {
			for (int x = 0; x < this.ancho; x++) {
				int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
				int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;

				if (colisiones[x + y * this.ancho]) {
					final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
					areasColision.add(r);
				}
			}
		}
	}
	public void dibujar(Graphics g, final int posicionX, final int posicionY) {

		for (int y = 0; y < this.alto; y++) {
			for (int x = 0; x < this.ancho; x++) {
				BufferedImage imagen = this.paleta[this.sprites[x + y * this.ancho]].getImagen();

				int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
				int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;

				g.drawImage(imagen, puntoX, puntoY, null);
			}
		}
	}

	public Rectangle obtenerBordes(final int posicionX, final int posicionY, final int anchoJugador,
			final int altoJugador) {
		int x = MARGEN_X - posicionX + anchoJugador;
		int y = MARGEN_Y - posicionY + altoJugador;
		int ancho = this.ancho * Constantes.LADO_SPRITE - anchoJugador * 2;
		int alto = this.alto * Constantes.LADO_SPRITE - altoJugador * 2;

		return new Rectangle(x, y, ancho, alto);
	}
}
