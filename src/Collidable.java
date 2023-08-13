/**
 * 
 * @author Raymond Klarr
 *	interface for objects that must handle collision
 *	7/18/2023
 */

public interface Collidable 
{	
	public boolean isCollided(MotionObject obj); //Pending removal.
	
	public Collider getCollider(); //Every collidable object must possess a collider
}
