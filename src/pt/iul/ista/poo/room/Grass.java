package pt.iul.ista.poo.room;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Grass implements ImageTile{
	
	private Position p;
	
	public Grass(Position p){
		this.p = p;
	}

	@Override
	public String getName() {
		return "Grass";
	}

	@Override
	public Position getPosition() {
		return p;
	}
}