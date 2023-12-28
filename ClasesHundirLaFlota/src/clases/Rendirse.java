package clases;

public class Rendirse {
	
	private int idUsuario;
	private int idPartida;
	private boolean rendir;
	
	public Rendirse(int idUsuario, int idPartida, boolean rendir) {
		this.idUsuario = idUsuario;
		this.idPartida = idPartida;
		this.rendir = rendir;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public boolean isRendir() {
		return rendir;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public void setRendir(boolean rendir) {
		this.rendir = rendir;
	}

	@Override
	public String toString() {
		return "El usuario " + idUsuario + " se ha rendido en la partida " + idPartida;
	}
	
	
	
	
	
	


}
