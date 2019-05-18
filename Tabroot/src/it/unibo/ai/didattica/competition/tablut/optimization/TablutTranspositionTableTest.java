package it.unibo.ai.didattica.competition.tablut.optimization;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import it.unibo.ai.didattica.competition.tablut.domain.Action;
import it.unibo.ai.didattica.competition.tablut.domain.State;
import it.unibo.ai.didattica.competition.tablut.domain.State.Turn;
import it.unibo.ai.didattica.competition.tablut.optimization.ValuedAction;
import it.unibo.ai.didattica.competition.tablut.domain.StateTablut;

class TablutTranspositionTableTest {
	
	TablutTranspositionTable tableToTest = new TablutTranspositionTable();
	
	State state1=new StateTablut();
	State state2=new StateTablut();
	State state3=new StateTablut();
	
	@Test
	void testGetPutAction() {
		Action action1=null;
		Action action2=null;
		Action action3=null;
		try {
			action1=new Action("e3", "a3", Turn.WHITE);
			action2=new Action("e2", "g2", Turn.BLACK);
			action3=new Action("e2", "c2", Turn.BLACK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ValuedAction a1= new ValuedAction(action1);
		ValuedAction a2= new ValuedAction(action2);
		ValuedAction a3= new ValuedAction(action3);
		
		state2.removePawn(3,0);
		
		tableToTest.putAction(state1, a1, 3);
		assertEquals(a1,tableToTest.getAction(state1));
		
		tableToTest.putAction(state2, a2, 4);
		assertEquals(a2,tableToTest.getAction(state2));
		
		tableToTest.putAction(state2, a3, 5);
		assertEquals(a3,tableToTest.getAction(state2));
		
		tableToTest.putAction(state2, a1, 4);
		assertEquals(a3,tableToTest.getAction(state2));
		
	}
/*
	@Test
	public void givenPassword_whenHashing_thenVerifying() 
	  throws NoSuchAlgorithmException {	         
	    MessageDigest md = MessageDigest.getInstance("MD5");
	   
	    //Calcolo l'hash dello stato1
	    md.update(state1.boardString().getBytes());
	    byte[] digest1 = md.digest();
	    String myHash1 = DatatypeConverter.printHexBinary(digest1).toUpperCase();
	    
	  //Calcolo l'hash dello stato2 equivalente allo stato1
	    md.update(state2.boardString().getBytes());
	    byte[] digest2 = md.digest();
	    String myHash2 = DatatypeConverter.printHexBinary(digest2).toUpperCase();
	    
	    assertEquals(myHash1, myHash2);
	    
	    state2.removePawn(3,0);
	  //Ricalcolo l'hash dello stato2 ora diverso dallo stato1
	    md.update(state2.boardString().getBytes());
	    digest2 = md.digest();
	    myHash2 = DatatypeConverter.printHexBinary(digest2).toUpperCase();
	    
	    assertNotEquals(myHash2, myHash1);
	}*/

	@Test
	void testTTHashCode() {	         
		//Calcolo l'hash dello stato1
		long myHash1=state1.ttHashCode(); 

		//Calcolo l'hash dello stato2 equivalente allo stato1
		long myHash2 = state2.ttHashCode();

		assertEquals(myHash1, myHash2);

		state2.removePawn(3,0);
		//Ricalcolo l'hash dello stato2 ora diverso dallo stato1
		myHash2 = state2.ttHashCode();

		assertNotEquals(myHash2, myHash1);
	}

}
