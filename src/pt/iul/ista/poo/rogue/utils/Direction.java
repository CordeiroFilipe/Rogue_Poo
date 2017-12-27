package pt.iul.ista.poo.rogue.utils;

public enum Direction {
	LEFT, UP, RIGHT, DOWN;

	public Vector2D asVector() {
		Vector2D v = new Vector2D(0,0);
		if(this == Direction.UP){
			v.setX(0);
			v.setY(-1);
		}
		if(this == Direction.DOWN){
			v.setX(0);
			v.setY(1);
		}
		if(this == Direction.LEFT){
			v.setX(-1);
			v.setY(0);
		}
		if(this == Direction.RIGHT){
			v.setX(1);
			v.setY(0);
		}
		return v;
	}
}