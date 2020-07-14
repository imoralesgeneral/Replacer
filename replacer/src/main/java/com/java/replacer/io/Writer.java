package com.java.replacer.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Writer {
	
	Path pathOut = Paths.get("output");

	public Writer() {
		
	}
	
	public void write(List<String> listStr, String fileName) {
		File fileOut = Paths.get(pathOut.toFile().getAbsolutePath(), fileName).toFile();
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut))) {
			for(String str : listStr) {
				writer.write(str);
				writer.newLine();
			}
		} catch (IOException e) {
			
		}
	}
	
}
