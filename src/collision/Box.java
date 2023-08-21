package collision;

import javafx.scene.shape.Rectangle;
/**
 * 
 * @author Raymond Klarr
 *	Class that represents a rectangle on a coordinate grid
 *	7/18/2023
 */

public class Box extends MotionObject implements Collidable
{
	private final Rectangle rec;
	private final Collider boxCollider;
	
	public Box(int width, int height, int startX, int startY) {
		super(width, height, startX, startY);
		rec = new Rectangle(startX, startY, width, height);
		boxCollider = new Collider(width, height, startX, startY);
		// TODO Auto-generated constructor stub
	}

	public Box(int width, int height) 
	{
		super(width, height);
		rec = new Rectangle(0,0 ,width, height);
		boxCollider = new Collider(width, height, 0, 0);
	}
	
	public void update()
	{		
		this.setPosX(this.getPosX() + this.getVelX());
		this.setPosY(this.getPosY() + this.getVelY());
		boxCollider.setColliderX(this.getPosX() + this.getVelX());
		boxCollider.setColliderY(this.getPosY() + this.getVelY());
	}
	
	public void shift(int[] adj)
	{
		
		int xShifted = this.getPosX() + adj[0] + this.getVelX();
		int yShifted = this.getPosY() + adj[1] + this.getVelY();
		
		this.setPosX(xShifted);
		this.setPosY(yShifted);
		
		boxCollider.setColliderX(xShifted);
		boxCollider.setColliderY(yShifted);
		
//		rec.relocate(xShifted, yShifted);
	}

	public void resize(int w, int h)
	{
		this.setWidth(w);
		this.setHeight(h);
		rec.resize(w, h);
	}

	public void reRender()
	{
		if(this.getHeight() != rec.getHeight())
			rec.resize(this.getWidth(), this.getHeight());

		if(this.getPosX() != rec.getX() || this.getPosY() != rec.getY())
			this.getRectangle().relocate(this.getPosX(), this.getPosY());
	}
	
	public Collider getCollider()
	{
		return boxCollider;
	}
	
	public Rectangle getRectangle()
	{
		return rec;
	}
}
