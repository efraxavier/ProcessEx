package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}
	
	private String OS() {
		String OS = System.getProperty("os.name");
		//String arch = System.getProperty("os.arch");
		//String version = System.getProperty("os.version");
		return OS;
	}

	
	public void ip() {
		String os = OS();
		String process = "";
		if (os.contains("Windows")) {
			process = "IPCONFIG";
		} 
		if (os.contains("Linux")) {
			process = "ip addr";
		} 
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("cmd /c");
			buffer.append(" ");
			buffer.append(process);
			
			Process p = Runtime.getRuntime().exec(buffer.toString());
			InputStream flux = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(flux);
			BufferedReader bufferRead = new BufferedReader(reader);
			String line = bufferRead.readLine();
			String nomeAdp = null;
			while (line != null) {
				if (line.contains("Adaptador")) {
					nomeAdp = line;
				}
				if (line.contains("IPv4")) { 
					System.out.println(nomeAdp); 
					System.out.println(line); 
					}
				
				line = bufferRead.readLine();
			}
			bufferRead.close();
			reader.close();
			flux.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	  } 
	}

