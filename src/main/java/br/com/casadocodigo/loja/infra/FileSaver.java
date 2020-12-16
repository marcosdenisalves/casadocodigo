package br.com.casadocodigo.loja.infra;

import java.io.IOException;

import javax.servlet.http.Part;

public class FileSaver {
	
	private static final String SERVER_PATH = "/home/denis/Imagens";
	
	public String write(Part file, String path) {
		String relativePath = path + "/" + file.getSubmittedFileName();
		try {
			file.write(SERVER_PATH + "/" + relativePath);
			
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
