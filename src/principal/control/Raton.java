package principal.control;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;

public class Raton extends MouseAdapter {

	private final Cursor cursor;

	public Raton() {
		Toolkit configuracion = Toolkit.getDefaultToolkit();

		BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_CURSOR);

		Point punta = new Point(0, 0);
		this.cursor = configuracion.createCustomCursor(icono, punta, "cursor por defecto");

		configuracion.getBestCursorSize(10, 10);
	}

	public Cursor getCursor() {
		return this.cursor;
	}
}
