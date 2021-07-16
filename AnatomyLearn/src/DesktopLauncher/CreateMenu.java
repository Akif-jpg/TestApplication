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
	JMenuItem openFile,createFile,exitThis,drawBoard,Hakkýmýzda,Yardým;
	BarPanelMessager MainPanel;
	MyFileExplorer explorer;
	CreateQuest quester;
	int ActiveSoru = 0;
	OpenTest TestExplorer;
	public CreateMenu(BarPanelMessager msgr) {
		// TODO Auto-generated constructor stub
		MainPanel = msgr;
		
		TestExplorer = new OpenTest();//Bu bizim testlerle ilgili iþlerimizi yapacak olan fonksiyon
		quester = new CreateQuest("Resim konumlarý", this);//Askýya alnýmýþþtýr
				
		menuBar = new JMenuBar();
		File = new JMenu("Dosyalarým");
		Windows = new JMenu("Araçlar");
		AboutUs = new JMenu("Hakkýmýzýda");
		HelpMe = new JMenu("Yardým");
		
		explorer = new MyFileExplorer(this);
		
		openFile = new JMenuItem("Test Aç",new ImageIcon("assets/TestAc.png"));
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
		
		createFile = new JMenuItem("Test Oluþtur",new ImageIcon("assets/document.png"));
		createFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pathname = JOptionPane.showInputDialog("Testin adýný giriniz");
				if(pathname != null) {
					pathname = "Test/Quest/"+pathname+".tst";
					TestExplorer.createTest(pathname);
					TestExplorer.saveFileIndex(pathname);
				}
			}
		});
		
		exitThis = new JMenuItem("Testten Çýk");
		exitThis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			System.out.println("Dosya þuraya kaydedildi: " + TestExplorer.getActiveTestIndex());
			TestExplorer.saveTest();
			TestExplorer.exitTest();
			MainPanel.showTest(null, null, null, null, null, null, null);
				
			}
		});
		
		drawBoard = new JMenuItem("Yazý Tahtasý");
		drawBoard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame;
				frame = new JFrame("Yazý Tahtasý");
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
		System.out.println("Sonraki soruya geçildi: "+ActiveSoru);
		if(TestExplorer.getActiveTest() != null)
			if(TestExplorer.getActiveTest().QuestBank.size()>ActiveSoru) {//Testlerin görüntülenmesi ile ilgili
				Quest q = TestExplorer.getActiveTest().QuestBank.get(ActiveSoru);
				MainPanel.showTest(q.ImgPathName, q.QuizText, q.A,q.B, q.C, q.D, q.E);
			}else {
				MainPanel.showTest(null, null, null, null, null, null, null);
			}
		else
			JOptionPane.showMessageDialog(null, "geçerli bir test seçiniz");
	}
	
	@Override
	public void backQuest() {//Soru geri
		// TODO Auto-generated method stub
		
		if(ActiveSoru > 0)
			ActiveSoru--;
		System.out.println("Önceki soruya dönüldü: "+ActiveSoru);
		if(TestExplorer.getActiveTest() != null)
			if(TestExplorer.getActiveTest().QuestBank.size()>ActiveSoru) {//Testlerin görüntülenmesi ile ilgili
				Quest q = TestExplorer.getActiveTest().QuestBank.get(ActiveSoru);
				MainPanel.showTest(q.ImgPathName, q.QuizText, q.A,q.B, q.C, q.D, q.E);
			}else {
				MainPanel.showTest(null, null, null, null, null, null, null);
			}
		else
			JOptionPane.showMessageDialog(null, "geçerli bir test seçiniz");
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
	public void TestIndex(String Text) {//Test seçimi yapýldý görüntüle
		// TODO Auto-generated method stub
		showQuest(Text);
	}

	@Override
	public void newQuest(Quest q) {
		// TODO Auto-generated method stub
		if(TestExplorer.getActiveTest() != null) {
			TestExplorer.getActiveTest().addQuest(q);
		}else {
			JOptionPane.showMessageDialog(null, "Geçerli bir test seçiniz");
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
	public void CreateQuest() {//Bir tane CreateQuest frame oluþtur demek...
		// TODO Auto-generated method stub
		System.out.println("Yeni soru oluþturuluyor");
		if(TestExplorer.getActiveTest() != null) {
			newQuest(new Quest(
					JOptionPane.showInputDialog("Soru metnini giriniz"),
					JOptionPane.showInputDialog("Resmin ismini giriniz"), 
					JOptionPane.showInputDialog("A þýkký"),
					JOptionPane.showInputDialog("B þýkký"), 
					JOptionPane.showInputDialog("C þýkký"), 
					JOptionPane.showInputDialog("D þýkký"),
					JOptionPane.showInputDialog("E þýkký"), 
					StringOption(JOptionPane.showInputDialog("Doðru cevabý giriniz"))));
			
			if(TestExplorer.getActiveTest().QuestBank.size()>ActiveSoru) {
				Quest q = TestExplorer.getActiveTest().QuestBank.get(ActiveSoru);
				MainPanel.showTest(q.ImgPathName, q.QuizText, q.A,q.B, q.C, q.D, q.E);
			}else {
				MainPanel.showTest(null, null, null, null, null, null, null);
			}
		}
		else
			JOptionPane.showConfirmDialog(menuBar, "Geçerli bir mesaj giriniz");
		
		
	}
	
	/*
	 * Þimdi burada yapýlmasý gereken þey kayýt sistemini eklemek
	 * Her soru eklendiðinde soru ileri veya soru geri yapýldýðýnda showWindow demek
	 */
}
