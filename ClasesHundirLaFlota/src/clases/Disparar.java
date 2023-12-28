package clases;

public class Disparar {
	
	private int idUsuario;
	private int posicionY;
	private int posicionX;
	private int idPartida;
	
	public Disparar(int idUsuario, int posicionY, int posicionX, int idPartida) {
		this.idUsuario = idUsuario;
		this.posicionY = posicionY;
		this.posicionX = posicionX;
		this.idPartida = idPartida;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	@Override
	public String toString() {
		return "Disparo realizado a la posicion " + posicionX + " X " + posicionY + " Y, por el usuario " + idUsuario + " en la partida " + idPartida;  
	}
	
	
	
	
	
	
	

}
