package Model.Core.Multiplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
		
		
			// connessione con il server ed inzializzazione giocatore
			try {
				address = InetAddress.getByName("202.61.250.68");
			
			
			boolean waitingToSend = true;
			
			while(waitingToSend) {
				
				String playerData = new String(isHost + " " + playerName + " " + gameCode + " " + playerNumber);
				
				byte[] b = playerData.getBytes();
	            datagramSocket = new DatagramSocket();
	            DatagramPacket packet = new DatagramPacket(b, b.length, address, 4864);
	            datagramSocket.send(packet);
	            
	            
	            boolean waitingForReply = true;
	           //
	            while (waitingForReply) {
	           
	            	//
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
	                
	                System.out.println(AllInfo);
	                

	                if (isJoined.equals("false")) {
	                	
	                	waitingToSend = false;
	                	waitingForReply = false;
	                	
	                	game.multiplayerError(); 
	                }
	                
	                
	               if (isJoined.equals("true")) {
	                	
	            	   waitingToSend = false;
	            	   waitingForReply = false;
	            	   
	            	   if(numberOfMissingPlayer == 0) {
	            		   game.setNumberOfPlayer(numberOfPlayer);
	            		   game.setPlayerIndex(playerIndex);
	            		   game.setNumberOfMissingPlayer(numberOfMissingPlayer);
	            		   game.startGame();
	            	   }
	            	   
	            	   else {
	            		   
	            		   game.waitingMissingPlayer(); //apre menu di attesa
	            		   
	            		   while(numberOfMissingPlayer != 0) {
	            			   
		            			byte[] d = new byte[1024];
		       	                DatagramPacket packet2 = new DatagramPacket(d, d.length);
		       	                datagramSocket.receive(packet2);
		       	                String numberOfMissingPlayerString = new String(packet2.getData(), 0, packet2.getLength());
		       	                
		    	                numberOfMissingPlayer = Integer.parseInt(numberOfMissingPlayerString);

		       	                game.setNumberOfMissingPlayer(numberOfMissingPlayer);
		       	                game.updateMissingPlayer();
	            			   
	            		   }
	            		   
	            		   game.setNumberOfPlayer(numberOfPlayer);
	            		   game.setPlayerIndex(playerIndex);
	            		   game.setNumberOfMissingPlayer(numberOfMissingPlayer);
	            		   game.startGame();
	           
	            	   }
	                	
	                }
	                	
	            }
			}

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
	        
       
	}
	
	public void startThread(MultiplayerScreen multiplayerScreen) {
		
		thread = new ClientThread(address, portNewPlayer, datagramSocket, multiplayerScreen);
        thread.start();
	}
	
	

}
