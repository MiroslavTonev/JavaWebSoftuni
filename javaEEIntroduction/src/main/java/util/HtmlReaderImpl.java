package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class HtmlReaderImpl implements HtmlReader {
	
	private BufferedReader reader; 
	
	@Override
	public String readHtmlFile(String htmlFilePath) throws IOException {
		
		this.reader =  new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(htmlFilePath))
				));
		
		StringBuilder htmlFileContent = new StringBuilder();
		String htmlFileLine;
		
		while((htmlFileLine = reader.readLine()) != null) {
			htmlFileContent.append(htmlFileLine).append(System.lineSeparator());
			
		}
		
		return htmlFileContent.toString().trim();
	}

}
