package com.Railway.Plotting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.ZoneView;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class North extends JFrame implements ActionListener, MouseListener {

	JPanel p1, panel;
	JLabel bckl;
	JTextField source, dest;
	static JLabel src, targ, blocked;
	static int[][] zoneMatrix;
	int co[], block[];
	static Graphics g = null;
	final static int N = 36;
	final static int IN = 9999999;
	static HashMap<String, Integer> stationIndex;// for indexing station->number
	static int laty[], lonx[], SOURCE, DESTN;// for storing the latitude &
												// longitude
	static int path[], count,count1;// for storing the path
	static boolean hasPath, isBlocked, wasSearched;
	static int currMatrix[][] = new int[N][N];// Doing all the manipulations in
												// this matrix

	static ArrayList<Integer> blockSt1, blockSt2;// Keeping track of Blocked
	Graphics2D g3; // Route
	static String stName[];
	int width, height;
	static JComboBox<String> srctxt, desttxt;
	JButton findPath;
	JButton Block, unBlock;
	GetStationList stlist = new GetStationList();
	JPanel bckpnl;
	private Color lncolor = Color.gray;
	boolean found = false, isRouteBlocked = false;
	JLabel menubar, distance, close;
	JFrame frame;

	// constructor
	public North() {
		// Plot();
		// TODO Auto-generated constructor stub
		String st[] = { "hey", "khan", "hello" };

		setTitle("Railway Zonal Map Of India | East");

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.width = width;
		this.height = height;

		makeUI();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setVisible(true);

	}

	public void makeUI() {

		JPanel navbar = new JPanel();

		navbar.setBounds(0, 0, width, (int) (height * 0.05));
		navbar.setBackground(new Color(10, 10, 10, 200));

		JLabel head = new JLabel("NORTH ZONE");
		head.setBounds(30, 0, 200, 40);
		head.setFont(new Font("segoe ui light", Font.PLAIN, 17));
		head.setForeground(Color.white);
		navbar.add(head);
		add(navbar);

		bckpnl = new JPanel();
		bckpnl.setBounds(0, (int) (height * 0.1), width, (int) (height * 0.8));
		bckpnl.setLayout(null);
		bckpnl.setBackground(Color.decode("#e8e8e5"));

		ImageIcon bckicon = new ImageIcon("back.png");
		bckl = new JLabel();
		bckl.setIcon(bckicon);
		bckl.setBounds(15, 42, 30, 30);
		bckl.addMouseListener(this);
		add(bckl);
		add(bckpnl);

		ImageIcon menu = new ImageIcon("menu.png");
		menubar = new JLabel();
		menubar.setIcon(menu);
		menubar.setBounds(70, 40, 40, 35);
		menubar.addMouseListener(this);
		menubar.setCursor(getCursor().getPredefinedCursor(HAND_CURSOR));

		add(menubar);
	}

	private void makeInnerWindow() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.decode("#2593E5"));

		JPanel panel = new JPanel();
		panel.setBounds(0, 300, 400, 300);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.add(panel);

		ImageIcon cross = new ImageIcon("cross.png");
		close = new JLabel();
		close.setIcon(cross);
		close.setToolTipText("Source");
		close.setBounds(350, 10, 50, 50);
		close.addMouseListener(this);
		close.setCursor(getCursor().getPredefinedCursor(HAND_CURSOR));
		frame.add(close);

		ImageIcon src = new ImageIcon("src.png");
		JLabel lab = new JLabel();
		lab.setIcon(src);
		lab.setToolTipText("Source");
		lab.setBounds(40, 100, 32, 82);
		frame.add(lab);

		ImageIcon dest = new ImageIcon("dest.png");
		JLabel lab1 = new JLabel();
		lab1.setIcon(dest);
		lab1.setToolTipText("Destination");
		lab1.setBounds(40, 180, 32, 82);
		frame.add(lab1);

		srctxt = new JComboBox(stlist.NStationList());
		srctxt.setBounds(85, 125, 200, 30);
		srctxt.setBackground(Color.decode("#ffffff"));
		srctxt.setEditable(true);
		Object child = srctxt.getAccessibleContext().getAccessibleChild(0);
		BasicComboPopup popup = (BasicComboPopup) child;
		JList list = popup.getList();
		list.setBackground(Color.white);
		list.setSelectionBackground(Color.decode("#88bae8"));
		JTextField tf = ((JTextField) srctxt.getEditor().getEditorComponent());
		tf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.gray));

		srctxt.setFocusable(false);
		srctxt.setMaximumRowCount(5);
		srctxt.setFont(new Font("segoe ui", Font.PLAIN, 15));
		frame.add(srctxt);

		desttxt = new JComboBox(stlist.NStationList());
		desttxt.setBounds(85, 205, 200, 30);
		desttxt.setBackground(Color.decode("#ffffff"));
		desttxt.setEditable(true);
		child = desttxt.getAccessibleContext().getAccessibleChild(0);
		popup = (BasicComboPopup) child;
		list = popup.getList();
		list.setBackground(Color.white);
		list.setSelectionBackground(Color.decode("#88bae8"));
		tf = ((JTextField) desttxt.getEditor().getEditorComponent());
		tf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.gray));

		desttxt.setMaximumRowCount(5);
		desttxt.setFont(new Font("segoe ui", Font.PLAIN, 15));
		desttxt.setFocusable(false);
		frame.add(desttxt);

		Block = new JButton("Block");
		Block.setBackground(Color.BLACK);
		Block.setForeground(Color.WHITE);
		Block.setBounds(145, 50, 100, 30);
		Block.addActionListener(this);
		panel.add(Block);

		unBlock = new JButton("UnBlock");
		unBlock.setBackground(Color.BLACK);
		unBlock.setForeground(Color.WHITE);
		unBlock.setBounds(255, 50, 100, 30);
		unBlock.addActionListener(this);
		panel.add(unBlock);
		
		findPath = new JButton("Find Path");
		findPath.setBackground(Color.BLACK);
		findPath.setForeground(Color.WHITE);
		findPath.setBounds(35, 50, 100, 30);
		findPath.addActionListener(this);
		panel.add(findPath);

		distance = new JLabel("Shortest Distance : 0km");
		distance.setForeground(Color.WHITE);
		distance.setBounds(115, 250, 150, 30);
		frame.add(distance);

		
		ImageIcon lineDetails=new ImageIcon("Untitled.png");
		JLabel index=new JLabel();
		index.setIcon(lineDetails);
		index.setBounds(0,80,250,230);
		panel.add(index);
		
		frame.setSize(400, 600);
		frame.setAlwaysOnTop(true);
		frame.setLocation(0, 100);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public void paint(Graphics g) {

		super.paint(g);
		// String line1 = null;

		String zoneMatrixFile = "NorthZoneMatrix.txt";
		createConnection(zoneMatrixFile);

		RenderingHints rh1 = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g3 = (Graphics2D) g;
		g3.setRenderingHints(rh1);
		// The name of the file to open.
		String fileName = "North.txt";
		String juncname = "north_Jn.txt";
		String line1 = null;
		plotJunctions(juncname, g3);
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			stationIndex = new HashMap<>();

			laty = new int[N];
			lonx = new int[N];
			stName = new String[N];
			int i = 0;
			Graphics g1 = g3;
			g1.setColor(Color.red);
			g1.fillOval((int) ((83.37 - 79.66) * (width / 8.43)- 4),
					(int) ((28.42 - 26.76) * (height / 4.11)- 4), 15, 15);
			g1.setFont(new Font("segoe ui light", Font.BOLD, 15));
			g1.drawString("Gorakhpur",
					(int) ((83.37 - 79.66) * (width / 8.43)),
					(int) ((28.42 - 26.76) * (height / 4.11)) - 8);

			g3.setFont(new Font("arial", Font.PLAIN, 10));

			String[] token;
			while ((line1 = bufferedReader.readLine()) != null) {

				// \\s+ means any number of whitespaces between tokens
				token = line1.split("\\s+");
				float lat = Float.parseFloat(token[0]);

				float lon = Float.parseFloat(token[1]);
				String station = token[2];
				// System.out.println(tokens[2]);
				stName[i] = station;
				stationIndex.put(station, i);
				laty[i] = (int) ((28.42 - lat) * (height / 4.11));

				g3.setColor(new Color(50, 55, 50, 255)); // 13.71

				lonx[i] = (int) ((lon - 79.66) * (width / 8.43));// 35
				g3.fillOval(lonx[i], laty[i], 7, 7);

				// System.out.println(distance);

				g3.drawString(station, lonx[i] + 20, laty[i] + 8);

				i++;
				/*
				 * for (int j = 0; j < 36; j++) { zoneMatrix[l][j] =
				 * Integer.parseInt(token[j]);
				 * System.out.println(Integer.parseInt(token[j])); } l++;
				 */

			}
			// Always close files.
			// bufferedReader.close();
			if (isRouteBlocked == false) {
				for (int a = 0; a < N; a++) {
					for (int j = 0; j < N; j++) {
						currMatrix[a][j] = zoneMatrix[a][j];

						if (a == j)// if diagonal then leave it to 0
							continue;
						if (currMatrix[a][j] == 0)// replace zero with IN=99999
							currMatrix[a][j] = IN;

					}

				}
			}

			for (i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (zoneMatrix[i][j] != 0) {
						g3.setColor(Color.decode("#999999"));
						g3.drawLine(lonx[i] + 2, laty[i] + 2, lonx[j] + 2,
								laty[j] + 2);

					}
				}
			}

			if (found == true) {

			 
					for (int o = 0; o < count - 1; o++) {
						g3.setColor(Color.GREEN);
						g3.setStroke(new BasicStroke(4));
						g3.drawLine(lonx[co[o]] + 2, laty[co[o]] + 2,
								lonx[co[o + 1]] + 2, laty[co[o + 1]] + 2);
						// System.out.println(lonx[co[o]] + "   " +
						// laty[co[o]]);
						System.out.print(co[o] + "  ");

					}
				
			}
			if (isRouteBlocked == true) {

				for (int o = 0; o < count1- 1; o++) {
					g3.setColor(Color.RED);
					g3.setStroke(new BasicStroke(2));
					g3.drawLine(lonx[block[o]] + 2, laty[block[o]] + 2,
							lonx[block[o + 1]] + 2, laty[block[o + 1]] + 2);
					// System.out.println(lonx[co[o]] + "   " + laty[co[o]]);

				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + zoneMatrixFile + "'");
		} catch (IOException ex1) {
			System.out.println("Error reading file '" + zoneMatrixFile + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	void createConnection(String zoneMatrixFile) {
		try {
			String line1 = null;
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(zoneMatrixFile);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// stationIndex = new HashMap<>();

			String[] token;
			int l = 0;
			zoneMatrix = new int[N][N];
			while ((line1 = bufferedReader.readLine()) != null) {

				// \\s+ means any number of whitespaces between tokens
				token = line1.split("\\s+");

				// System.out.println(tokens[2]);
				for (int j = 0; j < N; j++) {
					zoneMatrix[l][j] = Integer.parseInt(token[j]);

				}
				l++;

			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + zoneMatrixFile + "'");
		} catch (IOException ex1) {
			System.out.println("Error reading file '" + zoneMatrixFile + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	public void plotJunctions(String juncname, Graphics2D g3) {
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(juncname);
			String line = null;
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			stationIndex = new HashMap<>();

			laty = new int[N];
			lonx = new int[N];
			stName = new String[N];
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {

				// \\s+ means any number of whitespaces between tokens
				String[] tokens = line.split("\\s+");
				float lat = Float.parseFloat(tokens[0]);

				float lon = Float.parseFloat(tokens[1]);

				// stationIndex.put(station, i);
				laty[i] = (int) ((24.8 - lat) * (height / 2.72));// 30instead
				// of
				g3.setColor(new Color(0, 155, 255, 255)); // 13.71

				lonx[i] = (int) ((lon - 85.2) * (width / 3.9));// 35
				g3.drawOval(lonx[i] - 3, laty[i] - 3, 12, 12);

				i++;

				// System.out.println(line);
				// System.out.println(var_1+" "+var_2+" "+var_3);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {

		} catch (IOException ex1) {

			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		blockSt1 = new ArrayList<>();
		blockSt2 = new ArrayList<>();
		new North();

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == findPath) {
			int src = 0, dest = 0;

			if (srctxt.getSelectedItem().equals("---Select---")
					|| desttxt.getSelectedItem().equals("---Select---")) {
				JOptionPane.showMessageDialog(p1,
						"Please specify source and destination  !");

			} else {

				src = stationIndex.get(srctxt.getSelectedItem());
				dest = stationIndex.get(desttxt.getSelectedItem());

				if (src != dest) {
					SOURCE = src;
					DESTN = dest;
					System.out.println("src :" + src + " dest :" + dest);

					co = dijsktra(currMatrix, SOURCE, DESTN, "search");
					if (co[count] >= 777777) {
						JOptionPane.showMessageDialog(null,
								"No Alternate routes are available !");

					}else if (co[count] != IN) {
						distance.setText("Shortest Distance: " + co[count]
								+ "Km");
						found = true;
						repaint();
					} else
						distance.setText("");
					System.out.print("shortest Path::" + co[count]);
				} else
					JOptionPane.showMessageDialog(p1,
							"Source and Destination Cannot be Same !");
			}
		}
		if (a.getSource() == Block) {
			int src = 0, dest = 0;

			if (srctxt.getSelectedItem().equals("---Select---")
					|| desttxt.getSelectedItem().equals("---Select---")) {
				JOptionPane.showMessageDialog(p1,
						"Please specify source and destination  !");

			} else {

				src = stationIndex.get(srctxt.getSelectedItem());
				dest = stationIndex.get(desttxt.getSelectedItem());

				if (src != dest) {
					SOURCE = src;
					DESTN = dest;
					System.out.println("src :" + src + " dest :" + dest);

					block = dijsktra(currMatrix, SOURCE, DESTN, "blocked");
					if (count > 2) {

						JOptionPane.showMessageDialog(null,
								"There is no direct route to block");
					} else {
						count1=count;
						int n1 = block[0];
						int n2 = block[1];
						System.out.println(n1 + "   " + n2);
						currMatrix[n1][n2] = 777777;
						currMatrix[n2][n1] = 777777;
						System.out.println(currMatrix[24][20]);
						/*
						 * for (int i = 0; i < N; i++) { for (int j = 0; j < N;
						 * j++) { System.out.print(currMatrix[i][j] + "  "); }
						 * System.out.println(); }
						 */
						isRouteBlocked = true;
						found=false;
						repaint();
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Source and Destination Cannot be Same !");
			}
		}
		if (a.getSource() == unBlock) {
			isRouteBlocked = false;
			repaint();
		}
	}

	public static int[] dijsktra(int cost[][], int source, int target,
			String string) {

		int list[] = new int[50];
		System.out.println("With dijkstra ::" + source + "  " + target);
		int dist[], prev[], selected[];
		int i, m, min, start, d, j, noPath = 0;
		boolean hasNoPath = false;
		path = new int[N];
		dist = new int[N];
		prev = new int[N];
		selected = new int[N];
		System.out.println("Entered Djikstra");

		for (i = 0; i < N; i++) {
			dist[i] = IN;
			prev[i] = -1;
		}
		start = source;
		selected[start] = 1;
		dist[start] = 0;
		while (selected[target] == 0) {
			min = IN;
			m = 0;
			for (i = 0; i < N; i++) {
				d = dist[start] + cost[start][i];
				if (d < dist[i] && selected[i] == 0) {
					dist[i] = d;
					prev[i] = start;
				}
				if (min > dist[i] && selected[i] == 0) {
					min = dist[i];
					m = i;
				}
			}
			start = m;
			selected[start] = 1;
			System.out.println("Within while");
			noPath++;
			if (noPath > 100)// it catches the noPath logic
			{
				hasNoPath = true;
				break;
			}
		}
		System.out.println("Out of while");

		if (hasNoPath == true) {
			hasPath = false;
			if (string.equals("search"))
				JOptionPane.showMessageDialog(null,
						"There is no route available !");
			;

		} else {
			start = target;
			j = 0;
			count = 0;
			while (start != -1) {
				path[j] = start;
				start = prev[start];
				count++;
				j++;

				if (count > 50) {
					System.out.println("No path");
					break;
				}
				System.out.println("within while start");
			}
			System.out.println(count);
			// path[j]='\0';
			// strrev(path);
			for (j = 0; j < count; j++) {
				System.out.print(path[j] + "->");
				list[j] = path[j];
			}
			if (count >= 2)
				hasPath = true;

			// p1.repaint();
			// p1.revalidate();
			// p1.validate();
			// p1.updateUI();
			System.out.println("count is more than 2");

		}
		// System.out.println(source);
		list[count] = dist[target];
		return list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bckl) {
			new HeadQuarters();
			dispose();
		}
		if (e.getSource() == menubar) {
			makeInnerWindow();

		}
		if (e.getSource() == close) {
			frame.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
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
