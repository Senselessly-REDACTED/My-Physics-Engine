/**
 * 
 * @author Raymond Klarr
 *	Abstract class for objects that move and can collide.
 *	7/18/2023
 */

public abstract class MotionObject
{
	private int width, height, velX, velY, posX, posY;
	
	public MotionObject(int width, int height) 
	{
		this.width = width;
		this.height = height;
		velX = 0;
		velY = 0;
		posX = 0;
		posY = 0;
	}
	
	public MotionObject(int width, int height, int startX, int startY) 
	{
		this.width = width;
		this.height = height;
		velX = 0;
		velY = 0;
		posX = startX;
		posY = startY;
	}
	
	public boolean isCollided(MotionObject obj) 
	{
		if(this.getPosX() - obj.getPosX() == 0 && this.getPosY() - obj.getPosY() == 0) //Check if the object is on the same coordinate
			return true;
		
		
//		int diffX = Math.abs(Math.min(this.getPosX(), obj.getPosX()) - Math.max(this.getPosX(), obj.getPosX()));
//		int diffY = Math.abs(Math.min(this.getPosY(), obj.getPosY()) - Math.max(this.getPosY(), obj.getPosY()));
		
		if(Math.abs(this.getPosX() - obj.getPosX()) < obj.width) // is obj positioned to the left of this object?
		{
			if(Math.abs(this.getPosY() - obj.getPosY()) < obj.height)
				return true;
			else
				return false;
		}
		else
		{
			return false;
		}
		
	}
	
	//Getters & Setters
	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	public String toString()
	{
		return "(" +posX + ", " + posY + ")";
	}
	
}
