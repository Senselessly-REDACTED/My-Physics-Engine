

import java.util.ArrayList;
import java.util.List;


import collision.Box;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Runner extends Application
{

    private static final List<Box> boxes = new ArrayList<>();
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Hi");
		Box b1 = new Box(50,50,89, 0);
		Box floor = new Box(50,50);

		b1.setVelY(5);

		floor.getRectangle().setFill(Color.BLUE);
		floor.getRectangle().setOpacity(.7);
		b1.getRectangle().setOpacity(.7);
		floor.setVelX(5);
		
		boxes.add(b1);
		boxes.add(floor);
		System.out.println(b1.isCollided(floor));
		
		
		//Creating a Group object  
	    Group root = new Group();
	    root.getChildren().addAll(b1.getRectangle(),floor.getRectangle());
	    //Creating a scene object 
	    Scene scene = new Scene(root, 600, 300);  
		
	    EventHandler<MouseEvent> eH = arg0 -> update();

	    root.addEventFilter(MouseEvent.MOUSE_CLICKED, eH);
	    
		primaryStage.setScene(scene);
		primaryStage.setHeight(300);
		primaryStage.setWidth(400);
		primaryStage.show();
		
		
/*
		while(true)
		{
		    for(collision.Box n : boxes)
		    {
		    	n.getRectangle().relocate(n.getPosX() + n.getVelX(), n.getPosY() + n.getVelY());
		    	try {
		    	Thread.sleep(5);
		    	}
		    	catch(Exception e)
		    	{
		    		return;
		    	}
		    	System.out.println("d");
		    }
*/


    }
	
	public static void update()
	{
		 for(Box n : boxes)
		    {
			 System.out.println(n);
		    	n.update();
				n.reRender();
		    }
		 System.out.println(boxes.get(0).isCollided(boxes.get(1)));
	}
}
