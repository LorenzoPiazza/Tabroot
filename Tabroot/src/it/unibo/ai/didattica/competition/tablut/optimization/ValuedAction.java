package it.unibo.ai.didattica.competition.tablut.optimization;

import it.unibo.ai.didattica.competition.tablut.domain.Action;

//Classe di utility. Rappresenta una Action con rispettivo valore euristico
public class ValuedAction{
		private Action action;
		private double value;
		private int flag;		//0:Exact value, 1:Alpha value, 2:Beta value
			
		public ValuedAction() {
		}
		
		public ValuedAction(Action a) {
			this.action=a;
		}

		public Action getAction() {
			return action;
		}

		public void setAction(Action action) {
			this.action = action;
		}
		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}
	}