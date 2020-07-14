package com.java.replacer.executor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.replacer.io.Writer;
import com.java.replacer.rules.Rules;

public class Executor {
	
	Path pathIn = Paths.get("input");
	
	@Autowired
	private Writer writer;
	
	@Autowired
	private Rules rules;

	public Executor() {
		
	}
	
	@PostConstruct
	public void execute() {
		Stream<Path> listFiles = recovFiles();
		listFiles.forEach(file -> replaceAndWrite(file));
	}
	
	public Stream<Path> recovFiles() {
		Stream<Path> listFiles;
		try {
			listFiles = Files.list(pathIn)
			.filter(p -> ! Files.isDirectory(p))
			.map(p -> p.toAbsolutePath());
		} catch (IOException ioExcp) {
			return null;
		}
		return listFiles;
	}
	
	public void replaceAndWrite(Path file) {
		//System.out.println("Probando: "+file.getFileName());
		try {
			List<String> list = Files.lines(file).map(s -> repl(s)).collect(Collectors.toList());
			writer.write(list, file.getFileName().toString());
		} catch (IOException ioExcp) {
			
		}
	}
	
	private String repl(String s) {
		String line = s;
		for(String sust : rules.getSustituciones()) {
			if(s.contains(sust.toString())) {
				line = line.replaceAll(sust.toString(), Rules.SUSTITUTO);
			}
		}
		for(String sust_corta : rules.getSustituciones_cortas()) {
			if(s.contains(sust_corta.toString())) {
				line = line.replaceAll(sust_corta.toString(), Rules.SUSTITUTO);
			}
		}
		for(String patron : rules.getPatrones()) {
			line = line.replaceAll(patron, Rules.SUSTITUTO);
		}
		return line;		
	}
		
}
