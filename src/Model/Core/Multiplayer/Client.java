package Model.Core.Multiplayer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;


public class Client {
	
	private DatagramSocket datagramSocket;
	private int serverPort;
	private ClientThread thread;
	private InetAddress address;
	private  String portNewPlayer = null;

	public void join(Boolean isHost, String gameCode, String playerName, int playerNumber) {
		
		//String playerName = "player1";
		
		try {
			address = InetAddress.getByName("202.61.250.68");
			String playerData = new String(isHost + " " + playerName + " " + gameCode + " " + playerNumber);
            byte[] b = playerData.getBytes();
            datagramSocket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(b, b.length, address, 4745);
            datagramSocket.send(packet);
            boolean waitingForReply = true;
           
            while (waitingForReply) {
            	System.out.println("here");
	            byte[] c = new byte[1024];
                DatagramPacket packet1 = new DatagramPacket(c, c.length);
                datagramSocket.receive(packet1);
                String AllInfo = new String(packet1.getData(), 0, packet1.getLength());
                String AllInfos[] = AllInfo.split(" ");
                String trueOrFalse = AllInfos[0];
                portNewPlayer = AllInfos[1];
                System.out.println(trueOrFalse);
                System.out.println(portNewPlayer);
                if (trueOrFalse!=null) waitingForReply=false;
            }

           
            
	        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nerwork Error", "Network error", 1);
            //game.setScreen(new MainMenuScreen(game));
        }
		
	}
	
	public void startThread() {
		
		thread = new ClientThread(address, Integer.parseInt(portNewPlayer), datagramSocket);
        thread.start();
	}
	
	

}
