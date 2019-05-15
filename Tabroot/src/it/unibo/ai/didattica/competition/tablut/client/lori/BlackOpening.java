/**
 * 
 */
package it.unibo.ai.didattica.competition.tablut.client.lori;

import java.io.IOException;

import it.unibo.ai.didattica.competition.tablut.domain.Action;
import it.unibo.ai.didattica.competition.tablut.domain.State;
import it.unibo.ai.didattica.competition.tablut.domain.State.Turn;

/**
 * @author L.Piazza
 *
 */
public class BlackOpening implements IOpening {


	@Override
	public Action openingMove(State currentState) {
		/*Se in apertura ho possibilità di mangiare ai bordi lo faccio*/
		Action a=controllaMangiata(currentState);
		if(a!=null)
			return a;

		/*Altrimenti guardo prima se il bianco ha aperto muovendo le pedine interne più vicine al Re*/
		if(currentState.getPawn(3,4).equalsPawn("O") )
			try {
				return new Action("e2", "g2", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(4,5).equalsPawn("O"))	
			try {
				return new Action("h5", "h7", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(5,4).equalsPawn("O"))	
			try {
				return new Action("e8", "c8", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(4,3).equalsPawn("O"))	
			try {
				return new Action("b5", "b7", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		/*Guardo ora se invece il bianco ha aperto muovendo una delle pedine più esterne*/
		else if(currentState.getPawn(2,4).equalsPawn("O")) {
			if(!whiteInBorderUpLeft(currentState)){
				try {
					return new Action("e2", "c2", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				try {
					return new Action("e2", "g2", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		else if(currentState.getPawn(4,6).equalsPawn("O")) {
			if(!whiteInBorderUpRight(currentState)){
				try {
					return new Action("h5", "h3", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				try {
					return new Action("h5", "h7", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		else if(currentState.getPawn(6,4).equalsPawn("O")) {
			if(!whiteInBorderDownRight(currentState)){
				try {
					return new Action("e8", "g8", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				try {
					return new Action("e8", "c8", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		else if(currentState.getPawn(4,2).equalsPawn("O")) {
			if(!whiteInBorderDownLeft(currentState)){
				try {
					return new Action("e2", "c2", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				try {
					return new Action("e2", "g2", Turn.BLACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		/*Never returned*/
		return null;		
	}
	
	private Action controllaMangiata(State currentState) {
		if(currentState.getPawn(2,0).equalsPawn("W"))
			try {
				return new Action("e2", "a2", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(0,2).equalsPawn("W"))
			try {
				return new Action("b5", "b1", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(0,6).equalsPawn("W"))
			try {
				return new Action("h5", "h1", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(2,8).equalsPawn("W"))
			try {
				return new Action("e2", "i2", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(6,8).equalsPawn("W"))
			try {
				return new Action("e8", "i8", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(8,6).equalsPawn("W"))
			try {
				return new Action("h5", "h9", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(8,2).equalsPawn("W"))
			try {
				return new Action("b5", "b9", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(currentState.getPawn(6,0).equalsPawn("W"))
			try {
				return new Action("e8", "a8", Turn.BLACK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * @param state Lo stato attuale
	 * @return result true se ci sono delle pedine bianche lungo il bordo del quadrante in alto a sinistra della scacchiera.
	 * 
	 * (Code by L.Piazza A.Dalmonte)
	 */	
	public boolean whiteInBorderUpLeft(State state) {
		boolean result=false;
		
		for(int i=0; i<3 && !result; i++) {
			if(state.getPawn(i, 0).equalsPawn("W")) {
				result=true;
			}
		}
		for(int i=0; i<2 && !result; i++) {
			if(state.getPawn(i, 1).equalsPawn("W")) {
				result=true;
			}
		}
		if(!result && state.getPawn(0, 2).equalsPawn("W")) {
			result=true;
		}
		return result;	
	}
	
	/**
	 * @param state Lo stato attuale
	 * @return result true se ci sono delle pedine bianche lungo il bordo del quadrante in basso a sinistra della scacchiera.
	 * 
	 * (Code by L.Piazza A.Dalmonte)
	 */
	public boolean whiteInBorderDownLeft(State state) {
		boolean result=false;
		
		for(int i=6; i<9 && !result; i++) {
			if(state.getPawn(i, 0).equalsPawn("W")) {
				result=true;
			}
		}
		for(int i=7; i<9 && !result; i++) {
			if(state.getPawn(i, 1).equalsPawn("W")) {
				result=true;
			}
		}
		if(!result && state.getPawn(8, 2).equalsPawn("W")) {
			result=true;
		}
		return result;	
	}
	
	/**
	 * @param state Lo stato attuale
	 * @return result true se ci sono delle pedine bianche lungo il bordo del quadrante in alto a destra della scacchiera.
	 * 
	 * (Code by L.Piazza A.Dalmonte)
	 */
	public boolean whiteInBorderUpRight(State state) {
		boolean result=false;
		
		for(int i=0; i<3 && !result; i++) {
			if(state.getPawn(i, 8).equalsPawn("W")) {
				result=true;
			}
		}
		for(int i=0; i<2 && !result; i++) {
			if(state.getPawn(i, 7).equalsPawn("W")) {
				result=true;
			}
		}
		if(!result && state.getPawn(0, 6).equalsPawn("W")) {
			result=true;
		}
		return result;	
	}
	
	/**
	 * @param state Lo stato attuale
	 * @return result true se ci sono delle pedine bianche lungo il bordo del quadrante in basso a destra della scacchiera.
	 * 
	 * (Code by L.Piazza A.Dalmonte)
	 */
	public boolean whiteInBorderDownRight(State state) {
		boolean result=false;
		
		for(int i=6; i<9 && !result; i++) {
			if(state.getPawn(i, 8).equalsPawn("W")) {
				result=true;
			}
		}
		for(int i=7; i<9 && !result; i++) {
			if(state.getPawn(i, 7).equalsPawn("W")) {
				result=true;
			}
		}
		if(!result && state.getPawn(8, 6).equalsPawn("W")) {
			result=true;
		}
		return result;	
	}

}
