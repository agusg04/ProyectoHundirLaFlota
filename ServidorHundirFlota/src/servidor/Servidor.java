package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Servidor {
	ServerSocket socketServidor = null;
	Socket socketCliente = null;
	GestionarHundirLaFlota gestionarHundirLaFlota;
	

	public static void main(String[] args) {
		
		GestionarHundirLaFlota gestionarHundirLaFlota = new GestionarHundirLaFlota();
		
		final int PUERTO = 5000;
		
		try {
			ServerSocket socketServidor = new ServerSocket(PUERTO);
			
			System.out.println("Servidor esperando conexiones en el puerto " +PUERTO);
			
			System.out.println("Iniciando linea de chat");
			
			//crear instancia gestor hundir flota
			gestionarHundirLaFlota = new GestionarHundirLaFlota();
			
			
			while (true) {
				
				 Socket socketCliente = socketServidor.accept();				
								
				System.out.println("Cliente conectado desde " + socketCliente.getRemoteSocketAddress());
				
				GestionarCliente gestionarCliente = new GestionarCliente(socketCliente, gestionarHundirLaFlota);
				gestionarHundirLaFlota.agregarCliente(gestionarCliente);
				
				Thread hiloCliente = new Thread(gestionarCliente);
				
				hiloCliente.start();
				
			}
		} catch (IOException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}

}
