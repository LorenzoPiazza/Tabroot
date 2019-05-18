package it.unibo.ai.didattica.competition.tablut.client.lori;

import java.util.ArrayList;
import java.util.List;

import it.unibo.ai.didattica.competition.tablut.domain.State;

/**
 * @author L.Piazza A.Dalmonte, S.Cancello 
 *
 */
public class BlackStrategy {
	
	public BlackStrategy() {
	}

	//modifica per guardare le gabbie adiacenti al quadrante del rè dato un valore minore (Alan)
		//inoltre guarda anche se si è messo nella croce in che parte di essa e da un valore un pò buono alla gabbia in quei due lati della croce
		
		public double valutaAssettoGabbiaLight(State state, int[] king) {
			switch(quadranteKing(king)) { 
				case "UL":
					if(!pawnInBorderUpLeft(state, "W")) {
						if(state.getPawn(1,2).equalsPawn("B") && state.getPawn(2,1).equalsPawn("B") ) {
							return 1.0;
						}
						if(state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B") ) {
							return 0.5;
						}
					}
					else if ((pawnInBorderUpRight(state, "W") == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))
							|| (pawnInBorderDownLeft(state, "W") == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B")) ) {
							return 0.2;
					}
					break;
				case"UR":
					if(pawnInBorderUpRight(state, "W")==false) {	
						if(state.getPawn(1,6).equalsPawn("B") && state.getPawn(2,7).equalsPawn("B") ) {
							return 1.0;
						}
						if(state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B") ) {
							return 0.5;
						}
					}else if((pawnInBorderUpLeft(state, "W") == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))
							|| (pawnInBorderDownRight(state, "W") == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B"))) {
						return 0.2;
					}
					break;
				case"DL":
					if(pawnInBorderDownLeft(state, "W")==false) {	
						if(state.getPawn(6,1).equalsPawn("B") && state.getPawn(7,2).equalsPawn("B") ) {
							return 1.0;
						}
						if(state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B") ) {
							return 0.5;
						}
					}else if ((pawnInBorderDownRight(state, "W") == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B"))
							|| (pawnInBorderUpLeft(state, "W") == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))) {
						return 0.2;
					}
					break;
				case"DR":
					if(pawnInBorderDownRight(state, "W")==false) {	
						if(state.getPawn(7,6).equalsPawn("B") && state.getPawn(6,7).equalsPawn("B") ) {
							return 1.0;
						}
						if(state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B") ) {
							return 0.5;
						}
					}else if((pawnInBorderDownLeft(state, "W") == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B"))
							|| (pawnInBorderUpRight(state, "W") == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))) {
						return 0.2;
					}
					break;
				case"CU":
					if(pawnInBorderUpRight(state, "W") == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B")
					|| (pawnInBorderUpLeft(state, "W") == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))) {
						return 0.5;
					}
					break;
				case"CD":
					if(pawnInBorderDownRight(state, "W") == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B")
					|| (pawnInBorderDownLeft(state, "W") == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B"))) {
						return 0.5;
					}
					break;
				case"CR":
					if(pawnInBorderDownRight(state, "W") == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B")
					|| (pawnInBorderUpRight(state, "W") == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))) {
						return 0.5;
					}
					break;
				case"CL":
					if(pawnInBorderDownLeft(state, "W") == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B")
					|| (pawnInBorderUpLeft(state, "W") == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))) {
						return 0.5;
					}
					break;
				case "Throne":
					double valutazione=0;
					//guardo alto a sinistra
					if(!pawnInBorderUpLeft(state, "W")) {
						if(state.getPawn(1,2).equalsPawn("B") && state.getPawn(2,1).equalsPawn("B") ) {
							valutazione+= 1.0;
						}
						else if(state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B") ) {
							valutazione+= 0.5;
						}
					}
					else if ((pawnInBorderUpRight(state, "W") == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))
							|| (pawnInBorderDownLeft(state, "W") == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B")) ) {
							valutazione+= 0.2;
					}
					
					//guardo alto a destra
					if(pawnInBorderUpRight(state, "W")==false) {	
						if(state.getPawn(1,6).equalsPawn("B") && state.getPawn(2,7).equalsPawn("B") ) {
							valutazione+= 1.0;
						}
						else if(state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B") ) {
							valutazione+= 0.5;
						}
					}else if((pawnInBorderUpLeft(state, "W") == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))
							|| (pawnInBorderDownRight(state, "W") == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B"))) {
						valutazione+= 0.2;
					}
					
					//guardo basso a sinistra
					if(pawnInBorderDownLeft(state, "W")==false) {	
						if(state.getPawn(6,1).equalsPawn("B") && state.getPawn(7,2).equalsPawn("B") ) {
							valutazione+= 1.0;
						}
						else if(state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B") ) {
							valutazione+= 0.5;
						}
					}else if ((pawnInBorderDownRight(state, "W") == false && state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B"))
							|| (pawnInBorderUpLeft(state, "W") == false && state.getPawn(1,2).equalsPawn("B") || state.getPawn(2,1).equalsPawn("B"))) {
						valutazione+= 0.2;
					}
					
					//guardo basso a destra
					if(pawnInBorderDownRight(state, "W")==false) {	
						if(state.getPawn(7,6).equalsPawn("B") && state.getPawn(6,7).equalsPawn("B") ) {
							valutazione+= 1.0;
						}
						else if(state.getPawn(7,6).equalsPawn("B") || state.getPawn(6,7).equalsPawn("B") ) {
							valutazione+= 0.5;
						}
					}else if((pawnInBorderDownLeft(state, "W") == false && state.getPawn(6,1).equalsPawn("B") || state.getPawn(7,2).equalsPawn("B"))
							|| (pawnInBorderUpRight(state, "W") == false && state.getPawn(1,6).equalsPawn("B") || state.getPawn(2,7).equalsPawn("B"))) {
						valutazione+= 0.2;
					}
					
					return valutazione/4.0;
					//return blackInAssettoGabbiaLight(state).size()/8.0;
			default:
					return 0;
			}
			return 0;	
		}
		
		
		public double valutaAssettoGabbiaStrong(State state, int[] king) {
			switch(quadranteKing(king)) { 
			case "UL":
				if(!pawnInBorderUpLeft(state,"W") && !pawnInBoxUpLeft(state) && !pawnInBorderUpLeft(state,"B")) 
					return gabbiaStrettaUpLeft(state);
				break;
			case "UR":
				if(!pawnInBorderUpRight(state, "W") && !pawnInBoxUpRight(state) && !pawnInBorderUpRight(state, "B")) 
					return gabbiaStrettaUpRight(state);
				break;
			case"DL":
				if(!pawnInBorderDownLeft(state, "W") && !pawnInBoxDownLeft(state) && !pawnInBorderDownLeft(state, "B"))
					return gabbiaStrettaDownLeft(state);
				break;
			case"DR":
				if(!pawnInBorderDownRight(state, "W") && !pawnInBoxDownRight(state) && !pawnInBorderDownRight(state, "B"))	
					return gabbiaStrettaDownRight(state);
				break;
			case"CU":
				if( (!pawnInBorderUpLeft(state,"W") && !pawnInBoxUpLeft(state) && !pawnInBorderUpLeft(state,"B"))
				|| (!pawnInBorderUpRight(state, "W") && !pawnInBoxUpRight(state) && !pawnInBorderUpRight(state, "B"))) {
					return (gabbiaStrettaUpLeft(state)+gabbiaStrettaUpRight(state))/2.0;
				}
				break;
			case"CD":
				if( (!pawnInBorderDownLeft(state, "W") && !pawnInBoxDownLeft(state) && !pawnInBorderDownLeft(state, "B"))
				|| (!pawnInBorderDownRight(state, "W") && !pawnInBoxDownRight(state) && !pawnInBorderDownRight(state, "B")) ) {
					return (gabbiaStrettaDownLeft(state)+gabbiaStrettaDownRight(state))/2.0;
				}
				break;
			case"CR":
				if( (!pawnInBorderDownRight(state, "W") && !pawnInBoxDownRight(state) && !pawnInBorderDownRight(state, "B"))
				|| (!pawnInBorderUpRight(state, "W") && !pawnInBoxUpRight(state) && !pawnInBorderUpRight(state, "B") ) ) {
					return (gabbiaStrettaDownRight(state)+gabbiaStrettaUpRight(state))/2.0;
				}
				break;
			case"CL":
				if( (!pawnInBorderUpLeft(state,"W") && !pawnInBoxUpLeft(state) && !pawnInBorderUpLeft(state,"B")) 
				|| (!pawnInBorderDownLeft(state, "W") && !pawnInBoxDownLeft(state) && !pawnInBorderDownLeft(state, "B"))) {
					return (gabbiaStrettaDownLeft(state)+gabbiaStrettaUpLeft(state))/2.0;
				}
				break;
			case "Throne":
				double valutazione=0;
				if(!pawnInBorderUpRight(state, "W") && !pawnInBoxUpRight(state) && !pawnInBorderUpRight(state, "B")) //Ci sono le condizioni per la gabbia stretta in alto a sx
					valutazione+= gabbiaStrettaUpRight(state);
				if(!pawnInBorderUpLeft(state,"W") && !pawnInBoxUpLeft(state) && !pawnInBorderUpLeft(state,"B")) //Ci sono le condizioni per la gabbia stretta in alto a dx
					valutazione+= gabbiaStrettaUpLeft(state);
				if(!pawnInBorderDownLeft(state, "W") && !pawnInBoxDownLeft(state) && !pawnInBorderDownLeft(state, "B")) //Ci sono le condizioni per la gabbia stretta in basso a sx
					valutazione+= gabbiaStrettaDownLeft(state);
				if(!pawnInBorderDownRight(state, "W") && !pawnInBoxDownRight(state) && !pawnInBorderDownRight(state, "B"))	 //Ci sono le condizioni per la gabbia stretta in basso a dx
					valutazione+= gabbiaStrettaDownRight(state);
				return valutazione/4.0;
			default:
				return 0;
		}
			return 0;	
		}
	
	/**
	 * Metodo che controlla quante pedine Black sono in assetto per formare la gabbia.
	 * @param state Lo stato attuale della scacchiera
	 * @return Il numero delle pedine black in assetto giusto.
	 * 
	 * (Code by L.Piazza)
	 */
	
	private ArrayList<int[]> blackInAssettoGabbiaLight(State state) {
		int [] posizione = new int[2];
		ArrayList<int[]> blackInAssetto = new ArrayList<int[]>();
	
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
	private boolean isGabbia(List<int[]> blackInAssetto) {
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
	private boolean pawnInBorderUpLeft(State state, String color) {
		boolean result=false;
		
		for(int i=0; i<3 && !result; i++) {
			if(state.getPawn(i, 0).equalsPawn(color)) {
				result=true;
			}
		}
		for(int i=0; i<2 && !result; i++) {
			if(state.getPawn(i, 1).equalsPawn(color)) {
				result=true;
			}
		}
		if(!result && state.getPawn(0, 2).equalsPawn(color)) {
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
	private boolean pawnInBorderDownLeft(State state, String color) {
		boolean result=false;
		
		for(int i=6; i<9 && !result; i++) {
			if(state.getPawn(i, 0).equalsPawn(color)) {
				result=true;
			}
		}
		for(int i=7; i<9 && !result; i++) {
			if(state.getPawn(i, 1).equalsPawn(color)) {
				result=true;
			}
		}
		if(!result && state.getPawn(8, 2).equalsPawn(color)) {
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
	private boolean pawnInBorderUpRight(State state, String color) {
		boolean result=false;
		
		for(int i=0; i<3 && !result; i++) {
			if(state.getPawn(i, 8).equalsPawn(color)) {
				result=true;
			}
		}
		for(int i=0; i<2 && !result; i++) {
			if(state.getPawn(i, 7).equalsPawn(color)) {
				result=true;
			}
		}
		if(!result && state.getPawn(0, 6).equalsPawn(color)) {
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
	private boolean pawnInBorderDownRight(State state, String color) {
		boolean result=false;
		
		for(int i=6; i<9 && !result; i++) {
			if(state.getPawn(i, 8).equalsPawn(color)) {
				result=true;
			}
		}
		for(int i=7; i<9 && !result; i++) {
			if(state.getPawn(i, 7).equalsPawn(color)) {
				result=true;
			}
		}
		if(!result && state.getPawn(8, 6).equalsPawn(color)) {
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
	private String quadranteKing(int[] king) {
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
			result="CL";
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
	
	
	//SCanc
	
	public double gabbiaStrettaDownLeft(State state) {
		//CONTROLLI FATTI IN UN'ALTRA FUNZIONE
		/*int[] controlloPedine= {0,0};
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
		*/
		if(state.getPawn(5,2).equalsPawn("B") && state.getPawn(6,3).equalsPawn("B"))
			return 1.0;
		if(state.getPawn(5,2).equalsPawn("B") || state.getPawn(6,3).equalsPawn("B"))
			return 0.5;
		
		return 0;	
	}
	
	public double gabbiaStrettaDownRight(State state) {
		/*boolean bianchi=false;
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
		}while(!bianchi&&white.size()<i);*/
		
		if(state.getPawn(5,6).equalsPawn("B") && state.getPawn(6,5).equalsPawn("B"))
			return 1.0;
		if(state.getPawn(5,6).equalsPawn("B") || state.getPawn(6,5).equalsPawn("B"))
			return 0.5;
		
		return 0;
	}
	
	public double gabbiaStrettaUpRight(State state) {
		/*boolean bianchi=false;
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
		*/
		
		if(state.getPawn(2,5).equalsPawn("B") && state.getPawn(3,6).equalsPawn("B"))
			return 1;
		if (state.getPawn(2,5).equalsPawn("B") || state.getPawn(3,6).equalsPawn("B"))
			return 0.5;
		return 0;
	}
	
	public double gabbiaStrettaUpLeft(State state) {
		/*boolean bianchi=false;
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
		*/
		if(state.getPawn(3,2).equalsPawn("B") && state.getPawn(2,3).equalsPawn("B"))
			return 1;
		if(state.getPawn(3,2).equalsPawn("B") && state.getPawn(2,3).equalsPawn("B"))
			return 0.5;
		
		return 0;
	}
	
	
	/**
	 * Funzione ottimizzata rispetto a fare un ciclo di confronti.
	 * Fa confronti singoli e, non appena trova una pedina bianca(cosa probabile) interrompe i controlli ritorna false
	 * @param state, color. Lo stato attuale della scacchiera e il colore di pedina da cercare.
	 * @return false se non ci sono pedine bianche nelle posizioni interne critiche per fare la gabbia stretta in basso a sx. 
	 *
	 * (Code by L.Piazza) 
	 */
	private boolean pawnInBoxDownLeft(State state) {
		if(state.getPawn(5,1).equalsPawn("O") && state.getPawn(6,1).equalsPawn("O") 
				&& state.getPawn(6,2).equalsPawn("O") && state.getPawn(7,1).equalsPawn("O")
				&& state.getPawn(7,2).equalsPawn("O") && state.getPawn(7,3).equalsPawn("O"))
			return false;
		return true;
	}
	
	/**
	 * Funzione ottimizzata rispetto a fare un ciclo di confronti.
	 * Fa confronti singoli e, non appena trova una pedina bianca(cosa probabile) interrompe i controlli ritorna false
	 * @param state, color. Lo stato attuale della scacchiera e il colore di pedina da cercare.
	 * @return false se non ci sono pedine bianche nelle posizioni interne critiche per fare la gabbia stretta in alto a sx. 
	 *
	 * (Code by L.Piazza) 
	 */
	private boolean pawnInBoxUpLeft(State state) {
		if(state.getPawn(1,1).equalsPawn("O") && state.getPawn(1,2).equalsPawn("O") 
				&& state.getPawn(1,3).equalsPawn("O") && state.getPawn(2,1).equalsPawn("O")
				&& state.getPawn(2,2).equalsPawn("O") && state.getPawn(3,1).equalsPawn("O"))
			return false;
		return true;
	}
	
	/**
	 * Funzione ottimizzata rispetto a fare un ciclo di confronti.
	 * Fa confronti singoli e, non appena trova una pedina bianca(cosa probabile) interrompe i controlli ritorna false
	 * @param state, color. Lo stato attuale della scacchiera e il colore di pedina da cercare.
	 * @return false se non ci sono pedine bianche nelle posizioni interne critiche per fare la gabbia stretta in alto a dx. 
	 *
	 * (Code by L.Piazza) 
	 */
	private boolean pawnInBoxUpRight(State state) {
		if(state.getPawn(1,7).equalsPawn("O") && state.getPawn(2,7).equalsPawn("O") 
				&& state.getPawn(3,7).equalsPawn("O") && state.getPawn(1,6).equalsPawn("O")
				&& state.getPawn(2,6).equalsPawn("O") && state.getPawn(1,5).equalsPawn("O"))
			return false;
		return true;
	}
	
	/**
	 * Funzione ottimizzata rispetto a fare un ciclo di confronti.
	 * Fa confronti singoli e, non appena trova una pedina bianca(cosa probabile) interrompe i controlli ritorna false
	 * @param state, color. Lo stato attuale della scacchiera e il colore di pedina da cercare.
	 * @return false se non ci sono pedine bianche nelle posizioni interne critiche per fare la gabbia stretta in basso a dx. 
	 *
	 * (Code by L.Piazza) 
	 */
	private boolean pawnInBoxDownRight(State state) {
		if(state.getPawn(5,7).equalsPawn("O") && state.getPawn(6,7).equalsPawn("O") 
				&& state.getPawn(7,7).equalsPawn("O") && state.getPawn(6,6).equalsPawn("O")
				&& state.getPawn(7,6).equalsPawn("O") && state.getPawn(7,5).equalsPawn("O"))
			return false;
		return true;
	}

}
