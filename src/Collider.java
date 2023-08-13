
/**
 * @author Raymond Klarr
 * class that represents a collision box
 * 7/23/2023
 */

public class Collider implements Comparable<Collider>
{
	private int colliderWidth, colliderHeight, colliderX, colliderY;
	private int colliderID; //Identity of the collider, -1 means unassigned.

	public Collider()
	{
		colliderWidth = 0;
		colliderHeight = 0;
		colliderX = 0;
		colliderY = 0;
		colliderID = -1;
	}

	public Collider(int colliderWidth, int colliderHeight, int colliderX, int colliderY)
	{
		this.colliderWidth = colliderWidth;
		this.colliderHeight = colliderHeight;
		this.colliderX = colliderX;
		this.colliderY = colliderY;
		this.colliderID = -1;
	}

	
	//Getters/Setters & toString
	public int getColliderWidth()
	{
		return colliderWidth;
	}

	public int getColliderHeight()
	{
		return colliderHeight;
	}

	public int getColliderX()
	{
		return colliderX;
	}

	public int getColliderY()
	{
		return colliderY;
	}

	public int getColliderID()
	{
		return colliderID;
	}
	
	public void setColliderWidth(int colliderWidth)
	{
		this.colliderWidth = colliderWidth;
	}

	public void setColliderHeight(int colliderHeight)
	{
		this.colliderHeight = colliderHeight;
	}

	public void setColliderX(int colliderX)
	{
		this.colliderX = colliderX;
	}

	public void setColliderY(int colliderY)
	{
		this.colliderY = colliderY;
	}

	public void setColliderID(int colliderID)
	{
		this.colliderID = colliderID;
	}
	
	@Override
	public boolean equals(Object o)
	{
		Collider c = (Collider)o;
		return this.colliderID == c.getColliderID();
	}
	
	public String toString()
	{
		return "[w:" + this.colliderWidth + ", h:" + this.colliderHeight + ", x:" + this.colliderX + ", y:" + this.colliderY + ", id:" + this.colliderID + "]";
	}

	@Override
	public int compareTo(Collider c)
	{
		return this.colliderID - c.getColliderID();
	}
}
