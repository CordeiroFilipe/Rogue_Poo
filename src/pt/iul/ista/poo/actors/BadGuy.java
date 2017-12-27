package pt.iul.ista.poo.actors;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class BadGuy extends Enemy implements ImageTile{

	public BadGuy(Position p, int vida, int ataque) {
		super(p, vida, ataque);
		setName("BadGuy");
	}
}