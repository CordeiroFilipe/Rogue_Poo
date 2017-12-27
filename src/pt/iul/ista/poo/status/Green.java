package pt.iul.ista.poo.status;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Green implements ImageTile{

	private int x = 0;
	public Green(int x){
		this.x=x;
	}
	@Override
	public String getName() {
		return "Green";
	}

	@Override
	public Position getPosition() {
		return new Position(x,0);
	}
}