package pt.iul.ista.poo.status;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Black implements ImageTile{
	
	private Position p;
	
	public Black(int x){
		p = new Position(x,0);
	}

	public Black(Position p) {
		this.p = p;
	}

	@Override
	public String getName() {
		return "Black";
	}
	@Override
	public Position getPosition() {
		return p;
	}
}