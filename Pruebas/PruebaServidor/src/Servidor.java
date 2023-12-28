import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

	final int PUERTO;

	public Servidor(int puerto) {
		this.PUERTO = puerto;

	}

	public void iniciarServidor() {
		try (ServerSocket socketServidor = new ServerSocket(PUERTO)){
			
			Socket socketCliente;

			System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

			System.out.println("Iniciando linea de chat");

			// crear instancia gestor hundir flota
			GestionarHundirLaFlota gestionarHundirLaFlota = new GestionarHundirLaFlota();
			
			//Añadir usuarios
			gestionarHundirLaFlota.agregarUsuarios();
			
			//Añadir usuarios
			//gestionarHundirLaFlota.escribirUsuarios("usuarios.txt");
			
			//Leer usuarios
			gestionarHundirLaFlota.agregarUsuarios();

			while (true) {

				socketCliente = socketServidor.accept();
				System.out.println("Cliente conectado desde " + socketCliente);
				
				ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
				
				oos.writeUTF("Bienvenido al chat");
				oos.flush();
				
				String nombreCliente = ois.readUTF();
				System.out.println("Nombre leido");
				
				String contrasenia = ois.readUTF();
				System.out.println("Contrasenia leida");
				
				if (gestionarHundirLaFlota.comprobarUsuario(nombreCliente, contrasenia)) {
					oos.writeUTF("Inicio de sesion exitoso");
					oos.flush();
					
					GestionarCliente nuevoCliente = new GestionarCliente(socketCliente, gestionarHundirLaFlota, ois, oos, nombreCliente);
					gestionarHundirLaFlota.agregarCliente(nuevoCliente);
					
					new Thread(nuevoCliente).start();
				}else {
					oos.writeUTF("Contraseña o usuario incorrecto Espabila");
					oos.flush();
					System.out.println("Contraseña o usuario incorrecto Espabila");
				}

			}
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}

	public static void main(String[] args) {
		Servidor servidor = new Servidor(5000);

		servidor.iniciarServidor();

	}

}