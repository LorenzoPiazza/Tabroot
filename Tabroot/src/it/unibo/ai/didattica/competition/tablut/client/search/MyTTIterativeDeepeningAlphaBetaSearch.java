package it.unibo.ai.didattica.competition.tablut.client.search;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.adversarial.Game;
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch;
import aima.core.search.framework.Metrics;
import it.unibo.ai.didattica.competition.tablut.domain.*;
import it.unibo.ai.didattica.competition.tablut.domain.State.Turn;
import it.unibo.ai.didattica.competition.tablut.optimization.TablutTranspositionTable;
import it.unibo.ai.didattica.competition.tablut.optimization.ValuedAction;


public class MyTTIterativeDeepeningAlphaBetaSearch extends IterativeDeepeningAlphaBetaSearch<State, Action, Turn> {

	private boolean heuristicEvaluationUsed; // indicates that non-terminal
    // nodes
    // have been evaluated.
	private Timer timer1;
    private boolean logEnabled;
    private Metrics metrics = new Metrics();
    private TablutTranspositionTable transpositionTable;
    
	public MyTTIterativeDeepeningAlphaBetaSearch(Game<State, Action, Turn> game, double utilMin, double utilMax,
			int time, TablutTranspositionTable transpositionTable) {
		super(game, utilMin, utilMax, time);
        this.transpositionTable=transpositionTable;
        this.timer1=new Timer (time);
	}
	
	/**
     * Override makeDecision method to improve with Transposition Table optimization.
     */
    @Override
    public Action makeDecision(State state) {
        metrics = new Metrics();
        StringBuffer logText = null;
        Turn player = game.getPlayer(state);
        List<Action> results = orderActions(state, game.getActions(state), player, 0);
        timer1.start();
        currDepthLimit = 0;
        do {
            incrementDepthLimit();
            if (logEnabled)
                logText = new StringBuffer("depth " + currDepthLimit + ": ");
            heuristicEvaluationUsed = false;
            ActionStore<Action> newResults = new ActionStore<>();
            for (Action action : results) {
                double value = minValue(game.getResult(state, action), player, Double.NEGATIVE_INFINITY,
                        Double.POSITIVE_INFINITY, 1, action);
                if (timer1.timeOutOccurred())
                    break; // exit from action loop
                newResults.add(action, value);
                if (logEnabled)
                    logText.append(action).append("->").append(value).append(" ");
            }
            if (logEnabled)
                System.out.println(logText);
            if (newResults.size() > 0) {
                results = newResults.actions;
                if (!timer1.timeOutOccurred()) {
                    if (hasSafeWinner(newResults.utilValues.get(0)))
                        break; // exit from iterative deepening loop
                    else if (newResults.size() > 1
                            && isSignificantlyBetter(newResults.utilValues.get(0), newResults.utilValues.get(1)))
                        break; // exit from iterative deepening loop
                }
            }
        } while (!timer1.timeOutOccurred() && heuristicEvaluationUsed);
     
        return results.get(0);
    }

    // returns an utility value
    public double maxValue(State state, Turn player, double alpha, double beta, int depth, Action a) {
        updateMetrics(depth);        	
        if (game.isTerminal(state) || depth >= currDepthLimit || timer1.timeOutOccurred()) {
        	ValuedAction retrievedAction=transpositionTable.getAction(state);
        	if(retrievedAction != null)
        		return retrievedAction.getValue();
        	else
        		return eval(state, player);
        } else {
            double value = Double.NEGATIVE_INFINITY;
            for (Action action : orderActions(state, game.getActions(state), player, depth)) {
                value = Math.max(value, minValue(game.getResult(state, action),
                        player, alpha, beta, depth + 1, action));
                if (value >= beta) {
                	ValuedAction toInsert=new ValuedAction(action);
                	toInsert.setValue(value);
                	transpositionTable.putAction(state, toInsert, depth);
                    return value;
                }
                alpha = Math.max(alpha, value);
            }
            ValuedAction toInsert=new ValuedAction(a);
         	toInsert.setValue(value);
         	transpositionTable.putAction(state, toInsert, depth);
            return value;
        }
    }

    // returns an utility value
    public double minValue(State state, Turn player, double alpha, double beta, int depth, Action a) {
        updateMetrics(depth);
        if (game.isTerminal(state) || depth >= currDepthLimit || timer1.timeOutOccurred()) {
        	ValuedAction retrievedAction=transpositionTable.getAction(state);
        	if(retrievedAction != null)
        		return retrievedAction.getValue();
        	else {
        		return eval(state, player);
        	}
        	
        } else {
            double value = Double.POSITIVE_INFINITY;
            for (Action action : orderActions(state, game.getActions(state), player, depth)) {
                value = Math.min(value, maxValue(game.getResult(state, action), //
                        player, alpha, beta, depth + 1, action));
                if (value <= alpha) {
                	ValuedAction toInsert=new ValuedAction(action);
                	toInsert.setValue(value);
                	transpositionTable.putAction(state, toInsert, depth);
                    return value;
                }
                beta = Math.min(beta, value);
            }
            ValuedAction toInsert=new ValuedAction(a);
        	toInsert.setValue(value);
        	transpositionTable.putAction(state, toInsert, depth);
            return value;
        }
    }
    
	
	@Override
	protected double eval(State state, Turn turn) {
		super.eval(state, turn);
		return this.game.getUtility(state, turn);	
	}

    private void updateMetrics(int depth) {
        metrics.incrementInt(METRICS_NODES_EXPANDED);
        metrics.set(METRICS_MAX_DEPTH, Math.max(metrics.getInt(METRICS_MAX_DEPTH), depth));
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    // nested helper classes

    private static class Timer {
        private long duration;
        private long startTime;

        Timer(int maxSeconds) {
            this.duration = 1000 * maxSeconds;
        }

        void start() {
            startTime = System.currentTimeMillis();
        }

        boolean timeOutOccurred() {
            return System.currentTimeMillis() > startTime + duration;
        }
    }

    /**
     * Orders actions by utility.
     */
    private static class ActionStore<A> {
        private List<A> actions = new ArrayList<>();
        private List<Double> utilValues = new ArrayList<>();

        void add(A action, double utilValue) {
            int idx = 0;
            while (idx < actions.size() && utilValue <= utilValues.get(idx))
                idx++;
            actions.add(idx, action);
            utilValues.add(idx, utilValue);
        }

        int size() {
            return actions.size();
        }
    }
}

