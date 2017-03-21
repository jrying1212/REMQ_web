import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer extends Thread {

	
	public static void main(String[] args) throws IOException {
		int filesize=1022386; 
		int bytesRead; 
		int currentTot = 0; 
		ServerSocket serverSocket = new ServerSocket(21501); 
		Socket socket = serverSocket.accept(); 
		System.out.println("Accepted connection : " + socket); 
		
		byte [] bytearray = new byte [filesize]; 
		InputStream is = socket.getInputStream(); 
		FileOutputStream fos = new FileOutputStream("C://uploads/test.txt"); 
		BufferedOutputStream bos = new BufferedOutputStream(fos); 
		bytesRead = is.read(bytearray,0,bytearray.length); 
		currentTot = bytesRead; 

		while(bytesRead > -1){
			bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot)); if(bytesRead >= 0) currentTot += bytesRead;
		}
		bos.write(bytearray, 0 , currentTot); 
		bos.flush(); 
		bos.close(); 
		socket.close();

    }     
	

}
