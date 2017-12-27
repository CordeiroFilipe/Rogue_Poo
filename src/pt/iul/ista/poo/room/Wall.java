package pt.iul.ista.poo.room;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Wall implements ImageTile{
	
	private Position p;
	
	public Wall(Position p){
		this.p = p;
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public Position getPosition() {
		return p;
	}
}