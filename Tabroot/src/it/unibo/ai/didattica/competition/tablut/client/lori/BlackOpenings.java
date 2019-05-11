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
	
	public BlackOpenings(MyGame game) {
		this.game=game;
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
	public List<int[]> blackInAssettoGabbia(State state) {
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
		
		return blackInAssetto;
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
	  
}
