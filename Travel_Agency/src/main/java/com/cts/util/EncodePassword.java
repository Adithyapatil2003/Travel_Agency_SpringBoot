package com.cts.util;
 
import java.beans.Encoder;
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
public class EncodePassword {
 
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("adi"));
		System.out.println(bCryptPasswordEncoder.encode("bhu"));
 
	}
 
}