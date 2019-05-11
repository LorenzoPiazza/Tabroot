package it.unibo.ai.didattica.competition.tablut.client.lori;

import java.util.ArrayList;
import java.util.List;

import it.unibo.ai.didattica.competition.tablut.domain.GameAshtonTablut;
import it.unibo.ai.didattica.competition.tablut.domain.State;

/**
 * @author Lorenzo
 *
 */
public class BlackOpenings {
	private GameAshtonTablut game;
	
	public BlackOpenings() {
	}
	
	
	public void openingsGabbia(State state) {
		
	}
	
	
	/**
	 * Metodo che controlla quali pedine Black sono in assetto per formare la gabbia.
	 * @param state Lo stato attuale della scacchiera
	 * @return Una lista di array in cui ci sono salvate le posizioni delle pedine black in assetto.
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
	
	public String findKing(State state) {
		String result="Cross";
		boolean trovato=false;
		
		for(int i=1; i<4 && !trovato; i++) {
			for(int j=1; j<4 && !trovato; j++) {
				if(state.getPawn(i, j).equalsPawn("K")) {
					trovato=true;
					result="UL";
				}	
			}
		}
		
		for(int i=5; i<8 && !trovato; i++) {
			for(int j=1; j<4 && !trovato; j++) {
				if(state.getPawn(i, j).equalsPawn("K")) {
					trovato=true;
					result="DL";
				}	
			}
		}
		
		for(int i=1; i<4 && !trovato; i++) {
			for(int j=5; j<8 && !trovato; j++) {
				if(state.getPawn(i, j).equalsPawn("K")) {
					trovato=true;
					result="UR";
				}	
			}
		}
		
		for(int i=5; i<8 && !trovato; i++) {
			for(int j=5; j<8 && !trovato; j++) {
				if(state.getPawn(i, j).equalsPawn("K")) {
					trovato=true;
					result="DR";
				}	
			}
		}

		return result;
	}
}
