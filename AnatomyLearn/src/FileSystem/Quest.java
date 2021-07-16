package FileSystem;

import java.io.Serializable;

public class Quest implements Serializable{
	public String QuizText;
	public String ImgPathName;
	public String A,B,C,D,E;
	public Option Answer;
	public Quest(String quizText,String imgPathName,String A,String B,String C,String D,String E,Option answer) {
		// TODO Auto-generated constructor stub
		QuizText = quizText;
		ImgPathName = imgPathName;
		Answer = answer;
		this.A = A;
		this.B =B;
		this.C = C;
		this.D = D;
		this.E = E;
	}

	public boolean isTrue(Option select) {
		return Answer == select;
	}
}
