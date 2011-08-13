import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
	
	public static String open(File file) throws FileNotFoundException {
		Scanner s;
		s = new Scanner(file);
		
		String fileContent;
		fileContent = "";
		
		while( s.hasNextLine() ) {
			fileContent = fileContent + s.nextLine() + "\n";
		}
		return fileContent;
	}
	
	public static void save(File file, String content) throws IOException {
		BufferedWriter writer;
		writer = new BufferedWriter( new FileWriter(file) );
		writer.write(content);
		writer.flush();
		writer.close();
	}

}
