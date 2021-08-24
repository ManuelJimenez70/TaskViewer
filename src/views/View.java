package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import models.Task;

public class View extends JFrame{

	private static final String TITLE_ICON = "/resources/images/titleIcon.png";
	private static final String TITTLE = "TaskTitle";
	private Header header;
	private Body body;
	
	
	public View(MouseMotionListener motionListener) {
		super();
		this.addMouseMotionListener(motionListener);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource(TITLE_ICON)));
		this.setTitle(TITTLE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents() {
		this.header = new Header();
		this.body = new Body();
		this.add(this.header,BorderLayout.NORTH);
		this.add(this.body,BorderLayout.CENTER);
	}

	public void updateUI(ArrayList<Task> tasks) {
		this.body.setTaskList(tasks);
		this.body.repaint();
	}
}
