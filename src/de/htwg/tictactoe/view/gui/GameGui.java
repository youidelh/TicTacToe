package de.htwg.tictactoe.view.gui;
/*
 * Copyright (c) 2010, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import de.htwg.tictactoe.controller.Controller;
import de.htwg.tictactoe.model.Messages;
import de.htwg.util.observer.IObserver;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

public class GameGui extends Application  implements IObserver {

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    final Cam camOffset = new Cam();
    final Cam cam = new Cam();
    public static Controller controller;
    final Shear shear = new Shear();
    public static StatusPanel statusPanel;
    private final int GRIDS_SIZE = 3;
    public GridPanel[] grids ;
    Scene scene;
    Stage stage;
    Group Appgroup;
    public GameGui() {
    }

    public void setController(Controller controller){
 	   GameGui.controller = controller;
    }
    public void init(Stage stage){
    	this.stage = stage;
        Appgroup = new Group();
        statusPanel = new StatusPanel();
        statusPanel.setText(Messages.MOVEMENT+controller.getStatus());
    }
    @Override
    public void start(final Stage stage) {
    	init(stage);
		controller.addObserver(this);
        stage.setTitle("tictactoe 3*3");
        camOffset.getChildren().add(cam);
        Appgroup.getChildren().addAll(camOffset, statusPanel);
        resetCam();
        scene = new Scene(Appgroup, 800, 600, true);
        scene.setFill(new RadialGradient(225, 0.85, 300, 300, 500, false,
                                         CycleMethod.NO_CYCLE, new Stop[]
                                         { new Stop(0f, Color.BLUE),
                                           new Stop(1f, Color.LIGHTBLUE) }));
        scene.setCamera(new PerspectiveCamera());
        cam.setScaleX(0.5);
        cam.setScaleY(0.5);
        
        cam.getChildren().addAll(setGrids());
        
        frameCam(stage, scene);
        
        /**
         * Set on key pressed
         */
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (KeyCode.X.equals(ke.getCode())) {
                    controller.exit();
                }
                if (KeyCode.R.equals(ke.getCode())) {
                    controller.reset();
                }
                if(KeyCode.D.equals(ke.getCode())){
                    cam.ry.setAngle(cam.ry.getAngle() - 5);
                }
                if(KeyCode.A.equals(ke.getCode())){
                    cam.ry.setAngle(cam.ry.getAngle() + 5);
                }
                if(KeyCode.W.equals(ke.getCode()) && cam.rx.getAngle() > -8){
                    cam.rx.setAngle(cam.rx.getAngle() - 5);
                }
                if(KeyCode.S.equals(ke.getCode()) && cam.rx.getAngle() < 47){
                    cam.rx.setAngle(cam.rx.getAngle() + 5);
                    System.out.println(cam.rx.getAngle());
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
	/**
	 * set Grids 
	 * @return
	 */
    public Group setGrids(){
    	Group gridsGroup = new Group();
        gridsGroup.getTransforms().add(shear);
        gridsGroup.setDepthTest(DepthTest.ENABLE);

        grids = new GridPanel[GRIDS_SIZE];
        int position = 0 ;

        for (int i = 0; i < GRIDS_SIZE; i++) {
        	grids[i] = new GridPanel(controller, 1.0, 1.0, i);
        	grids[i].setTranslateX(50);
        	grids[i].setTranslateZ(300);
        	grids[i].setTranslateY(position);
        	grids[i].setScaleX(50);
        	grids[i].setScaleZ(50);
        	grids[i].setScaleY(5);
        	position-= 15;
		}
        gridsGroup.getChildren().addAll(grids[0], grids[1], grids[2]);
        gridsGroup.setScaleX(2.5);
        gridsGroup.setScaleY(2.5);
        gridsGroup.setScaleZ(2.5);
        return gridsGroup;
    }
    /**
     * Frame Cam
     * @param stage
     * @param scene
     */
    public void frameCam(final Stage stage, final Scene scene) {
        setCamOffset(camOffset, scene);
        // cam.resetTSP();
        setCamPivot(cam);
        setCamTranslate(cam);
        setCamScale(cam, scene);
        
    }

   /**
    * Cam offset
    * @param camOffset
    * @param scene
    */
    public void setCamOffset(final Cam camOffset, final Scene scene) {
        double width = scene.getWidth();
        double height = scene.getHeight();
        camOffset.t.setX(width/2.0);
        camOffset.t.setY(height/2.0);
    }

    /**
     * Cam scale
     * @param cam
     * @param scene
     */
    public void setCamScale(final Cam cam, final Scene scene) {
        final Bounds bounds = cam.getBoundsInLocal();
        double width = scene.getWidth();
        double height = scene.getHeight();

        double scaleFactor = 1.0;
        double scaleFactorY = 1.0;
        double scaleFactorX = 1.0;
        if (bounds.getWidth() > 0.0001) {
            scaleFactorX = width / bounds.getWidth(); // / 2.0;
        }
        if (bounds.getHeight() > 0.0001) {
            scaleFactorY = height / bounds.getHeight(); //  / 1.5;
        }
        if (scaleFactorX > scaleFactorY) {
            scaleFactor = scaleFactorY;
        } else {
            scaleFactor = scaleFactorX;
        }
        cam.s.setX(scaleFactor);
        cam.s.setY(scaleFactor);
        cam.s.setZ(scaleFactor);
    }

    /**
     * Cam pivot
     * @param cam
     */
    public void setCamPivot(final Cam cam) {
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth()/2;
        final double pivotY = bounds.getMinY() + bounds.getHeight()/2;
        final double pivotZ = bounds.getMinZ() + bounds.getDepth()/2;
        cam.p.setX(pivotX);
        cam.p.setY(pivotY);
        cam.p.setZ(pivotZ);
        cam.ip.setX(-pivotX);
        cam.ip.setY(-pivotY);
        cam.ip.setZ(-pivotZ);
    }

    /**
     * Cam Translate
     * @param cam
     */
    public void setCamTranslate(final Cam cam) {
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth()/2;
        final double pivotY = bounds.getMinY() + bounds.getHeight()/2;
        cam.t.setX(-pivotX);
        cam.t.setY(-pivotY);
    }

    /**
     * Reset Cam
     */
    public void resetCam() {
        cam.t.setX(0.0);
        cam.t.setY(0.0);
        cam.t.setZ(0.0);
        cam.rx.setAngle(27.0);
        cam.ry.setAngle(-7.0);
        cam.rz.setAngle(0.0);
        cam.s.setX(1.25);
        cam.s.setY(1.25);
        cam.s.setZ(1.25);
        
        cam.p.setX(0.0);
        cam.p.setY(0.0);
        cam.p.setZ(0.0);

        cam.ip.setX(0.0);
        cam.ip.setY(0.0);
        cam.ip.setZ(0.0);

        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth() / 2;
        final double pivotY = bounds.getMinY() + bounds.getHeight() / 2;
        final double pivotZ = bounds.getMinZ() + bounds.getDepth() / 2;

        cam.p.setX(pivotX);
        cam.p.setY(pivotY);
        cam.p.setZ(pivotZ);

        cam.ip.setX(-pivotX);
        cam.ip.setY(-pivotY);
        cam.ip.setZ(-pivotZ);
    }
    
	public void update() {
		cam.getChildren().addAll(setGrids());
		statusPanel.setText(Messages.MOVEMENT+controller.getStatus());
	}
}
