package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel{
	
	private static final URL HEADER_ICON = View.class.getResource("/resources/images/headerIcon.png");
	private JLabel txtheader;

	public Header() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500,40));
		setBackground(Color.WHITE);
		initComponents();
	}

	private void initComponents() {
		this.txtheader = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(HEADER_ICON)));
		this.add(txtheader, BorderLayout.WEST);
	}

}
