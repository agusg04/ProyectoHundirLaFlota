import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionarHundirLaFlota {
	private ArrayList<GestionarCliente> clientesConectados;
	private ArrayList<Usuario> usuarios;

	public GestionarHundirLaFlota() {
		this.clientesConectados = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}

	public void cargarUsuarios(String archivo) {
		try (ObjectInputStream leerArchivo = new ObjectInputStream(new FileInputStream(archivo))) {
			this.usuarios = (ArrayList<Usuario>) leerArchivo.readObject();
		} catch (Exception e) {
			System.out.println("Error de lectura");
		}

	}

	public void agregarUsuarios() {
		usuarios.add(new Usuario("Juan", "juan"));
		usuarios.add(new Usuario("Agu", "agu"));
		usuarios.add(new Usuario("Rafa", "rafa"));
		usuarios.add(new Usuario("Jose", "jose"));
		usuarios.add(new Usuario("Dani", "dani"));
	}

	public void escribirUsuarios(String archivo) {
		try (ObjectOutputStream escribirArchivo = new ObjectOutputStream(new FileOutputStream(archivo))) {
			escribirArchivo.writeObject(usuarios);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean comprobarUsuario(String nombre, String contrasenia) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(nombre) && usuario.getContrasenia().equals(contrasenia)) {
				return true;
			}
		}
		return false;
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

	public synchronized void enviarMensajePrivado(String mensaje, String destinatario, GestionarCliente remitente) {
		for (GestionarCliente cliente : clientesConectados) {
			if (cliente.getNombre().equals(destinatario)) {
				cliente.enviarMensaje("Mensaje privado de " + remitente.getNombre() + ": " + mensaje);
				return;
			}
		}
		remitente.enviarMensaje("El destinatario no está disponible o no existe.");
	}

	public synchronized void modificarArray(int posicion, int valor, String destinatario, GestionarCliente remitente) {
		for (GestionarCliente cliente : clientesConectados) {
			if (cliente.getNombre().equals(destinatario)) {
				cliente.modificarArray(posicion, valor);
				// cliente.mostrarArrayCliente();
				cliente.enviarMensaje(cliente.mostrarArrayCliente());
				cliente.enviarMensaje("Array modificado por " + remitente.getNombre());
				return;
			}
		}
	}

}