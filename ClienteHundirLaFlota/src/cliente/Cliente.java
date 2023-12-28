package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

	public Cliente() {
		
	}

	public static void main(String[] args) {
		
		final String HOST = "localhost";
		final int PUERTO = 5000;
		
		try {
			Socket socketCliente = new Socket(HOST, PUERTO);
			
			DataInputStream dis = new DataInputStream(socketCliente.getInputStream());
            DataOutputStream dos = new DataOutputStream(socketCliente.getOutputStream());
            
            System.out.print("Ingrese su nombre: ");
            
            Scanner scanner = new Scanner(System.in);
            String nombre = scanner.nextLine();
            
            dos.writeUTF(nombre);
            
            Thread hiloRecepcion = new Thread(() -> {
            	try {
					while (true) {
						String mensaje = dis.readUTF();
						System.out.println(mensaje);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            });
            
            hiloRecepcion.start();
            
            String mensaje;
            
			System.out.println("Ecriba su mensaje. Escribe 'bye' para salir del chat");
			
			do {
			
				mensaje = scanner.nextLine();
				
				dos.writeUTF(mensaje);
								
			} while (!mensaje.equalsIgnoreCase("bye"));

			
		} catch (IOException e) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
