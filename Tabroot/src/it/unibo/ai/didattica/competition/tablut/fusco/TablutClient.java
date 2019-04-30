package it.unibo.ai.didattica.competition.tablut.fusco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import com.google.gson.Gson;

import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch;
import it.unibo.ai.didattica.competition.tablut.domain.Action;
import it.unibo.ai.didattica.competition.tablut.domain.State;
import it.unibo.ai.didattica.competition.tablut.domain.StateTablut;
import it.unibo.ai.didattica.competition.tablut.exceptions.BoardException;
import it.unibo.ai.didattica.competition.tablut.exceptions.CitadelException;
import it.unibo.ai.didattica.competition.tablut.exceptions.ClimbingCitadelException;
import it.unibo.ai.didattica.competition.tablut.exceptions.ClimbingException;
import it.unibo.ai.didattica.competition.tablut.exceptions.DiagonalException;
import it.unibo.ai.didattica.competition.tablut.exceptions.OccupitedException;
import it.unibo.ai.didattica.competition.tablut.exceptions.PawnException;
import it.unibo.ai.didattica.competition.tablut.exceptions.StopException;
import it.unibo.ai.didattica.competition.tablut.exceptions.ThroneException;

/**
 * Classe astratta di un client per il gioco Tablut
 * 
 * @author Andrea Piretti
 *
 */
public abstract class TablutClient implements Runnable {

	private State.Turn player;
	private String name;
	private Socket playerSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private Gson gson;
	private State currentState;
	private List<String> citadels;
	//private Action action;

	public State.Turn getPlayer() {
		return player;
	}

	public void setPlayer(State.Turn player) {
		this.player = player;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	/**
	 * Creates a new player initializing the sockets and the logger
	 * 
	 * @param player
	 *            The role of the player (black or white)
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public TablutClient(String player, String name) throws UnknownHostException, IOException {
		int port = 0;
		this.gson = new Gson();
		if (player.toLowerCase().equals("white")) {
			this.player = State.Turn.WHITE;
			port = 5800;
		} else if (player.toLowerCase().equals("black")) {
			this.player = State.Turn.BLACK;
			port = 5801;
		} else {
			throw new InvalidParameterException("Player role must be BLACK or WHITE");
		}
		playerSocket = new Socket("localhost", port);
		out = new DataOutputStream(playerSocket.getOutputStream());
		in = new DataInputStream(playerSocket.getInputStream());
		this.name = name;
		citadels = new ArrayList<String>();
		// this.strangeCitadels = new ArrayList<String>();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Write an action to the server
	 */
	public void write(Action action) throws IOException, ClassNotFoundException {
		out.writeUTF(this.gson.toJson(action));
	}
	
	/**
	 * Write the name to the server
	 */
	public void declareName() throws IOException, ClassNotFoundException {
		out.writeUTF(this.gson.toJson(this.name));
	}

	/**
	 * Read the state from the server
	 */
	public void read() throws ClassNotFoundException, IOException {
		this.currentState = this.gson.fromJson(in.readUTF(), StateTablut.class);
	}
	

	
	
	//Alpha-Beta Search


	public double maxValue(State state, double alpha, double beta, int depth) {


		if (isTerminalState(state)||depth==0)
			return getHeuristicValueWhite(state);

		double v = Double.NEGATIVE_INFINITY;

		for (Action Action : getActions(state,state.getTurn().toString())) {
			v = Math.max(v, minValue(movePawn(state,Action), alpha, beta, depth-1));

			if (v >= beta)
				return v;

			alpha = Math.max(alpha, v);
		}
		return v;

	}


	public double minValue(State state, double alpha, double beta, int depth) {



		if (isTerminalState(state)||depth==0)
			return getHeuristicValueBlack(state);


		double v = Double.POSITIVE_INFINITY;

		for (Action Action : getActions(state,state.getTurn().toString())) {
			v = Math.min(v, maxValue(movePawn(state,Action), alpha, beta, depth-1));

			if (v <= alpha)
				return v;

			beta = Math.min(beta, v);

		}

		return v;
	}


	private double getHeuristicValueBlack(State state) {
		double v=(double) (Math.random() * 100);
		//System.out.println("valutazione= " +v);
		return v;
	}

	private double getHeuristicValueWhite(State state) {
		double v=(double) (Math.random() * 100);
		//System.out.println("valutazione= " +v);
		return v;
	}

	private boolean isTerminalState(State state) {
		if(state.getTurn().toString()=="WW"|| state.getTurn().toString()=="BW" || state.getTurn().toString()=="D")
			return true;
		return false;

	}


	//scelgo la prossima mossa
	public Action makeDecision(State state) { 
		Action result = null;
		double resultValue = Double.NEGATIVE_INFINITY;
		int maxDepth=1;

			for (Action Action : getActions(state,"W")) {
				System.out.println("azione del bianco valida: " + Action.toString());
				double value = minValue(movePawn(state, Action),
						Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,maxDepth);


				if (value > resultValue) {
					result = Action;
					resultValue = value;
				}


			}
		
		return result;
	}

	
	// deve ritornare tutte le possibili Actions per uno Stato  
	// riutilizzare la checkMove(state,action)
	private List<Action> getActions(State state, String player) {
		// TODO Auto-generated method stub

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
		if(player.equals("W"))	
			it = white.iterator(); 
		else
			it = black.iterator(); 

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

				ctrl= checkMove(colonna, j, riga, riga, ctrl, state);


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

		    	ctrl= checkMove(colonna, colonna, riga, i, ctrl, state);

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

	
	private State movePawn(State state, Action a) {

		State.Pawn pawn = state.getPawn(a.getRowFrom(), a.getColumnFrom());



		State.Pawn[][] newBoard = state.getBoard();

		//libero il trono o una casella qualunque
		if(newBoard.length==9)
		{
			if(a.getColumnFrom()==4 && a.getRowFrom()==4)
			{
				newBoard[a.getRowFrom()][a.getColumnFrom()]= State.Pawn.THRONE;
			}
			else
			{
				newBoard[a.getRowFrom()][a.getColumnFrom()]= State.Pawn.EMPTY;
			}
		}
		if(newBoard.length==7)
		{
			if(a.getColumnFrom()==3 && a.getRowFrom()==3)
			{
				newBoard[a.getRowFrom()][a.getColumnFrom()]= State.Pawn.THRONE;
			}
			else
			{
				newBoard[a.getRowFrom()][a.getColumnFrom()]= State.Pawn.EMPTY;
			}
		}




		//metto nel nuovo tabellone la pedina mossa
		newBoard[a.getRowTo()][a.getColumnTo()]=pawn;
		//aggiorno il tabellone
		state.setBoard(newBoard);
		//cambio il turno
		if(state.getTurn().equalsTurn(State.Turn.WHITE.toString()))
		{
			state.setTurn(State.Turn.BLACK);
		}
		else
		{
			state.setTurn(State.Turn.WHITE);
		}

		//System.out.println(state.toString());
		return state;
	}
	
	
	
	
	public int checkMove(int columnFrom, int columnTo, int rowFrom, int rowTo, int ctrl, State state){

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
	
	
	 
	 
}
