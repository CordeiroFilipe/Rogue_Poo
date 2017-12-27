package pt.iul.ista.poo.room;

import java.io.*;
import java.util.*;

import pt.iul.ista.poo.actors.*;
import pt.iul.ista.poo.doors.*;
import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.items.*;
import pt.iul.ista.poo.rogue.utils.*;
import pt.iul.ista.poo.status.*;

public class Room {
	
	private String nome;
	private ArrayList<ImageTile> tiles = new ArrayList<ImageTile>();
	private ArrayList<Door> portas = new ArrayList<Door>();
	private ArrayList<Key> keys = new ArrayList<Key>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Room(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void read() throws FileNotFoundException{
		Scanner sc = new Scanner(new File(nome));
		int i = 0;
		int nporta = 0;
		int nkey = 0;
		Random r = new Random();
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				int g = r.nextInt(9);
				if(g == 1){
					tiles.add(new Grass(new Position(x,y)));
				}
				else{
					tiles.add(new Floor(new Position(x,y)));
				}
			}
		}
		while(sc.hasNextLine()){
			String p = sc.nextLine();
			if(p.substring(0,1).equals("#")){
				if(p.length() > 2){
					if(!p.substring(2,3).equals("k")){
						if(p.length() > 18){
							Key k = Key.newKey(p);
							keys.add(k);
						}
						Door d = Door.newDoor(p);
						portas.add(d);
					}
				}
				i--;
			}
			else{
				for(int j = 0; j < p.length(); j++){
					if(p.substring(j,j+1).equals(String.valueOf(nporta))){
						portas.get(nporta).setP(new Position(j,i));
						tiles.add(portas.get(nporta));
						nporta++;
					}
					if(p.substring(j,j+1).equals("W")){
						tiles.add(new Wall(new Position(j,i)));
					}
					if(p.substring(j,j+1).equals("V")){
						tiles.add(new Vida(new Position(j,i)));
					}
					if(p.substring(j,j+1).equals("F")){
						tiles.add(new Fire(new Position(j,i)));
					}
					if(p.substring(j,j+1).equals("s")){
						Sword s = new Sword(new Position(j,i), 4, 5);
						tiles.add(s);
						items.add(s);
					}
					if(p.substring(j,j+1).equals("h")){
						Hammer h = new Hammer(new Position(j,i), 4, 5);
						tiles.add(h);
						items.add(h);
					}
					if(p.substring(j,j+1).equals("S")){
						enemies.add(new Skeleton(new Position(j,i), 1, 6));
					}
					if(p.substring(j,j+1).equals("B")){
						enemies.add(new Bat(new Position(j,i), 1, 8));
					}
					if(p.substring(j,j+1).equals("G")){
						enemies.add(new BadGuy(new Position(j,i), 2, 20));
					}
					if(p.substring(j,j+1).equals("T")){
						enemies.add(new Thief(new Position(j,i), 2, 15));
					}
					if(p.substring(j,j+1).equals("k")){
						keys.get(nkey).setP(new Position(j,i));
						tiles.add(keys.get(nkey));
						items.add(keys.get(nkey));
						nkey++;
					}
				}
			}
			i++;
		}
		for(int e = 0; e < enemies.size(); e++){
			tiles.add(enemies.get(e));
		}
		sc.close();
	}
	
	public Door ActualDoor(Position p){
		for(int i = 0; i < portas.size(); i++){
			if(portas.get(i).getPosition().equals(p)){
				return portas.get(i);
			}
		}
		return null;
	}
	
	public Key ActualKey(Position p){
		for(int i = 0; i < keys.size(); i++){
			if(keys.get(i).getPosition().equals(p)){
				return keys.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public ArrayList<Key> getKeys() {
		return keys;
	}
	
	public ArrayList<Door> getPortas() {
		return portas;
	}
	
	public ArrayList<ImageTile> getTiles() {
		return tiles;
	}

	public void moveEnemies() {
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).move(4, tiles);
		}
	}
	
	public void clear(){
		tiles.clear();
		enemies.clear();
		keys.clear();
		portas.clear();
		items.clear();
	}
}