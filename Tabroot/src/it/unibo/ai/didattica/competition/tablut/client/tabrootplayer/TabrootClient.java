package it.unibo.ai.didattica.competition.tablut.client.tabrootplayer;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalTime;

import aima.core.search.adversarial.*;
import aima.core.search.framework.Metrics;
import it.unibo.ai.didattica.competition.tablut.client.search.BlackOpening;
import it.unibo.ai.didattica.competition.tablut.client.search.IOpening;
import it.unibo.ai.didattica.competition.tablut.client.search.MyGame;
import it.unibo.ai.didattica.competition.tablut.client.search.MyIterativeDeepeningAlphaBetaSearch;
import it.unibo.ai.didattica.competition.tablut.domain.*;
import it.unibo.ai.didattica.competition.tablut.domain.Game;
import it.unibo.ai.didattica.competition.tablut.domain.State.Turn;
import it.unibo.ai.didattica.competition.tablut.optimization.TablutTranspositionTable;


public class TabrootClient extends TablutClient {

	private int time;
	private TablutTranspositionTable transpositionTable;

	public TabrootClient(String player, String name, int time) throws UnknownHostException, IOException {
		super(player, name);
		this.time=time;
	}

	public TabrootClient(String player) throws UnknownHostException, IOException {
		this(player, "Tabroot",  59);
	}

	public TabrootClient(String player, String name) throws UnknownHostException, IOException {
		this(player, name, 59);
	}

	public TabrootClient(String player, int time) throws UnknownHostException, IOException {
		this(player, "Tabroot", time);
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String role = "";
		String name = "Tabroot";
		int time = 60;		
		
		String usage = "Usage: java TabrootClient WHITE or BLACK [-t <time>] [-n <name>]\n"
				+"\ttime must be an integer (number of seconds for evaluate the next action); default: 60\n"
				+"name insert the name of the team; default: Tabroot";
		
		if (args.length < 1) {
			System.out.println("You must specify which player you are (WHITE or BLACK)");
			System.exit(-1);
		} else {
			System.out.println(args[0]);
			role = (args[0]);
		}
		
		for (int i = 0; i < args.length - 1; i++) {
			if (args[i].equals("-t")) {
				i++;
				try {
					time = Integer.parseInt(args[i]);
					if (time < 1) {
						System.out.println("Time format not allowed!");
						System.out.println(args[i]);
						System.out.println(usage);
						System.exit(1);
					}
				} catch (Exception e) {
					System.out.println("The time format is not correct!");
					System.out.println(args[i]);
					System.out.println(usage);
					System.exit(1);
				}
			}
			if(args[i].equals("-n")) {
				i++;
				name=args[i];
			}
		}
		
		System.out.println("Selected client: " + role);
		System.out.println("Time choose for evaluate the action: " + time+". (1 second is subtracted to not exceed the turn time)." );
		System.out.println("Team name: " + name);
		
		TabrootClient client = new TabrootClient(role, name, time-1);
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
		MyGame myGame = null; 
		
		state = new StateTablut();
		state.setTurn(State.Turn.WHITE);
		rules = new GameAshtonTablut(99, 0, "garbage", "fake", "fake");
		//Creo l'oggetto MyGame che servira' alla classe di ricerca
		myGame = new MyGame(state, (GameAshtonTablut) rules, "fake", "fake");
		System.out.println("Ashton Tablut game");

		//this.transpositionTable=new TablutTranspositionTable();
		
		//Creo l'oggetto MyIterativeDeepeningAlphaBetaSearch che realizzera' la ricerca della mossa nello spazio degli stati
		MyIterativeDeepeningAlphaBetaSearch myItDeepAlgorithm = new MyIterativeDeepeningAlphaBetaSearch(myGame, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, time);
		myItDeepAlgorithm.setLogEnabled(true);
		
		
		/*TODO: Algoritmo ottimizzato con Transposition Table*/
		//MyTTIterativeDeepeningAlphaBetaSearch ttAlgorithm = new MyTTIterativeDeepeningAlphaBetaSearch(myGame, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, time, transpositionTable);
		//ttAlgorithm.setLogEnabled(true);
		
		//Eventuali altri algoritmi:
		/*
		 * IterativeDeepeningAlphaBetaSearch<State, Action, Turn> itDeepAlgorithm = IterativeDeepeningAlphaBetaSearch.createFor(myGame, -1.0, 1.0, time);
		 * itDeepAlgorithm.setLogEnabled(true);
		 * MinimaxSearch<State, Action, Turn> miniMaxAlgorithm = MinimaxSearch.createFor(myGame);
		 * AlphaBetaSearch<State, Action, Turn> alphaBetaAlgorithm = AlphaBetaSearch.createFor(myGame);
		 */
		
		int turn=0;
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
			
			if (this.getPlayer().equals(Turn.WHITE)) {
				//sono il giocatore bianco
				if (this.getCurrentState().getTurn().equals(StateTablut.Turn.WHITE)) {
					//ed e' il turno del bianco (tocca a me giocare)
					printStartTime();//controllo tempo
					Action a = null;
					try {
						a = new Action("z0", "z0", State.Turn.WHITE);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
					if(turn==0) {
						turn=1;
						a.setFrom("f5");
						a.setTo("f2");
						System.out.println("Mossa d'apertura scelta: " + a.toString());
						
						if(a!=null) {
							try {
								this.write(a);
							} catch (ClassNotFoundException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						continue;
						}
					}

					//Selezione azione con ALGORITMO
					//a=itDeepAlgorithm.makeDecision(state);
					//a=miniMaxAlgorithm.makeDecision(state);
					//a=alphaBetaAlgorithm.makeDecision(state);
					a=myItDeepAlgorithm.makeDecision(state);
					//a=ttAlgorithm.makeDecision(state);
					
					System.out.println("Mossa scelta: " + a.toString());
					//printStatistics(itDeepAlgorithm);
					printStatistics(myItDeepAlgorithm);
					printEndTime();//controllo tempo
					/*GESTIONE MEMORIA: DA CONTROLLARE!!!*/
//					long memOccupata=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
					/*Conversione da bytes a MB*/
//					double memOccupataMB=memOccupata*Math.pow(9.537, Math.pow(10, -7));
//					System.out.println("MEMORIA OCCUPATA: " + memOccupataMB+" MB");
					printEndTime();//controllo tempo
					try {
						this.write(a);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
				// e' il turno dell'avversario
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

			} else {
				// sono il giocatore nero
				if (this.getCurrentState().getTurn().equals(StateTablut.Turn.BLACK)) { 
					// ed e' il turno del nero (tocca a me giocare)
					printStartTime();//controllo tempo
					Action a = null;
					try {
						a = new Action("z0", "z0", State.Turn.BLACK);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//Se e' il primo turno apro con una delle mosse d'apertura
					if(turn==0) {
						turn=1;
						IOpening opener = new BlackOpening();
						a=opener.openingMove(getCurrentState());
						System.out.println("Mossa d'apertura scelta: " + a.toString());
						
						if(a!=null) {
							try {
								this.write(a);
							} catch (ClassNotFoundException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						continue;
						}
					}
										
					//Selezione azione con ALGORITMO
					a = myItDeepAlgorithm.makeDecision(state);
					
					System.out.println("Mossa scelta: " + a.toString());
					printStatistics(myItDeepAlgorithm);
					
					/*GESTIONE MEMORIA: DA CONTROLLARE!!!*/
//					long memOccupata=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
					/*Conversione da bytes a MB*/
//					double memOccupataMB=memOccupata*Math.pow(9.537, Math.pow(10, -7));
//					System.out.println("MEMORIA OCCUPATA: " + memOccupataMB+" MB");
					printEndTime();//controllo tempo
					
					try {
						this.write(a);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else if (state.getTurn().equals(StateTablut.Turn.WHITE)) {
					System.out.println("Waiting for your opponent move... ");
				} else if (state.getTurn().equals(StateTablut.Turn.WHITEWIN)) {
					System.out.println("YOU LOSE!");
					System.exit(0);
				} else if (state.getTurn().equals(StateTablut.Turn.BLACKWIN)) {
					System.out.println("YOU WIN!");
					System.exit(0);
				} else if (state.getTurn().equals(StateTablut.Turn.DRAW)) {
					System.out.println("DRAW!");
					System.exit(0);
				}

			}
		}

	}
	
	private void printStatistics (AdversarialSearch<State, Action> algorithm) {
		Metrics metrics = algorithm.getMetrics();
		for (String key : metrics.keySet()) {
			String value = metrics.get(key);
			System.out.println("["+key+"]:"+value);
		}
	}
	
	private void printStartTime() {
		LocalTime t=LocalTime.now();
		System.out.println("***Ora Inizio: "+t);
	}
	private void printEndTime() {
		LocalTime t=LocalTime.now();
		System.out.println("***Ora fine: "+t);
	}
	
}
