package pt.iul.ista.poo.items;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Hammer extends Item implements ImageTile  {
	
	private int ataque;
	private int limit;

	public Hammer(Position p, int ataque, int limit) {
		super(p);
		setName("Hammer");
		this.ataque = ataque;
		this.limit = limit;
	}

	public int getAtaque() {
		return ataque;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void minusLimit() {
		limit -= 1;
	}
}