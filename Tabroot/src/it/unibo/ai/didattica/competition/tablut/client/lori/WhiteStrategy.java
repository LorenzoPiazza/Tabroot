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
				pedineInAngolo+=0.07;
				continue;
			}				
			if(controlloPedine[0]==8 && controlloPedine[1]==8) {
				pedineInAngolo+=0.07;
				continue;
			}				
			if(controlloPedine[0]==8 && controlloPedine[1]==0 ) {
				pedineInAngolo+=0.07;
				continue;
			}				
			if(controlloPedine[0]==0 && controlloPedine[1]==8) {
				pedineInAngolo+=0.07;
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
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}				
			if(controlloPedine[0]==2 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==1 ){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==2){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}

			//in alto a destra
			if(controlloPedine[0]==1 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==2 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==0 && controlloPedine[1]==7 ){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==2 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}

			//in basso a sinistra 
			if(controlloPedine[0]==6 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==7 && controlloPedine[1]==0){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==1 ){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==2){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}

			//in basso a destra 
			if(controlloPedine[0]==7 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==6 && controlloPedine[1]==8){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==7 ){
				valutazionePedinaBordiAngoli+=0.01;
				continue;
			}
			if(controlloPedine[0]==8 && controlloPedine[1]==6){
				valutazionePedinaBordiAngoli+=0.01;
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
