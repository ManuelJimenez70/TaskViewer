package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import models.MyModel;
import views.View;

public class Controller implements MouseMotionListener {

	private View view;
	private MyModel model;

	public Controller() {
		this.model = new MyModel();
		model.start();
		this.view = new View(this);
		Timer t = new Timer((int) TimeUnit.SECONDS.toMillis(3), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.updateUI(model.getTasks());
			}
		});
		t.start();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		this.model.writeNewPosition(e.getPoint().x, e.getPoint().y);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}


}
