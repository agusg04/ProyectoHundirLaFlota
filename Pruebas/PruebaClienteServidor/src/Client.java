import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 40001);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        Scanner sn = new Scanner(System.in);
        
        //Mensaje inicial servidor
        System.out.println(in.readUTF());
        
        while (true) {
            System.out.println("Envie la orden deseada con el formato correxto");
            String orden = sn.nextLine();
            out.writeUTF(orden);
            out.flush();

            // Consumir el salto de línea pendiente después de nextInt()
            //sn.nextLine();

            /*
			System.out.println("Escriba el mensaje");
			String mensaje = sn.nextLine();
			out.writeUTF(mensaje);
			out.flush();
			*/
            
            System.out.println(in.readUTF());            
        }
                
        
    }
}