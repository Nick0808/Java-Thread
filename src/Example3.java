import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TimerCount3 extends JFrame implements Runnable {
	private Container frame;
	private JLabel[] timerLabel = new JLabel[2];
	private Thread th;
	private int order;
	
	public TimerCount3() {
		this.setTitle("Timer");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = getContentPane();
		frame.setLayout(new FlowLayout());
		
		for(int index = 0; index < 2; index++) {
			timerLabel[index] = new JLabel();
			timerLabel[index].setPreferredSize(new Dimension(200, 200));
			timerLabel[index].setFont(new Font("고딕", Font.ITALIC, 100));
			//Label Center Alignment
			timerLabel[index].setHorizontalAlignment((int)CENTER_ALIGNMENT);
			timerLabel[index].setForeground(Color.DARK_GRAY);
			frame.add(timerLabel[index]);
			
			// 스레드로 타이머 동작
			th = new Thread(this);
			th.start(); // 스레드의 run() 수행
		}
		
		
		this.pack();
		this.setVisible(true);
		

	}
		@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		JLabel label = timerLabel[order];
		
		while(true) {
			label.setText(Integer.toString(count++));
			
			try {
				// sleep()는 반드시 예외처리 필요
				Thread.sleep(1000); // Timer : millisecond (1s : 1000ms)
				
			} catch(Exception exp) {
				return;
			}
			
		
		}
		
	}

	
}



public class Example3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TimerCount3();
	}

}
