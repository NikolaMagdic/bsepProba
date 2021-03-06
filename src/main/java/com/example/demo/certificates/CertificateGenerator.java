package com.example.demo.certificates;

import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import com.example.demo.dto.CertificateDTO;
import com.example.demo.dto.ExtendedKeyUsageDTO;
import com.example.demo.dto.KeyUsageDTO;
import com.example.demo.model.IssuerData;
import com.example.demo.model.SubjectData;

public class CertificateGenerator {
	public CertificateGenerator() {}
	
	public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData, CertificateDTO certificateDTO) {
		try {
			//Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
			//Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
			//Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			//Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
			builder = builder.setProvider("BC");

			//Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
			ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

			//Postavljaju se podaci za generisanje sertifikata
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
					new BigInteger(subjectData.getSerialNumber()),
					certificateDTO.getStartDate(),
					certificateDTO.getEndDate(),
					subjectData.getX500name(),
					subjectData.getPublicKey());
			
			// Dodavanje cekiranih KeyUsage u sertifikat
			KeyUsageDTO keyUsageDTO = certificateDTO.getKeyUsageDTO();
			KeyUsage k = new KeyUsage(keyUsageDTO.getcRLSign() | keyUsageDTO.getDataEncipherment() | keyUsageDTO.getDecipherOnly() | keyUsageDTO.getDigitalSignature() | keyUsageDTO.getEncipherOnly() | keyUsageDTO.getKeyAgreement() | keyUsageDTO.getKeyCertSign() | keyUsageDTO.getKeyEncipherment() | keyUsageDTO.getNonRepudiation());
			
			try {
				certGen.addExtension(Extension.keyUsage, false, k);
			} catch (CertIOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Dodavanje cekiranih Extended KeyUsage u sertifikat
			ExtendedKeyUsageDTO extendedKeyUsageDTO = certificateDTO.getExtendedKeyUsageDTO();
			ExtendedKeyUsage eku = new ExtendedKeyUsage(extendedKeyUsageDTO.makeArray());
			
			try {
				certGen.addExtension(Extension.extendedKeyUsage, false, eku);
			} catch (CertIOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Generise se sertifikat
			X509CertificateHolder certHolder = certGen.build(contentSigner);

			//Builder generise sertifikat kao objekat klase X509CertificateHolder
			//Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");

			//Konvertuje objekat u sertifikat
			return certConverter.getCertificate(certHolder);
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (OperatorCreationException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		return null;
	}
}