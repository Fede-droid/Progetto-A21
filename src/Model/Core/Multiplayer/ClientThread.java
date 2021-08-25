package Model.Core.Multiplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import Model.Core.MultiplayerScreen;
import Model.Core.Screen;
import Model.Logic.Player;

public class ClientThread extends Thread {
    private DatagramSocket socket;
    private byte[] buf=new byte[4096];
    private String message;
   //private Player player1;
    private int serverPort;
    private DatagramSocket datagramSocket;
    private MultiplayerScreen screen;

    public ClientThread(InetAddress address, int port,  DatagramSocket socket, MultiplayerScreen screen) {
        message = "";
        this.serverPort=port;
        socket.connect(address, serverPort);
        //this.player1=player1;
        this.datagramSocket=socket;
        this.screen=screen;
        
    }

    /**
     * Metodo che viene chiamato ogni volta che parte il Thread
     */
    synchronized public void run() {
    	
    	try {
			datagramSocket.setSoTimeout(30);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        while (true) {
			try {
				byte[] b = new byte[1024];
	        	b = (((Integer) screen.getPaddleXPosition()).toString()+" "+((Integer) screen.getPaddleYPosition()).toString()).getBytes();
	        	DatagramPacket packetBack;
				packetBack = new DatagramPacket(b, b.length, InetAddress.getByName(Client.serverIP), serverPort);
				datagramSocket.send(packetBack);
				
				byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                datagramSocket.receive(packet);
                String allGameInfos = new String(packet.getData(), 0, packet.getLength());
                //ystem.out.println(allGameInfos);
                
                screen.setStringGameStatus(allGameInfos);
                
				wait(25);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  catch (SocketTimeoutException e) {
	            // incoming package timeout exception.
	            System.out.println("Incoming package lost!");
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public String getMessage() {
        return message;
    }
     


}