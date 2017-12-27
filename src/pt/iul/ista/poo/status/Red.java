package pt.iul.ista.poo.status;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Red implements ImageTile{

	private Position p;
	
	public Red(int x){
		p = new Position(x,0);
	}

	public Red(Position p){
		this.p = p;
	}
	
	@Override
	public String getName() {
		return "Red";
	}

	@Override
	public Position getPosition() {
		return p;
	}
}