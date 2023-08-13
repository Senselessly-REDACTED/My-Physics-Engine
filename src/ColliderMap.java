import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/***
 * Structure that holds colliders and checks if a collider is collided with any in the structure.
 * @author Raymond Klarr
 * 7/21/2023
 */

public class ColliderMap
{
	private final Map<Integer, ArrayList<Collider>> colliderReference;
	private static int roster = 0;
	
	public ColliderMap()
	{
		colliderReference = new TreeMap<>();
	}
	
	/**
	 * Adds a collider to the ColliderMap
	 * each key represents x values counted by 100s. i.e. 0: 0-99, 1: 100-199, ..., n: (n*100)-(n*100+99)
	 * A collider that has been assigned an ID is assumed to already belong to a Collider Map and will not be added.
	 * @param col collider to be added.
	 * @return true if add was successful, false otherwise
	 */
	public boolean addCollider(Collider col)
	{
		if(col.getColliderID() != -1)
			return false;
		
		int nS = col.getColliderX()/100;
		int nE = (col.getColliderX() + col.getColliderWidth())/100;
		
		for(int n = nS; n <= nE; n++) 
		{
			if(colliderReference.containsKey(n))
			{
				col.setColliderID(roster);
				colliderReference.get(n).add(col);
			}
			else
			{
				col.setColliderID(roster);
				ArrayList<Collider> temp = new ArrayList<>();
				temp.add(col);
				colliderReference.put(n, temp);
			}
		}
		roster++;
		return true;
	}
	
	/**
	 * Overload of the addCollider method, QOL method that lets you directly pass an object with a collider.
	 * @param col collidable object
	 * @return true if add was successful, false otherwise
	 */
	public boolean addCollider(Collidable col)
	{
		return addCollider(col.getCollider());
	}
	
	/**
	 * Check a certain Collider for collisions in its area
	 * @param col - Collider to check
	 * @return ArrayList of Colliders that col is collided with
	 */
	public Set<Collider> checkColliders(Collider col)
	{
		Set<Collider> ret = new TreeSet<>();
		int nStart = col.getColliderX()/100;
		int nEnd = (col.getColliderX() + col.getColliderWidth())/100;

		for(int n = nStart; n <= nEnd; n++)
		{

			if(colliderReference.containsKey(n)) 
			{
				for(Collider z : colliderReference.get(n))
				{
					int x0 = z.getColliderX();
					int x1 = col.getColliderX();
					int a = z.getColliderWidth();
					int b = col.getColliderWidth();
					
					if((x0 < x1 && x1 < (x0 + a))
							|| (x0 < x1+b && (x1+b) < (x0 + a))
							|| (x1 < x0 && (x0 + a) < (x1 + b)))
					{
						int y0 = z.getColliderY();
						int y1 = col.getColliderY();
						int c = z.getColliderHeight();
						int d = col.getColliderHeight();
						
						if((y0 < y1 && y1 < (y0 + c))
								|| (y0 < y1+d && (y1+d) < (y0 + c))
								|| (y1 < y0 && (y0 + c) < (y1 + d)))
						{
							ret.add(z);
						}
					}
				}
			}
		}
		return ret;
	}
	
	/**
	 * Overload of the checkColliders method, QOL method that lets you directly pass an object with a collider.
	 * @param col collidable object
	 * @return set of colliders in this structure that are collided with col
	 */
	public Set<Collider> checkColliders(Collidable col)
	{
		return checkColliders(col.getCollider());
	}
	
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		Set<Integer> s = colliderReference.keySet();
		
		for(Integer x : s)
            ret.append(x).append(": ").append(colliderReference.get(x)).append(", ");
		
		return ret.substring(0, ret.length()-2);
	}
}
