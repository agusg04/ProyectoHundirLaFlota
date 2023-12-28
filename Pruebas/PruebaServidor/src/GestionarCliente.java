import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class GestionarCliente implements Runnable {

	private Socket socketCliente;
	private GestionarHundirLaFlota gestionarHundirLaFlota;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String nombre;
	private ArrayList<Integer> miArray;

	public GestionarCliente(Socket socketCliente, GestionarHundirLaFlota gestionarHundirLaFlota, ObjectInputStream ois,
			ObjectOutputStream oos, String nombre) {

		this.socketCliente = socketCliente;
		this.gestionarHundirLaFlota = gestionarHundirLaFlota;
		this.ois = ois;
		this.oos = oos;
		this.nombre = nombre;
		this.miArray = new ArrayList<>();

	}

	public String getNombre() {
		return nombre;
	}

	public void enviarMensaje(String mensaje) {

		try {
			oos.writeUTF(mensaje);
			oos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void modificarArray(int pos, int valor) {		
		for (int i = 0; i <= pos; i++) {
			if (i == pos) {
				this.miArray.set(i, valor);	
			}
		}
		
	}
	
	public void mostrarArray() {
		
		for (int i = 0; i < this.miArray.size(); i++) {
			System.out.print(this.miArray.get(i) + " ");
		}
	}
	
	public String mostrarArrayCliente() {
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < this.miArray.size(); i++) {
			stringBuilder.append(this.miArray.get(i)).append(" ");
		}
		
		return stringBuilder.toString();
	}
	
	public void rellenarArray() {
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
			this.miArray.add(random.nextInt(10) + 1);
		}
	}

	@Override
	public void run() {

		gestionarHundirLaFlota.difundirMensaje("Se ha unido al chat", this);
		
		rellenarArray();
		enviarMensaje(mostrarArrayCliente());
		mostrarArray();

		try {
			while (socketCliente.isConnected()) {
				String orden = ois.readUTF();
				
				String[] partes = orden.split(";");
				
				switch (partes[0]) {
				case "D":
					
					//mensaje = in.readUTF();
					
					System.out.println("Se ha elegido la opcion 'Disparar': " + orden);
					
					// Enviar mensaje
					oos.writeUTF("Se ha elegido la opcion 'Disparar': " + orden);
					oos.flush();
					
					gestionarHundirLaFlota.enviarMensajePrivado(partes[0], partes[1], this);
					
					break;
				case "R":		
					
					System.out.println("Se ha elegido la opcion 'Rendirse': " + orden);
					
					// Enviar mensaje
					oos.writeUTF("Se ha elegido la opcion 'Rendirse': " + orden);
					oos.flush();
					
					gestionarHundirLaFlota.enviarMensajePrivado(partes[0], partes[1], this);

				
					break;
				case "S":
					cerrarSocket();
					
				case "C":
					
					System.out.println("Se ha elegido la opcion 'Cambiar': " + orden);
					
					// Enviar mensaje
					oos.writeUTF("Se ha elegido la opcion 'Cambiar': " + orden);
					oos.flush();

					gestionarHundirLaFlota.modificarArray(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), partes[3], this);					
					
				}


			}

			gestionarHundirLaFlota.difundirMensaje("Se ha desconectao del chat", this);

			cerrarSocket();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cerrarSocket() {

		try {
			ois.close();
			oos.close();
			socketCliente.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}