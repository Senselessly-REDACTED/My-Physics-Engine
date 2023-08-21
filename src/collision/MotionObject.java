package collision;

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
		

		
		if(Math.abs(this.getPosX() - obj.getPosX()) < obj.width) // is obj positioned to the left of this object?
		{
            return Math.abs(this.getPosY() - obj.getPosY()) < obj.height;
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

	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	//Overridden Methods
	@Override
	public String toString()
	{
		return "(X: " + posX + ", Y: " + posY + ", W: " + width + ", H: " + height + ")";
	}
}
