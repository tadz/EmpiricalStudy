package pl.tadz.matrix.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;

import pl.tadz.matrix.Matrix;

public class Capture extends JFrame {

	private static final long serialVersionUID = 1L;
	Container content;

	public Capture() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);

		content = getContentPane();
		content.setSize(800, 600);

		content.setLayout(new BorderLayout());
		content.setBackground(Color.white);
		
		content.add(new Matrix());
	}
}
