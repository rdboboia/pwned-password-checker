package com.rdboboia.converter;

import javax.swing.JOptionPane;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.rdboboia.converter.BytesToHexString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BytesToHexStringTests {

	byte[] bytes;
	
	public void init() {
		System.out.println("Allocating memory space...");
		bytes = new byte[100_000_000];
		
		System.out.println("Filling array with random numbers...");
		for (int i = 0 ; i < bytes.length ; i++) {
			bytes[i] = (byte) (Math.random() * 120);
		}
	}
	
	@Test
	public void aWarning() {
		int decision = JOptionPane.showConfirmDialog(null, "Warning! Memory intensive tests! Around 5GB of RAM could be used. Continue?", "Warning!", JOptionPane.OK_CANCEL_OPTION);
		if (decision == JOptionPane.CANCEL_OPTION) {
			System.exit(-1);
		}
	}
	
	@Test
	public void test() {
		init();
		
		long s, f;
		s = System.currentTimeMillis();
		
		System.out.println("Converting using default version...");
		BytesToHexString.getHexString(bytes);
		
		f = System.currentTimeMillis();
		System.out.println((f-s) + " ms.");
	}
	
	@Test
	public void testV2() { // Seems to use more memory.
		init();
		
		long s, f;
		s = System.currentTimeMillis();
		
		System.out.println("Converting using V2...");
		BytesToHexString.getHexStringV2(bytes);
		
		f = System.currentTimeMillis();
		System.out.println((f-s) + " ms.");
	}
	
	
//	@Test
//	public void testV3() {
//		init();
//		
//		long s, f;
//		s = System.currentTimeMillis();
//		
//		System.out.println("Converting using V3...");
//		BytesToHexString.getHexStringV3(bytes);
//		
//		f = System.currentTimeMillis();
//		System.out.println((f-s) + " ms.");
//	}
}