import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
public class UDPServer {

	public static void main(String[] args)throws Exception
	{
			DatagramSocket ds1 = new DatagramSocket(9999);
			DatagramSocket ds2 = new DatagramSocket(8888);

			boolean a =true;
			
			System.out.println("Server is Running........9999");
			System.out.println("Server is Running........8888");
			while(a)

			{
					
			



			
			//recive from client
			byte b1[]=new byte[1024];
			byte b2[] = new byte[1024];
			DatagramPacket dp1 = new DatagramPacket(b1,b1.length);
			DatagramPacket dp2 = new DatagramPacket(b2, b2.length);
			System.out.println("Server is Waiting.......for 9999");

			ds1.receive(dp1);
			System.out.println("Server is Received........for 9999");
			System.out.println("Server is Waiting.......for 8888");

			ds2.receive(dp2);
			System.out.println("Server is Received........for 8888");

			
			//Preparation
			String str1 = new String(dp2.getData());
			String str2 = new String(dp1.getData());
			
			int num1 = Integer.parseInt(str1.trim());
			int num2 = Integer.parseInt(str2.trim());
			
			
			
			byte b11[] = (num1 + "").getBytes();
			byte b22[]= (num2+"").getBytes();
			
			InetAddress ia = InetAddress.getLocalHost();
			DatagramPacket dp11 = new DatagramPacket(b11,b11.length,ia,dp1.getPort());
			System.out.println("From client........ 1"+num1);
			System.out.println("From client........2 "+num2);

			DatagramPacket dp22 = new DatagramPacket(b22, b22.length, ia, dp2.getPort());



			//send to client


			
			ds1.send(dp11);
			ds2.send(dp22);
			
			if(num1 >10000)
			{
				System.out.println("Server Stopeed........");
				a=false;
				ds1.disconnect();
				ds2.disconnect();
			}



			}
			
	}

}
