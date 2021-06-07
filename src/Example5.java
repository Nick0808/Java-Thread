import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

class GenNumber extends JFrame {
	private String[] number = {"", "", ""};
	private Container frame;
	private JLabel numberLabel = new JLabel();
	
	// 여러개의 Thread를 하나의 배열로 관리
	private Thread[] thread = new Thread[3];
	
	
	public GenNumber() {
		// TODO Auto-generated constructor stub
		this.setTitle("Random");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = getContentPane();
		frame.setLayout(new FlowLayout());
		
		numberLabel.setPreferredSize(new Dimension(300, 150));
		numberLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		numberLabel.setFont(new Font("고딕", Font.ITALIC, 100));
		
		//testGenerate();
		
		frame.add(numberLabel);
		
		int delay = 4000;
		for(int index = 0; index < 3; index++) {
			delay -= 1000;
			thread[index] = new RndNumber(index, delay);
			thread[index].start();
		}
		
		
		this.pack();
		this.setVisible(true);
		
	}
	
	class RndNumber extends Thread {
		private int positon, delay;
		
		// position : 선택자리, delay : 시간간격
		public RndNumber(int position, int delay) {
			// TODO Auto-generated constructor stub
			this.positon = position;
			this.delay = delay;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//랜덤값 생성
			Random random = new Random();
			String display ="";
			
				try {
					Thread.sleep(1000);
				}catch(Exception exp) {
					return;
				}
				
				// 0~9 : 한자리 숫자 랜덤 생성
				number[positon] = Integer.toString(random.nextInt(10));
				
				for(int count = 0; count < 3; count++) {
					display += number[count];
					numberLabel.setText(display);
				}
			}
	}
}



public class Example5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GenNumber();
	}

}
