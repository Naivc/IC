package GUI;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;
import java.math.*;
import java.security.PublicKey;

/*explicacion de direccion que va cada tramo de ruta
 *1 2 3
 *4 0 5
 *6 7 8
 *posion 0 es la posicion actual , resto son las direccion
 */
public class Aestrella {
	public class Pair {
		public Point p;
		public double d;

		public Pair(Point p, double d) {
			// TODO Auto-generated constructor stub
			this.p = p;
			this.d = d;
		}
	}

	private Set<Point> casillasProhibida;
	private Set<Point> rutaPoints;
	private Map<Point, Integer> direcciones;
	private Point ini;
	private Point fin;
	private int altura;
	private int anchura;

	// para A*
	private List<Pair> abiertaList;
	private List<Point> cerradaList;

	public Aestrella(Set<Point> prohibido, Point inicio, Point meta, int anchula, int altura) {
		ini = inicio;
		fin = meta;
		this.anchura = anchula;
		this.altura = altura;
		casillasProhibida = prohibido;

		// aqui se rellena los detalle a*
		double g = 0;
		double h = distancia(ini, fin);
		double f = g + h;
		// añadir ini a abierta
		abiertaList.add(new Pair(ini, f));
		// añadir las celdas prohibidas a la lista cerradas
		cerradaList.addAll(casillasProhibida);
		boolean llegaMeta = false;
		while (!abiertaList.isEmpty() && !llegaMeta) {
			Point actualPoint = abiertaList.get(0).p;
			abiertaList.remove(0);
			// añadir ini a cerrada y expandir ini
			cerradaList.add(actualPoint);
			// añadir los nodo accesible alredor de punto actual a lista abierta
			for (int i = -1; i < 2 && !llegaMeta; i++) {
				for (int j = -1; j < 2 && !llegaMeta; j++) {
					Point nextPoint = new Point(actualPoint.x + i, actualPoint.y + j);
					if (!acederFuera(nextPoint) && !cerradaList.contains(nextPoint) && !containEnAbierta(nextPoint)) {

						// calcula la distancia g+h y meter los nodo expandido a list abierto
						double newg = g + distancia(actualPoint, nextPoint);
						h = distancia(nextPoint, fin);
						f = newg + h;
						abiertaList.add(new Pair(nextPoint, f));
						if (nextPoint.equals(fin)) {
							cerradaList.add(nextPoint);
							llegaMeta = true;
						}
					}
				}
			}
			// ordenar las lista abierta
			Collections.sort(abiertaList, new Comparator<Pair>() {
				public int compare(Pair a, Pair b) {
					return Double.compare(a.d, b.d);
				}
			});
		}
		// retroceder la lista cerrada para conseguir la ruta
	}

	private boolean containEnAbierta(Point np) {
		// TODO Auto-generated method stub
		boolean puntoEncontrado = false;
		for (Pair pair : abiertaList) {
			if (pair.p.equals(np)) {
				puntoEncontrado = true;
				break;
			}
		}
		return puntoEncontrado;
	}

	private boolean acederFuera(Point actualPoint) {
		// TODO Auto-generated method stub
		return actualPoint.x < 0 || actualPoint.x >= anchura || actualPoint.y < 0 || actualPoint.y >= altura;
	}

	private double distancia(Point p1, Point p2) {
		// TODO Auto-generated method stub
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));

	}

	public Boolean existeruta(Point p) {
		return rutaPoints.contains(p);
	}

	public Integer direccion(Point p) {
		return direcciones.get(p);
	}
}
