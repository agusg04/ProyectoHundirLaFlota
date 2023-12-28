package servidor;

import java.util.ArrayList;

public class GestionarHundirLaFlota {
	private ArrayList<GestionarCliente> clientesConectados;
	
	public GestionarHundirLaFlota() {
		this.clientesConectados = new ArrayList<>();
	}
	
	public void agregarCliente(GestionarCliente cliente) {
		clientesConectados.add(cliente);
		
	}
	
	public void eliminarCliente(GestionarCliente cliente) {
		clientesConectados.remove(cliente);
		
	}
	
	public synchronized void difundirMensaje(String mensaje, GestionarCliente remitente) {
		for (GestionarCliente cliente : clientesConectados) {
			if (cliente != remitente) {
				cliente.enviarMensaje(remitente.getNombre() + ": " + mensaje);
				
			}
		}	
	}
	
	
	
	
	

}
