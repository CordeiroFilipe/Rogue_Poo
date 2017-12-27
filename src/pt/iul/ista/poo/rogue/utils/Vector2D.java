package pt.iul.ista.poo.rogue.utils;

public class Vector2D {
	
	private int x;
	private int y;

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Vector2D plus(Vector2D v) {
		return new Vector2D(v.getX() + x, v.getY() + y);
	}

	public Vector2D minus(Vector2D v) {
		return new Vector2D(x - v.getX(), y - v.getY());
	}
}