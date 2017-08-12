package principal;

public class Constantes {

	public static final int LADO_SPRITE = 32;
	public static final int LADO_TILE = 32;

	public static int ANCHO_JUEGO = 1280;
	public static int ALTO_JUEGO = 720;

	public static int ANCHO_PANTALLA_COMPLETA = 1366;
	public static int ALTO_PANTALLA_COMPLETA = 768;

	public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
	public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;

	public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
	public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;

	public static int APS = 0;
	public static int FPS = 0;

	public static int APS_CONTADOR = 0;
	public static int FPS_CONTADOR = 0;

	public static String RUTA_JUGADOR = "/imagenes/hojasPersonajes/2.png";
	public static String RUTA_MAPA = "/imagenes/hojasTexturas/1.png";
	public static String RUTA_CURSOR = "/imagenes/cursores/2.png";
	public static String RUTA_LOGO = "/imagenes/hojasPersonajes/1.png";

	public static final int RESISTENCIA_MAXIMA = 600;
	public static final int RECUPERACION_MAXIMA = 100;
}
