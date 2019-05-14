package it.unibo.ai.didattica.competition.tablut.client.lori;

import java.util.List;

import it.unibo.ai.didattica.competition.tablut.domain.State;

public class WhiteStrategy {
	public WhiteStrategy() {
		
	}
	
	public double posKing(int[] king) {
		double posKing=0;	
		/*indice 0: riga, indice 1: colonna*/
		if((king[0]==2)&&( king[1]==2 ||king[1]==6))
			posKing=1;
		if((king[0]==6)&&( king[1]==2 ||king[1]==6))
			posKing=1;
		
		return posKing;
	}
	
	/*(LORI: Ho aggiunto il continue per evitare dei controlli if superflui). Va bene??*/
	public double pedineInAngoli(List<int[]> white) {
		int[] controlloPedine= {0,0};
		double pedineInAngolo=0;
		
		for(int i=0;i<white.size();i++){
			controlloPedine=white.get(i);
			if(controlloPedine[0]==0 && controlloPedine[1]==0) {
				pedineInAngolo+=0.15;
				continue;
			}				
			if(controlloPedine[0]==8 && controlloPedine[1]==8) {
				pedineInAngolo+=0.15;
				continue;
			}				
			if(controlloPedine[0]==8 && controlloPedine[1]==0 ) {
				pedineInAngolo+=0.15;
				continue;
			}				
			if(controlloPedine[0]==0 && controlloPedine[1]==8) {
				pedineInAngolo+=0.15;
				continue;
			}		
		}
		return pedineInAngolo;
	}
	
	public double scappaRe(State state, List<int[]> white, List<int[]> black, int[] king) {
		double scappaRe=0;
		int latiCopertiDalTronoDelRe=0;
		int neriVicinoAlRe=0;
		int[] controlloPedine= {0,0};
		
		//controllo se il re è vicino al trono
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
					//Se ho più di tre lati occupati devo scappare con il re.
					if(latiCopertiDalTronoDelRe>=3)
						scappaRe=-10;
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
						scappaRe=-1;
				}
				return scappaRe;
	}
	
	/*A.Fuschino
	 * valuto positivamente(ma meno rispetto a una pedina nell'angolo) una pedina vicina a gli angoli e ai bordi della tavola da gioco
	 * variabile: valutazionePedinaBordiAngoli
	 * valutazione: 0.01 ???
	 *(LORI: Ho aggiunto il continue per evitare dei controlli if superflui. Va bene??)*/
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
			if(controlloPedine[0]==2 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==7 ){
				valutazionePedinaBordiAngoli+=0.05;
				continue;
			}
			if(controlloPedine[0]==2 && controlloPedine[1]==8){
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
	 * controllo se i bianchi sono nella formazione buona per poter iniziare a muovere il re (quella che piace a me)
	 * variabile: valutazioneAssettoFusco
	 * note: per ora ho considerato il quadrante in basso a destra bisogna fare la stessa cosa con gli altri 3 quadranti a seconda se i neri hanno mosso una o due pedine critiche
	 *(LORI: Ho aggiunto il continue per evitare dei controlli if superflui. Va bene??)*/
	public double valutazioneAssettoFusco(State state, List<int[]> white, int[] king){
		
		double valutazioneAssettoFusco=0;
			
	
		//assetto base inziale

		int assettoIniziale=0;
	

		if(state.getPawn(4,5).equalsPawn("W") && state.getPawn(3,5).equalsPawn("W")){
			valutazioneAssettoFusco+=1;
			assettoIniziale++;
		}

		if(state.getPawn(5,4).equalsPawn("W") && state.getPawn(5,3).equalsPawn("W")){
			valutazioneAssettoFusco+=1;		
			assettoIniziale++;
		}
		
	

		if(state.getPawn(4,6).equalsPawn("W")) {
			valutazioneAssettoFusco+=0.2;		
			assettoIniziale++;
		}

		if(state.getPawn(6,4).equalsPawn("W")) {
			valutazioneAssettoFusco+=0.2;	
			assettoIniziale++;
		}


		//cerca di contrastare la gabbia 

		if(state.getPawn(7,6).equalsPawn("B"))
			if(state.getPawn(6,7).equalsPawn("W"))
				valutazioneAssettoFusco+=0.35;

		if(state.getPawn(6,7).equalsPawn("B"))
			if(state.getPawn(7,6).equalsPawn("W"))
				valutazioneAssettoFusco+=0.35;

		
		//mosse dopo l'assetto inziale	


		if(state.getPawn(7,5).equalsPawn("W") && state.getPawn(3,5).equalsPawn("W"))
			valutazioneAssettoFusco+=0.7;
		if(state.getPawn(5,7).equalsPawn("W") && state.getPawn(5,3).equalsPawn("W"))
			valutazioneAssettoFusco+=0.7;


		if(!state.getPawn(6,0).equalsPawn("B") && !state.getPawn(6,1).equalsPawn("B") && !state.getPawn(6,2).equalsPawn("B") && !state.getPawn(6,3).equalsPawn("B") && assettoIniziale==4)  
			if(state.getPawn(6,8).equalsPawn("W"))
				valutazioneAssettoFusco+=0.35;	
		if(!state.getPawn(0,6).equalsPawn("B") && !state.getPawn(1,6).equalsPawn("B") && !state.getPawn(2,6).equalsPawn("B") && !state.getPawn(3,6).equalsPawn("B") && assettoIniziale==4)  
			if(state.getPawn(6,8).equalsPawn("W"))
				valutazioneAssettoFusco+=0.35;

		if(state.getPawn(6,2).equalsPawn("W") && state.getPawn(6,3).equalsPawn("W") && assettoIniziale==4)  
			valutazioneAssettoFusco+=0.25;
		if(state.getPawn(2,6).equalsPawn("W") && state.getPawn(3,6).equalsPawn("W") && assettoIniziale==4)  
			valutazioneAssettoFusco+=0.25;


		//incomincio a muovere il re 
		if(state.getPawn(5,3).equalsPawn("W") && (king[0]==5 && king[1]==4) && assettoIniziale==4)
			valutazioneAssettoFusco+=0.35;

	

		return valutazioneAssettoFusco;
	}
	
	
	/*A.Fuschino
	 * controlla se nero sta facendo la gabbia e cerca di dargli fastidio mettendo pedine bianche in mezzoz al suo assetto
		ritorna: contrastaGabbiaNero
	 */
	public double contrastaGabbia(State state, List<int[]> white, int[] king){
		
		double contrastaGabbia=0;
		
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
	 * guardare pedine angoli !!
	 */
	
	public double mosseIntelligenti(State state, List<int[]> white, int[] king){

		double valutazioneMosseIntelligenti=0;

		//se la casella in alto degli accampamenti è vuota mi muovo verso il bordo angolo evitando di essere mangiato


		//quadrante dx in basso: sinistra  
		if(state.getPawn(4,7).equalsPawn("O") && !state.getPawn(5,7).equalsPawn("B")  && !state.getPawn(6,7).equalsPawn("B") && !state.getPawn(7,7).equalsPawn("B")&& !state.getPawn(8,7).equalsPawn("B")   && state.getPawn(8,6).equalsPawn("W"))
			valutazioneMosseIntelligenti+=0.125;	
		//quadrante dx in basso: destra  
		if(state.getPawn(7,4).equalsPawn("O") && state.getPawn(6,8).equalsPawn("W") && !state.getPawn(7,5).equalsPawn("B") && !state.getPawn(7,6).equalsPawn("B") && !state.getPawn(7,7).equalsPawn("B")&& !state.getPawn(7,8).equalsPawn("B"))
			valutazioneMosseIntelligenti+=0.125;


		//quadrante dx in alto: destra
		if(state.getPawn(1,4).equalsPawn("O") && state.getPawn(2,8).equalsPawn("W") && !state.getPawn(1,5).equalsPawn("B") && !state.getPawn(1,6).equalsPawn("B") && !state.getPawn(1,7).equalsPawn("B")&& !state.getPawn(1,8).equalsPawn("B"))
			valutazioneMosseIntelligenti+=0.125;
		//quadrante dx in alto: sinistra 
		if(state.getPawn(4,7).equalsPawn("O") && state.getPawn(0,6).equalsPawn("W") && !state.getPawn(3,7).equalsPawn("B") && !state.getPawn(2,7).equalsPawn("B") && !state.getPawn(1,7).equalsPawn("B")&& !state.getPawn(0,7).equalsPawn("B"))
			valutazioneMosseIntelligenti+=0.125;


		//quadrante sx in alto: destra
		if(state.getPawn(1,4).equalsPawn("O") && state.getPawn(2,0).equalsPawn("W") && !state.getPawn(1,3).equalsPawn("B") && !state.getPawn(1,2).equalsPawn("B") && !state.getPawn(1,1).equalsPawn("B")&& !state.getPawn(1,0).equalsPawn("B"))
			valutazioneMosseIntelligenti+=0.125;
		//quadrante sx in alto: sinistra 
		if(state.getPawn(4,1).equalsPawn("O") && state.getPawn(0,2).equalsPawn("W") && !state.getPawn(3,1).equalsPawn("B") && !state.getPawn(2,1).equalsPawn("B") && !state.getPawn(1,1).equalsPawn("B")&& !state.getPawn(0,1).equalsPawn("B"))
			valutazioneMosseIntelligenti+=0.125;

		//quadrante sx in basso: destra
		if(state.getPawn(7,4).equalsPawn("O") && state.getPawn(6,0).equalsPawn("W") && !state.getPawn(7,3).equalsPawn("B") && !state.getPawn(7,2).equalsPawn("B") && !state.getPawn(7,1).equalsPawn("B")&& !state.getPawn(7,0).equalsPawn("B"))
			valutazioneMosseIntelligenti+=0.125;
		//quadrante sx in basso: sinistra 
		if(state.getPawn(4,1).equalsPawn("O") && state.getPawn(8,2).equalsPawn("W") && !state.getPawn(5,1).equalsPawn("B") && !state.getPawn(6,1).equalsPawn("B") && !state.getPawn(7,1).equalsPawn("B")&& !state.getPawn(8,1).equalsPawn("B"))
			valutazioneMosseIntelligenti+=0.125;
		
		return valutazioneMosseIntelligenti;
		
	}
	
	
	/* S.Cancello and A.Fuschino
	 *  valutiamo nei quattro quadranti il migliore in cui sostare il re protrggendolo con ua pedina adiacente
	 */

	public double valQuadranti(State state) {	
		return valQuadranteAltoSX(state)+ valQuadranteAltoDX(state)+valQuadranteBassoDX(state)+valQuadranteBassoSX(state);
	}


	public double valQuadranteAltoSX(State state) {

		boolean pedinaNeraSuRiga=false;
		boolean pedinaNeraSuColonna=false;
		double valutazione=0;

		for(int i=0; i<=4;i++) {
			if(state.getPawn(1, i).equals("B"))
				pedinaNeraSuRiga=true;
			if(state.getPawn(i, 1).equals("B"))
				pedinaNeraSuColonna=true;
		}

		if(state.getPawn(3, 4).equals("K"))
			valutazione+= 0.4;
		if(state.getPawn(3, 5).equals("W")&&pedinaNeraSuRiga&&pedinaNeraSuColonna)
			valutazione+=0.6;

		return valutazione; 

	}


	public double valQuadranteAltoDX(State state) {

		boolean pedinaNeraSuRiga=false;
		boolean pedinaNeraSuColonna=false;
		double valutazione=0;

		for(int i=0; i<=4;i++) {
			if(state.getPawn(1,4+i).equals("B"))
				pedinaNeraSuRiga=true;
			if(state.getPawn(i, 7).equals("B"))
				pedinaNeraSuColonna=true;
		}

		if(state.getPawn(3, 4).equals("K"))
			valutazione+= 0.4;
		if(state.getPawn(3, 3).equals("W")&&pedinaNeraSuRiga&&pedinaNeraSuColonna)
			valutazione+=0.6;

		return valutazione; 

	}


	public double valQuadranteBassoDX(State state) {

		boolean pedinaNeraSuRiga=false;
		boolean pedinaNeraSuColonna=false;
		double valutazione=0;

		for(int i=0; i<=4;i++) {
			if(state.getPawn(7,4+i).equals("B"))
				pedinaNeraSuRiga=true;
			if(state.getPawn(i+4, 7).equals("B"))
				pedinaNeraSuColonna=true;
		}

		if(state.getPawn(5, 4).equals("K"))
			valutazione+= 0.4;
		if(state.getPawn(5, 3).equals("W")&&pedinaNeraSuRiga&&pedinaNeraSuColonna)
			valutazione+=0.6;

		return valutazione; 

	}


	public double valQuadranteBassoSX(State state) {

		boolean pedinaNeraSuRiga=false;
		boolean pedinaNeraSuColonna=false;
		double valutazione=0;

		for(int i=0; i<=4;i++) {
			if(state.getPawn(7,i).equals("B"))
				pedinaNeraSuRiga=true;
			if(state.getPawn(i+4, 1).equals("B"))
				pedinaNeraSuColonna=true;
		}

		if(state.getPawn(5, 4).equals("K"))
			valutazione+= 0.4;
		if(state.getPawn(5, 5).equals("W")&&pedinaNeraSuRiga&&pedinaNeraSuColonna)
			valutazione+=0.6;

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
					valutazioneAssettoTorre=5;
				//configurazione torre
				if((pedinaSinistra && pedinaSotto && pedinaSottoSinistra)||(pedinaSinistra && pedinaSopra&& pedinaSopraSinistra)||
						(pedinaDestra&&pedinaSotto&&pedinaSottoDestra)||(pedinaDestra&&pedinaSopra&&pedinaSopraDestra))
					valutazioneAssettoTorre=10;
			}	
		}
		return valutazioneAssettoTorre;
	}
	
}
