package GUI;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/*explicacion de direccion que va cada tramo de ruta
 *1 2 3
 *4 0 5
 *6 7 8
 *posion 0 es la posicion actual , resto son las direccion
 */
public class Aestrella {
	private Set<Point> casillasProhibida;
	private Set<Point> rutaPoints;
	private HashMap<Point, Integer> direcciones;
	private Point ini;
	private Point fin;
	
	public Aestrella(Set<Point> prohibido, Point inicio, Point meta) {
		ini=inicio;
		fin=meta;
		casillasProhibida= prohibido;
	}
	public Boolean existeruta(Point p) {
		return rutaPoints.contains(p);
	}
	public Integer direccion(Point p) {
		return direcciones.get(p);
	}
}
