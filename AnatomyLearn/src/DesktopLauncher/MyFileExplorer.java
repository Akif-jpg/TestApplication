package DesktopLauncher;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Messager.ExplorerBarMessager;

public class MyFileExplorer extends JFrame{
	JTextArea text;
	JButton buton;
	JTextField search;
	JScrollPane pane;
	ExplorerBarMessager ebm;
	public MyFileExplorer(ExplorerBarMessager ebmiz) {
		// TODO Auto-generated constructor stub
		 super("Test Gezgini");
		 setLayout(new FlowLayout());
		 
		 ebm = ebmiz;
		 
		 buton = new JButton("Open");
		 search = new JTextField(20);
		 text = new JTextArea(17,30);
		 text.setEditable(false);
		 
		 pane = new JScrollPane(text);
         pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         
         buton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Ýþin devamýnda buraya aramayla ilgili bazý özellikleri ekleyeceksin...
				ebm.TestIndex(search.getText());
			}
		});
         
         this.add(search);
         this.add(buton);
         this.add(pane);
               
	}   
	
	public void showFile(String FileList) {
		text.setText(FileList);
	}

}
