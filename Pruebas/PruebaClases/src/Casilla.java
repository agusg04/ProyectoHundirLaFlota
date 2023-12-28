
public class Casilla {
	
	private boolean ocupada;

	public Casilla() {
		this.ocupada = false;
	}

	public boolean estaOcupada() {
		return ocupada;
	}

	public void ocupar() {
		this.ocupada = true;
	}

	public void desocupar() {
		this.ocupada = false;
	}

}
