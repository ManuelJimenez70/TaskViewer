package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Task;

public class Body extends JPanel {

	private static final int INSET_Y = 5;
	private static final int INSET_X = 10;
	private static final int MIN_SIZE = 60;
	private static final int MAX_COLOR = 255;
	private static final int RANDOM_COLOR_FACTOR = 0x1000000;
	private Random random;
	private ArrayList<Task> taskList;

	public Body() {
		this.taskList = new ArrayList<Task>();
		random = new Random();
		setBackground(Color.WHITE);
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList.clear();
		this.taskList.addAll(taskList);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		getGraphics2D(g2);
		paintBackground(g2);
		paintTasks(g2);
	}

	private void paintTasks(Graphics2D g) {
		int x = INSET_X, y = INSET_Y;
		int auxY = 0;
		for (Task task : taskList) {
			int size = task.getMemory() / 2;
			size = size>MIN_SIZE?size:MIN_SIZE;
			auxY = auxY < size ? size : auxY;
			if ((x + size) > getWidth()) {
				x = 10;
				y += auxY + auxY/10;
				auxY = 0;
			}
			Color c = new Color((int) (random.nextInt(RANDOM_COLOR_FACTOR)));
			g.setColor(c);
			g.fillOval(x, y, size, size);
			g.setColor(new Color(MAX_COLOR - c.getRed(), MAX_COLOR - c.getGreen(), MAX_COLOR - c.getBlue()));
			paintName(g, task.getName(), x, y, size);
			x += size + 10;
		}
	}

	private void paintName(Graphics2D g2, String name, int x, int y, int size) {
		g2.drawString(name, x + size/3, y+ size/2);
	}

	private void getGraphics2D(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
	}

	private void paintBackground(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		for (int i = 0; i < getWidth(); i++) {
			g.fillOval(random.nextInt(getWidth()), random.nextInt(getHeight()), 2, 2);
		}
	}

}
