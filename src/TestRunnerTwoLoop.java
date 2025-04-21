import java.util.*;

import collision.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestRunnerTwoLoop extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		Group root = new Group();

		//Create Boxes
		ColliderMap terrainColliderMap = new ColliderMap();

		Box player = new Box(50, 50, 300, 100);
		Box wall = new Box(25,300,50,0);
		Box wall2 = new Box(25,300,500,0);
		Box floor = new Box(600,25,0,0);
		Box wall3 = new Box(25,25,225,75);
		Box wall4 = new Box(25,25,250,100);

		root.getChildren().addAll(wall3.getRectangle(), wall4.getRectangle());

		for(int i = 0; i < 100; i++)
		{
			Box bp = new Box((int)(10*Math.random() + 20), (int)(10*Math.random() + 20), (int)(700*Math.random()), (int)(700*Math.random()));
			root.getChildren().add(bp.getRectangle());
			terrainColliderMap.addCollider(bp);
		}


		terrainColliderMap.addCollider(wall3);
		terrainColliderMap.addCollider(wall4);
		terrainColliderMap.addCollider(wall2);
		terrainColliderMap.addCollider(floor);
		terrainColliderMap.addCollider(wall);
		
		//Style Boxes
		wall.getRectangle().setOpacity(.7);
		wall2.getRectangle().setOpacity(.7);
		floor.getRectangle().setOpacity(.7);
		player.getRectangle().setFill(Color.BLUE);
		player.getRectangle().setOpacity(.7);
		
		

		root.getChildren().addAll(player.getRectangle(), wall.getRectangle(), floor.getRectangle(), wall2.getRectangle()); //floor.getRectangle()
		Scene sce = new Scene(root);
		primaryStage.setScene(sce);

		Map<String, Boolean> keys = new TreeMap<>();
        for (String s : Arrays.asList("W", "A", "S", "D", "F")) {
            keys.put(s, false);
        }

        sce.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
							switch (key.getCode().getChar()) {
								case ("W") -> {
									if(!keys.get("W")) {
										player.setVelY(player.getVelY() - 1);
										keys.replace("W", true);
									}
								}
								case ("A") -> {
									if(!keys.get("A")) {
										player.setVelX(player.getVelX() - 1);
										keys.replace("A", true);
									}
								}
								case ("S") -> {
									if(!keys.get("S")) {
										player.setVelY(player.getVelY() + 1);
										keys.replace("S", true);
									}
								}
								case ("D") -> {
									if(!keys.get("D")) {
										player.setVelX(player.getVelX() + 1);
										keys.replace("D", true);
									}
								}
								case ("F") -> {
									if(!keys.get("F")) {
										System.out.println(terrainColliderMap.removeCollider(player.getCollider().getColliderID()));
						keys.replace("F", true);
					}
				}
            }
		});

		sce.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            switch (key.getCode().getChar()) {
				case ("W") -> {
					if(keys.get("W")) {
						player.setVelY(player.getVelY() + 1);
						keys.replace("W", false);
					}
				}
				case ("A") -> {
					if(keys.get("A")) {
						player.setVelX(player.getVelX() + 1);
						keys.replace("A", false);
					}
				}
				case ("S") -> {
					if(keys.get("S")) {
						player.setVelY(player.getVelY() - 1);
						keys.replace("S", false);
					}
				}
				case ("D") -> {
					if(keys.get("D")) {
						player.setVelX(player.getVelX() - 1);
						keys.replace("D", false);
					}
				}
				case ("F") -> {
					if(keys.get("F")) {
						System.out.println(player.getCollider().getColliderID());
						terrainColliderMap.addCollider(player.getCollider(), true);
						keys.replace("F", false);
					}
				}
            }
		});
		
		AnimationTimer x = new AnimationTimer()
		{
			@Override
			public void handle(long arg0)
			{
				player.update();
				Set<Collider> colSet = terrainColliderMap.checkColliders(player);
				//System.out.println(player + "\t" + colSet);
				if(colSet != null)
					player.shift(correct(player, colSet));
				player.reRender();
			}
		};
		
		x.start();
		
		primaryStage.show();
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
