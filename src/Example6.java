import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class slotMachine extends JFrame {
	private Container frame;
	private String[] number = new String[3];
	private Thread[] thread = new Thread[3];
	private JLabel numberLabel;
	private JPanel buttonPanel;
	private JButton btnStart, btnStop;
	
	public slotMachine() {
		// TODO Auto-generated constructor stub
		this.setTitle("파칭코");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = getContentPane();
		frame.setLayout(new BorderLayout());
		
		initLabel();
		initButton();
		
		this.pack();
		this.setVisible(true);
	}
	
	public void initLabel() {
		numberLabel = new JLabel();
		numberLabel.setPreferredSize(new Dimension(300, 150));
		numberLabel.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		numberLabel.setFont(new Font("고딕", 0, 100));
		numberLabel.setText("000");
		frame.add(numberLabel, BorderLayout.NORTH);
	}
	
	public void initButton() {
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(300, 50));
		buttonPanel.setLayout(new FlowLayout());
		btnStart = new JButton("시작");
		btnStop = new JButton("정지");
		buttonPanel.add(btnStart);
		buttonPanel.add(btnStop);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		btnStart.addActionListener(new ButtonClickListener());
		btnStop.addActionListener(new ButtonClickListener());
	}
	
	// 내부클래스에 버튼클릭 리스너
	class ButtonClickListener implements ActionListener {
		// 스레드(task)의 작업 번호
		private int thIndex = 0; // thIndex = threadIndex
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnStart) {
				// 시작버튼
				machineStart();
			} else {
				// 정지버튼
				machineStop();
			}
			
		}
		
		public void machineStart() {
			for(int index = 0; index < 3; index++) {
				int delay = new Random().nextInt(201);
				thread[index] = new GenNumber(index, delay);
				thread[index].start();
			}
		}
		
		public void machineStop() {
			// 1초간격으로 스레드 중지
			// 타이머 사용 (Timer 클래스, TimerTask 클래스)
			// TimerTaks 클래스의 run() 콜백메소드 오버라이드 정의
			Timer time = new Timer(); // 타이머 동작
			// 타이머 객체가 수행할 내용을 정의
			TimerTask task = new TimerTask() {
				// 타이머가 수행되는 동안 코드를 수행
				@Override
				public void run() {
					// TODO Auto-generated method stub
					// 3개의 스레드를 순차적으로 정지(interrupt())
					if(thIndex < 3) {
						thread[thIndex].interrupt();
						thIndex++;
						if(thIndex == 3)
							thIndex = 0;
					} else {
						time.cancel();
					}
				}
			};
			
			// 타이머에 task를 적용
			// 단발성
			time.schedule(task, 1000); // 1초 지나고 task 수행
			// 연발성 : 1초후(delay)에 다시 1초 간격(period) 타이머 실행
			//time.schedule(task, 1000, 1000);
			
		}
	}
	
	// 스레드 클래스
	class GenNumber extends Thread {
		private int position;
		private int delay;
		
		public GenNumber(int position, int delay) {
			// TODO Auto-generated constructor stub
			this.position = position;
			this.delay = delay;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			generate2();
		}
		
		// 랜덤으로 각 숫자가 나타나며, 나타나는 간격도 랜덤
		public void generate1() {
			Random random = new Random();
			while(true) {
				number[position] = Integer.toString(random.nextInt(10)); // 0 ~ 9까지
				
				String display = "";
				for(int count = 0; count < 3; count++) {
					display += number[count];
					numberLabel.setText(display);
				}
				
				try {
					Thread.sleep(delay);
				} catch(Exception exp) {
					return;
				}
			}
		}
		
		// 숫자가 차례로 카운트하며 나타남, 간격 랜덤
		public void generate2() {
			int n = 0;
			while(true) {
				number[position] = Integer.toString(n++);
				
				String display = "";
				for(int count = 0; count < 3; count++) {
					display += number[count];
					numberLabel.setText(display);
				}
				
				try {
					Thread.sleep(delay);
				} catch(Exception exp) {
					return;
				}
				
				if(n == 10) n = 0;
			}
		}
	}
}

public class Example6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new slotMachine();
	}

}
