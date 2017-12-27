package pt.iul.ista.poo.status;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import pt.iul.ista.poo.actors.*;
import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.items.*;
import pt.iul.ista.poo.rogue.utils.Position;
import pt.iul.ista.poo.room.*;

public class Status {
	
	private ArrayList<ImageTile> status = new ArrayList<ImageTile>();
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	private int nfire = 3;
	private boolean saved = false;
	private Position savedP;
	private Room savedR;

	public void setStatus(){
		for(int i = 0; i < 10; i++){
			status.add(new Black(i));
		}
		status.add(new Fire(0));
		status.add(new Fire(1));
		status.add(new Fire(2));
		status.add(new Red(3));
		status.add(new Red(4));
		status.add(new Red(5));
		status.add(new Red(6));
		status.add(new GreenRed(3));
		status.add(new Green(3));
		status.add(new GreenRed(4));
		status.add(new Green(4));
		status.add(new GreenRed(5));
		status.add(new Green(5));
		status.add(new GreenRed(6));
		status.add(new Green(6));
	}
	
	public void dropItem(int keyCode, Hero h, ArrayList<Item> items){
		for(int i = status.size() -1; i > 0; i--){
			if(keyCode == KeyEvent.VK_1){
				if(status.get(i).getName().equals("Sword")){
					System.out.println("Hero: Larguei a espada!");
					h.minusAtaque(((Sword) status.get(i)).getAtaque());
					gui.removeStatusImage(status.get(i));
					items.remove(status.get(i));
					status.remove(i);
					break;
				}
			}
			if(keyCode == KeyEvent.VK_2){
				if(status.get(i).getName().equals("Hammer")){
					System.out.println("Hero: Larguei o martelo!");
					h.minusAtaque(((Hammer) status.get(i)).getAtaque());
					gui.removeStatusImage(status.get(i));
					items.remove(status.get(i));
					status.remove(i);
					break;
				}
			}
			if(keyCode == KeyEvent.VK_3){
				if(status.get(i).getName().equals("key")){
					System.out.println("Hero: Larguei a key!");
					gui.removeStatusImage(status.get(i));
					items.remove(status.get(i));
					status.remove(i);
					break;
				}
			}
		}
	}
	
	public void fire(int keyCode, Hero h, Room r){
		ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
		if(keyCode == KeyEvent.VK_SPACE && !h.checkWall(r.getTiles(), h.getD())){
			Fire fire = new Fire(h.getPosition().plus(h.getD().asVector()));
			for(int i = status.size() -1; i > 0; i--){
				if(status.get(i).getName().equals("Fire")){
					gui.addImage(fire);
					System.out.println("Hero: Disparei uma " + status.get(i).getName() + "Ball!");
					gui.removeStatusImage(status.get(i));
					status.remove(i);
					fire.fireAnimation(h, r);
					gui.removeImage(fire);
					nfire--;
					break;
				}
			}
		}
	}
	
	public void removeVida(int dano) {
		ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
		for(int d = 0; d < dano; d++){
			for(int i = status.size() -1; i > 0; i--){
				if(status.get(i).getName().equals("Green") || status.get(i).getName().equals("GreenRed")){
					gui.removeStatusImage(status.get(i));
					status.remove(i);
					break;
				}
			}
		}
	}
	
	public ArrayList<ImageTile> getStatus() {
		return status;
	}
	
	public void resetVida(Room r, Hero h){
		for(int i = 0; i < r.getTiles().size(); i++){
			if(r.getTiles().get(i).getName().equals("Vida")){
				if(r.getTiles().get(i).getPosition().equals(h.getPosition())){
					resetHealthBar(h.getVida());
					gui.removeImage(r.getTiles().get(i));
					r.getTiles().remove(i);
					h.setVida(8);
					saved = true;
					savedP = h.getPosition();
					savedR = r;
					System.out.println("Jogo gravado!");
				}
			}
		}
	}
	
	public boolean isSaved() {
		return saved;
	}
	
	public Position getSavedP() {
		return savedP;
	}
	
	public Room getSavedR() {
		return savedR;
	}

	public void resetHealthBar(int n) {
		System.out.println("Vida restaurada...");
		int vida = 8 - n;
		int j = 6;
		if(vida > 2 && vida <= 4)
			j = 5;
		else if(vida > 4 && vida <= 6)
			j = 4;
		else if(vida > 6 && vida <= 8)
			j = 3;
		for(int i = 1; i <= vida; i++){
			if(vida % 2 == 0){
				if(i % 2 == 0){
					status.add(new Green(j));
					j++;
				}
				else{
					status.add(new GreenRed(j));
				}
			}
			else{
				if(i % 2 == 0){
					status.add(new GreenRed(j));
				}
				else{
					status.add(new Green(j));
					j++;
				}
			}
		}
		gui.clearStatus();
		gui.newStatusImages(status);
	}
	
	public void resetFire(Room r, Hero h){
		for(int i = 0; i < r.getTiles().size(); i++){
			if(r.getTiles().get(i).getName().equals("Fire")){
				if(r.getTiles().get(i).getPosition().equals(h.getPosition())){
					while(nfire != 3){
						status.add(new Fire(nfire));
						nfire++;
					}
					gui.clearStatus();
					gui.newStatusImages(status);
					gui.removeImage(r.getTiles().get(i));
					r.getTiles().remove(i);
					System.out.println("Fireballs restauradas...");
				}
			}
		}
	}
}