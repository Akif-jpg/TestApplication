package Messager;

import FileSystem.TestFile;

public interface BarPanelMessager {
	public void showTest(String imgPathName,String questText,String A,String B,String C,String D,String E);
	public void QuestAnswered(boolean isTrue);
}
