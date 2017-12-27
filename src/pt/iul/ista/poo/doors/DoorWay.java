package pt.iul.ista.poo.doors;

import pt.iul.ista.poo.gui.ImageTile;

public class DoorWay extends Door implements ImageTile{

	public DoorWay(int nporta, String room, int pdestino) {
		super(nporta, room, pdestino);
		setNome("DoorWay");
	}
}