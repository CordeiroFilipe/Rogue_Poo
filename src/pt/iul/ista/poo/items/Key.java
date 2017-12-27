package pt.iul.ista.poo.items;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Key extends Item implements ImageTile{
	
	private int nporta;

	public Key(Position p,int nporta) {
		super(p);
		setName("key");
		this.nporta = nporta;
	}
	
	public static Key newKey(String p){
		Key k = null;
		String[] tokens = p.split(" ");
		k = new Key(new Position(0,0),Integer.valueOf(tokens[1]));
		return k;
	}
	
	public int getNporta() {
		return nporta;
	}
}