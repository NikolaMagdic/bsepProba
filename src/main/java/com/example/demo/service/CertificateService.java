package com.example.demo.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
	
	KeyStore keyStore;

	public CertificateService() {}
	
	public X509Certificate createCertificate(X509Certificate cert, PrivateKey pk) {

		keyStoreWriter = new KeyStoreWriter(); 
		
		//BufferedInputStream in = new BufferedInputStream(new FileInputStream("/data/keystore.p12"));
		
		keyStoreWriter.loadKeyStore("src/main/resources/data/root_keystore.p12", "123".toCharArray());
		keyStoreWriter.write("asfa", pk, "123".toCharArray(), cert);
		
		return cert;
	}
}
