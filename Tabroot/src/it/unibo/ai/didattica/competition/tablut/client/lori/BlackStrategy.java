package it.unibo.ai.didattica.competition.tablut.client.lori;

import java.util.ArrayList;
import java.util.List;

import it.unibo.ai.didattica.competition.tablut.domain.State;

/**
 * @author L.Piazza A.Dalmonte 
 *
 */
public class BlackStrategy {
	
	public BlackStrategy() {
	}

	
	
	/**
	 * Metodo che controlla quante pedine Black sono in assetto per formare la gabbia.
	 * @param state Lo stato attuale della scacchiera
	 * @return Il numero delle pedine black in assetto giusto.
	 * 
	 * (Code by L.Piazza)
	 */
	public int blackInAssettoGabbia(State state) {
		int [] posizione = new int[2];
		List<int[]> blackInAssetto = new ArrayList<int[]>();
	
		if(state.getPawn(1,2).equalsPawn("B")) {
			posizione[0]=1;
			posizione[1]=2;
			blackInAssetto.add(posizione);
		}
		if(state.getPawn(1,6).equalsPawn("B")) {
			posizione[0]=1;
			posizione[1]=6;
			blackInAssetto.add(posizione);
		}
		if(state.getPawn(2,1).equalsPawn("B")) {
			posizione[0]=2;
			posizione[1]=1;
			blackInAssetto.add(posizione);
		}
		if(state.getPawn(2,7).equalsPawn("B")) {
			posizione[0]=2;
			posizione[1]=7;
			blackInAssetto.add(posizione);
		}
		if(state.getPawn(6,1).equalsPawn("B")) {
			posizione[0]=6;
			posizione[1]=1;
			blackInAssetto.add(posizione);
		}
		if(state.getPawn(6,7).equalsPawn("B")) {
			posizione[0]=6;
			posizione[1]=7;
			blackInAssetto.add(posizione);
		}
		if(state.getPawn(7,2).equalsPawn("B")) {
			posizione[0]=7;
			posizione[1]=2;
			blackInAssetto.add(posizione);
		}
		if(state.getPawn(7,6).equalsPawn("B")) {
			posizione[0]=7;
			posizione[1]=6;
			blackInAssetto.add(posizione);
		}
		
		return blackInAssetto.size();
	}
	
	/**
	 * @param blackInAssetto Una lista contentente le posizioni delle pedine black in assetto di gabbia
	 * @return True se tutte e 8 le pedine sono in assetto.
	 * 
	 * (Code by L.Piazza)
	 */
	public boolean isGabbia(List<int[]> blackInAssetto) {
		if(blackInAssetto.size()==8)
			return true;
		
		return false;
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
	
	/**
	 * @param state Lo stato attuale
	 * @return result Una stringa che contiene il quadrante della scacchiera in cui si trova il re o "Cross" se è sul trono o nelle caselle centrali
	 * 
	 * (Code by L.Piazza A.Dalmonte)
	 */
	public String quadranteKing(int[] king) {
		String result="Cross";
		
		//Re nel quadrante in alto a sinistra
		if( (king[0]>=1 && king[0]<=3) && (king[1]>=1 && king[1]<=3) )
			result="UL";
		
		//Re nel quadrante in basso a sinistra
		if( (king[0]>=5 && king[0]<=7) && (king[1]>=1 && king[1]>=3) )
			result="DL";
		
		//Re nel quadrante in alto a destra
		if( (king[0]>=1 && king[0]<=3) && (king[1]>=5 && king[1]>=7) )
			result="UR";
		
		//Re nel quadrante in basso a destra
		if( (king[0]>=5 && king[0]<=7) && (king[1]>=5 && king[1]>=7) )
			result="DR";
		
		return result;
	}
	
	public double valutaAssettoGabbia(State state, int[] king) {
		switch(quadranteKing(king)) { 
			case "UL":
				if(whiteInBorderUpLeft(state)==false) {	
					if(state.getPawn(1,2).equalsPawn("B") && state.getPawn(2,1).equalsPawn("B") ) {
						return 2.0;
					}
					if(state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B") ) {
						return 1.0;
					}
				}
				break;
			case"UR":
				if(whiteInBorderUpRight(state)==false) {	
					if(state.getPawn(1,6).equalsPawn("B") && state.getPawn(2,7).equalsPawn("B") ) {
						return 2.0;
					}
					if(state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B") ) {
						return 1.0;
					}
				}
				break;
			case"DL":
				if(whiteInBorderDownLeft(state)==false) {	
					if(state.getPawn(6,1).equalsPawn("B") && state.getPawn(7,2).equalsPawn("B") ) {
						return 2.0;
					}
					if(state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B") ) {
						return 1.0;
					}
				}
				break;
			case"DR":
				if(whiteInBorderDownLeft(state)==false) {	
					if(state.getPawn(7,6).equalsPawn("B") && state.getPawn(6,7).equalsPawn("B") ) {
						return 2.0;
					}
					if(state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B") ) {
						return 1.0;
					}
				}
				break;
			case "Cross":
				//TODO
				return blackInAssettoGabbia(state)/8.0;
			default:
				return 0;
		}
		return 0;	
	}
}
