package pt.iul.ista.poo.doors;

import pt.iul.ista.poo.gui.ImageTile;

public class DoorOpen extends Door implements ImageTile {

	public DoorOpen(int nporta, String room, int pdestino) {
		super(nporta, room, pdestino);
		setNome("DoorOpen");
	}
}