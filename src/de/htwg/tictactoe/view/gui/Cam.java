package de.htwg.tictactoe.view.gui;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

class Cam extends Group {
	
	
    public Translate t;
    public Translate p;
    public Translate ip;
    public Rotate rx;
    Rotate ry; 
    Rotate rz;
    Scale s;
    public Cam() { 
    	super(); 
    	init();
    	getTransforms().addAll(t, p, rx, rz, ry, s, ip); 
	}
    
    public void init(){
    	t  = new Translate();
        p  = new Translate();
        ip = new Translate();
        rx = new Rotate();
        rx.setAxis(Rotate.X_AXIS);
        ry = new Rotate();
        ry.setAxis(Rotate.Y_AXIS); 
        rz = new Rotate();
        rz.setAxis(Rotate.Z_AXIS);
        s = new Scale();
    }
}
