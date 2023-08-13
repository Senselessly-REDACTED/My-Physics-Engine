import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
/**
 * 
 * @author Raymond Klarr
 *	Class that represents a square on a coordinate grid
 *	7/18/2023
 */

public class SkewBox extends Shape
{
	private Rectangle rec;
	
	public SkewBox(int width, int height, int startX, int startY) {
//		super(width, height, startX, startY);
//		rec = new Rectangle(startX, startY, width, height);
		// TODO Auto-generated constructor stub
	}

	public SkewBox(int width, int height) 
	{
//		super(width, height);
//		rec = new Rectangle(width, height, 0, 0);
	}
	
	public Rectangle getRectangle()
	{
		return rec;
	}
	
}
