import java.util.Arrays;
import java.util.Set;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestRunnerOneCollider extends Application
{
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		//Create Boxes
		ColliderMap terrainColliderMap = new ColliderMap();
		Box player = new Box(50, 50, 40, 190);
		Box wall = new Box(25,300,50,0);
		Box floor = new Box(600,25,0,200);
		terrainColliderMap.addCollider(floor);
		terrainColliderMap.addCollider(wall);
		
		//Style Boxes
		wall.getRectangle().setOpacity(.7);
		floor.getRectangle().setOpacity(.7);
		player.getRectangle().setFill(Color.BLUE);
		player.getRectangle().setOpacity(.7);
		
		//add objects to scene
		Group root = new Group();
		root.getChildren().addAll(player.getRectangle(), wall.getRectangle(), floor.getRectangle()); //floor.getRectangle()
		Scene sce = new Scene(root);
		primaryStage.setScene(sce);
		
		
		
		Set<Collider> collisions = terrainColliderMap.checkColliders(player.getCollider());
//		terrainColliderMap.checkColliders(player.getCollider());
		//debug
		System.out.println(collisions + "\n" + wall.getCollider());//+ "\n" + terrainColliderMap
//		System.out.println(player.getCollider());
		
		int[] adj = correct(player, collisions);
		System.out.println("\n\n" + Arrays.toString(adj));

		var eH = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
//				System.out.println(arg0.isPrimaryButtonDown());
				player.shift(correct(player, terrainColliderMap.checkColliders(player.getCollider())));
				System.out.println(terrainColliderMap.checkColliders(player.getCollider()) + "\t" + player);
				player.reRender();
			}
		};
	    
	    root.addEventFilter(MouseEvent.MOUSE_CLICKED, eH);
		
		primaryStage.show();
		
		
		
		
		
		
		
		collisions = terrainColliderMap.checkColliders(player.getCollider());
		System.out.println("\n" + collisions);
		
		
	}
	
	public int[] correct(Collider col, Set<Collider> colSet)
	{
		int adjX = 0; int adjY = 0;
		
		for(Collider n : colSet)
		{
			int distanceLeftEdge = col.getColliderX() - n.getColliderX();
			int distanceUpEdge = col.getColliderY() - n.getColliderY();
			int distanceRightEdge = n.getColliderWidth() - (distanceLeftEdge + col.getColliderWidth());
			int distanceDownEdge = n.getColliderHeight() - (distanceUpEdge + col.getColliderHeight());
			int shortestY = Math.min(distanceUpEdge, distanceDownEdge);
			int shortestX = Math.min(distanceRightEdge, distanceLeftEdge);

			
			if(shortestY <= shortestX)
			{
				if(distanceUpEdge <= distanceDownEdge)
				{
					adjY += -1*(distanceUpEdge + col.getColliderHeight());
				}
				else
				{
					adjY += (distanceDownEdge + col.getColliderHeight());
				}
			}
			else
			{
				if(distanceRightEdge <= distanceLeftEdge)
				{
//					System.out.println("ping");
					adjX += (distanceRightEdge + col.getColliderWidth());
				}
				else
				{
					adjX += -1*(distanceLeftEdge + col.getColliderWidth());
				}
			}
		}

        return new int[]{adjX, adjY};
	}
	
	public int[] correct(Collidable col, Set<Collider> colSet)
	{
		return correct(col.getCollider(), colSet);
	}
}
