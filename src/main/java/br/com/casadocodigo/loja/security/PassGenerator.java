package br.com.casadocodigo.loja.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.security.Base64Encoder;

public class PassGenerator {
	
	public static void main(String[] args) {
		System.out.println(new PassGenerator().generate("12345"));
	}
	
	public String generate(String senha) {
		try {
			byte[] digest = MessageDigest
					.getInstance("sha-256").digest(senha.getBytes());
			return Base64Encoder.encode(digest);
		} catch (NoSuchAlgorithmException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
