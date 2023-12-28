import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(40001);

		System.out.println("Servidor a la espera de conexiones");

		Socket s = server.accept();
		System.out.println("Cliente conectado en " + s);
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());

		// Enviar mensaje inicial
		out.writeUTF("Bienvenido al chat");
		out.flush();
		
		while (s.isConnected()) {
			String orden = in.readUTF();
			
			String[] partes = orden.split(";");

			switch (partes[0]) {
			case "D":
				
				//mensaje = in.readUTF();
				
				System.out.println("Se ha elegido la opcion 'Disparar': " + orden);
				
				// Enviar mensaje
				out.writeUTF("Se ha elegido la opcion 'Disparar': " + orden);
				out.flush();
				
				break;
			case "R":		
				
				System.out.println("Se ha elegido la opcion 'Rendirse': " + orden);
				
				// Enviar mensaje
				out.writeUTF("Se ha elegido la opcion 'Rendirse': " + orden);
				out.flush();
			
				break;
			}
		}
	}
}