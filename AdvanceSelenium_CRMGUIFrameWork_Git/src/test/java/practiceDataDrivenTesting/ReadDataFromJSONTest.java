package practiceDataDrivenTesting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJSONTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		//step1: parse Json physical file in to java object using jsonparse class
		
		JSONParser parser=new JSONParser();
		
		Object obj=parser.parse(new FileReader("src/test/resources/data/appcommon.json"));
		
		//step2: convert javA object  into json object using down casting
		JSONObject map=(JSONObject)obj;
		
		
		//3: get the value from json file using key
		System.out.println(map.get("url"));
		
	}

}
