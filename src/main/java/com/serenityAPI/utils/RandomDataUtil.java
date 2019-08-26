package com.serenityAPI.utils;

import java.util.Random;

public class RandomDataUtil {

	public String generateRandomData() {
		String charSequence = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer buffer = new StringBuffer(charSequence.charAt(random.nextInt(charSequence.length())));
		
		for (int i=0; i< 8; i++) {
			buffer.append(charSequence.charAt(random.nextInt(charSequence.length())));
		}		
		return new String(buffer);		
	}
}
