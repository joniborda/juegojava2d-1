package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;

import principal.Constantes;

public class InterfazUsuario {

	public static void dibujarBarraResistencia(Graphics g, final int resistencia) {
		int ancho = Constantes.RECUPERACION_MAXIMA * resistencia / Constantes.RESISTENCIA_MAXIMA;
		Color rojoOscuro = new Color(153, 0, 0);

		g.setColor(Color.WHITE);
		g.drawRect(19, 99, 102, 17);

		g.setColor(Color.RED);
		g.fillRect(20, 100, ancho, 5);

		g.setColor(rojoOscuro);
		g.fillRect(20, 105, ancho, 10);
	}
}
