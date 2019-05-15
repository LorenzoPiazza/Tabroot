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
		String result="Throne";
		
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
		
		//Re nella parte alta della croce
		if((king[0]==2 || king[0]==3) && king[1]==4) {
			result="CU";
		}
		
		//Re nella parte bassa della croce
		if((king[0]==5 || king[0]==6) && king[1]==4) {
			result="CD";
		}
		
		//Re nella parte destra della croce
		if(king[0]==4 && (king[0]==5 || king[0]==6)) {
			result="CR";
		}
		
		//Re nella parte sinistra della croce
		if(king[0]==4 && (king[0]==2 || king[0]==3)) {
			result="CR";
		}
		return result;
	}
	
	
	/*private List<int[]> puntiDiFugaRe(State state){
		List<int[]> puntiDiFugaRe=new ArrayList<int[]>();
		if(state.getPawn(3, 4).equalsPawn("O")) {
			puntiDiFugaRe.add(new int [] {3,4});
		}
		if(state.getPawn(4, 5).equalsPawn("O")) {
			puntiDiFugaRe.add(new int [] {4,5});
		}
		if(state.getPawn(5, 4).equalsPawn("O")) {
			puntiDiFugaRe.add(new int [] {5,4});
		}
		if(state.getPawn(4, 3).equalsPawn("O")) {
			puntiDiFugaRe.add(new int [] {4,3});
		}
		
		return puntiDiFugaRe;
	}*/
	
	//modifica per guardare le gabbie adiacenti al quadrante del rè dato un valore minore (Alan)
	//inoltre guarda anche se si è messo nella croce in che parte di essa e da un valore un pò buono alla gabbia in quei due lati della croce
	
	public double valutaAssettoGabbia(State state, int[] king) {
		switch(quadranteKing(king)) { 
			case "UL":
				if(whiteInBorderUpLeft(state)==false) {	
					if(state.getPawn(1,2).equalsPawn("B") && state.getPawn(2,1).equalsPawn("B") ) {
						return 1.0;
					}
					if(state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B") ) {
						return 0.5;
					}
				}
				else if ((whiteInBorderUpRight(state) == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))
						|| (whiteInBorderDownLeft(state) == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B")) ) {
						return 0.2;
				}
				break;
			case"UR":
				if(whiteInBorderUpRight(state)==false) {	
					if(state.getPawn(1,6).equalsPawn("B") && state.getPawn(2,7).equalsPawn("B") ) {
						return 1.0;
					}
					if(state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B") ) {
						return 0.5;
					}
				}else if((whiteInBorderUpLeft(state) == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))
						|| (whiteInBorderDownRight(state) == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B"))) {
					return 0.2;
				}
				break;
			case"DL":
				if(whiteInBorderDownLeft(state)==false) {	
					if(state.getPawn(6,1).equalsPawn("B") && state.getPawn(7,2).equalsPawn("B") ) {
						return 1.0;
					}
					if(state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B") ) {
						return 0.5;
					}
				}else if ((whiteInBorderDownRight(state) == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B"))
						|| (whiteInBorderUpLeft(state) == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))) {
					return 0.2;
				}
				break;
			case"DR":
				if(whiteInBorderDownRight(state)==false) {	
					if(state.getPawn(7,6).equalsPawn("B") && state.getPawn(6,7).equalsPawn("B") ) {
						return 1.0;
					}
					if(state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B") ) {
						return 0.5;
					}
				}else if((whiteInBorderDownLeft(state) == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B"))
						|| (whiteInBorderUpRight(state) == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))) {
					return 0.2;
				}
				break;
			case"CU":
				if(whiteInBorderUpRight(state) == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B")
				|| (whiteInBorderUpLeft(state) == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))) {
					return 0.5;
				}
				break;
			case"CD":
				if(whiteInBorderDownRight(state) == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B")
				|| (whiteInBorderDownLeft(state) == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B"))) {
					return 0.5;
				}
				break;
			case"CR":
				if(whiteInBorderDownRight(state) == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B")
				|| (whiteInBorderUpRight(state) == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))) {
					return 0.5;
				}
				break;
			case"CL":
				if(whiteInBorderDownLeft(state) == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B")
				|| (whiteInBorderUpLeft(state) == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))) {
					return 0.5;
				}
				break;
			case "Throne":
				return blackInAssettoGabbia(state)/8.0;
			default:
				return 0;
		}
		return 0;	
	}
	
	//SCanc
	
	public double gabbiaStrettaDownLeft(State state, List<int[]> white) {
		boolean bianchi=false;
		int[] controlloPedine= {0,0};
		int i=-1;
		
		do{
			i++;
			controlloPedine=white.get(i);
			if(controlloPedine[0]==5 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==6 && controlloPedine[1]==0)
				bianchi=true;
			if(controlloPedine[0]==6 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==6 && controlloPedine[1]==2)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==0)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==2)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==3)
				bianchi=true;
			if(controlloPedine[0]==8 && controlloPedine[1]==0)
				bianchi=true;
			if(controlloPedine[0]==8 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==8 && controlloPedine[1]==2)
				bianchi=true;
		}while(!bianchi&&white.size()<i);
		
		if(!bianchi && state.getPawn(5,2).equalsPawn("W")&&
				state.getPawn(6,3).equalsPawn("W"))
			return 1;
		
		return 0;
	}
	
	public double gabbiaStrettaDownRight(State state, List<int[]> white) {
		boolean bianchi=false;
		int[] controlloPedine= {0,0};
		int i=-1;
		
		do{
			i++;
			controlloPedine=white.get(i);
			if(controlloPedine[0]==5 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==6 && controlloPedine[1]==8)
				bianchi=true;
			if(controlloPedine[0]==6 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==6 && controlloPedine[1]==6)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==8)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==6)
				bianchi=true;
			if(controlloPedine[0]==7 && controlloPedine[1]==5)
				bianchi=true;
			if(controlloPedine[0]==8 && controlloPedine[1]==8)
				bianchi=true;
			if(controlloPedine[0]==8 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==8 && controlloPedine[1]==6)
				bianchi=true;
		}while(!bianchi&&white.size()<i);
		
		if(!bianchi && state.getPawn(5,6).equalsPawn("W")&&
				state.getPawn(6,5).equalsPawn("W"))
			return 1;
		
		return 0;
	}
	
	public double gabbiaStrettaUpRight(State state, List<int[]> white) {
		boolean bianchi=false;
		int[] controlloPedine= {0,0};
		int i=-1;
		
		do{
			i++;
			controlloPedine=white.get(i);
			if(controlloPedine[0]==3 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==2 && controlloPedine[1]==8)
				bianchi=true;
			if(controlloPedine[0]==2 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==2 && controlloPedine[1]==6)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==8)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==6)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==5)
				bianchi=true;
			if(controlloPedine[0]==0 && controlloPedine[1]==8)
				bianchi=true;
			if(controlloPedine[0]==0 && controlloPedine[1]==7)
				bianchi=true;
			if(controlloPedine[0]==0 && controlloPedine[1]==6)
				bianchi=true;
		}while(!bianchi&&white.size()<i);
		
		if(!bianchi && state.getPawn(2,5).equalsPawn("W")&&
				state.getPawn(3,6).equalsPawn("W"))
			return 1;
		
		return 0;
	}
	
	public double gabbiaStrettaUpLeft(State state, List<int[]> white) {
		boolean bianchi=false;
		int[] controlloPedine= {0,0};
		int i=-1;
		
		do{
			i++;
			controlloPedine=white.get(i);
			if(controlloPedine[0]==3 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==2 && controlloPedine[1]==0)
				bianchi=true;
			if(controlloPedine[0]==2 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==2 && controlloPedine[1]==2)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==0)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==2)
				bianchi=true;
			if(controlloPedine[0]==1 && controlloPedine[1]==3)
				bianchi=true;
			if(controlloPedine[0]==0 && controlloPedine[1]==0)
				bianchi=true;
			if(controlloPedine[0]==0 && controlloPedine[1]==1)
				bianchi=true;
			if(controlloPedine[0]==0 && controlloPedine[1]==2)
				bianchi=true;
		}while(!bianchi&&white.size()<i);
		
		if(!bianchi && state.getPawn(3,2).equalsPawn("W")&&
				state.getPawn(2,3).equalsPawn("W"))
			return 1;
		
		return 0;
	}
}
