package pt.iul.ista.poo.items;

import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.rogue.utils.*;

public abstract class Item implements ImageTile{

	private Position p;
	private String name;
	
	public Item(Position p){
		this.p = p;
	}
	
	public void setP(Position p) {
		this.p = p;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Position getPosition() {
		return p;
	}
}