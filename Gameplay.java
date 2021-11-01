package GamePlay;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.util.*;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel  implements KeyListener,ActionListener
{
	
	///////////////////Networking////////////////
		DatagramSocket ds  =new DatagramSocket();
		public InetAddress ia =InetAddress.getLocalHost();
		//InetAddress address = packet.getAddress();
	///////////////////////////////////////////////
		private boolean play = false;
		private int score = 0;
		private Timer timer ;
		private int delay = 1;
		private int speed = 2;
		private int playerX = 310;
		public  int ballPosX=124;
		public  int ballPosY=350;
		private int ballYdir = -1;
		private int ballXdir = -2;
		private int paddleWidth=100;
		private int paddleHeight=8;
		private int paddleY=645;
		private int otherPlayerX =150;
		//private int Background 
		//UDP client =new UDP();
		network n1= new network();
		
		
		public void moveRight()
		{
			play =true;
			playerX= playerX+20;
		}
		public void moveLeft()
		{
			play =true;
			playerX= playerX-20;
			
		}
		
	

		public void paint(Graphics g)
		{	
			//Background
			g.setColor(Color.blue);
			g.fillRect(1, 1, 600, 700);
			
			//borders
			g.setColor(Color.yellow);
			//left border
			g.fillRect(0,0,10,700);
			//top border
			g.fillRect(0,0,692,3);
			//right border
			g.fillRect(575,0,10,700);
			
			//bottom border
			g.fillRect(0,655,575,10);
			
			//center line
			
			g.setColor(Color.yellow);
			g.fillRect(0, 330, 600,4 );
			
			//paddle
			g.setColor(Color.green);
			g.fillRect(playerX, paddleY, paddleWidth,paddleHeight);
			
			//Ball
			g.setColor(Color.yellow);
			g.fillOval(ballPosX, ballPosY, 20, 20);
			
			//Other Player
			g.setColor(Color.red);
			g.fillRect(otherPlayerX, 50, paddleWidth,paddleHeight);
			
			
			//System.out.println(ballPosX);
			//System.out.println(ballPosY);
			
			
		}
	
		public void keyTyped(KeyEvent e) 
		{
			
		}

	
	
	
		public void keyReleased(KeyEvent e) 
		{
		
		
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		public void actionPerformed(ActionEvent e) 
		{
			timer.start();
			
			
			if(play)
			{
				/////////////////////////////////////////////////////////////////////////
				/////////////Networking////////////////////////////////////////////
				
				
				//Creating String
				 
				byte b2[]= (playerX+"").getBytes();
			//	int i= 10000;
			//	byte b[] = String.valueOf(i).getBytes();
			
				//InetAddress ia =InetAddress.getLocalHost();
				/*if(play==true)
				{	 i= 10000;
					 b = String.valueOf(i).getBytes();//10000 is the value for play == true
					//to say that client is ready
				}*/
				
				DatagramPacket dp = new DatagramPacket(b2,b2.length,ia,9999);
				////////////////////////////////////////////////////////////////////////
				///////////Send Data to the server//////////////////////////////////////
				try {
					ds.send(dp);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				///////////////////////////////////////////////////////////////////////////////////
				///////////Receive the data from server//////////////////////////////////////////
				byte b1[] = new byte[1024];
				DatagramPacket dp1 = new DatagramPacket(b1,b1.length);
				try {
					ds.receive(dp1);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String str = new String(dp1.getData());
				
				System.out.println(str);
				
				//Integer re = Integer.parseInt(str);
				int num = Integer.parseInt(str.trim());
				otherPlayerX=num;
				////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////
				/*
				
				//ballPosX = ballPosX+ (ballXdir*speed);
				ballPosY = ballPosY+ (ballYdir*speed);
				
				int intValue = num;
				ballPosX =intValue+ (ballXdir*speed);
				//ballPosX = ballPosX+ (ballXdir*speed);
				
				if( new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,paddleY,paddleWidth,paddleHeight)))
				{
					ballYdir= -ballYdir;
				}
				//client.Display();
				if(ballPosX<0)
				{
					ballXdir = -ballXdir;
				}
				if(ballPosY<0)
				{
					ballYdir = -ballYdir;
				}
				if(ballPosX>555)
				{
					ballXdir = -ballXdir;
				}
				*/
			}
			
			
				ds.disconnect();
			
			repaint();
			
		}
		
		
		public void keyPressed(KeyEvent e) 
		{
		
			if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			{
					if(playerX >=475)
					{
						playerX = 475;
					}
					else
					{
							
						moveRight();
					}
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT)
			{
				if(playerX <= 10)
				{
					playerX = 10;
				}
				else
				{
						
					moveLeft();
				}
			}
		
		}
	


		public void BallPosition(int ballPosX,int ballPosY)
		{
			this.ballPosX = ballPosX;
			this.ballPosY = ballPosY;
			
			

			
			
		}
		public void createSocket()throws Exception
		{
			
		}
	
	public Gameplay() throws Exception
	{	
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay,this);
		timer.start();
		
			
	}

	
	
}

class network
{
	public int  rub()throws Exception
	{
		DatagramSocket ds =new DatagramSocket();
		System.out.println("Inside network");
		int i=10;
		//Scanner sobj = new Scanner(System.in);
		//System.out.println("Enter the Number");
		//i=sobj.nextInt();
		
		//To Send the data
		
		byte b[] = String.valueOf(i).getBytes();
		InetAddress ia =InetAddress.getLocalHost();
		DatagramPacket dp = new DatagramPacket(b,b.length,ia,9999);
		ds.send(dp);
		
		//To receive the data from server
		byte b1[] = new byte[1024];
		DatagramPacket dp1 = new DatagramPacket(b1,b1.length);
		System.out.println("Inside1 network");
		ds.receive(dp1);
		
		System.out.println("Inside2 network");
		
		//To Display the data
		
		String str = new String(dp1.getData());
		
		System.out.println("Result is "+str);
		
		ds.disconnect();
		int re = Integer.valueOf(str);
		return re;
	}
	
	
}



























