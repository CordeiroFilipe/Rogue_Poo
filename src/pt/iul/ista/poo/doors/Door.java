package pt.iul.ista.poo.doors;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;

public class Door implements ImageTile{
	
	private String nome;
	private Position p;
	private String room;
	private int nporta;
	private int pdestino;
	
	public Door(int nporta, String room, int pdestino){
		this.room = "rooms\\" + room;
		this.nporta = nporta;
		this.pdestino = pdestino;
	}
	
	public void setP(Position p) {
		this.p = p;
	}

	@Override
	public String getName() {
		return nome;
	}

	@Override
	public Position getPosition() {
		return p;
	}

	public static Door newDoor(String n) {
		String[] tokens = n.split(" ");
		Door d = null;
		if(tokens.length < 6){
			if(tokens[2].equals("D")){
				d = new DoorOpen(Integer.valueOf(tokens[1]) , tokens[3], Integer.valueOf(tokens[4]));
			}
			if(tokens[2].equals("E")){
				d = new DoorWay(Integer.valueOf(tokens[1]), tokens[3], Integer.valueOf(tokens[4]));
			}
		}
		else{
			d = new DoorClosed(Integer.valueOf(tokens[1]), tokens[3], Integer.valueOf(tokens[4]));
		}
		return d;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRoom() {
		return room;
	}
	
	public int getNporta() {
		return nporta;
	}
	
	public int getPdestino() {
		return pdestino;
	}
}