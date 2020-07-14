package com.java.replacer.rules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

public class Rules {

	Path pathSust = Paths.get("cfg//sustituciones.txt");
	Path pathPatr = Paths.get("cfg//patrones.txt");
	
	Set<String> sustituciones = new HashSet<>();
	Set<String> sustituciones_cortas = new HashSet<>();
	Set<String> patrones = new HashSet<>();
	
	public static String SUSTITUTO = "XXXXX";
		
	public Rules() {
		
	}

	public Set<String> getSustituciones() {
		return sustituciones;
	}

	public void setSustituciones(Set<String> sustituciones) {
		this.sustituciones = sustituciones;
	}
	
	public Set<String> getSustituciones_cortas() {
		return sustituciones_cortas;
	}

	public void setSustituciones_cortas(Set<String> sustituciones_cortas) {
		this.sustituciones_cortas = sustituciones_cortas;
	}
	
	public Set<String> getPatrones() {
		return patrones;
	}

	public void setPatrones(Set<String> patrones) {
		this.patrones = patrones;
	}

	@PostConstruct
	public void cargarReglas() {
		try {
			sustituciones = Files.lines(pathSust).collect(Collectors.toSet());
			for(String str : sustituciones) {
				if(str.split(" ").length > 1) {
					for(String st : str.split(" ")) {
						sustituciones_cortas.add(st);
					}
				}
			}
			patrones = Files.lines(pathPatr).collect(Collectors.toSet());
		} catch(IOException e) {
			
		}
	}
	
}
