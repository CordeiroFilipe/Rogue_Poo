package pt.iul.ista.poo.jogo;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

import pt.iul.ista.poo.actors.*;
import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.rogue.utils.*;
import pt.iul.ista.poo.room.*;
import pt.iul.ista.poo.status.*;

public class Jogo implements Observer{
	
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Status status;
	private GameOver game;
	private Hero h;
	private Position antes;
	private Position atual;
	private Room roomAtual;
	private int npassos;
	private int allEne = 0;
	private int nkills = 0;
	private int nMortes = 0;
	private int limiteM = 10;
	
	public Jogo(String nome) {
		gui.setName(nome);
		gui.addObserver(this);
		game = new GameOver();
		
		status = new Status();
		status.setStatus();
		
		h = new Hero(new Position(4,5), 2, 8);
		atual = h.getPosition();
		System.out.println("---------------------Bem Vindo ao " + nome + "!---------------------");
		System.out.println("Controls:");
		System.out.println("- Mover nas setas!");
		System.out.println("- Teclas 1, 2 ou 3 para largar items!");
		System.out.println("- Barra de Espaço para disparar Fireballs!");
		System.out.println("Objetivos:");
		System.out.println("- Sobreviver (" + limiteM + " tentativas)!");
		System.out.println("- Matar todos os inimigos!");
		System.out.println("---------------------------------Have fun!--------------------------------");
	}
	
	public void start(){
		if(new File("rooms\\").listFiles().length == 8){
			new File("rooms\\room7.txt").delete();
		}
		RandomRoom rr = new RandomRoom();
		rr.generateRoom();
		
		loadFiles();
		roomAtual = rooms.get(0);
		gui.newImages(roomAtual.getTiles());
		gui.newStatusImages(status.getStatus());
		gui.go();
	}
	
	public void loadFiles(){
		File folder = new File("rooms\\");
		for(int i = 0; i < folder.listFiles().length; i++){
			roomAtual = new Room("rooms\\room" + i + ".txt");
			roomAtual.clear();
			rooms.add(roomAtual);
			try {
				roomAtual.read();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			allEne += roomAtual.getEnemies().size();
			roomAtual.getTiles().add(h);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		int keyCode = (Integer) arg;
		gui.update();
		if(game.isAlive()){
			if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT ||
					keyCode == KeyEvent.VK_RIGHT){
				antes = atual;
				h.move(keyCode, roomAtual.getTiles());
				h.attack(roomAtual.getEnemies());
				atual = h.getPosition();
				if(!atual.equals(antes)){
					roomAtual.moveEnemies();
					ataqueEn();
					npassos++;
				}
			}
			status.resetVida(roomAtual, h);
			status.resetFire(roomAtual, h);
			status.fire(keyCode, h, roomAtual);
			delEnemie();
			status.dropItem(keyCode, h, h.getItems());
			h.apanhaObj(roomAtual, status.getStatus());
			mudaRoom();
			game.Win(allEne, npassos, nkills, nMortes);
		}
	}
	
	public void ataqueEn(){
		int vida = h.getVida();
		for(int e = 0; e < roomAtual.getEnemies().size(); e++){
			roomAtual.getEnemies().get(e).attack(h, roomAtual.getEnemies().get(e).getD());
			if(h.getVida() != vida){
				vida = h.getVida();
				status.removeVida(roomAtual.getEnemies().get(e).getAtaque());
				if(h.getVida() <= 0){
					nMortes++;
					if(nMortes == limiteM){
						System.out.println("Excedeste o limite de mortes...");
						h.setName("DeadHero");
						game.animation();
						game.gameOver(npassos, nkills, nMortes);
					}
					else{
						h.setName("DeadHero");
						try {
							Thread.sleep(600);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						h.setName("Hero");
						reset();
					}
				}
			}
		}
	}
	
	public void reset(){
		System.out.println("Reset " + nMortes + "/" + limiteM);
		gui.clearImages();
		gui.clearStatus();
		status.getStatus().clear();
		if(!status.isSaved()){
			allEne = 0;
			rooms.clear();
			loadFiles();
			roomAtual = rooms.get(0);
			h.setPosition(new Position(4,5));
		}
		else{
			roomAtual = status.getSavedR();
			h.setPosition(status.getSavedP());
		}
		h.resetVida();
		h.getItems().clear();
		h.resetAtaque();
		status.setStatus();
		gui.newImages(roomAtual.getTiles());
		gui.newStatusImages(status.getStatus());
	}
	
	public void delEnemie(){
		for(int i = 0; i < roomAtual.getEnemies().size(); i++){
			if(roomAtual.getEnemies().get(i).getVida() <= 0){
				roomAtual.getTiles().remove(roomAtual.getEnemies().get(i));
				gui.removeImage(roomAtual.getEnemies().get(i));
				roomAtual.getEnemies().remove(roomAtual.getEnemies().get(i));
				allEne--;
				nkills++;
			}
		}
	}
	
	public void mudaRoom(){
		int d = 0;
		int q = 0;
		for(int i = 0; i < roomAtual.getTiles().size(); i++){
			if(roomAtual.getTiles().get(i).getName().equals("DoorOpen") || roomAtual.getTiles().get(i).getName().equals("DoorWay")){
				if(roomAtual.getTiles().get(i).getPosition().equals(atual)){
					gui.clearImages();
					d = roomAtual.ActualDoor(atual).getPdestino();
					q = Integer.valueOf(roomAtual.ActualDoor(atual).getRoom().substring(10, 11));
					roomAtual = rooms.get(q);
					h.setPosition(roomAtual.getPortas().get(d).getPosition());
					System.out.println("Hero: Entrei no " + roomAtual.getNome().substring(6, 11) + "!");
					gui.newImages(roomAtual.getTiles());
					break;
				}
			}
		}
	}
}