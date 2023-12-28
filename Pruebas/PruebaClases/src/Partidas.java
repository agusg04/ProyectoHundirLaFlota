import java.util.ArrayList;

public class Partidas {
	private ArrayList<Tablero> tableros;
	
	public Partidas(int numTableros) {
		tableros = new ArrayList<>();
		
		for (int i = 0; i < numTableros; i++) {
			tableros.add(new Tablero());
		}
		
	}
	
	public void cargarPartidas() {}
	

}
