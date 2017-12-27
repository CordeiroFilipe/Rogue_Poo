package pt.iul.ista.poo.actors;


import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.rogue.utils.*;

public class Skeleton extends Enemy implements ImageTile{
	
	public Skeleton(Position p, int vida, int ataque) {
		super(p, vida, ataque);
		setName("Skeleton");
	}
}