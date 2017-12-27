package pt.iul.ista.poo.actors;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Direction;
import pt.iul.ista.poo.rogue.utils.Position;

public class Thief  extends Enemy implements ImageTile{

	public Thief(Position p, int vida, int ataque) {
		super(p, vida, ataque);
		setName("Thief");
	}
	
	@Override
	public void attack(Hero h, Direction d){
		if(h.getPosition().equals(getPosition().plus(d.asVector()))){
			System.out.println("Bat: Ataquei o heroi!");
			h.minusVida(getAtaque());
		}
	}
}