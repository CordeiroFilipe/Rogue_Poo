package pt.iul.ista.poo.room;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Floor implements ImageTile {
	
	private Position p;
	
	public Floor(Position p){
		this.p = p;
	}

	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public Position getPosition() {
		return p;
	}
}