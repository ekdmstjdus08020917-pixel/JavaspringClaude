package com.kopo.hkcode.pojo;

public class ConsolePrinter implements Printer {

	@Override
	public void print(String messge) {
		System.out.println("console call" + messge);
		// TODO Auto-generated method stub

	}

}
