// this program will extract the Fix tags using Pattern 
package puttyJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

	static String read;
	public static void main(String[] args) throws IOException {
		
		//String expected_value = "Hello, world!";

	    Path files = Path.of("C:\\Users\\Vipin\\Desktop\\test.json");
	    String fixfile = Files.readString(files);
	    
			
		Pattern p = Pattern.compile("8=FIX(.+)(?<=\\|)10=(.+?)(?=\\|)", Pattern.MULTILINE);
						
		Matcher m = p.matcher(fixfile);
		//System.out.println(fixfile);
		
		while (m.find()) {
		 System.out.println(m.group());
		 System.out.println("\t" + m.group(1));
		 System.out.println("\t" + m.group(2));
		}
		
	}

}
