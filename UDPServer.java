import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
public class UDPServer {

	public static void main(String[] args)throws Exception
	{
			DatagramSocket ds1 = new DatagramSocket(9999);
			

			System.out.println("Server is Running........9999");
			

			boolean a =true;
		
			
			while(a)

			{
					
			



			
			//recive from client
			byte b1[]=new byte[1024];
			
			DatagramPacket dp1 = new DatagramPacket(b1,b1.length);
		
			System.out.println("Server is Waiting.......for 9999");

			ds1.receive(dp1);
			System.out.println("Server is Received........for 9999");
			
			
			//Preparation
			String str1 = new String(dp1.getData());
			
			int num1 = Integer.parseInt(str1.trim());
		
			
			
			
			byte b11[] = (num1 + "").getBytes();
			
			
			InetAddress ia = dp1.getAddress();
			System.out.println(ia);
			DatagramPacket dp11 = new DatagramPacket(b11,b11.length,ia,dp1.getPort());
			System.out.println("From client........ 1"+num1);
			


			//send to client


			
			ds1.send(dp11);
			String exit = "exit";
			
			if(str1.equals(exit))
			{
				System.out.println("Server Stopeed........");
				a=false;
				ds1.disconnect();
				
			}



			}
			
	}

}
