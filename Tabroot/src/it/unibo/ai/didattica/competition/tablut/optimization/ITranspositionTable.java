package it.unibo.ai.didattica.competition.tablut.optimization;

import it.unibo.ai.didattica.competition.tablut.domain.State;
import it.unibo.ai.didattica.competition.tablut.optimization.ValuedAction;

public interface ITranspositionTable {
		
	public ValuedAction getAction(State state);
	public void putAction(State state, ValuedAction action, int depth);
	public void clear();
}

