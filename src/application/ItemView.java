package application;



import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemView {
	
	private static Pane pane;
	private static ArrayList<ImageView> imageList;
	private static ArrayList<Integer> itemList;
	private static Rectangle squre;
	
	public ItemView(Pane pane) {
		
		this.pane = pane;
		imageList = new ArrayList<ImageView>();
		itemList = new ArrayList<Integer>();
		NoteDropTask.item=1;
		
		Rectangle line=new Rectangle(420, 5);
		line.setLayoutX(54);
		line.setLayoutY(986);
		line.setFill(Paint.valueOf("#FFFFFF"));
		
		pane.getChildren().add(line);
		
		squre=new Rectangle(75, 75);
		squre.setFill(Paint.valueOf("#FFFFFF00"));
		squre.setStroke(Paint.valueOf("#e40505"));
		squre.setLayoutX(54);
		squre.setLayoutY(900);
		squre.setStrokeWidth(2);
		squre.setOpacity(0);
		pane.getChildren().add(squre);
		
		Text text = new Text("ITEM");
		text.setFill(Paint.valueOf("#fcf7f7"));
		text.setLayoutX(54);
		text.setLayoutY(876);
		text.setFont(Font.font(41));
		
		pane.getChildren().add(text);
	
		
	}
	
	public static void addItem(int i) {
		String itemName="";
		if(i==0)
			itemName="Change.png";
		else if(i==1)
			itemName="Hide.png";
		else if(i==2)
			itemName="Fast.png";
		
		if(imageList.size()<5) {
			ImageView imageView=new ImageView();
			imageView.setFitHeight(65);
			imageView.setFitWidth(65);
			imageView.setLayoutX(60+85*imageList.size());
			imageView.setLayoutY(905);
			imageView.setImage(new ImageParser(itemName).getImage());
			
			pane.getChildren().add(imageView);
			imageList.add(imageView);
		}
		
		itemList.add(i);
		
		if(itemList.size()>0)
			squre.setOpacity(1);
	}
	
	public static void useItem(String user) {
		if(!itemList.isEmpty()) {
			MultiThreadClient.useItem(user, itemList.get(0));
			
			itemList.remove(0);
			imageList.get(0).setImage(null);
			imageList.remove(0);
			
			System.out.println("imageSize "+imageList.size());
			
			for(int i=0; i<imageList.size(); i++) {
				imageList.get(i).setLayoutX(60+85*i);
			}
			
			if(itemList.size()>=5) {
				String itemName="";
				int i=itemList.get(4);
				if(i==0)
					itemName="Change.png";
				else if(i==1)
					itemName="Hide.png";
				else if(i==2)
					itemName="Fast.png";
				
				ImageView imageView=new ImageView();
				imageView.setFitHeight(65);
				imageView.setFitWidth(65);
				imageView.setLayoutX(60+85*imageList.size());
				imageView.setLayoutY(905);
				imageView.setImage(new ImageParser(itemName).getImage());
				
				pane.getChildren().add(imageView);
				imageList.add(imageView);
			}
			
			if(itemList.size()==0)
				squre.setOpacity(0);
		}
	}

}
