package DesktopLauncher;

import java.awt.HeadlessException;import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import FileSystem.Filewalker;
import Messager.QuestBarMessager;


public class CreateQuest extends JFrame {//Projeyi daha hýzlý bitirmek adýna askýya aldým
	QuestBarMessager qbm;
	public CreateQuest(String title,QuestBarMessager qbmiz) throws HeadlessException {
		super(title);
		JTextArea JTextArea = new JTextArea(new Filewalker().walk("Test\\Image"));
		JTextArea.setEditable(false);
		JScrollPane pane = new JScrollPane(JTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(pane);
		this.setBounds(30, 30, 400, 500);
				
				
	}
	
	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		super.setVisible(b);
		if(b) {
			JOptionPane.showMessageDialog(this, "ölüm");
			super.setVisible(false);
		}
	}

}
