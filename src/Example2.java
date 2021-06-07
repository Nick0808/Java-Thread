import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TimerCount2 extends JFrame {
	private Container frame;
	private JLabel[] timerLabel = new JLabel[2];
	private Thread th;
	
	public TimerCount2() {
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
			TimerRunnable runnable = new TimerRunnable(timerLabel[index]);
			th = new Thread(runnable);
			th.start(); // 스레드의 run() 수행
		}
		
		
		// 타이머를 먼저 수행할 경우
		// 타이머내의 무한루프로 인해 다음 코드로 진행불가
		//timerStart();
		this.pack();
		this.setVisible(true);
		

	}
	

	
}


class TimerRunnable extends Thread implements Runnable{
	private JLabel label;
	
	public TimerRunnable(JLabel label) {
		// TODO Auto-generated constructor stub
		this.label = label;
	}
	
	
	
	// 스레드를 수행하는 메소드
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		
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

public class Example2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TimerCount2();
	}

}
