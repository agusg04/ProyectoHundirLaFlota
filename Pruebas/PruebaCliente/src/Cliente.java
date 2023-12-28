import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		final String HOST = "localhost";
		final int PUERTO = 5000;

		try (Socket socketCliente = new Socket(HOST, PUERTO);
				ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
				Scanner scanner = new Scanner(System.in);){

			// Leer mensaje de bienvenida
			System.out.println(ois.readUTF());

			System.out.println("Introduce tu nombre: ");
			String nombre = scanner.nextLine();

			oos.writeUTF(nombre);
			oos.flush();

			System.out.println("Introduce tu contrasenia: ");
			String contrasenia = scanner.nextLine();

			oos.writeUTF(contrasenia);
			oos.flush();

			System.out.println(ois.readUTF());

			// Hilo para escuchar mensajes del servidor
			Thread hiloRecepcion = new Thread(() -> {
				try {
					while (true) {
						String mensajeRecepcion = ois.readUTF();
						System.out.println(mensajeRecepcion);

						// Comprobar si el mensaje indica que el cliente debe cerrarse
						if (mensajeRecepcion.equals("Se ha desconectado del chat")) {
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			hiloRecepcion.start();

			System.out.println("Escriba la orden con el formato correcto 'D/R;Usuario'");

			while (true) {
				// scanner.nextLine();

				String orden = scanner.nextLine();

				oos.writeUTF(orden);
				oos.flush();

				if (orden.equals("S")) {
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}