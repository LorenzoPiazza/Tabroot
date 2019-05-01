package it.unibo.ai.didattica.competition.tablut.client.lori;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aima.core.search.adversarial.Game;
import it.unibo.ai.didattica.competition.tablut.domain.*;
import it.unibo.ai.didattica.competition.tablut.domain.State.Turn;


/* Questa classe estende la classe GameAshtonTablut del progetto del prof.
 * Inoltre implementa l'interfaccia Game della libreria aima e i rispettivi metodi:
 */

/**
 * @author L.Piazza
 *
 */
public class myGame extends GameAshtonTablut implements Game<State, Action, Turn> {

	private List<String> citadels;
	
	//Costruttori che semplicemente richiamano i costruttori della classe GameAshtonTablut e inizializzano le citadels (servono alla myCheckMove() )
	public myGame(int repeated_moves_allowed, int cache_size, String logs_folder, String whiteName, String blackName) {
		super(repeated_moves_allowed, cache_size, logs_folder, whiteName, blackName);
	}
	
	public myGame(State state, int repeated_moves_allowed, int cache_size, String logs_folder,
			String whiteName, String blackName) {
		super(state, repeated_moves_allowed, cache_size, logs_folder, whiteName, blackName);
		
		//E' giusto inizializzarle qui? Erano private in GameAshtonTablut
		citadels = new ArrayList<String>();
		citadels.add("a4");
		citadels.add("a5");
		citadels.add("a6");
		citadels.add("b5");
		citadels.add("d1");
		citadels.add("e1");
		citadels.add("f1");
		citadels.add("e2");
		citadels.add("i4");
		citadels.add("i5");
		citadels.add("i6");
		citadels.add("h5");
		citadels.add("d9");
		citadels.add("e9");
		citadels.add("f9");
		citadels.add("e8");
	}
	
	
	/* 
	 * Questa funzione a differenza della checkMove già fornita salta alcuni controlli inutili per come vengono fornite le mosse da valutare (es: impossibile muovere in diagonale quindi non controllo).
	 * Un'altra differenza è che non muove la pedina nel caso la mossa sia possibile.
	 * Resituisce 0 se la mossa è possibile, 1 altrimenti.
	 * Viene chiamata in seguito all'interno di getActions().
	 */
	public int myCheckMove(int columnFrom, int columnTo, int rowFrom, int rowTo, int ctrl, State state){

		if (columnFrom > state.getBoard().length - 1 || rowFrom > state.getBoard().length - 1
				|| rowTo > state.getBoard().length - 1 || columnTo > state.getBoard().length - 1 || columnFrom < 0
				|| rowFrom < 0 || rowTo < 0 || columnTo < 0) 
			ctrl=1;


		// controllo che non vada sul trono
		if (state.getPawn(rowTo, columnTo).equalsPawn(State.Pawn.THRONE.toString())) {
			ctrl=1;
		}

		// controllo la casella di arrivo
		if (!state.getPawn(rowTo, columnTo).equalsPawn(State.Pawn.EMPTY.toString())) {
			ctrl=1;
		}
		if (citadels.contains(state.getBox(rowTo, columnTo))
				&& !citadels.contains(state.getBox(rowFrom, columnFrom))) {
			ctrl=1;
		}
		if (citadels.contains(state.getBox(rowTo, columnTo))
				&& citadels.contains(state.getBox(rowFrom, columnFrom))) {
			if (rowFrom == rowTo) {
				if (columnFrom - columnTo > 5 || columnFrom - columnTo < -5) {
					ctrl=1;
				}
			} else {
				if (rowFrom - rowTo > 5 || rowFrom - rowTo < -5) {
					ctrl=1;
				}
			}

		}

		// controllo se cerco di stare fermo
		if (rowFrom == rowTo && columnFrom == columnTo) {
			ctrl=1;
		}

		// controllo se sto muovendo una pedina giusta
		if (state.getTurn().equalsTurn(State.Turn.WHITE.toString())) {
			if (!state.getPawn(rowFrom, columnFrom).equalsPawn("W")
					&& !state.getPawn(rowFrom, columnFrom).equalsPawn("K")) {
				ctrl=1;
			}
		}
		if (state.getTurn().equalsTurn(State.Turn.BLACK.toString())) {
			if (!state.getPawn(rowFrom, columnFrom).equalsPawn("B")) {
				ctrl=1;
			}
		}


		// controllo di non scavalcare pedine
		if (rowFrom == rowTo) {
			if (columnFrom > columnTo) {
				for (int i = columnTo; i < columnFrom; i++) {
					if (!state.getPawn(rowFrom, i).equalsPawn(State.Pawn.EMPTY.toString())) {
						if (state.getPawn(rowFrom, i).equalsPawn(State.Pawn.THRONE.toString())) {
							ctrl=1;
						} else {
							ctrl=1;
						}
					}
					if (citadels.contains(state.getBox(rowFrom, i))
							&& !citadels.contains(state.getBox(rowFrom, columnFrom))) {
						ctrl=1;
					}
				}
			} else {
				for (int i = columnFrom + 1; i <= columnTo; i++) {
					if (!state.getPawn(rowFrom, i).equalsPawn(State.Pawn.EMPTY.toString())) {
						if (state.getPawn(rowFrom, i).equalsPawn(State.Pawn.THRONE.toString())) {
							ctrl=1;
						} else {
							ctrl=1;
						}
					}
					if (citadels.contains(state.getBox(rowFrom, i))
							&& !citadels.contains(state.getBox(rowFrom, columnFrom))) {
						ctrl=1;
					}
				}
			}
		} else {
			if (rowFrom > rowTo) {
				for (int i = rowTo; i < rowFrom; i++) {
					if (!state.getPawn(i, columnFrom).equalsPawn(State.Pawn.EMPTY.toString())) {
						if (state.getPawn(i, columnFrom).equalsPawn(State.Pawn.THRONE.toString())) {
							ctrl=1;
						} else {
							ctrl=1;
						}
					}
					if (citadels.contains(state.getBox(i, columnFrom))
							&& !citadels.contains(state.getBox(rowFrom, columnFrom))) {
						ctrl=1;
					}
				}
			} else {
				for (int i = rowFrom + 1; i <= rowTo; i++) {
					if (!state.getPawn(i, columnFrom).equalsPawn(State.Pawn.EMPTY.toString())) {
						if (state.getPawn(i, columnFrom).equalsPawn(State.Pawn.THRONE.toString())) {
							ctrl=1;
						} else {
							ctrl=1;
						}
					}
					if (citadels.contains(state.getBox(i, columnFrom))
							&& !citadels.contains(state.getBox(rowFrom, columnFrom))) {
						ctrl=1;
					}
				}
			}
		}
		return ctrl;
	}

	
	//Da qui in poi implemento i metodi richiesti dall'interfaccia aima.core.search.adversarial.Game:
	
	/*	
	 * getActions(State state):
	 * Restituisce tutte le mosse possibili del giocatore a cui tocca muovere.
	 * Capisce a chi tocca muovere controllando il turno insito nello stato che gli viene passato).
	 * 
	 * Codice by A.Fuschino
	 */
	
	@Override
	public List<Action> getActions(State state) {
			
		List<int[]> white = new ArrayList<int[]>(); //tengo traccia della posizione nello stato dei bianchi
		List<int[]> black = new ArrayList<int[]>(); //uguale per i neri

		int[] buf; //mi indica la posizione ex."z6" 

		for (int i = 0; i < state.getBoard().length; i++) {
			for (int j = 0; j < state.getBoard().length; j++) {
				if (state.getPawn(i, j).equalsPawn("W") 
						|| state.getPawn(i, j).equalsPawn("K")) {
					buf = new int[2];
					buf[0] = i;
					//System.out.println( "riga: " + buf[0] + " ");
					buf[1] = j;
					//System.out.println( "colonna: " + buf[1] + " \n");
					white.add(buf);							
				} else if (state.getPawn(i, j).equalsPawn("B")) {
					buf = new int[2];
					buf[0] = i;
					buf[1] = j;
					black.add(buf);
				}
			}
		}
		
		Iterator<int[]> it;	
		
		if (state.getTurn().equals(Turn.WHITE)) {	//Cerco tutte le mosse possibili per il bianco
			it = white.iterator();
		} else {
			if(state.getTurn().equals(Turn.BLACK)) { //Cerco tutte le mosse possibili per il nero
				it = black.iterator(); 
			}
		}	

		List<Action> actions = new ArrayList<Action>();
		int colonna=0;
		int riga=0;

		Action action = null;
		try {
			action = new Action("z0", "z0", state.getTurn());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String fromString=null;
		String toString=null;
		int ctrl;

		while(it.hasNext()){
			buf=it.next();
			colonna=buf[1];  
			riga=buf[0];

			// System.out.println( "riga: " + buf[0] + " ");
			//System.out.println( "colonna: " + buf[1] + " \n");

			//tengo ferma la riga e muovo la colonna
			for (int j = 0; j < state.getBoard().length; j++){	    

				ctrl=0;
				ctrl=myCheckMove(colonna, j, riga, riga, ctrl, state);

				//se sono arrivato qui con ctrl=0 ho una mossa valida 
				if(ctrl==0){

					char colNew=(char)j;
					char colNewConverted=(char) Character.toLowerCase(colNew + 97);		

					char colOld=(char)colonna;
					char colonOldConverted=(char) Character.toLowerCase(colOld + 97);

					toString= new StringBuilder().append(colNewConverted).append(riga + 1).toString();	
					fromString= new StringBuilder().append(colonOldConverted).append(riga + 1).toString();

					//System.out.println("action da: " + fromString + " a " + toString + " \n");

					try {
						action = new Action(fromString, toString, state.getTurn());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//System.out.println(action.toString() + "\n");
					actions.add(action);

				}
			}   
		    
		    //tengo ferma la colonna e muovo la riga

		    for (int i = 0; i < state.getBoard().length; i++){

		    	ctrl=0;

		    	ctrl= myCheckMove(colonna, colonna, riga, i, ctrl, state);

		    	//se sono arrivato qui con ctrl=0 ho una mossa valida 
		    	if(ctrl==0){

		    		char col=(char)colonna;
		    		char colConverted=(char) Character.toLowerCase(col + 97);

		    		toString= new StringBuilder().append(colConverted).append(i+1).toString();	
		    		fromString= new StringBuilder().append(colConverted).append(riga+1).toString();

		    		//System.out.println("action da: " + fromString + " a " + toString + " \n");

		    		try {
		    			action = new Action(fromString, toString, state.getTurn());
		    		} catch (IOException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}

		    		//System.out.println(action.toString() + "\n");
		    		actions.add(action);
		    	}

		    }

		}
		//System.out.println("tutte le possibili mosse: " + actions.toString());

		return actions;
	}

	@Override
	public State getInitialState() {
		return new StateTablut();
	}

	@Override
	public Turn getPlayer(State state) {
		return state.getTurn();
	}

	@Override
	public Turn[] getPlayers() {
		Turn[] players = new Turn[2];
		players[0]=Turn.BLACK;
		players[1]=Turn.WHITE;
		return players;
	}

	@Override
	public State getResult(State state, Action a) {
		// TODO Auto-generated method stub
		return super.movePawn(state, a);
	}

	// TODO
	@Override
	public boolean isTerminal(State arg0) {
		
		return false;
	}

	// TODO 
	@Override
	public double getUtility(State arg0, Turn arg1) {
		
		return 0;
	}

}
