package pt.iul.ista.poo.doors;

import pt.iul.ista.poo.gui.ImageTile;

public class DoorClosed extends Door implements ImageTile {

	public DoorClosed(int nporta, String room, int pdestino) {
		super(nporta, room, pdestino);
		setNome("DoorClosed");
	}
}