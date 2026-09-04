package practiceContactTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUsingJava {

	public static void main(String[] args) {
		Date dateobj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String actdat=sim.format(dateobj);
		System.out.println(actdat);
		 Calendar cal = sim.getCalendar();
		 cal.add(Calendar.DAY_OF_MONTH, 30);
		 String datereque=sim.format(cal.getTime());
		 System.out.println(datereque);
		
		
		

	}

}
