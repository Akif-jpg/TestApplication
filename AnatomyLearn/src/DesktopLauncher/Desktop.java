package DesktopLauncher;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import PANELS.MainPanel;

public class Desktop {
	public static JFrame frame;
	public Desktop() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		
		CreateMenu menu;
		menu = new CreateMenu(null);
		
		MainPanel panel;
		panel = new MainPanel(menu);
		menu.setBarPanelMessager(panel);
		
		ImageIcon img = new ImageIcon("Assets/user-interface (2).png");
		
		frame = new JFrame("BAGÖ TEST PROGRAMI");
		frame.setBounds(50, 50, 670, 480);
		frame.setJMenuBar(menu.getMenu());
		frame.add(panel);
		frame.setIconImage(img.getImage());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

}
