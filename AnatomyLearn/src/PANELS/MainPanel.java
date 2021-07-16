package PANELS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Messager.BarPanelMessager;
import Messager.PanelBarMessager;

public class MainPanel extends JPanel implements MouseListener,BarPanelMessager{
	final JPopupMenu popup = new JPopupMenu();
	JMenuItem nextQuest;
	JMenuItem backQuest;
	JMenuItem addQuest;
	JMenuItem copy_inBoard;
	
	Image background;//Arka plan resmimiz
	Image background2;
	

	PanelBarMessager CreateMenu;
	public MainPanel(PanelBarMessager msgr) {
		
		this.setLayout(null);
		
		CreateMenu = msgr;
		
		nextQuest = new JMenuItem("Sonraki Soru(D)", new ImageIcon("Assets/next.png"));
		nextQuest.setMnemonic(KeyEvent.VK_D);
		nextQuest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateMenu.nextQuest();
			}
		});
		
		backQuest = new JMenuItem("Önceki Soru(A)", new ImageIcon("assets/back.png"));
		backQuest.setMnemonic(KeyEvent.VK_A);
		backQuest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateMenu.backQuest();
			}
		});
		
		addQuest = new JMenuItem("Yeni Soru Ekle(P)", new ImageIcon("assets/plus.png"));
		addQuest.setMnemonic(KeyEvent.VK_P);
		addQuest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("yeni soru eklendi");
				CreateMenu.CreateQuest();
			}
		});
		
		copy_inBoard = new JMenuItem("Resmi Tahtaya Kopyala(C)", new ImageIcon("assets/presentation.png"));
		copy_inBoard.setMnemonic(KeyEvent.VK_C);
		copy_inBoard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame("Yazý Tahtasý");
				try {
					if(img == null)	
						frame.add(new BoardPanel(ImageIO.read(new File("Assets/ddx.jpg"))));
					else
						frame.add(new BoardPanel(img));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//Burayý daha sonrasýnda deðiþebilen resimler alabilecek þekilde düzenleyeceðiz
				frame.setBounds(50, 50, 300, 400);
				frame.setVisible(true);
			
			}
		});
		
		popup.add(nextQuest);
		popup.add(backQuest);
		popup.add(addQuest);
		popup.add(copy_inBoard);
		this.add(popup);
		this.addMouseListener(this);
				
		try {
			background = ImageIO.read(new File("Assets/technical-support.png"));
			background2 = ImageIO.read(new File("Assets/ddx.jpg"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}

	public MainPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public MainPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public MainPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	private void showPopupMenu(MouseEvent e) {
		   if (e.isPopupTrigger()) {
               popup.show(e.getComponent(),
                       e.getX(), e.getY());
           }
       
	}
	//implements metodlar
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		showPopupMenu(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		showPopupMenu(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	BufferedImage img = null;
	String questText;
	String A,B,C,D,E;
	@Override
	public void showTest(String imgPathName, String questText, String A, String B, String C, String D, String E) {
		// TODO Auto-generated method stub
		this.questText = questText;
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
		this.E = E;
		if(imgPathName != null && !imgPathName.equals("")) {
			try {
				img = ImageIO.read(new File(imgPathName));
			}catch(IOException e) {
				System.out.println("Geçerli resim bulunamadý");
				img = null;
			}
		}
		
		System.out.println("göster komutu geldi: "+questText);
		
		repaint();
	}
	
	@Override
	public void QuestAnswered(boolean isTrue) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(background2, 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		g.setColor(Color.RED);
		if(img != null) {
			g.drawImage(img, this.getWidth()*1/8, this.getHeight()*1/8, this.getWidth()*6/8, this.getHeight()*5/8, null);
		}
		
		if(questText != null) {
			Font font = new Font("LucidaSans", Font.PLAIN, 20);
			AttributedString atString= new AttributedString(questText);
			atString.addAttribute(TextAttribute.FONT, font);
			g.drawString(atString.getIterator(),this.getWidth()*1/8,this.getHeight()*3/5);
			if(A != null) {
				g.setColor(Color.BLUE);
				AttributedString optionA = new AttributedString(A);
				optionA.addAttribute(TextAttribute.FONT, font);
				g.drawString(optionA.getIterator(), this.getWidth()*1/10, this.getHeight()*5/7);
			}
			if(B != null) {
				g.setColor(Color.BLUE);
				AttributedString optionB = new AttributedString(B);
				optionB.addAttribute(TextAttribute.FONT, font);
				g.drawString(optionB.getIterator(), this.getWidth()*3/10, this.getHeight()*5/7);
			}
			if(C != null) {
				g.setColor(Color.BLUE);
				AttributedString optionC = new AttributedString(C);
				optionC.addAttribute(TextAttribute.FONT, font);
				g.drawString(optionC.getIterator(), this.getWidth()*5/10, this.getHeight()*5/7);
			}
			if(D != null) {
				g.setColor(Color.BLUE);
				AttributedString optionD = new AttributedString(D);
				optionD.addAttribute(TextAttribute.FONT, font);
				g.drawString(optionD.getIterator(), this.getWidth()*7/10, this.getHeight()*5/7);
			}
			if(E != null) {
				g.setColor(Color.BLUE);
				AttributedString optionE = new AttributedString(E);
				optionE.addAttribute(TextAttribute.FONT, font);
				g.drawString(optionE.getIterator(), this.getWidth()*9/10, this.getHeight()*5/7);
			}
		
		}
		
		
	}
	

}
