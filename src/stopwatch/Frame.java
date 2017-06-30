package stopwatch;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Frame extends JFrame implements StopwatchTaskHandler, ActionListener {
	
	private static final long serialVersionUID = 1152530366979513505L;
	
	private static final String TITLE = "Stopwatch";
	
	private JPanel contentPane;
	private JLabel labelTime;
	private JButton buttonStartPause, buttonStop;
	
	private Stopwatch stopwatch;
	
	public Frame() {
		stopwatch = new Stopwatch(this);
		
		initialize();
	}
	
	private void initialize() {
		setTitle(TITLE);
		setSize(500, 235);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		labelTime = new JLabel("00:00:00");
		labelTime.setFont(new Font("Arial", Font.PLAIN, 110));
		labelTime.setHorizontalAlignment(SwingConstants.CENTER);
		labelTime.setBounds(10, 10, 474, 154);
		contentPane.add(labelTime);
		
		buttonStartPause = new JButton("Start");
		buttonStartPause.setBounds(296, 175, 89, 23);
		buttonStartPause.addActionListener(this);
		contentPane.add(buttonStartPause);
		
		buttonStop = new JButton("Stop");
		buttonStop.setBounds(395, 175, 89, 23);
		buttonStop.addActionListener(this);
		contentPane.add(buttonStop);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(buttonStartPause)) {
			if (buttonStartPause.getText().equals("Start")) {
				stopwatch.start();
				
				buttonStartPause.setText("Pause");
			}
			else {
				stopwatch.pause();
				buttonStartPause.setText("Start");
			}
		}
		else {
			stopwatch.stop();
			buttonStartPause.setText("Start");
		}
	}

	@Override
	public void stopwatchTaskHandled() {
		labelTime.setText(stopwatch.toString());
	}
	
}