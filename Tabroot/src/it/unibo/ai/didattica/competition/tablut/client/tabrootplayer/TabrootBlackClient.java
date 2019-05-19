package it.unibo.ai.didattica.competition.tablut.client.tabrootplayer;

import java.io.IOException;
import java.net.UnknownHostException;

public class TabrootBlackClient {
	
	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException {
		String[] array = new String[]{"BLACK"};
		if (args.length>0){
			array = new String[]{"BLACK", args[0]};
		}
		TabrootClient.main(array);
	}

}
