package pt.iul.ista.poo.actors;

import java.util.ArrayList;
import java.util.Random;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Direction;
import pt.iul.ista.poo.rogue.utils.Position;
import pt.iul.ista.poo.rogue.utils.Vector2D;

public class Bat extends Enemy implements ImageTile{

	public Bat(Position p, int vida, int ataque) {
		super(p, vida, ataque);
		setName("Bat");
	}
	
	@Override
	public void move(int i, ArrayList<ImageTile> tiles){
		Random r = new Random();
		Direction D = getD();
		Position p = getPosition();
		int x = p.getX();
		int y = p.getY();
		Vector2D v = new Vector2D(x, y);
		int m = r.nextInt(i);
		if(isHeroClose(tiles, Direction.UP)){
			m = 0;
		}
		if(isHeroClose(tiles, Direction.DOWN)){
			m = 1;
		}
		if(isHeroClose(tiles, Direction.LEFT)){
			m = 2;
		}
		if(isHeroClose(tiles, Direction.RIGHT)){
			m = 3;
		}
		if(m == 0){
				D = Direction.UP;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para cima " + getPosition());
		}
		if(m == 1){
				D = Direction.DOWN;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para baixo " + getPosition());
		}
		if(m == 2){
				D = Direction.LEFT;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para esquerda " + getPosition());
		}
		if(m == 3){
				D = Direction.RIGHT;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para direita " + getPosition());
		}
		moveLimits(x, y);
	}
}