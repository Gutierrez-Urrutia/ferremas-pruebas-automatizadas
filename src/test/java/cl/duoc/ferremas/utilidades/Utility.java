package cl.duoc.ferremas.utilidades;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	
	 public static void captureScreenShot(WebDriver webdriver, String filePath)throws IOException{
	    	
	    	TakesScreenshot screenShot=((TakesScreenshot)webdriver);
	    	File screenFile=screenShot.getScreenshotAs(OutputType.FILE);
	    	File DestinoFile= new File(filePath);
	    	FileUtils.copyFile(screenFile, DestinoFile); 
	}

	public static String GetTimeStampValue() throws IOException {
		Calendar cal = Calendar.getInstance();
		Date time = cal.getTime();
		String timestamp = time.toString();
		String systime = timestamp.replace(":", "_");
		return systime;
	}
}
