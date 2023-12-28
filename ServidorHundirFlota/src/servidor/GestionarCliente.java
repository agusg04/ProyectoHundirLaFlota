package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GestionarCliente implements Runnable {
	
	private String nombre;
	private Socket socketCliente;
	private DataInputStream dis;
	private DataOutputStream dos;
	private GestionarHundirLaFlota gestionarHundirLaFlota;
	
	public GestionarCliente(Socket cliente, GestionarHundirLaFlota gestionarHundirLaFlota) {
		this.socketCliente = cliente;
		this.gestionarHundirLaFlota = gestionarHundirLaFlota;
		
		try {
			this.dis = new DataInputStream(cliente.getInputStream());
            this.dos = new DataOutputStream(cliente.getOutputStream());
            
            this.nombre = dis.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getNombre() {
		return nombre;
	}

	public void enviarMensaje(String mensaje) {
		try {
			dos.writeUTF(mensaje);
			dos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void run() {
		
		try {
						
			gestionarHundirLaFlota.difundirMensaje("Se ha unido al chat", this);
			
			String mensaje;
			
			do {
				
				mensaje = dis.readUTF();
				
				gestionarHundirLaFlota.difundirMensaje(mensaje, this);

				
			} while (!mensaje.equalsIgnoreCase("bye"));
			
			gestionarHundirLaFlota.eliminarCliente(this);

			socketCliente.close();
			
            System.out.println("Cliente desconectado: " + this.getNombre());

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}
