package chyshka.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlReader {
	
	public String readHtmlFile(String htmlFilePath) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(htmlFilePath))));
		StringBuilder htmlFileContent = new StringBuilder();
		String line;
		
		while((line = reader.readLine()) != null) {
			htmlFileContent.append(line).append(System.lineSeparator());
		}
		
		return htmlFileContent.toString().trim();
	}
}
