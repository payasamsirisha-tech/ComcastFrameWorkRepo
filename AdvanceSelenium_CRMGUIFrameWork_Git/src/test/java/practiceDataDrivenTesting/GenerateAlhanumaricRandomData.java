package practiceDataDrivenTesting;

public class GenerateAlhanumaricRandomData {

	public static void main(String[] args) {
		int n=20;
		//choose a character random from this string
		
		String AlphaNumaricString="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		
		//create string buffer size of AlphanumaricString
		
		StringBuffer sb=new StringBuffer(n);
		
		for(int i=0;i<n;i++) { 
			//generate a random number number between 0 to alphanumaricString variable length
			int index=(int)(AlphaNumaricString.length()*Math.random());
			
			//add character one by one in end of sb
			sb.append(AlphaNumaricString.charAt(index));
		}
		System.out.println(sb);
	

	}

}
