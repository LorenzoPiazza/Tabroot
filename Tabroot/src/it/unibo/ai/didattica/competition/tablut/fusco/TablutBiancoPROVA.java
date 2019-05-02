package it.unibo.ai.didattica.competition.tablut.fusco;

import java.io.IOException;
import java.net.UnknownHostException;



import it.unibo.ai.didattica.competition.tablut.domain.*;
/**
 * 
 * @author A. Piretti, Andrea Galassi
 *
 */
public class TablutBiancoPROVA extends TablutClient {

	private int game;
	
	
	public TablutBiancoPROVA(String player, String name, int gameChosen) throws UnknownHostException, IOException {
		super(player, name);
		game = gameChosen;
	}

	public TablutBiancoPROVA(String player) throws UnknownHostException, IOException {
		this(player, "random", 4);
	}

	public TablutBiancoPROVA(String player, String name) throws UnknownHostException, IOException {
		this(player, name, 4);
	}

	public TablutBiancoPROVA(String player, int gameChosen) throws UnknownHostException, IOException {
		this(player, "random", gameChosen);
	}
	


	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		int gametype = 4;
		String role = "";
		String name = "random";
		// TODO: change the behavior?
		if (args.length < 1) {
			System.out.println("You must specify which player you are (WHITE or BLACK)");
			System.exit(-1);
		} else {
			System.out.println(args[0]);
			role = (args[0]);
		}
		if (args.length == 2) {
			System.out.println(args[1]);
			gametype = Integer.parseInt(args[1]);
		}
		if (args.length == 3) {
			name = args[2];
		}
		System.out.println("Selected client: " + args[0]);

		TablutBiancoPROVA client = new TablutBiancoPROVA(role, name, gametype);
		client.run();
	}

	@Override
	public void run() {
		
		try {
			this.declareName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		State state;

			Game rules = null;
		
			state = new StateTablut();
			state.setTurn(State.Turn.WHITE);
			rules = new GameTablut();
			System.out.println("Tablut game");
			
	

		Action a;
		
		System.out.println("You are player " + this.getPlayer().toString() + "!");

		while (true) {
			
			try {
				this.read();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(1);
			}
			System.out.println("Current state:");
			state = this.getCurrentState();
			System.out.println(state.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			
				
				if (this.getCurrentState().getTurn().equals(StateTablut.Turn.WHITE)) {
						
				// è il mio turno
				
				
				
				a=makeDecision(state);
				
				
				
				System.out.println("Mossa scelta: " + a.toString());
				
					try {
						this.write(a);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				

				}
				// è il turno dell'avversario
				else if (state.getTurn().equals(StateTablut.Turn.BLACK)) {
					System.out.println("Waiting for your opponent move... ");
				}
				// ho vinto
				else if (state.getTurn().equals(StateTablut.Turn.WHITEWIN)) {
					System.out.println("YOU WIN!");
					System.exit(0);
				}
				// ho perso
				else if (state.getTurn().equals(StateTablut.Turn.BLACKWIN)) {
					System.out.println("YOU LOSE!");
					System.exit(0);
				}
				// pareggio
				else if (state.getTurn().equals(StateTablut.Turn.DRAW)) {
					System.out.println("DRAW!");
					System.exit(0);
				}


			}
		
		}
}
	

