package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.LoggerController;

public class KeyDisplay extends JFrame implements ActionListener{
	private JButton set,clear;
	private JComboBox email,shot;
	private LoggerController log;
	
	private void configureButtons() {
		set.addActionListener(this);
		clear.addActionListener(this);
	}
	
	private void configureView() {
		JPanel p1;
		
		String[] intervals = {"10 secs","20 secs","30 secs","1 min","5 min","15 min","30 min"};
		setTitle("Military Logger");
		 setVisible(true);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setLayout(new GridLayout(0,2));
		 add(new JLabel("Set ScreenCapture Interval:"));
		 add(email = new JComboBox(intervals));
		 add(new JLabel("Set Email Interval:"));
		 add(shot = new JComboBox(intervals));
		 add(new JLabel("Email:"));
		 add(new JTextArea(2,20));
		 add(p1 = new JPanel(new GridLayout(0,2)));
		 p1.add(set = new JButton("SET"));
		 p1.add(clear = new JButton("CLEAR"));
		 pack();
	}
	
	
	 public KeyDisplay(){
		 log = new LoggerController();
		 configureView();
		 configureButtons();
	 }
	 
	 @Override
	 public void actionPerformed(ActionEvent e) {
		 if(e.getSource().equals(set)) {
			//log.sendEmail(convertToSeconds(email.getSelectedIndex()));
			 log.screenshot(convertToSeconds(shot.getSelectedIndex()));
		 }
		 if(e.getSource().equals(clear)) {
			 
		 }
	 }
	 
	 private int convertToSeconds(int x) {
		 int second = 1000;
		 switch(x) {
			 case 0:{
				 second *= 10;
			 }break;
			 case 1:{
				 second *= 20;
				 break;
			 }
			 case 2:{
				 second *= 30;
				 break;
			 }
			 case 3:{
				 second *= 60;
				 break;
			 }
			 case 4:{
				 second *= 60*5;
				 break;
			 }
			 case 5:{
				 second *= 60*15;
			 }
			 case 6:{
				 second *= 60*30;
			 }
		 }
		 return second;
	 }
}
