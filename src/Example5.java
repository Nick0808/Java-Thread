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
	
	// �������� Thread�� �ϳ��� �迭�� ����
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
		numberLabel.setFont(new Font("���", Font.ITALIC, 100));
		
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
		
		// position : �����ڸ�, delay : �ð�����
		public RndNumber(int position, int delay) {
			// TODO Auto-generated constructor stub
			this.positon = position;
			this.delay = delay;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//������ ����
			Random random = new Random();
			String display ="";
			
				try {
					Thread.sleep(1000);
				}catch(Exception exp) {
					return;
				}
				
				// 0~9 : ���ڸ� ���� ���� ����
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
