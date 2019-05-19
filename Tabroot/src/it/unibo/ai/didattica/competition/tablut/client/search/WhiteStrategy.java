package it.unibo.ai.didattica.competition.tablut.client.search;

import java.util.ArrayList;
import java.util.List;

import it.unibo.ai.didattica.competition.tablut.domain.State;

public class WhiteStrategy {
	public WhiteStrategy() {
		
	}
	
	public double posKing(int[] king) {
		double posKing=0;	
		/*indice 0: riga, indice 1: colonna*/
		if((king[0]==2)&&( king[1]==2 ||king[1]==6))
			posKing=1.0;
		if((king[0]==6)&&( king[1]==2 ||king[1]==6))
			posKing=1.0;
		
		return posKing;
	}
		
	public double pedineInAngoli(State state, int[] king) {
		switch(quadranteKing(king)) { 
		case "UL":
			if(state.getPawn(0, 0).equalsPawn("W"))
				return 1.0;		
			break;
		case"UR":
			if(state.getPawn(0, 8).equalsPawn("W"))
				return 1.0;	
			break;
		case"DL":
			if(state.getPawn(8, 0).equalsPawn("W"))
				return 1.0;	
			break;
		case"DR":
			if(state.getPawn(8, 8).equalsPawn("W"))
				return 1.0;	
			break;
		case"CU":
			if(state.getPawn(0, 0).equalsPawn("W") && state.getPawn(0, 8).equalsPawn("W"))
				return 1.0;
			else if(state.getPawn(0, 0).equalsPawn("W") || state.getPawn(0, 8).equalsPawn("W"))
				return 0.5;
			break;
		case"CD":
			if(state.getPawn(8, 0).equalsPawn("W") && state.getPawn(8, 8).equalsPawn("W"))
				return 1.0;
			else if(state.getPawn(8, 0).equalsPawn("W") || state.getPawn(8, 8).equalsPawn("W"))
				return 0.5;
			break;
		case"CR":
			if(state.getPawn(0, 8).equalsPawn("W") && state.getPawn(8, 8).equalsPawn("W"))
				return 1.0;
			else if(state.getPawn(0, 8).equalsPawn("W") || state.getPawn(8, 8).equalsPawn("W"))
				return 0.5;
			break;
		case"CL":
			if(state.getPawn(8, 0).equalsPawn("W") && state.getPawn(0, 0).equalsPawn("W"))
				return 1.0;
			else if(state.getPawn(8, 0).equalsPawn("W") || state.getPawn(0, 0).equalsPawn("W"))
				return 0.5;
			break;
		case "Throne":
			return whiteInAngoli(state)/4.0;
		default:
			return 0;

		}
		return 0;
	}
	
	public double scappaRe(State state, List<int[]> white, List<int[]> black, int[] king) {
		double scappaRe=0;
		int latiCopertiDalTronoDelRe=0;
		int neriVicinoAlRe=0;
		int[] controlloPedine= {0,0};
		
		//controllo se il re e' vicino al trono
				if((king[0]== 3 && king[1]==4)||(king[0]== 5 && king[1]==4)
						||(king[0]== 4 && king[1]==3)||(king[0]== 4 && king[1]==5))
					latiCopertiDalTronoDelRe++;
				//se sono sul trono o sono vicino al trono il re deve essere circondato
				if((king[0]== 4 && king[1]==4)||(latiCopertiDalTronoDelRe==1))
				{
					//conto i neri vicino al re
					for(int i=0;i<black.size();i++) {
						controlloPedine=black.get(i);
						//controllo a sinistra del re
						if(controlloPedine[0]==(king[0]) &&controlloPedine[1]==(king[1]-1)) 
							neriVicinoAlRe++;
						//controllo a destra del re
						if(controlloPedine[0]==(king[0]) &&controlloPedine[1]==(king[1]+1)) 
							neriVicinoAlRe++;
						//controllo a sopra del re
						if(controlloPedine[0]==(king[0]-1) &&controlloPedine[1]==(king[1]))
							neriVicinoAlRe++;
						//controllo a sotto del re
						if(controlloPedine[0]==(king[0]+1) &&controlloPedine[1]==(king[1])) 
							neriVicinoAlRe++;
					}
					latiCopertiDalTronoDelRe+=neriVicinoAlRe;
					//Se ho piu' di tre lati occupati devo scappare con il re.
					if(latiCopertiDalTronoDelRe>=3)
						scappaRe=-1.0;
				}
				else {/*non sono sul trono e neanche accanto ad esso,
					quindi puo' essere mangiato normalmente.*/
					//controllo le intersezioni doppie D2/F2/H4/H6/F8/D8/B6/B4
					boolean adiacenteAccampamento=false;
					if((king[0]== 3 && king[1]==1)||(king[0]== 5 && king[1]==1)
							||(king[0]== 1 && king[1]==3)||(king[0]== 1 && king[1]==5)
							||(king[0]== 7 && king[1]==3)||(king[0]== 7 && king[1]==5)
							||(king[0]== 3 && king[1]==7)||(king[0]== 5 && king[1]==7))
						adiacenteAccampamento=true;
					//controllo gli accampamenti E3/C5/H5/E5
					if((king[0]== 2 && king[1]==4)||(king[0]== 6 && king[1]==4)
							||(king[0]== 4 && king[1]==2)||(king[0]== 4 && king[1]==6))
						adiacenteAccampamento=true;
					//conto i neri vicino al re
					for(int i=0;i<black.size();i++) {
						controlloPedine=black.get(i);
						//controllo a sinistra del re
						if(controlloPedine[0]==(king[0]) &&controlloPedine[1]==(king[1]-1)) 
							neriVicinoAlRe++;
						//controllo a destra del re
						if(controlloPedine[0]==(king[0]) &&controlloPedine[1]==(king[1]+1)) 
							neriVicinoAlRe++;
						//controllo a sopra del re
						if(controlloPedine[0]==(king[0]-1) &&controlloPedine[1]==(king[1]))
							neriVicinoAlRe++;
						//controllo a sotto del re
						if(controlloPedine[0]==(king[0]+1) &&controlloPedine[1]==(king[1])) 
							neriVicinoAlRe++;
					}
					if(neriVicinoAlRe>=1 || adiacenteAccampamento)
						scappaRe=-1.0;
				}
				return scappaRe;
	}
	
	/*A.Fuschino
	 * valuto positivamente(ma meno rispetto a una pedina nell'angolo) una pedina vicina a gli angoli e ai bordi della tavola da gioco
	 * variabile: valutazionePedinaBordiAngoli
	 * valutazione: 0.01 ???
//	DA TOGLIERE?!*/
	
	public double vicinanzaBordiAngoli(List<int[]> white) {
		double valutazionePedinaBordiAngoli=0;
		int[] controlloPedine= {0,0};
		
		for(int i=0;i<white.size();i++){
			controlloPedine=white.get(i);

			//in alto a sinistra
			if(controlloPedine[0]==1 && controlloPedine[1]==0) {
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}				
			if(controlloPedine[0]==2 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==1 ){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==2){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}

			//in alto a destra
			if(controlloPedine[0]==1 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==2 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==6 ){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==7){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}

			//in basso a sinistra 
			if(controlloPedine[0]==6 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==7 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==1 ){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==2){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}

			//in basso a destra 
			if(controlloPedine[0]==7 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==6 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==7 ){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==6){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
		}
		return valutazionePedinaBordiAngoli;

	}
	
	
	/*A.Fuschino
	 * controlla se nero sta facendo la gabbia e cerca di dargli fastidio mettendo pedine bianche in mezzoz al suo assetto
		ritorna: contrastaGabbiaNero
	Non usata
	 */
	public double contrastaGabbia(State state, List<int[]> white, int[] king){
		
		double contrastaGabbia=0;
		double count=0;
		
		//in alto a destra
		if(state.getPawn(1,6).equalsPawn("B") && state.getPawn(2,7).equalsPawn("W"))
			contrastaGabbia+=0.125;
		if(state.getPawn(1,6).equalsPawn("W") && state.getPawn(2,7).equalsPawn("B"))
			contrastaGabbia+=0.125;
		
		//in basso destra
		if(state.getPawn(6,7).equalsPawn("B") && state.getPawn(7,6).equalsPawn("W"))
			contrastaGabbia+=0.125;
		if(state.getPawn(6,7).equalsPawn("W") && state.getPawn(7,6).equalsPawn("B"))
			contrastaGabbia+=0.125;
		
		//in basso a sinistra 
		if(state.getPawn(6,1).equalsPawn("B") && state.getPawn(7,2).equalsPawn("W"))
			contrastaGabbia+=0.125;
		if(state.getPawn(6,1).equalsPawn("W") && state.getPawn(7,2).equalsPawn("B"))
			contrastaGabbia+=0.125;
		
		//in altro a sinstra
		if(state.getPawn(1,2).equalsPawn("B") && state.getPawn(2,1).equalsPawn("W"))
			contrastaGabbia+=0.125;
		if(state.getPawn(1,2).equalsPawn("W") && state.getPawn(2,1).equalsPawn("B"))
			contrastaGabbia+=0.125;
		
	
		return contrastaGabbia;		
	}
	
	
	/*A.Fuschino
	 * alcune mosse intelligente da fare in determinate situazioni
	 * ritorna:valutazioneMosseIntelligenti 
	 */
	
	public double mosseIntelligenti(State state, List<int[]> white, int[] king){

		double valutazioneMosseIntelligenti=0;

		//se la casella in alto degli accampamenti e' vuota mi muovo verso il bordo angolo evitando di essere mangiato


		//quadrante dx in basso: sinistra  
		if(state.getPawn(4,7).equalsPawn("O") 
				&& !state.getPawn(5,7).equalsPawn("B")  
				&& !state.getPawn(6,7).equalsPawn("B") 
				&& !state.getPawn(7,7).equalsPawn("B")
				&& !state.getPawn(8,7).equalsPawn("B")
				&& state.getPawn(8,6).equalsPawn("W")
				&& !state.getPawn(8,8).equalsPawn("B")
				&& (state.getPawn(8,5).equalsPawn("B")
				|| state.getPawn(8,4).equalsPawn("B")
				|| state.getPawn(8,3).equalsPawn("B"))) {
			valutazioneMosseIntelligenti+=0.15;	
		}
			
				
		//quadrante dx in basso: destra  
		if(state.getPawn(7,4).equalsPawn("O") 
				&& state.getPawn(6,8).equalsPawn("W")
				&& !state.getPawn(7,5).equalsPawn("B") 
				&& !state.getPawn(7,6).equalsPawn("B") 
				&& !state.getPawn(7,7).equalsPawn("B")
				&& !state.getPawn(7,8).equalsPawn("B")
				&& !state.getPawn(8,8).equalsPawn("B")
				&& (state.getPawn(3,8).equalsPawn("B")
				|| state.getPawn(4,8).equalsPawn("B")
				|| state.getPawn(5,8).equalsPawn("B"))){
			valutazioneMosseIntelligenti+=0.15;
		}
			
		//quadrante dx in alto: destra
		if(state.getPawn(1,4).equalsPawn("O") 
				&& state.getPawn(2,8).equalsPawn("W") 
				&& !state.getPawn(1,5).equalsPawn("B") 
				&& !state.getPawn(1,6).equalsPawn("B") 
				&& !state.getPawn(1,7).equalsPawn("B")
				&& !state.getPawn(1,8).equalsPawn("B") 
				&& !state.getPawn(0,8).equalsPawn("B")
				&&( state.getPawn(3,8).equalsPawn("B")
				||state.getPawn(4,8).equalsPawn("B")
				|| state.getPawn(5,8).equalsPawn("B"))) {
			valutazioneMosseIntelligenti+=0.15;
		}
			
		//quadrante dx in alto: sinistra 
		if(state.getPawn(4,7).equalsPawn("O") 
				&& state.getPawn(0,6).equalsPawn("W") 
				&& !state.getPawn(3,7).equalsPawn("B") 
				&& !state.getPawn(2,7).equalsPawn("B") 
				&& !state.getPawn(1,7).equalsPawn("B")
				&& !state.getPawn(0,7).equalsPawn("B")
				&& !state.getPawn(0,8).equalsPawn("B")
				&&(state.getPawn(0,3).equalsPawn("B")
				|| state.getPawn(0,4).equalsPawn("B")
				|| state.getPawn(0,5).equalsPawn("B"))) {
			valutazioneMosseIntelligenti+=0.15;
		}
			

		//quadrante sx in alto: destra
		if(state.getPawn(1,4).equalsPawn("O") 
				&& state.getPawn(2,0).equalsPawn("W") 
				&& !state.getPawn(1,3).equalsPawn("B") 
				&& !state.getPawn(1,2).equalsPawn("B") 
				&& !state.getPawn(1,1).equalsPawn("B")
				&& !state.getPawn(1,0).equalsPawn("B")
				&& !state.getPawn(0,0).equalsPawn("B")
				&&(state.getPawn(3,0).equalsPawn("B")
				||state.getPawn(4,0).equalsPawn("B")
				||state.getPawn(5,0).equalsPawn("B"))) {
			valutazioneMosseIntelligenti+=0.15;
		}
			

		//quadrante sx in alto: sinistra 
		if(state.getPawn(4,1).equalsPawn("O") 
			&& state.getPawn(0,2).equalsPawn("W") 
			&& !state.getPawn(3,1).equalsPawn("B")
			&& !state.getPawn(2,1).equalsPawn("B") 
			&& !state.getPawn(1,1).equalsPawn("B")
			&& !state.getPawn(0,1).equalsPawn("B")
			&& !state.getPawn(0,0).equalsPawn("B")
			&&( state.getPawn(0,3).equalsPawn("B")
			|| state.getPawn(0,4).equalsPawn("B")
			||state.getPawn(0,5).equalsPawn("B"))) {
			valutazioneMosseIntelligenti+=0.15;
		}
			

		//quadrante sx in basso: destra
		if(state.getPawn(7,4).equalsPawn("O") 
				&& state.getPawn(6,0).equalsPawn("W") 
				&& !state.getPawn(7,3).equalsPawn("B") 
				&& !state.getPawn(7,2).equalsPawn("B") 
				&& !state.getPawn(7,1).equalsPawn("B")
				&& !state.getPawn(7,0).equalsPawn("B")
				&& !state.getPawn(8,0).equalsPawn("B")
				&&(state.getPawn(3,0).equalsPawn("B")
				|| state.getPawn(4,0).equalsPawn("B")
				|| state.getPawn(5,0).equalsPawn("B"))) {
			valutazioneMosseIntelligenti+=0.15;
		}
			
		
		//quadrante sx in basso: sinistra 
		if(state.getPawn(4,1).equalsPawn("O") 
		   && state.getPawn(8,2).equalsPawn("W") 
		   && !state.getPawn(5,1).equalsPawn("B") 
		   && !state.getPawn(6,1).equalsPawn("B") 
		   && !state.getPawn(7,1).equalsPawn("B")
		   && !state.getPawn(8,1).equalsPawn("B")
		   && !state.getPawn(8,0).equalsPawn("B")
		   && (state.getPawn(8,3).equalsPawn("B")
		   || state.getPawn(8,4).equalsPawn("B")
		   || state.getPawn(8,5).equalsPawn("B"))) {
			valutazioneMosseIntelligenti+=0.15;
		}
			
		
		if(valutazioneMosseIntelligenti>0.5)
			return 0;
		
		if(valutazioneMosseIntelligenti > 0.15 && state.getPawn(8,2).equalsPawn("W")&&
				state.getPawn(6,0).equalsPawn("W"))
			valutazioneMosseIntelligenti*=2.0;	
		
		if(valutazioneMosseIntelligenti > 0.15 && state.getPawn(0,2).equalsPawn("W")&&
				state.getPawn(2,0).equalsPawn("W"))
			valutazioneMosseIntelligenti*=2.0;
		
		if(valutazioneMosseIntelligenti > 0.15 && state.getPawn(8,6).equalsPawn("W")&&
				state.getPawn(6,8).equalsPawn("W"))
			valutazioneMosseIntelligenti*=2.0;
		
		if(valutazioneMosseIntelligenti > 0.15 && state.getPawn(2,8).equalsPawn("W")&&
				state.getPawn(0,6).equalsPawn("W") )
			valutazioneMosseIntelligenti*=2.0;
		
		return valutazioneMosseIntelligenti;	
	}
	
	
	/* S.Cancello and A.Fuschino
	 *  valutiamo nei quattro quadranti il migliore in cui sostare il re protrggendolo con ua pedina adiacente
	 */

	/*
	
	public double valQuadranti(State state) {	
		
		
		double valQuadranteAltoSX=valQuadranteAltoSX(state);
		double valQuadranteAltoDX=valQuadranteAltoDX(state);
		double valQuadranteBassoDX=valQuadranteBassoDX(state);
		double valQuadranteBassoSX=valQuadranteBassoSX(state);
		
		return Math.max(valQuadranteAltoSX,Math.max( valQuadranteAltoDX,Math.max( valQuadranteBassoDX,valQuadranteBassoSX)));
	}
*/
	
	public double valQuadranteAltoSX(State state) {

		int countBianchi=0;
		
		if(state.getPawn(0, 0).equalsPawn(("W")))
				countBianchi++;
		
		for(int j=1; j<3;j++) {
			
			if(state.getPawn(0, j).equalsPawn(("W")))
				countBianchi++;
			
			if(state.getPawn(j, 0).equalsPawn(("W")))
				countBianchi++;	
		}
		
		return countBianchi;
	}


	public double valQuadranteAltoDX(State state) {

		int countBianchi=0;
			
		if(state.getPawn(0, 8).equalsPawn(("W")))
			countBianchi++;

		
		for(int j=1; j<3;j++) {

			if(state.getPawn(0, 5+j).equalsPawn(("W")))
				countBianchi++;

			if(state.getPawn(j, 8).equalsPawn(("W")))
				countBianchi++;	
		}

		return countBianchi;
	}

	public double valQuadranteBassoDX(State state) {

		int countBianchi=0;

		if(state.getPawn(8, 8).equalsPawn(("W")))
			countBianchi++;

		for(int j=1; j<3;j++) {

			if(state.getPawn(8, 5+j).equalsPawn(("W")))
				countBianchi++;

			if(state.getPawn(5+j, 8).equalsPawn(("W")))
				countBianchi++;
		}

		return countBianchi;
	}



	public double valQuadranteBassoSX(State state) {

		int countBianchi=0;

		if(state.getPawn(8, 0).equalsPawn(("W")))
			countBianchi++;

		for(int j=1; j<3;j++) {

			if(state.getPawn(8, j).equalsPawn(("W")))
				countBianchi++;

			if(state.getPawn(5+j, 0).equalsPawn(("W")))
				countBianchi++;	

		}

		return countBianchi;
	}
	

	public double valutaQuadrantiKing(State state, int[] king) {
		
		double valutazione=0;

		switch(quadranteKing(king)) { 

		case "UL":

			if(valQuadranteAltoSX(state) == 1) {
				valutazione=0.25;
			}else if(valQuadranteAltoSX(state)>1) {
				valutazione=0.40;
			}


			if(state.getPawn(2, 5).equalsPawn(("W"))||state.getPawn(2, 6).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(3, 5).equalsPawn(("W"))||state.getPawn(3, 6).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(5, 2).equalsPawn(("W"))||state.getPawn(6, 2).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(5, 3).equalsPawn(("W"))||state.getPawn(6, 3).equalsPawn(("W")))
				valutazione+=0.15;

			break;
			
			

		case"UR":
			
			if(valQuadranteAltoDX(state) == 1) {
				valutazione=0.25;
			}else if(valQuadranteAltoDX(state)>1) {
				valutazione=0.40;
			}
			
			
			if(state.getPawn(2, 3).equalsPawn(("W"))||state.getPawn(2, 2).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(3, 3).equalsPawn(("W"))||state.getPawn(3, 2).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(5, 5).equalsPawn(("W"))||state.getPawn(5, 6).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(6, 5).equalsPawn(("W"))||state.getPawn(6, 6).equalsPawn(("W")))
				valutazione+=0.15;
			break;

			
		case"DL":

			if(valQuadranteBassoSX(state) == 1) {
				valutazione=0.25;
			}else if(valQuadranteBassoSX(state)>1) {
				valutazione=0.40;
			}
		
			
			if(state.getPawn(3, 2).equalsPawn(("W"))||state.getPawn(2, 2).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(3, 3).equalsPawn(("W"))||state.getPawn(2, 3).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(5, 5).equalsPawn(("W"))||state.getPawn(5, 6).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(6, 5).equalsPawn(("W"))||state.getPawn(6, 6).equalsPawn(("W")))
				valutazione+=0.15;

			break;
	

		case"DR":

			if(valQuadranteBassoDX(state) == 1) {
				valutazione=0.25;
			}else if(valQuadranteBassoDX(state)>1) {
				valutazione=0.40;
			}

			if(state.getPawn(3, 5).equalsPawn(("W"))||state.getPawn(2, 5).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(3, 6).equalsPawn(("W"))||state.getPawn(2, 6).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(5, 3).equalsPawn(("W"))||state.getPawn(5, 2).equalsPawn(("W")))
				valutazione+=0.15;

			if(state.getPawn(6, 3).equalsPawn(("W"))||state.getPawn(6, 2).equalsPawn(("W")))
				valutazione+=0.15;
			break;

		case"CU":
			if(valQuadranteAltoDX(state)>0 || valQuadranteAltoSX(state)>0)
			{
				if(valQuadranteAltoDX(state)>=valQuadranteAltoSX(state)){

					if(state.getPawn(3, 3).equalsPawn(("W"))||state.getPawn(3, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(2, 3).equalsPawn(("W"))||state.getPawn(2, 2).equalsPawn(("W")))
						valutazione+=0.15;
					
					if(state.getPawn(5, 5).equalsPawn(("W"))||state.getPawn(5, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(6, 5).equalsPawn(("W"))||state.getPawn(6, 6).equalsPawn(("W")))
						valutazione+=0.15;

					return valutazione+=0.2*valQuadranteAltoDX(state);

				}else if(valQuadranteAltoDX(state)<valQuadranteAltoSX(state)){


					if(state.getPawn(2, 5).equalsPawn(("W"))||state.getPawn(2, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(3, 5).equalsPawn(("W"))||state.getPawn(3, 6).equalsPawn(("W")))
						valutazione+=0.15;
					
					if(state.getPawn(5, 2).equalsPawn(("W"))||state.getPawn(6, 2).equalsPawn(("W")))
						valutazione+=0.15;
					
					if(state.getPawn(5, 3).equalsPawn(("W"))||state.getPawn(6, 3).equalsPawn(("W")))
						valutazione+=0.15;

					return valutazione+=0.2*valQuadranteAltoSX(state);
				}
			}
			

			break;
			
		case"CD":
			if(valQuadranteBassoDX(state)>0 || valQuadranteBassoSX(state)>0)
			{
				if(valQuadranteBassoDX(state)>=valQuadranteBassoSX(state)){

					if(state.getPawn(3, 5).equalsPawn(("W"))||state.getPawn(2, 5).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(3, 6).equalsPawn(("W"))||state.getPawn(2, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(5, 3).equalsPawn(("W"))||state.getPawn(5, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(6, 3).equalsPawn(("W"))||state.getPawn(6, 2).equalsPawn(("W")))
						valutazione+=0.15;
					return valutazione+=0.2*valQuadranteBassoDX(state);

				}else if(valQuadranteBassoDX(state)<valQuadranteBassoSX(state)){


					if(state.getPawn(3, 2).equalsPawn(("W"))||state.getPawn(2, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(3, 3).equalsPawn(("W"))||state.getPawn(2, 3).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(5, 5).equalsPawn(("W"))||state.getPawn(5, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(6, 5).equalsPawn(("W"))||state.getPawn(6, 6).equalsPawn(("W")))
						valutazione+=0.15;
					return valutazione+=0.2*valQuadranteBassoSX(state);

				}
			}
			

			break;

		case"CR":
			if(valQuadranteAltoDX(state)>0||valQuadranteBassoDX(state)>0){
				if(valQuadranteAltoDX(state)>=valQuadranteBassoDX(state)){

					if(state.getPawn(2, 3).equalsPawn(("W"))||state.getPawn(2, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(3, 3).equalsPawn(("W"))||state.getPawn(3, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(5, 5).equalsPawn(("W"))||state.getPawn(5, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(6, 5).equalsPawn(("W"))||state.getPawn(6, 6).equalsPawn(("W")))
						valutazione+=0.15;

					return valutazione+=0.2*valQuadranteAltoDX(state);

				}else if(valQuadranteAltoDX(state)<valQuadranteBassoDX(state)){


					if(state.getPawn(3, 5).equalsPawn(("W"))||state.getPawn(2, 5).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(3, 6).equalsPawn(("W"))||state.getPawn(2, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(5, 3).equalsPawn(("W"))||state.getPawn(5, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(6, 3).equalsPawn(("W"))||state.getPawn(6, 2).equalsPawn(("W")))
						valutazione+=0.15;

					return valutazione+=0.2*valQuadranteBassoDX(state);

				}
			}
			
			break;

		case"CL":
			if(valQuadranteAltoSX(state)>0||valQuadranteBassoSX(state)>0){
				if(valQuadranteAltoSX(state)>=valQuadranteBassoSX(state)){

					if(state.getPawn(2, 5).equalsPawn(("W"))||state.getPawn(2, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(3, 5).equalsPawn(("W"))||state.getPawn(3, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(5, 2).equalsPawn(("W"))||state.getPawn(6, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(5, 3).equalsPawn(("W"))||state.getPawn(6, 3).equalsPawn(("W")))
						valutazione+=0.15;

					return valutazione+=0.2*valQuadranteAltoSX(state);

				}else if(valQuadranteAltoSX(state)<valQuadranteBassoSX(state)){

					if(state.getPawn(3, 2).equalsPawn(("W"))||state.getPawn(2, 2).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(3, 3).equalsPawn(("W"))||state.getPawn(2, 3).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(5, 5).equalsPawn(("W"))||state.getPawn(5, 6).equalsPawn(("W")))
						valutazione+=0.15;

					if(state.getPawn(6, 5).equalsPawn(("W"))||state.getPawn(6, 6).equalsPawn(("W")))
						valutazione+=0.15;

					return valutazione+=0.2*valQuadranteBassoSX(state);

				}
			}
			
			break;

		default:
			return 0;
		}
		return valutazione;
	}


	public double valutazioneAssettoTorre(State state, List<int[]> white, int[] king){
		double valutazioneAssettoTorre=0;
		boolean pedinaSotto=false;
		boolean pedinaSopra=false;
		boolean pedinaDestra=false;
		boolean pedinaSinistra=false;
		boolean pedinaSottoSinistra=false;
		boolean pedinaSottoDestra=false;
		boolean pedinaSopraSinistra=false;
		boolean pedinaSopraDestra=false;
		int[] controlloPedine= {0,0};
		int nBianche=white.size();
		if(nBianche>=4)
		{
			for(int i=0;i<nBianche;i++){
				controlloPedine=white.get(i);
				//controllo che il re abbia una pedina bianca sopra
				if(controlloPedine[0]==king[0]-1 && controlloPedine[1]==king[1]) {
					pedinaSopra=true;
				}
				//controllo che il re abbia una pedina bianca sotto
				if(controlloPedine[0]==king[0]+1 && controlloPedine[1]==king[1]) {
					pedinaSotto=true;
				}
				//controllo che il re abbia una pedina bianca a sinistra
				if(controlloPedine[0]==king[0] && controlloPedine[1]==king[1]-1) {
					pedinaSinistra=true;
				}
				//controllo che il re abbia una pedina bianca a destra
				if(controlloPedine[0]==king[0] && controlloPedine[1]==king[1]+1) {
					pedinaDestra=true;
				}
				//controllo che il re abbia una pedina bianca sotto a sinistra
				if(controlloPedine[0]==king[0]+1 && controlloPedine[1]==king[1]-1) {
					pedinaSottoSinistra=true;
				}
				//controllo che il re abbia una pedina bianca sotto a destra
				if(controlloPedine[0]==king[0]+1 && controlloPedine[1]==king[1]+1) {
					pedinaSottoDestra=true;
				}
				//controllo che il re abbia una pedina bianca sopra a sinistra
				if(controlloPedine[0]==king[0]-1 && controlloPedine[1]==king[1]-1) {
					pedinaSopraSinistra=true;
				}
				//controllo che il re abbia una pedina bianca sopra a destra
				if(controlloPedine[0]==king[0]-1 && controlloPedine[1]==king[1]+1) {
					pedinaSopraDestra=true;
				}
				
				/* DA RAGIONARE insieme, potrebbe tendere a far restar ferme le pedina accanto al re.
				//se ha un lato coperto, esempio sopra 
				if(pedinaSinistra ||pedinaSopra||pedinaSotto||pedinaDestra)
					valutazioneAssettoTorre=3;
				*/
				//se ha due lati coperti, esempio sopra e destra
				if((pedinaSinistra && pedinaSotto )||(pedinaSinistra && pedinaSopra)||
						(pedinaDestra&&pedinaSotto)||(pedinaDestra&&pedinaSopra))
					valutazioneAssettoTorre=0.5;
				//configurazione torre
				if((pedinaSinistra && pedinaSotto && pedinaSottoSinistra)||(pedinaSinistra && pedinaSopra&& pedinaSopraSinistra)||
						(pedinaDestra&&pedinaSotto&&pedinaSottoDestra)||(pedinaDestra&&pedinaSopra&&pedinaSopraDestra))
					valutazioneAssettoTorre=1;
			}	
		}
		return valutazioneAssettoTorre;
	}
	
	
	public String quadranteKing(int[] king) {
		String result="Throne";
		
		//Re nel quadrante in alto a sinistra
		if( (king[0]>=1 && king[0]<=3) && (king[1]>=1 && king[1]<=3) )
			return "UL";
		
		//Re nel quadrante in basso a sinistra
		if( (king[0]>=5 && king[0]<=7) && (king[1]>=1 && king[1]>=3) )
			return "DL";
		
		//Re nel quadrante in alto a destra
		if( (king[0]>=1 && king[0]<=3) && (king[1]>=5 && king[1]>=7) )
			return"UR";
		
		//Re nel quadrante in basso a destra
		if( (king[0]>=5 && king[0]<=7) && (king[1]>=5 && king[1]>=7) )
			return "DR";
		
		//Re nella parte alta della croce
		if((king[0]==2 || king[0]==3) && king[1]==4)
			return "CU";
		
		//Re nella parte bassa della croce
		if((king[0]==5 || king[0]==6) && king[1]==4)
			return "CD";
		
		//Re nella parte destra della croce
		if(king[0]==4 && (king[0]==5 || king[0]==6))
			return "CR";
		
		//Re nella parte sinistra della croce
		if(king[0]==4 && (king[0]==2 || king[0]==3))
			return "CL";
		
		return result;
	}
	

	
	private int whiteInAngoli(State state) {
		int [] posizione = new int[2];
		List<int[]> whiteInAngoli = new ArrayList<int[]>();
	
		if(state.getPawn(0,0).equalsPawn("W")) {
			posizione[0]=0;
			posizione[1]=0;
			whiteInAngoli.add(posizione);
		}
		if(state.getPawn(0,8).equalsPawn("W")) {
			posizione[0]=0;
			posizione[1]=8;
			whiteInAngoli.add(posizione);
		}
		if(state.getPawn(8,0).equalsPawn("W")) {
			posizione[0]=8;
			posizione[1]=0;
			whiteInAngoli.add(posizione);
		}
		if(state.getPawn(8,8).equalsPawn("W")) {
			posizione[0]=8;
			posizione[1]=8;
			whiteInAngoli.add(posizione);
		}
		
		return whiteInAngoli.size();
	}
}
