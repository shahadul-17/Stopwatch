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
	private JLabel labelHoursMinutesSeconds, labelMilliseconds;
	private JButton buttonStartPause, buttonStop;
	
	private Stopwatch stopwatch;
	
	public Frame() {
		stopwatch = new Stopwatch(this);
		
		initialize();
	}
	
	private void initialize() {
		setTitle(TITLE);
		setSize(500, 183);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		labelHoursMinutesSeconds = new JLabel("00:00:00");
		labelHoursMinutesSeconds.setFont(new Font("Arial", Font.PLAIN, 105));
		labelHoursMinutesSeconds.setHorizontalAlignment(SwingConstants.LEFT);
		labelHoursMinutesSeconds.setBounds(10, 10, 406, 100);
		contentPane.add(labelHoursMinutesSeconds);
		
		labelMilliseconds = new JLabel("00");
		labelMilliseconds.setFont(new Font("Arial", Font.PLAIN, 60));
		labelMilliseconds.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMilliseconds.setBounds(418, 46, 66, 60);
		contentPane.add(labelMilliseconds);
		
		buttonStartPause = new JButton("Start");
		buttonStartPause.setBounds(296, 121, 89, 23);
		buttonStartPause.addActionListener(this);
		contentPane.add(buttonStartPause);
		
		buttonStop = new JButton("Stop");
		buttonStop.setBounds(395, 121, 89, 23);
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
		String[] splittedTime = stopwatch.toString().split("#");
		
		labelMilliseconds.setText(splittedTime[0]);
		labelHoursMinutesSeconds.setText(splittedTime[1]);
	}
	
}