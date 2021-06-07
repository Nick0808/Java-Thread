import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;


class FlickerLabel extends JLabel implements Runnable {
	private int delay;
	private Color[] rainbow = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, new Color(0, 0, 102), new Color(153, 0, 153)};
	
	
	public FlickerLabel(String message, int delay) {
		// TODO Auto-generated constructor stub
		super(message); // 레이블의 초기화
		this.delay = delay;
		this.setPreferredSize(new Dimension(350, 100));
		this.setHorizontalAlignment(CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		//this.setOpaque(true); // 배경색 변경이 가능하도록 지정
		
		Thread th = new Thread(this);
		th.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		while(true) {
			/*
			if(count % 2 == 1)
				this.setForeground(Color.ORANGE);
			else
				this.setForeground(Color.YELLOW);
			*/
			
			// Rainbow Color
			this.setForeground(rainbow[count]);
			
			try {
				
				Thread.sleep(delay);
			} catch(Exception exp) {
				return;
			}
			count++;
			if(count == rainbow.length) count = 0;
		}
	}
}

class FlickerFrame extends JFrame {
	private Container frame;
	
	public FlickerFrame() {
		// TODO Auto-generated constructor stub
		this.setTitle("Flicker");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = getContentPane();
		frame.setLayout(new FlowLayout());
		
		FlickerLabel label = new FlickerLabel("HELLO", 300);
		frame.add(label);
		
		this.pack();
		this.setVisible(true);
	}
}


public class Example4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FlickerFrame();
	}

}
