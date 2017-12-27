package pt.iul.ista.poo.jogo;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Win implements ImageTile {
	
	private Position p;
	
	public Win(Position p){
		this.p = p;
	}

	@Override
	public String getName() {
		return "Win";
	}

	@Override
	public Position getPosition() {
		return p;
	}
}