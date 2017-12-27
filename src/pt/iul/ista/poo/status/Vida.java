package pt.iul.ista.poo.status;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Vida implements ImageTile{
	
	private Position p;
	
	public Vida(Position p){
		this.p = p;
	}

	@Override
	public String getName() {
		return "Vida";
	}

	@Override
	public Position getPosition() {
		return p;
	}
}