package pt.iul.ista.poo.actors;

import java.awt.event.*;
import java.util.*;

import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.items.*;
import pt.iul.ista.poo.rogue.utils.*;
import pt.iul.ista.poo.room.Room;

public class Hero extends Actor implements ImageTile {
	
	private Direction D;
	private ArrayList<Item> items = new ArrayList<Item>();
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	
	/**
	 * @author Filipe Cordeiro
	 * 
	 * 			Hero is the main character of the game and extends the Actor class
	 *          since it has equal movement limitations like enemies who also extends
	 *          the Actor class.
	 *          
	 *          
	 * Constructor to initialize Hero.
	 * 
	 * 			It also gives a direction so that it knows where to fire the fireballs since 
	 * 			they fire into the last direction hero moved.
	 * 
	 * @param  position  	Sets the initial position
	 * @param  ataque  		Sets the initial attack points
	 * @param  vida  		Sets the initial health
	 * 		
	 */
	
	public Hero(Position position, int ataque, int vida) {
		super(position, ataque, vida);
		setName("Hero");
		D = Direction.UP;
	}
	
	/**
	 * Move hero
	 * 
	 * 			Changes hero position to a new one in the direction chosen by the arrow-keys.
	 * 
	 * @param  m  		KeyEvent from Arrow-key pressed.
	 * @param  tiles 	ArrayList that contains all the components from the actual room.
	 */
	
	public void move(int m, ArrayList<ImageTile> tiles) {
		Position position = getPosition();
		Vector2D v = new Vector2D(position.getX(), position.getY());
		int x = position.getX();
		int y = position.getY();
		if(m == KeyEvent.VK_UP && !checkWall(tiles, Direction.UP)){
			D = Direction.UP;
			v = v.plus(D.asVector());
			System.out.println("Hero: Andei para cima " + v);
			x = v.getX();
			y = v.getY();
			position = new Position(x,y);
		}
		if(m == KeyEvent.VK_DOWN && !checkWall(tiles, Direction.DOWN)){
			D = Direction.DOWN;
			v = v.plus(D.asVector());
			System.out.println("Hero: Andei para baixo: " + v);
			x = v.getX();
			y = v.getY();
			position = new Position(x,y);
		}
		if(m == KeyEvent.VK_LEFT && !checkWall(tiles, Direction.LEFT)){
			D = Direction.LEFT;
			v = v.plus(D.asVector());
			System.out.println("Hero: Andei para a esquerda: " + v);
			x = v.getX();
			y = v.getY();
			position = new Position(x,y);
		}
		if(m == KeyEvent.VK_RIGHT && !checkWall(tiles, Direction.RIGHT)){
			D = Direction.RIGHT;
			v = v.plus(D.asVector());
			System.out.println("Hero: Andei para a direita: " + v);
			x = v.getX();
			y = v.getY();
			position = new Position(x,y);
		}	
		moveLimits(x, y);
	}
	
	/**
	 * 
	 * Attack Enemies
	 * 
	 * 			If the enemie's position equals hero's position then remove health
	 * 			from that enemy. Also updates the weapons.
	 * 
	 * @param enemies  ArrayList of all the enemies in the actual room.
	 */
	
	public void attack(ArrayList<Enemy> enemies){
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).getPosition().equals(getPosition())){
				System.out.println("Hero: Ataquei o " + enemies.get(i).getName());
				enemies.get(i).minusVida(getAtaque());
				updateWeapons();
			}
		}
	}
	
	/**
	 * Update Weapons
	 * 
	 * 			This will update the limit in each weapon, since they only can be used
	 * 			a number of times until they finally break.
	 */
	
	public void updateWeapons(){
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getName().equals("Sword")){
				((Sword)items.get(i)).minusLimit();
				if(((Sword)items.get(i)).getLimit() == 0){
					gui.removeStatusImage(items.get(i));
					System.out.println("Hero: A espada partiu!");
					minusAtaque(((Sword)items.get(i)).getAtaque());
					items.remove(i);
					break;
				}
			}
			if(items.get(i).getName().equals("Hammer")){
				((Hammer) items.get(i)).minusLimit();
				if(((Hammer)items.get(i)).getLimit() == 0){
					gui.removeStatusImage(items.get(i));
					System.out.println("Hero: O martelo partiu!");
					minusAtaque(((Hammer)items.get(i)).getAtaque());
					items.remove(i);
					break;
				}
			}
		}
	}
	
	/**
	 * 
	 * Grab Objects
	 * 
	 * 			This will check if there are any items to collect in the position
	 * 			that hero is standing. If there are, then it collects then, deleting
	 * 			from the map, and adding it to the status bar.
	 * 
	 * @param r			Actual room
	 * @param status	ArrayList that contains all the status bar components
	 */
	
	public void apanhaObj(Room r, ArrayList<ImageTile> status){
		for(int i = 0; i < r.getItems().size(); i++){
			if(r.getItems().get(i).getPosition().equals(getPosition())){
				if(items.size() < 3){
					if(r.getItems().get(i).getName().equals("Sword")){
						plusAtaque(((Sword) r.getItems().get(i)).getAtaque());
						r.getItems().get(i).setP(new Position(7,0));
					}
					if(r.getItems().get(i).getName().equals("Hammer")){
						plusAtaque(((Hammer) r.getItems().get(i)).getAtaque());
						r.getItems().get(i).setP(new Position(8,0));
					}
					if(r.getItems().get(i).getName().equals("key")){
						for(int j = 0; j < r.getPortas().size(); j++){
							Key k = r.ActualKey(getPosition());
							if(r.getPortas().get(j).getNporta() == k.getNporta()){
								r.getPortas().get(j).setNome("DoorOpen");
							}
						}
						r.getItems().get(i).setP(new Position(9,0));
					}
					items.add(r.getItems().get(i));
					status.add(r.getItems().get(i));
					gui.addStatusImage(r.getItems().get(i));
					gui.removeImage(r.getItems().get(i));
					r.getTiles().remove(r.getItems().get(i));
					System.out.println("Hero: Apanhei um " + r.getItems().get(i).getName());
				}
				else{
					System.out.println("Hero: Não posso apanhar mais nada!");
				}
			}
		}
	}
	
	/**
	 * 
	 * getItems
	 * 
	 * @return ArrayList with the Items Hero has in his status.
	 */
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * getD
	 * 
	 * @return Last direction hero moved to.
	 */
	
	public Direction getD() {
		return D;
	}
}