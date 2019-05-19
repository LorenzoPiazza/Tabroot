/**
 * 
 */
package it.unibo.ai.didattica.competition.tablut.client.search;

import it.unibo.ai.didattica.competition.tablut.domain.Action;
import it.unibo.ai.didattica.competition.tablut.domain.State;

/**
 * @author L.Piazza
 *
 */
public interface IOpening {
	public Action openingMove (State currentState);
}
