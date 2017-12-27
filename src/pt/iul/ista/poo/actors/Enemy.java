package pt.iul.ista.poo.actors;

import java.util.*;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Direction;
import pt.iul.ista.poo.rogue.utils.Position;
import pt.iul.ista.poo.rogue.utils.Vector2D;

public class Enemy extends Actor implements ImageTile{
	
	private Position p;
	private Direction D;
	
	public Enemy(Position position, int ataque, int vida) {
		super(position, ataque, vida);
		p = getPosition();
		D = Direction.UP;
	}
	
	public void move(int i, ArrayList<ImageTile> tiles){
		Random r = new Random();
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
			if(!checkWall(tiles, Direction.UP)){
				D = Direction.UP;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para cima " + getPosition());
			}
		}
		if(m == 1){
			if(!checkWall(tiles, Direction.DOWN)){
				D = Direction.DOWN;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para baixo " + getPosition());
			}
		}
		if(m == 2){
			if(!checkWall(tiles, Direction.LEFT)){
				D = Direction.LEFT;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para esquerda " + getPosition());
			}
		}
		if(m == 3){
			if(!checkWall(tiles, Direction.RIGHT)){
				D = Direction.RIGHT;
				v = v.plus(D.asVector());
				x = v.getX();
				y = v.getY();
				p = new Position(x, y);
				System.out.println(getName() + ": Mexi para direita " + getPosition());
			}
		}
		moveLimits(x, y);
	}
	
	public void attack(Hero h, Direction d){
		if(h.getPosition().equals(getPosition())){
			System.out.println(getName() + ": Ataquei o heroi!");
			h.minusVida(getAtaque());
		}
	}
	
	public boolean isHeroClose(ArrayList<ImageTile> tiles, Direction d){
		for(int i = 0; i < tiles.size(); i++){
			if(tiles.get(i).getName().equals("Hero")){
				if(tiles.get(i).getPosition().equals(p.plus(d.asVector().plus(d.asVector())))){
					System.out.println(getName() + ": Hero por perto!");
					return true;
				}
			}
		}
		return false;
	}
	
	public Direction getD() {
		return D;
	}
	
	public void setD(Direction d) {
		D = d;
	}
}