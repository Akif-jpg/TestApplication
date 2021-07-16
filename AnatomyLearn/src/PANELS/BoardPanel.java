package PANELS;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;



public class BoardPanel extends JPanel implements MouseMotionListener,MouseListener{
	JButton BlackPencil;
	JButton RedPencil;
	JButton Eraser;
	
	boolean isBlack;
	boolean isRed;
	boolean isErase;
	
	BufferedImage Bckimg;
	BufferedImage img;
	Graphics g;
	Graphics g2;
	public BoardPanel(BufferedImage img) {
		// TODO Auto-generated constructor stub
		this.img = img;
		Bckimg = new BufferedImage(1920, 1080,BufferedImage.TYPE_INT_RGB);
		g = Bckimg.getGraphics();	
		if(img != null) {
			g2 = this.img.getGraphics();
			this.g.drawImage(img, 0, 0, null);
		}
		isBlack = false;
		isRed = false;
		isErase = false;
		this.setLayout(new FlowLayout());
		BlackPencil = new JButton(new ImageIcon("Assets/edit.png"));
		RedPencil = new JButton(new ImageIcon("Assets/edit-2-xxl.png"));
		Eraser = new JButton(new ImageIcon("Assets/eraser.png"));
		
		BlackPencil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isBlack = true;
				isRed = false;
				isErase = false;
			}
		});
		
		RedPencil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isBlack = false;
				isRed = true;
				isErase = false;
			}
		});
		
		Eraser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isBlack = false;
				isRed = false;
				isErase = true;
			}
		});
		
		this.add(BlackPencil);
		this.add(RedPencil);
		this.add(Eraser);
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(Bckimg, 0, 0, null);
		
	}

	Point P2 = new Point();
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point P1 = new Point(e.getX(), e.getY());
		if(e.getButton() == 0) {
			if(isBlack) {
				g.setColor(Color.WHITE);
				g.drawLine(P1.x, P1.y, P2.x, P2.y);
				repaint();
			}else if(isRed) {
				g.setColor(Color.RED);
				g.drawLine(P1.x, P1.y, P2.x, P2.y);
				repaint();
			}else if(isErase) {
				
				if(img != null&&new Rectangle(0,0,img.getWidth(), img.getHeight()).contains(new Rectangle(e.getX()-25, e.getY()-25, 50, 50))){
					for(int x = e.getX()-25;x < e.getX()+25;x++) {
						for(int y = e.getY()-25;y < e.getY()+25;y++) {
							Bckimg.setRGB(x, y, img.getRGB(x, y));
						}
					}
					repaint();
					
				}else {
					g.setColor(Color.BLACK);
					g.fillRect(e.getX()-25, e.getY()-25, 50, 50);
					repaint();
				}
			}
		}
		P2.setLocation(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
				
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) {
			P2.setLocation(e.getX(),e.getY());
			System.out.println("sað click");
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
