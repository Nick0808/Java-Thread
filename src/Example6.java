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
		this.setTitle("��Ī��");
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
		numberLabel.setFont(new Font("���", 0, 100));
		numberLabel.setText("000");
		frame.add(numberLabel, BorderLayout.NORTH);
	}
	
	public void initButton() {
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(300, 50));
		buttonPanel.setLayout(new FlowLayout());
		btnStart = new JButton("����");
		btnStop = new JButton("����");
		buttonPanel.add(btnStart);
		buttonPanel.add(btnStop);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		btnStart.addActionListener(new ButtonClickListener());
		btnStop.addActionListener(new ButtonClickListener());
	}
	
	// ����Ŭ������ ��ưŬ�� ������
	class ButtonClickListener implements ActionListener {
		// ������(task)�� �۾� ��ȣ
		private int thIndex = 0; // thIndex = threadIndex
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnStart) {
				// ���۹�ư
				machineStart();
			} else {
				// ������ư
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
			// 1�ʰ������� ������ ����
			// Ÿ�̸� ��� (Timer Ŭ����, TimerTask Ŭ����)
			// TimerTaks Ŭ������ run() �ݹ�޼ҵ� �������̵� ����
			Timer time = new Timer(); // Ÿ�̸� ����
			// Ÿ�̸� ��ü�� ������ ������ ����
			TimerTask task = new TimerTask() {
				// Ÿ�̸Ӱ� ����Ǵ� ���� �ڵ带 ����
				@Override
				public void run() {
					// TODO Auto-generated method stub
					// 3���� �����带 ���������� ����(interrupt())
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
			
			// Ÿ�̸ӿ� task�� ����
			// �ܹ߼�
			time.schedule(task, 1000); // 1�� ������ task ����
			// ���߼� : 1����(delay)�� �ٽ� 1�� ����(period) Ÿ�̸� ����
			//time.schedule(task, 1000, 1000);
			
		}
	}
	
	// ������ Ŭ����
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
		
		// �������� �� ���ڰ� ��Ÿ����, ��Ÿ���� ���ݵ� ����
		public void generate1() {
			Random random = new Random();
			while(true) {
				number[position] = Integer.toString(random.nextInt(10)); // 0 ~ 9����
				
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
		
		// ���ڰ� ���ʷ� ī��Ʈ�ϸ� ��Ÿ��, ���� ����
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
