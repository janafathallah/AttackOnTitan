package game.gui;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.WallTrap;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;


public class Main extends Application {
	public static void main(String[] args){
        launch(args);
    }
	Scene welcomeScene;
	Scene instructionsScene;
	Scene difficultyScene;
	Scene gameScene;
	Scene weaponshopScene;
	Battle battle;
	Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/BGM_3B.WAV").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
	Lane selectedLane;
	@Override
	public void start(Stage stage){
	        prepareWelcomeScene(stage);
	        stage.setScene(welcomeScene);
	        stage.show();
	}
	public void prepareWelcomeScene(Stage stage){
		BorderPane pane = new BorderPane();
        StackPane topPane = new StackPane();
       
        Image Titleimage= new Image("file:///C:/Users/janah/OneDrive/Pictures/fontbolt (2).png");
        ImageView titleView= new ImageView(Titleimage);
        StackPane.setAlignment(titleView,Pos.TOP_CENTER);
        topPane.getChildren().add(titleView);
        
        BorderPane.setMargin(topPane, new javafx.geometry.Insets(300, 0, 0, 0));
        pane.setTop(topPane);
        
        StackPane ButtonPane = new StackPane();
        Button button= new Button("start");
        Font buttonFont= new Font(35);
        button.setFont(buttonFont);
        button.setOnAction(new EventHandler <ActionEvent>(){
        	 public void handle(ActionEvent event) {
        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
        	     mediaPlayer.setAutoPlay(true);
        		 prepareInstructionsScene(stage);
        	 }});
        ButtonPane.getChildren().add(button);
        
        BackgroundSize size= new BackgroundSize(1800,900,false,false,false,true);
        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/115326.gif");
        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background= new Background(backgroundimage);
        pane.setBackground(background);
        pane.setCenter(ButtonPane);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
       
        welcomeScene= new Scene (pane,1800,900);
	}
	public void prepareInstructionsScene(Stage stage){
		BorderPane pane= new BorderPane();
		StackPane instructionPane = new StackPane();
        Label instructionLabel = new Label("Player Instructions:\nObjective:\nDefend the Utopia District from Titan attacks for as long as possible and achieve a high score by defeating as many Titans as you can.\nSetup:\n- The battlefield is divided into multiple lanes, each with a wall section to defend.Deploy various Anti-Titan weapons in these lanes.\nGameplay:\n1. Deployment Phase:\n- Purchase and deploy weapons using resources.\n- Different weapons have unique prices and damage values.\n2. Battle Phase:\n- Titans advance towards the walls each turn.\n- Weapons attack Titans in their lanes.\n- Titans reaching the walls will attack and reduce wall HP. \nPhases:\n- Early Phase: Few Titans.\n- Intense Phase: More and stronger Titans.\n- Grumbling Phase: Constant strong Titan attacks.\nEnd Condition:\nThe game ends when all wall sections are destroyed.\n Your score is based on the number of Titans defeated and resources gathered.\nTitan Types:\n- Pure Titan: Moderate HP and damage.\n- Abnormal Titan: Attacks twice per turn.\n- Armored Titan: Takes less damage.\n- Colossal Titan: Increases speed each turn.\nWeapon Types:\n- Piercing Cannon: Attacks closest 5 Titans.\n- Sniper Cannon: Attacks closest Titan.\n- Volley Spread Cannon: Attacks all Titans within a range.\n- Wall Trap: Attacks Titans that reach the wall.\nTips:\n- Spend resources wisely on different weapons.\n- Focus on lanes with higher danger levels.\n- Use Volley Spread Cannons for groups of Titans.\n- Place Wall Traps in heavily attacked lanes.\nControls:\n- Use the mouse to select and deploy weapons.\n- Monitor lane status and adjust your strategy accordingly.");
        instructionLabel.setStyle("-fx-font-weight: Bold; -fx-text-fill: black");
        instructionLabel.setFont(new Font(13));
        StackPane.setAlignment(instructionLabel, Pos.TOP_LEFT);
        instructionPane.getChildren().add(instructionLabel);
        
        BorderPane.setMargin(instructionPane, new javafx.geometry.Insets(80, 0, 0, 100));
        pane.setTop(instructionPane);
        
        StackPane ButtonPane = new StackPane();
        Button button= new Button("continue");
        Font buttonFont= new Font(35);
        button.setFont(buttonFont);
        button.setOnAction(new EventHandler <ActionEvent>(){
        	 public void handle(ActionEvent event) {
        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
        	     mediaPlayer.setAutoPlay(true);
        		 prepareDifficultyScene(stage);
        	 }});
        
        ButtonPane.getChildren().add(button);
        pane.setCenter(ButtonPane);
        BackgroundSize size= new BackgroundSize(1800,900,false,false,false,true);
        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/115318.gif");
        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background= new Background(backgroundimage);
        pane.setBackground(background);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        instructionsScene= new Scene (pane,1800,900);
        stage.setScene(instructionsScene);
    	stage.show();
	}
	public void prepareDifficultyScene(Stage stage){
		BorderPane difficultyPane= new BorderPane();
		StackPane topPane = new StackPane();
		topPane.setPrefHeight(200);
		
		Label label = new Label("Choose Game Difficulty");
		Font font= new Font(60);
        label.setFont(font);
        label.setStyle("-fx-font-weight: Bold");
        StackPane.setAlignment(label,Pos.BOTTOM_CENTER);
        topPane.getChildren().add(label);
        
        Button backButton= new Button("back");
        backButton.setOnAction(new EventHandler <ActionEvent>(){
        	 public void handle(ActionEvent event) {
        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
        	     mediaPlayer.setAutoPlay(true);
        		 stage.setScene(instructionsScene);
        	 }});
        backButton.setFont(new Font(20));
        StackPane.setAlignment(backButton, Pos.TOP_RIGHT);
        topPane.getChildren().add(backButton);
        
        difficultyPane.setTop(topPane);
        
        StackPane Button = new StackPane();
        
        Button x= new Button("Easy");
        Button y= new Button("Hard");
        Font buttonFont= new Font(35);
        x.setFont(buttonFont);
        y.setFont(buttonFont);
        x.setOnAction(new EventHandler <ActionEvent>(){
        	public void handle(ActionEvent event) {
        		Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
       	     	MediaPlayer mediaPlayer = new MediaPlayer(media);
       	     	mediaPlayer.setAutoPlay(true);
        		 try{
        		 battle= new Battle(1,0,1000,3,250);
        		 prepareGameScene(stage);
        		 }
        		 catch(IOException e){
        			 e.printStackTrace();
     				openPopup(e); 
        		 }
        	 }});
        y.setOnAction(new EventHandler <ActionEvent>(){
        	 public void handle(ActionEvent event) {
        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
        	     mediaPlayer.setAutoPlay(true);
        		 try{
        		 battle= new Battle(1,0,1000,5,125);
        		 prepareGameScene(stage);
        		 }
        		 catch(IOException e){
        			 e.printStackTrace();
     				openPopup(e); 
        		 }
        	 }});
        StackPane.setAlignment(x,Pos.CENTER_LEFT);
        StackPane.setAlignment(y,Pos.CENTER_RIGHT);
        Button.getChildren().add(x);
        Button.getChildren().add(y);
        
        BorderPane.setMargin(Button, new javafx.geometry.Insets(0, 400, 0, 400));
        difficultyPane.setCenter(Button);
        

        
        BackgroundSize size= new BackgroundSize(1800,900,false,false,false,true);
        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/tumblr_ml8ttkEzP01s4t17io4_400.gif");
        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background= new Background(backgroundimage);
        difficultyPane.setBackground(background);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    	difficultyScene= new Scene(difficultyPane,1800,900);
    	stage.setScene(difficultyScene);
    	stage.show();
	}
	
	public void prepareGameScene(Stage stage) {
		VBox labelPane= new VBox();
		StackPane topPane= new StackPane();
		BorderPane gamePane= new BorderPane();
		try {
			
			Label resourcesLabel= new Label("resources="+battle.getResourcesGathered());
			Label scoreLabel= new Label("score="+battle.getScore());
			Label turnLabel= new Label("turn="+battle.getNumberOfTurns());
			Label phaseLabel= new Label("phase="+battle.getBattlePhase());
			resourcesLabel.setStyle("-fx-font-weight: Bold; -fx-text-fill: white; -fx-font-size: 20px");
			scoreLabel.setStyle("-fx-font-weight: Bold; -fx-text-fill: white; -fx-font-size: 20px");
			turnLabel.setStyle("-fx-font-weight: Bold; -fx-text-fill: white; -fx-font-size: 20px");
			phaseLabel.setStyle("-fx-font-weight: Bold; -fx-text-fill: white; -fx-font-size: 20px");
	        labelPane.getChildren().add(resourcesLabel);
	        labelPane.getChildren().add(scoreLabel);
	        labelPane.getChildren().add(turnLabel);
	        labelPane.getChildren().add(phaseLabel);
	        
	        StackPane.setAlignment(labelPane, Pos.CENTER_LEFT);
	        topPane.getChildren().add(labelPane);
	        
	        Button backButton= new Button("back");
	        backButton.setOnAction(new EventHandler <ActionEvent>(){
	        	 public void handle(ActionEvent event) {
	        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
	        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
	        	     mediaPlayer.setAutoPlay(true);
	        		 prepareDifficultyScene(stage);
	        	 }});
	        Font Font= new Font(20);
	        backButton.setFont(Font);
	        StackPane.setAlignment(backButton, Pos.CENTER_RIGHT);
	        topPane.getChildren().add(backButton);
	        gamePane.setTop(topPane);
	        
        	
        	StackPane WeaponShop= new StackPane();
	        
	        BorderPane.setMargin(WeaponShop, new javafx.geometry.Insets(0,0,0,0));
	        gamePane.setBottom(WeaponShop);
	        
	        Label titlews= new Label("Weapon Shop");
	        titlews.setStyle("-fx-font-size: 30px; -fx-font-weight: Bold; -fx-text-fill: white");
	        StackPane.setAlignment(titlews, Pos.CENTER);
	        StackPane.setMargin(titlews,new Insets(0, 450, 200, 450));
	        WeaponShop.getChildren().add(titlews);
	        
	        
	        HBox weapons= new HBox();
	        
	        for ( int key : battle.getWeaponFactory().getWeaponShop().keySet() ) {
	        	
	            final WeaponRegistry weaponRegistry= battle.getWeaponFactory().getWeaponShop().get(key);
	            Button weaponButton= new Button(weaponRegistry.getName());
	            weaponButton.setStyle("-fx-font-size: 30px;-fx-min-width: 360px; -fx-min-height: 180px;");
	            weaponButton.setOnAction(new EventHandler <ActionEvent>(){
		        	 public void handle(ActionEvent event) {
		        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
		        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
		        	     mediaPlayer.setAutoPlay(true);
		        		 if(selectedLane!=null){
		        			 try {
								battle.purchaseWeapon(weaponRegistry.getCode(), selectedLane);
								selectedLane=null;
								prepareGameScene(stage);
							} catch (InsufficientResourcesException
									| InvalidLaneException e) {
								openPopup(e);
							}
		        		 }
		        		 else{
		        			 openPopup(new InvalidLaneException("please select a lane before buying a weapon"));
		        		 }
		        	 }});
	            String weaponType;
	            if(weaponRegistry.getCode()==PiercingCannon.WEAPON_CODE){
	            	weaponType="Piercing Cannon";
	            }
	            else if(weaponRegistry.getCode()==SniperCannon.WEAPON_CODE){
	            	weaponType="Sniper Cannon";
	            }
	            else if(weaponRegistry.getCode()==VolleySpreadCannon.WEAPON_CODE){
	            	weaponType="Volley Spread Cannon";
	            }
	            else{
	            	weaponType="Wall Trap";
	            }
	            String tooltipString= "Type: "+weaponType +"\nPrice: "+weaponRegistry.getPrice()+"\nDamage: "+weaponRegistry.getDamage();
	            Tooltip weaponToolTip= new Tooltip(tooltipString);
	            weaponButton.setTooltip(weaponToolTip);
	            weapons.getChildren().add(weaponButton);
	        }
	        Button skipButton= new Button("pass turn");
	        skipButton.setStyle("-fx-font-size: 40px;-fx-min-width: 360px; -fx-min-height: 180px;");
	        skipButton.setOnAction(new EventHandler <ActionEvent>(){
	        	 public void handle(ActionEvent event) {
	        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
	        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
	        	     mediaPlayer.setAutoPlay(true);
	        		battle.passTurn();
	        		selectedLane=null;
	        		prepareGameScene(stage);
	        	 }});
	        weapons.getChildren().add(skipButton);
	        StackPane.setMargin(weapons,new Insets(200, 0, 0, 0));
	        WeaponShop.getChildren().add(weapons);
	        
	        
	        StackPane gameLanes= new StackPane();
	        BorderPane.setMargin(gameLanes, new javafx.geometry.Insets(25,0,35,0));
	        gamePane.setCenter(gameLanes);
	        VBox lanePane= new VBox();
	          lanePane.setSpacing(10);
	        
	        ArrayList<Lane> lanes= battle.getOriginalLanes();
	        
	        for(int i=0;i<lanes.size();i++){
	        	HBox laneComponents= new HBox();
	        	Pane laneTitansPane= new Pane();
	        	Pane titanpane= new Pane();
	        	laneTitansPane.setPrefWidth(1600);
	        	laneTitansPane.setStyle("-fx-background-color: Azure;");
	        	final int buttonIndex=i;
        		Button wall= new Button("wall, lane "+(i+1));
        		wall.setOnAction(new EventHandler <ActionEvent>(){
		        	 public void handle(ActionEvent event) {
		        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
		        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
		        	     mediaPlayer.setAutoPlay(true);
		        		 if(selectedLane==lanes.get(buttonIndex)){
		        			 selectedLane=null;
		        		 }
		        		 else{
		        			 selectedLane=lanes.get(buttonIndex);
		        		 }
		        		 prepareGameScene(stage);
		        	 }});
        		if(selectedLane==lanes.get(i)){
        			wall.setStyle("-fx-min-width: 150px; -fx-min-height: 60px;-fx-background-color: slateblue;");
        		}
        		else{
        		wall.setStyle("-fx-min-width: 150px; -fx-min-height: 60px;");
        		}
        		Pane lane= new Pane();
        		lane.setStyle("-fx-min-height: 30px;");
        		if(lanes.get(buttonIndex).isLaneLost()){
        			wall.setDisable(true);
        			addTitansToLane(lanes.get(i).getTitans(),laneTitansPane,true,titanpane);
        			addWeaponsToLane(lanes.get(i).getWeapons(),lane);
        			titanpane.setLayoutY(30);
        			laneTitansPane.getChildren().add(titanpane);
        			lane.setLayoutY(30);
        			laneTitansPane.getChildren().add(lane);
        		}
        		else{
        			addTitansToLane(lanes.get(i).getTitans(),laneTitansPane,false,titanpane);
        			addWeaponsToLane(lanes.get(i).getWeapons(),lane);
        			titanpane.setLayoutY(30);
        			laneTitansPane.getChildren().add(titanpane);
        			lane.setLayoutY(30);
        			laneTitansPane.getChildren().add(lane);
        		}
        		String tooltipString="Health: "+lanes.get(i).getLaneWall().getCurrentHealth()+"\nDanger Level: "+lanes.get(i).getDangerLevel();
        		if(!lanes.get(i).getWeapons().isEmpty()){
        			tooltipString+="\nAvailable Weapons: ";
        			for(int j=0;j<lanes.get(i).getWeapons().size();j++){
        				int weaponCode;
        				String weaponClass=(lanes.get(i).getWeapons().get(j).getClass().getSimpleName());
        				if( weaponClass.equals("PiercingCannon")){
        					weaponCode=PiercingCannon.WEAPON_CODE;
        				}
        				else if(weaponClass.equals("SniperCannon")){
        					weaponCode=SniperCannon.WEAPON_CODE;
        				}
        				else if(weaponClass.equals("VolleySpreadCannon")){
        					weaponCode=VolleySpreadCannon.WEAPON_CODE;
        				}
        				else {
        					weaponCode=WallTrap.WEAPON_CODE;
        				}
        				
        				WeaponRegistry weaponregistry=battle.getWeaponFactory().getWeaponShop().get(weaponCode);
        				tooltipString+="\n "+weaponregistry;
        			}
        		}
        		if(!lanes.get(i).getTitans().isEmpty()){
        			
        			tooltipString+="\nActive titans: ";
        			PriorityQueue<Titan> titans= lanes.get(i).getTitans();
        			Object[] titansArray= titans.toArray();
            				 for(int k=0;k<titansArray.length;k++){
                 				Titan titan=(Titan)titansArray[k];
            				 	String name= titan.getClass().getSimpleName();
            				 
            		            String titanName;
            		            if(name.equals("PureTitan")){
            		            	titanName="Pure Titan";
            		            }
            		            else if(name.equals("ArmoredTitan")){
            		            	titanName="Armored Titan";
            		            }
            		            else if(name.equals("ColossalTitan")){
            		            	titanName="Colossal Titan";
            		            }
            		            else{
            		            	titanName="Abnormal Titan";
            		            }
            		            tooltipString+="\n "+titanName;
            			 }
        		
        			}	
        		Tooltip wallToolTip= new Tooltip(tooltipString);
	            wall.setTooltip(wallToolTip);
	            
        		laneComponents.getChildren().add(wall);
        		laneComponents.getChildren().add(laneTitansPane);
        		lanePane.getChildren().add(laneComponents);
        	}
	        	
	        gameLanes.getChildren().add(lanePane);
	        
	        
	        BackgroundSize size= new BackgroundSize(1800,900,false,false,false,true);
	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/R (1).jpeg");
	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
	        Background background= new Background(backgroundimage);
	        gamePane.setBackground(background);
	        if(battle.isGameOver()==true){
	        	endPopup(stage);
	        }
	        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	        gameScene= new Scene(gamePane,1800,900);
        	stage.setScene(gameScene);
        	stage.show();
        	
	        } catch (Exception e) {
			e.printStackTrace();
			openPopup(e);
		}
        
	}

		private void openPopup(Exception e) {
	        Stage popupStage = new Stage();
	        popupStage.setTitle("An Error has Occurred");

	        StackPane popupRoot = new StackPane();
	        Label message= new Label(e.getMessage());
	        StackPane.setAlignment(message, Pos.CENTER);
	        popupRoot.getChildren().add(message);
	        Button button= new Button("Close Popup");
	        button.setOnAction(new EventHandler <ActionEvent>(){
	        	 public void handle(ActionEvent event) {
	        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
	        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
	        	     mediaPlayer.setAutoPlay(true);
	        		 popupStage.close();
	        	 }});
	        StackPane.setAlignment(button, Pos.BOTTOM_CENTER);
	        popupRoot.getChildren().add(button);

	        popupStage.setScene(new Scene(popupRoot, 400, 150));
	        popupStage.show();
	    }
		private void endPopup(Stage stage) {
	        Stage popupStage = new Stage();
	        popupStage.setTitle("GAME OVER");
	        StackPane popupRoot = new StackPane();
	        Label over= new Label("YOU LOST!");
	        Label message= new Label("score= "+battle.getScore());
	        StackPane.setAlignment(over,Pos.TOP_CENTER);
	        popupRoot.getChildren().add(over);
	        StackPane.setAlignment(message, Pos.CENTER);
	        popupRoot.getChildren().add(message);
	        
	        Button button= new Button("Start Again");
	        button.setOnAction(new EventHandler <ActionEvent>(){
	        	 public void handle(ActionEvent event) {
	        		 Media media = new Media(new File("C:/Users/janah/OneDrive/Pictures/SELECT06.WAV").toURI().toString());
	        	     MediaPlayer mediaPlayer = new MediaPlayer(media);
	        	     mediaPlayer.setAutoPlay(true);
	        		stage.setScene(welcomeScene);
	        		stage.show();
	        	 }});
	        StackPane.setAlignment(button, Pos.BOTTOM_CENTER);
	        popupRoot.getChildren().add(button);
	        popupStage.setScene(new Scene(popupRoot, 500, 350));
	        popupStage.show();
	    }
		private void addWeaponsToLane(ArrayList<Weapon> weapons,Pane lane){
			int plus=10;
			for(int i=0;i<weapons.size();i++){
				plus=plus+40;
				Weapon weapon= weapons.get(i);
				Button weaponButton= new Button();
				String name= weapon.getClass().getSimpleName();
	            if(name.equals("PiercingCannon")){
	            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
	    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/piercing cannon.jpeg");
	    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
	    	        Background background= new Background(backgroundimage);
	    	        weaponButton.setBackground(background);
	            }
	            else if(name.equals("SniperCannon")){
	            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
	    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/sniper (1).jpeg");
	    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
	    	        Background background= new Background(backgroundimage);
	    	        weaponButton.setBackground(background);
	            }
	            else if(name.equals("VolleySpreadCannon")){
	            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
	    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/volley.jpeg");
	    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
	    	        Background background= new Background(backgroundimage);
	    	        weaponButton.setBackground(background);
	            }
	            else{
	            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
	    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/wall trap.jpeg");
	    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
	    	        Background background= new Background(backgroundimage);
	    	        weaponButton.setBackground(background);
	            }
				weaponButton.setPrefSize(30, 30);
				weaponButton.setLayoutX(plus);
				lane.getChildren().add(weaponButton);
			}
		}
		private void addTitansToLane(PriorityQueue<Titan> titans,Pane pane,boolean laneLost,Pane titanLane){
				Object[] titansArray= titans.toArray();
				for(int i=0;i<titansArray.length;i++){
					Titan titan=(Titan)titansArray[i];
					Button titanButton= new Button();
					String tooltipString="currentHealth: "+titan.getCurrentHealth()+"\nDifference in height: "+titan.getHeightInMeters()+"\nDistanace from base: "+titan.getDistance()+"\nDistance moved: "+titan.getSpeed();
					Tooltip titanToolTip= new Tooltip(tooltipString);
					titanButton.setTooltip(titanToolTip);
					String name= titan.getClass().getSimpleName();
		            if(name.equals("PureTitan")){
		            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
		    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/pure titan.jpeg");
		    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		    	        Background background= new Background(backgroundimage);
		    	        titanButton.setBackground(background);
		            }
		            else if(name.equals("ArmoredTitan")){
		            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
		    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/armored (1).jpeg");
		    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		    	        Background background= new Background(backgroundimage);
		    	        titanButton.setBackground(background);
		            }
		            else if(name.equals("ColossalTitan")){
		            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
		    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/colossal titan.jpeg");
		    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		    	        Background background= new Background(backgroundimage);
		    	        titanButton.setBackground(background);
		            }
		            else{
		            	BackgroundSize size= new BackgroundSize(30,30,false,false,false,true);
		    	        Image image= new Image("file:///C:/Users/janah/OneDrive/Pictures/abnormal.jpeg");
		    	        BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		    	        Background background= new Background(backgroundimage);
		    	        titanButton.setBackground(background);
		            }
					if(laneLost==true){
						titanButton.setDisable(true);
					}
					titanButton.setPrefSize(30, 30);
					titanButton.setLayoutX((titan.getDistance()*(pane.getPrefWidth()-titanButton.getPrefWidth()))/battle.getTitanSpawnDistance());
					
					pane.getChildren().add(titanButton);
				}
						
		}      
}
