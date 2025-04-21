import java.security.PublicKey;
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
	public boolean addCollider(Collider col, boolean force)
	{
		if(!force && col.getColliderID() != -1)
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

	public boolean addCollider(Collider col)
	{
		return addCollider(col, false);
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
	 * Function removes a collider from the map by ID, this function has a horrible time complexity, will fix in the future
	 * for now use only as needed.
	 * O(n^3) : iterate all elements in the map O(n^2) use remove to remove from arraylist O(n); O(n^2) * O(n) = O(n^3)
	 * @param colliderId collider for removal
	 * @return reference to the removed collider
	 */
	public Collider removeCollider(int colliderId)
	{
		Integer[] temp = find(colliderId);
		if(temp == null)
			return null;

		return colliderReference.get(temp[0]).remove(temp[1].intValue());
	}

	/**
	 * find is a helper method which looks for a collider in the map
	 * @param colliderId the ID of the collider to find
	 * @return	a two element array of "coordinates" of the collider in the map, returns null if the element does not exist.
	 */
	private Integer[] find(int colliderId)
	{
		Integer[] ret = new Integer[2];

		Set<Integer> keys = colliderReference.keySet();
		for(Integer x : keys)
		{
			for(int i = 0; i < colliderReference.get(x).size(); i++)
			{
				ArrayList<Collider> comp = colliderReference.get(x);
				if(comp.get(i).getColliderID() == colliderId) {
					ret[0] = x;
					ret[1] = i;
					return ret;
				}
			}
		}
		return null;
	}

	/**
	 * PRECONDITION: the collider MUST exist within the map
	 * Check a certain Collider for collisions in its area
	 * @param col - Collider to check
	 * @return ArrayList of Colliders that col is collided with, null if the collider is not an element of the map.
	 */
	public Set<Collider> checkColliders(Collider col)
	{
		if(find(col.getColliderID()) == null)
			return null;

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
