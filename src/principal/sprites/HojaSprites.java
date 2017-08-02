package principal.sprites;

import java.awt.image.BufferedImage;

import principal.herramientas.CargadorRecursos;

public class HojaSprites {

	final private int anchoHojaEnPixeles;
	final private int altoHojaEnPixeles;

	final private int anchoHojaEnSprites;
	final private int altoHojaEnSprites;

	final private int anchoSprites;
	final private int altoSprites;

	final private Sprite[] sprites;

	public HojaSprites(final String ruta, final int tamanoSprites, final boolean hojaOpaca) {
		final BufferedImage imagen;

		if (hojaOpaca) {
			imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
		} else {
			imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
		}

		this.anchoHojaEnPixeles = imagen.getWidth();
		this.altoHojaEnPixeles = imagen.getHeight();

		anchoHojaEnSprites = anchoHojaEnPixeles / tamanoSprites;
		altoHojaEnSprites = altoHojaEnPixeles / tamanoSprites;

		anchoSprites = tamanoSprites;
		altoSprites = tamanoSprites;

		sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];

		rellenarSpritesDesdeImagen(imagen);
	}
	public HojaSprites(final String ruta, final int anchoSprites, final int altoSprites, final boolean hojaOpaca) {
		final BufferedImage imagen;

		if (hojaOpaca) {
			imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
		} else {
			imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
		}

		this.anchoHojaEnPixeles = imagen.getWidth();
		this.altoHojaEnPixeles = imagen.getHeight();

		anchoHojaEnSprites = anchoHojaEnPixeles / anchoSprites;
		altoHojaEnSprites = altoHojaEnPixeles / altoSprites;

		this.anchoSprites = anchoSprites;
		this.altoSprites = altoSprites;

		sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];

		rellenarSpritesDesdeImagen(imagen);
	}

	private void rellenarSpritesDesdeImagen(final BufferedImage imagen) {
		for (int y = 0; y < altoHojaEnSprites; y++) {
			for (int x = 0; x < anchoHojaEnSprites; x++) {
				final int positionX = x * anchoSprites;
				final int positionY = y * altoSprites;

				sprites[x + y * anchoHojaEnSprites] = new Sprite(imagen.getSubimage(positionX, positionY, anchoSprites,
						altoSprites));
			}
		}
	}

	public Sprite getSprite(final int indice) {
		return sprites[indice];
	}

	public Sprite getSprite(final int x, final int y) {
		return sprites[x + y * anchoHojaEnSprites];
	}
}
