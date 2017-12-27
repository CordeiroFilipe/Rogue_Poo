package pt.iul.ista.poo.status;

import pt.iul.ista.poo.actors.Hero;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;
import pt.iul.ista.poo.room.Room;

public class Fire implements ImageTile{

	private Position p;
	
	public Fire(int x){
		p = new Position(x,0);
	}
	
	public Fire(Position p){
		this.p = p;
	}
	
	public void setP(Position p) {
		this.p = p;
	}
	
	public void fireAnimation(Hero h, Room r){
		h.setName("Herogoku");
		p = h.getPosition().plus(h.getD().asVector());
		while(!checkObs(r)){
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p = p.plus(h.getD().asVector());
			setP(p);
		}
		if(checkObs(r)){
			for(int i = 0; i < r.getEnemies().size(); i++){
				if(r.getEnemies().get(i).getPosition().equals(p)){
					r.getEnemies().get(i).minusVida(20);
				}
			}
		}
		h.setName("Hero");
	}
	
	public boolean checkObs(Room r){
		for(int i = 0; i < r.getTiles().size(); i++){
			if(r.getTiles().get(i).getName().equals("Wall")){
				if(r.getTiles().get(i).getPosition().equals(p)){
					return true;
				}
			}
		}
		for(int j = 0; j < r.getEnemies().size(); j++){
			if(r.getEnemies().get(j).getPosition().equals(p)){
				return true;
			}
		}
		for(int k = 0; k < r.getPortas().size(); k++){
			if(r.getPortas().get(k).getPosition().equals(p)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getName() {
		return "Fire";
	}
	@Override
	public Position getPosition() {
		return p;
	}
}