package com.Railway.Plotting;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.*;

public class HeadQuarters extends JFrame implements MouseListener {

	JLabel guw, del, kol, mum, jai, alla, gor, chen, bhuba, haji, secund, hub,
			bilas, jabal;

	int width, height;

	double yfact;
	double xfact;

	double kollat = 37.06 - 22.57, kollon = 88.36 - 68.07;
	double mumlat = 37.06 - 19.08, mumlon = 72.88 - 68.07;
	double dellat = 37.06 - 28.61, dellon = 77.21 - 68.07;
	double kanlat = 37.06 - 8.08, kanlon = 77.53 - 68.07;
	double chenlat = 37.06 - 13.08, chenlon = 80.27 - 68.07;
	double guwlat = 37.06 - 26.14, guwlon = 91.73 - 68.07;
	double bhulat = 37.06 - 20.29, bhulon = 85.82 - 68.07;
	double hajilat = 37.06 - 27.69, hajilon = 76.45 - 68.07;
	double secundlat = 37.06 - 17.26, secundlon = 78.29 - 68.07;
	double gorakhlat = 37.06 - 26.76, gorakhlon = 83.37 - 68.07;
	double allalat = 37.06 - 25.43, allalon = 81.85 - 68.07;
	double jailat = 37.06 - 26.91, jailon = 75.79 - 68.07;
	double bilaslat = 37.06 - 22.07, bilaslon = 82.13 - 68.07;
	double hublat = 37.06 - 15.36, hublon = 75.12 - 68.07;
	double jaballat = 37.06 - 23.18, jaballon = 79.98 - 68.07;

	int a = 120, b = 30;

	public HeadQuarters() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.width = width;
		this.height = height;

		double yfact = height * 0.9 / 29.02;
		double xfact = (height * 0.885 / 28.55);
		this.xfact = xfact;
		this.yfact = yfact;

		makeGUI();

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.decode("#ffffff"));
		setLayout(null);
		setTitle("INDIAN RAILWAY'S ZONAL HEADQUARTER");
		setVisible(true);

	}

	public void makeGUI() {

		
		ImageIcon cover=new ImageIcon("SB.png");
		JLabel cov=new JLabel();
		cov.setIcon(cover);
		cov.setBounds(900, 60, 450, 550);
		add(cov);
		
		JPanel panel = new JPanel() {

			public void paintComponent(Graphics g) {

				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;

				RenderingHints rh = new RenderingHints(
						RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHints(rh);
				g2.setStroke(new BasicStroke(1));

				g2.setColor(Color.WHITE);
				g2.drawOval((int) (kollon * xfact) - 3,
						(int) (kollat * yfact) - 3, 11, 11);
				g2.fillOval((int) (kollon * xfact), (int) (kollat * yfact), 6,
						6);

				g2.drawOval((int) (jailon * xfact) - 3,
						(int) (jailat * yfact) - 3, 11, 11);

				g2.drawOval((int) (jailon * xfact) - 3,
						(int) (jailat * yfact) - 3, 11, 11);
				g2.fillOval((int) (jailon * xfact), (int) (jailat * yfact), 6,
						6);

				g2.drawOval((int) (dellon * xfact) - 3,
						(int) (dellat * yfact) - 3, 11, 11);
				g2.fillOval((int) (dellon * xfact), (int) (dellat * yfact), 6,
						6);

				g2.drawOval((int) (guwlon * xfact) - 3,
						(int) (guwlat * yfact) - 3, 11, 11);

				g2.fillOval((int) (guwlon * xfact), (int) (guwlat * yfact), 6,
						6);

				g2.drawOval((int) (mumlon * xfact) - 3,
						(int) (mumlat * yfact) - 3, 11, 11);
				g2.fillOval((int) (mumlon * xfact), (int) (mumlat * yfact), 6,
						6);

				g2.drawOval((int) (chenlon * xfact) - 3,
						(int) (chenlat * yfact) - 3, 11, 11);
				g2.fillOval((int) (chenlon * xfact), (int) (chenlat * yfact),
						6, 6);

				g2.drawOval((int) (hajilon * xfact) - 3,
						(int) (hajilat * yfact) - 3, 11, 11);
				g2.fillOval((int) (hajilon * xfact), (int) (hajilat * yfact),
						6, 6);

				g2.drawOval((int) (bhulon * xfact) - 3,
						(int) (bhulat * yfact) - 3, 11, 11);
				g2.fillOval((int) (bhulon * xfact), (int) (bhulat * yfact), 6,
						6);

				g2.drawOval((int) (secundlon * xfact) - 3,
						(int) (secundlat * yfact) - 3, 11, 11);
				g2.fillOval((int) (secundlon * xfact),
						(int) (secundlat * yfact), 6, 6);

				g2.drawOval((int) (gorakhlon * xfact) - 3,
						(int) (gorakhlat * yfact) - 3, 11, 11);
				g2.fillOval((int) (gorakhlon * xfact),
						(int) (gorakhlat * yfact), 6, 6);

				g2.drawOval((int) (jailon * xfact) - 3,
						(int) (jailat * yfact) - 3, 11, 11);
				g2.fillOval((int) (jailon * xfact), (int) (jailat * yfact), 6,
						6);

				g2.drawOval((int) (allalon * xfact) - 3,
						(int) (allalat * yfact) - 3, 11, 11);
				g2.fillOval((int) (allalon * xfact), (int) (allalat * yfact),
						6, 6);

				g2.drawOval((int) (hublon * xfact) - 3,
						(int) (hublat * yfact) - 3, 11, 11);
				g2.fillOval((int) (hublon * xfact), (int) (hublat * yfact), 6,
						6);

				g2.drawOval((int) (bilaslon * xfact) - 3,
						(int) (bilaslat * yfact) - 3, 11, 11);
				g2.fillOval((int) (bilaslon * xfact), (int) (bilaslat * yfact),
						6, 6);

				g2.drawOval((int) (jaballon * xfact) - 3,
						(int) (jaballat * yfact) - 3, 11, 11);
				g2.fillOval((int) (jaballon * xfact), (int) (jaballat * yfact),
						6, 6);
			}
		};

		panel.setBounds(100, 0, (int) (height * 0.885), (int) (height * 0.90));
		panel.setBackground(new Color(10, 10, 10, 0));
		panel.setLayout(null);

		add(panel);

		kol = new JLabel("Kolkata");
		kol.setBounds((int) (kollon * xfact), (int) (kollat * yfact), a, b);
		kol.setFont(new Font("arial", Font.PLAIN, 13));
		kol.addMouseListener(this);
		kol.setCursor(getCursor().getPredefinedCursor(HAND_CURSOR));
		panel.add(kol);

		del = new JLabel("Delhi");
		del.setBounds((int) (dellon * xfact), (int) (dellat * yfact), a, b);
		del.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(del);

		mum = new JLabel("Mumbai");
		mum.setBounds((int) (mumlon * xfact), (int) (mumlat * yfact), a, b);
		mum.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(mum);

		guw = new JLabel("Guwahati");
		guw.addMouseListener(this);
		guw.setCursor(getCursor().getPredefinedCursor(HAND_CURSOR));
		guw.setBounds((int) (guwlon * xfact), (int) (guwlat * yfact), a, b);
		guw.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(guw);

		jai = new JLabel("Jaipur");
		jai.setFont(new Font("arial", Font.PLAIN, 13));
		jai.setBounds((int) (jailon * xfact), (int) (jailat * yfact), a, b);
		panel.add(jai);

		alla = new JLabel("Allahabad");
		alla.setBounds((int) (allalon * xfact), (int) (allalat * yfact), a, b);
		alla.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(alla);

		gor = new JLabel("Gorakhpur");
		gor.setBounds((int) (gorakhlon * xfact), (int) (gorakhlat * yfact), a,
				b);
		gor.setFont(new Font("arial", Font.PLAIN, 13));
		gor.addMouseListener(this);
		gor.setCursor(getCursor().getPredefinedCursor(HAND_CURSOR));
		panel.add(gor);

		chen = new JLabel("Chennai");
		chen.setBounds((int) (chenlon * xfact), (int) (chenlat * yfact), a, b);
		chen.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(chen);

		bhuba = new JLabel("Bhubaneshwar");
		bhuba.setBounds((int) (bhulon * xfact), (int) (bhulat * yfact), a, b);
		bhuba.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(bhuba);

		haji = new JLabel("Hajipur");
		haji.setBounds((int) (hajilon * xfact), (int) (hajilat * yfact), a, b);
		haji.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(haji);

		secund = new JLabel("Secunderabad");
		secund.setBounds((int) (secundlon * xfact), (int) (secundlat * yfact),
				a, b);
		secund.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(secund);

		hub = new JLabel("Hubli");
		hub.setBounds((int) (hublon * xfact), (int) (hublat * yfact), a, b);
		hub.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(hub);

		bilas = new JLabel("Bilaspur");
		bilas.setBounds((int) (bilaslon * xfact), (int) (bilaslat * yfact), a,
				b);
		bilas.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(bilas);

		jabal = new JLabel("Jabalpur");
		jabal.setBounds((int) (jaballon * xfact), (int) (jaballat * yfact), a,
				b);
		jabal.setFont(new Font("arial", Font.PLAIN, 13));
		panel.add(jabal);

		ImageIcon icon = new ImageIcon("india.png");
		Image image1 = icon.getImage().getScaledInstance((int) (895),
				(int) (height * 0.9), Image.SCALE_SMOOTH);
		icon = new ImageIcon(image1);
		JLabel l = new JLabel();
		l.setIcon(icon);
		l.setBounds(-15, -40, 1010, height);
		add(l);
	}

	public static void main(String[] args) {

		new HeadQuarters();
	}

	@Override
	public void mouseClicked(MouseEvent a) {
		// TODO Auto-generated method stub

		if (a.getSource() == guw) {
			new NorthEast();
			//dispose();
		}
		
		if (a.getSource() == kol) {
			new Eastern();
			//dispose();
		}
		

		if (a.getSource() == gor) {
			new North();
			//dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
