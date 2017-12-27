package pt.iul.ista.poo.rogue.tests;

import org.junit.*;

import pt.iul.ista.poo.actors.*;
import pt.iul.ista.poo.rogue.utils.*;

public class HeroTest {
	
	private Hero h = new Hero(new Position(2,3) , 7, 5);
	
	@Test
	public void testConstructor(){
		Assert.assertEquals(new Position(2,3), h.getPosition());
		Assert.assertEquals(7, h.getAtaque());
		Assert.assertEquals(5, h.getVida());
	}
	
	@After
	public void testMove(){
		
	}
}