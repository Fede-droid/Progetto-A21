package Model.Core.Multiplayer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;

import Model.BreakoutGame;
import Model.Core.MultiplayerScreen;
import Model.Core.Screen;


public class Client {
	
	private DatagramSocket datagramSocket;
	private int serverPort;
	private ClientThread thread;
	private InetAddress address;
	private int portNewPlayer, numberOfMissingPlayer, numberOfPlayer, playerIndex;

	public void join(BreakoutGame game, Boolean isHost, String gameCode, String playerName, int playerNumber) {
		
		//String playerName = "player1";
		
		try {
			
			// connessione con il server ed inzializzazione giocatore
			address = InetAddress.getByName("202.61.250.68");
			
			boolean waitingToSend = true;
			
			while(waitingToSend) {
				
				String playerData = new String(isHost + " " + playerName + " " + gameCode + " " + playerNumber);
				System.out.println(playerData);
				byte[] b = playerData.getBytes();
	            datagramSocket = new DatagramSocket();
	            DatagramPacket packet = new DatagramPacket(b, b.length, address, 4861);
	            datagramSocket.send(packet);
	            
	            
	            boolean waitingForReply = true;
	           
	            while (waitingForReply) {
	           
	            	
		            byte[] c = new byte[1024];
	                DatagramPacket packet1 = new DatagramPacket(c, c.length);
	                datagramSocket.receive(packet1);
	                String AllInfo = new String(packet1.getData(), 0, packet1.getLength());
	                
	                String AllInfos[] = AllInfo.split(" ");
	                
	                String isJoined = AllInfos[0];
	               
	                portNewPlayer = Integer.parseInt(AllInfos[1]);
	                
	                numberOfMissingPlayer = Integer.parseInt(AllInfos[2]);
	                
	                numberOfPlayer = Integer.parseInt(AllInfos[3]);
	                
	                playerIndex = Integer.parseInt(AllInfos[4]);
	                
	                System.out.println(isJoined);
	                

	                if (isJoined.equals("false")) {
	                	
	                	waitingToSend = false;
	                	waitingForReply = false;
	                	
	                	game.multiplayerError(); 
	                	}
	                
	                
	               if (isJoined.equals("true")) {
	                	
	            	   waitingToSend = false;
	            	   waitingForReply = false;
	                	
	                	game.startGame();
	                	}
	                	
	            }
			}

           
            
	        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nerwork Error", "Network error", 1);
            //game.setScreen(new MainMenuScreen(game));
        }
		
	}
	
	public void startThread(MultiplayerScreen multiplayerScreen) {
		
		thread = new ClientThread(address, portNewPlayer, datagramSocket, multiplayerScreen);
        thread.start();
	}
	
	

}
