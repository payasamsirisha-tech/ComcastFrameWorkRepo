package practice;

import java.util.Date;

public class CaptureTimeStamp {
	public static void main(String[] args) {
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		
		//in Time we are getting spaces and special caracters but we are saving file name with
		//this timestamp so we have to use replace function and replace with _
		System.out.println(time);
		
	}

}
