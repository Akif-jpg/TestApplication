package DesktopLauncher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import FileSystem.Option;
import FileSystem.OpenTest;
import FileSystem.Quest;
import Messager.BarPanelMessager;
import Messager.ExplorerBarMessager;
import Messager.PanelBarMessager;
import Messager.QuestBarMessager;
import PANELS.BoardPanel;

public class CreateMenu implements PanelBarMessager,ExplorerBarMessager,QuestBarMessager{
	JMenuBar menuBar;
	JMenu File,Windows,AboutUs,HelpMe;
	JMenuItem openFile,createFile,exitThis,drawBoard,Hakk�m�zda,Yard�m;
	BarPanelMessager MainPanel;
	MyFileExplorer explorer;
	CreateQuest quester;
	int ActiveSoru = 0;
	OpenTest TestExplorer;
	public CreateMenu(BarPanelMessager msgr) {
		// TODO Auto-generated constructor stub
		MainPanel = msgr;
		
		TestExplorer = new OpenTest();//Bu bizim testlerle ilgili i�lerimizi yapacak olan fonksiyon
		quester = new CreateQuest("Resim konumlar�", this);//Ask�ya aln�m���t�r
				
		menuBar = new JMenuBar();
		File = new JMenu("Dosyalar�m");
		Windows = new JMenu("Ara�lar");
		AboutUs = new JMenu("Hakk�m�z�da");
		HelpMe = new JMenu("Yard�m");
		
		explorer = new MyFileExplorer(this);
		
		openFile = new JMenuItem("Test A�",new ImageIcon("assets/TestAc.png"));
		openFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!explorer.isVisible()) {
						
						explorer.setBounds(100,100,400,400);
						explorer.setVisible(true);
						explorer.showFile(TestExplorer.returnFileName());
						explorer.setResizable(false);
				}else {
					explorer.requestFocus();
					
				}
			}
			
		});
		
		createFile = new JMenuItem("Test Olu�tur",new ImageIcon("assets/document.png"));
		createFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pathname = JOptionPane.showInputDialog("Testin ad�n� giriniz");
				if(pathname != null) {
					pathname = "Test/Quest/"+pathname+".tst";
					TestExplorer.createTest(pathname);
					TestExplorer.saveFileIndex(pathname);
				}
			}
		});
		
		exitThis = new JMenuItem("Testten ��k");
		exitThis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			System.out.println("Dosya �uraya kaydedildi: " + TestExplorer.getActiveTestIndex());
			TestExplorer.saveTest();
			TestExplorer.exitTest();
			MainPanel.showTest(null, null, null, null, null, null, null);
				
			}
		});
		
		drawBoard = new JMenuItem("Yaz� Tahtas�");
		drawBoard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame;
				frame = new JFrame("Yaz� Tahtas�");
				frame.setVisible(true);
				frame.setBounds(150, 150, 300, 400);
				frame.add(new BoardPanel(null));
			}
		});
		Windows.add(drawBoard);
		File.add(openFile);
		File.add(createFile);
		File.add(exitThis);
		menuBar.add(File);
		menuBar.add(Windows);
		menuBar.add(AboutUs);
		menuBar.add(HelpMe);
	}
	
	public void setBarPanelMessager(BarPanelMessager msgr) {
		this.MainPanel = msgr;
	}
	public JMenuBar getMenu() {
		return menuBar;
	}
	
	@Override
	public void nextQuest() {//Soru ileri
		// TODO Auto-generated method stub
		ActiveSoru++;
		System.out.println("Sonraki soruya ge�ildi: "+ActiveSoru);
		if(TestExplorer.getActiveTest() != null)
			if(TestExplorer.getActiveTest().QuestBank.size()>ActiveSoru) {//Testlerin g�r�nt�lenmesi ile ilgili
				Quest q = TestExplorer.getActiveTest().QuestBank.get(ActiveSoru);
				MainPanel.showTest(q.ImgPathName, q.QuizText, q.A,q.B, q.C, q.D, q.E);
			}else {
				MainPanel.showTest(null, null, null, null, null, null, null);
			}
		else
			JOptionPane.showMessageDialog(null, "ge�erli bir test se�iniz");
	}
	
	@Override
	public void backQuest() {//Soru geri
		// TODO Auto-generated method stub
		
		if(ActiveSoru > 0)
			ActiveSoru--;
		System.out.println("�nceki soruya d�n�ld�: "+ActiveSoru);
		if(TestExplorer.getActiveTest() != null)
			if(TestExplorer.getActiveTest().QuestBank.size()>ActiveSoru) {//Testlerin g�r�nt�lenmesi ile ilgili
				Quest q = TestExplorer.getActiveTest().QuestBank.get(ActiveSoru);
				MainPanel.showTest(q.ImgPathName, q.QuizText, q.A,q.B, q.C, q.D, q.E);
			}else {
				MainPanel.showTest(null, null, null, null, null, null, null);
			}
		else
			JOptionPane.showMessageDialog(null, "ge�erli bir test se�iniz");
	}
	
	public void showQuest(String Text) {
		
		System.out.println(Text);
		TestExplorer.dowlandTest(Text);
		if(TestExplorer.getActiveTest().QuestBank.size()>ActiveSoru) {
			Quest q = TestExplorer.getActiveTest().QuestBank.get(ActiveSoru);
			MainPanel.showTest(q.ImgPathName, q.QuizText, q.A,q.B, q.C, q.D, q.E);
		}else {
			MainPanel.showTest(null, null, null, null, null, null, null);
		}

	}
	
	@Override
	public void TestIndex(String Text) {//Test se�imi yap�ld� g�r�nt�le
		// TODO Auto-generated method stub
		showQuest(Text);
	}

	@Override
	public void newQuest(Quest q) {
		// TODO Auto-generated method stub
		if(TestExplorer.getActiveTest() != null) {
			TestExplorer.getActiveTest().addQuest(q);
		}else {
			JOptionPane.showMessageDialog(null, "Ge�erli bir test se�iniz");
		}
	}
	
	public Option StringOption(String str) {
		if(str.charAt(0) == 'a'||str.charAt(0) == 'A') {
			return FileSystem.Option.option_A;
		}
		if(str.charAt(0) == 'b'||str.charAt(0) == 'B') {
			return FileSystem.Option.option_B;
		}
		if(str.charAt(0) == 'c'||str.charAt(0) == 'C') {
			return FileSystem.Option.option_C;
		}
		if(str.charAt(0) == 'd'||str.charAt(0) == 'D') {
			return FileSystem.Option.option_D;
		}
		return FileSystem.Option.option_E;
	}

	@Override
	public void CreateQuest() {//Bir tane CreateQuest frame olu�tur demek...
		// TODO Auto-generated method stub
		System.out.println("Yeni soru olu�turuluyor");
		if(TestExplorer.getActiveTest() != null) {
			newQuest(new Quest(
					JOptionPane.showInputDialog("Soru metnini giriniz"),
					JOptionPane.showInputDialog("Resmin ismini giriniz"), 
					JOptionPane.showInputDialog("A ��kk�"),
					JOptionPane.showInputDialog("B ��kk�"), 
					JOptionPane.showInputDialog("C ��kk�"), 
					JOptionPane.showInputDialog("D ��kk�"),
					JOptionPane.showInputDialog("E ��kk�"), 
					StringOption(JOptionPane.showInputDialog("Do�ru cevab� giriniz"))));
			
			if(TestExplorer.getActiveTest().QuestBank.size()>ActiveSoru) {
				Quest q = TestExplorer.getActiveTest().QuestBank.get(ActiveSoru);
				MainPanel.showTest(q.ImgPathName, q.QuizText, q.A,q.B, q.C, q.D, q.E);
			}else {
				MainPanel.showTest(null, null, null, null, null, null, null);
			}
		}
		else
			JOptionPane.showConfirmDialog(menuBar, "Ge�erli bir mesaj giriniz");
		
		
	}
	
	/*
	 * �imdi burada yap�lmas� gereken �ey kay�t sistemini eklemek
	 * Her soru eklendi�inde soru ileri veya soru geri yap�ld���nda showWindow demek
	 */
}
