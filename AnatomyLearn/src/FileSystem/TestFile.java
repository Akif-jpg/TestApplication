package FileSystem;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class TestFile implements Serializable{
	public ArrayList<Quest> QuestBank;
	public TestFile() {
		// TODO Auto-generated constructor stub
		QuestBank = new ArrayList<Quest>();
	}
	
	public Quest getIndex(int index) {
		return QuestBank.get(index);
	}
	
	public void addQuest(Quest q) {
		QuestBank.add(q);
	}

}
