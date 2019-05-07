package it.unibo.ai.didattica.competition.tablut.client.lori;

import aima.core.search.adversarial.Game;
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch;
import it.unibo.ai.didattica.competition.tablut.domain.*;
import it.unibo.ai.didattica.competition.tablut.domain.State.Turn;

public class MyIterativeDeepeningAlphaBetaSearch extends IterativeDeepeningAlphaBetaSearch<State, Action, Turn> {

	public MyIterativeDeepeningAlphaBetaSearch(Game<State, Action, Turn> game, double utilMin, double utilMax,
			int time) {
		super(game, utilMin, utilMax, time);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected double eval(State state, Turn turn) {
		//double result= super.eval(state, turn);
		return this.game.getUtility(state, turn);	
	}
}
