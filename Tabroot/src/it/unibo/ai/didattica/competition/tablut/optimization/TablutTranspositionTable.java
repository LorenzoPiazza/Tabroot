package it.unibo.ai.didattica.competition.tablut.optimization;

import java.util.HashMap;

import it.unibo.ai.didattica.competition.tablut.domain.State;
import it.unibo.ai.didattica.competition.tablut.optimization.ValuedAction;;

/**
 * @author L.Piazza
 *
 */
public class TablutTranspositionTable implements ITranspositionTable {

	public static final int DEFAULT_SIZE = 4000000; //MAX SIZE is 5600000
	
	private HashMap<Long, TTEntry> transpositionTable;
	
	public TablutTranspositionTable(int size) {
		transpositionTable = new HashMap<>(size);
	}
	
	public TablutTranspositionTable() {
		this(DEFAULT_SIZE);
	}
	@Override
	public ValuedAction getAction(State state) {
		TTEntry entry = transpositionTable.get(state.ttHashCode());
		//Se lo stato cercato non è in tabella ritorno null come azione
		if (entry == null)
			return null;
		//altrimenti ritorno la bestAction della entry trovata
		return entry.getBestAction();
	}

	@Override
	public void putAction(State state, ValuedAction actionToPut, int depth) {
		if (actionToPut == null)
			throw new IllegalArgumentException("action\n" + state);
			
		TTEntry entry = transpositionTable.get(state.ttHashCode());
		if (entry == null) {
			entry = new TTEntry();
			entry.setDepth(depth);
			entry.setBestAction(actionToPut);
			entry.setState(state);		
			transpositionTable.put(state.ttHashCode(), entry);
		} else {
			//Se la entry trovata in tabella è di un livello di profondità più superficiale aggiorno la best move con quella corrente che deriva da una esplorazione più profonda.
			if (entry.getDepth() <= depth) {
				entry.setBestAction(actionToPut);
				entry.setDepth(depth);
				entry.setState(state);
			}
		}
	}

	@Override
	public void clear() {
		transpositionTable=new HashMap<Long, TablutTranspositionTable.TTEntry>(DEFAULT_SIZE);

	}
	
	
	//Classe di utility. Rappresenta la entry della Transposition Table
	class TTEntry {
		private ValuedAction bestAction;
		private int depth;
		private State state;
		
		
		TTEntry() {
		}

		public ValuedAction getBestAction() {
			return bestAction;
		}

		public void setBestAction(ValuedAction bestAction) {
			this.bestAction = bestAction;
		}

		public int getDepth() {
			return depth;
		}

		public void setDepth(int depth) {
			this.depth = depth;
		}

		public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}

	}

	
}
