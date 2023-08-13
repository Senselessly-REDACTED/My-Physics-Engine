/**
 * 
 * @author Raymond Klarr
 *	interface for objects that must handle collision
 *	7/18/2023
 */

public interface Collidable 
{	
	boolean isCollided(MotionObject obj); //Pending removal.
	
	Collider getCollider(); //Every collidable object must possess a collider
}
