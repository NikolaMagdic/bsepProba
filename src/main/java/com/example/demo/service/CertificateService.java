package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.springframework.stereotype.Service;

import com.example.demo.keystores.KeyStoreReader;
import com.example.demo.keystores.KeyStoreWriter;

@Service
public class CertificateService {

	private KeyStoreReader keyStoreReader;
	
	private KeyStoreWriter keyStoreWriter;
	
	private KeyStore keyStore;

	public CertificateService() {}
	
	public X509Certificate createCertificate(X509Certificate cert, PrivateKey pk) throws IOException {

		this.keyStoreWriter = new KeyStoreWriter(); 
		
		this.keyStore = keyStoreWriter.getKeyStore();
				
		//BufferedInputStream in = new BufferedInputStream(new FileInputStream("/data/keystore.p12"));
		char[] password = "123".toCharArray();
		
		//keyStoreWriter.loadKeyStore(null, password);
		keyStoreWriter.loadKeyStore("keystorenas", password);
		keyStoreWriter.saveKeyStore("keystorenas", password);
			
		keyStoreWriter.write("asfa", pk, "123".toCharArray(), cert);
		keyStoreWriter.saveKeyStore("keystorenas", password);
		//test
		this.keyStoreReader = new KeyStoreReader();
		
		cert = (X509Certificate) keyStoreReader.readCertificate("keystorenas", "123", "asfa");
		if(cert != null) {
			System.out.println(cert);
		}else {
			System.out.println("NKEMA GAAAAAAAAAAAAA+++++++++++++++++++++");
		}
		
		return cert;
	}
}
