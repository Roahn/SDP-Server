import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.*;

public class UDPClient {

	public static void main(String[] args) throws Exception
	{
		
		
		DatagramSocket ds =new DatagramSocket();
		int i=0;
		Scanner sobj = new Scanner(System.in);
		System.out.println("Enter the Number");
		i=sobj.nextInt();
		
		//To Send the data
		
		byte b[] = String.valueOf(i).getBytes();
		InetAddress ia =InetAddress.getLocalHost();
		DatagramPacket dp = new DatagramPacket(b,b.length,ia,9999);
		ds.send(dp);
		
		//To receive the data from server
		byte b1[] = new byte[1024];
		DatagramPacket dp1 = new DatagramPacket(b1,b1.length);
		ds.receive(dp1);
		
		//To Display the data
		
		String str = new String(dp1.getData());
		
		System.out.println("Result is "+str);
		
		ds.disconnect();
		
	}

}
