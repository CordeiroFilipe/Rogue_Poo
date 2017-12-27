package pt.iul.ista.poo.actors;

import java.util.*;

import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.rogue.utils.*;

public abstract class Actor implements ImageTile {

	private String name;
	private Position position;
	private int ataque;
	private int ataqueIni;
	private int vida;
	private int vidainit;

	public Actor(Position position, int ataque, int vida){
		this.position = position;
		this.ataque = ataque;
		this.vida = vida;
		vidainit = vida;
		ataqueIni = ataque;
	}
	
	public abstract void move(int m, ArrayList<ImageTile> tiles);
	
	public boolean checkWall(List<ImageTile> tiles, Direction d){
		for(int i = 0; i < tiles.size(); i++){
			if(tiles.get(i).getName().equals("Wall") || tiles.get(i).getName().equals("DoorClosed")){
				if(tiles.get(i).getPosition().equals(position.plus(d.asVector()))){
					System.out.println(name + ": Não posso passar uma " + tiles.get(i).getName() + "!");
					return true;
				}
			}
		}
		return false;
	}
	
	public void moveLimits(int x, int y){
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x > 9)
			x = 9;
		if (y > 9)
			y = 9;
		this.position = new Position(x,y);
	}
	
	public void minusVida(int vida) {
		this.vida -= vida;
		if(this.vida > 0){
			System.out.println(name + ": Fui atacado! Tenho " + this.vida + " de vida restante!");
		}
		else{
			System.out.println(name + ": Morri...");
		}
	}
	
	public void plusAtaque(int ataque) {
		this.ataque += ataque;
		System.out.println(name + ": Tenho " + this.ataque + " de ataque!");
	}
	
	public void minusAtaque(int ataque) {
		this.ataque -= ataque;
		System.out.println(name + ": Tenho " + this.ataque + " de ataque!");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
		System.out.println(name + ": tenho " + vida + " de vida!");
	}
	
	public void resetVida() {
		this.vida = vidainit;
	}
	
	public int getAtaque() {
		return ataque;
	}
	
	public void resetAtaque() {
		ataque = ataqueIni;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Position getPosition() {
		return position;
	}
}