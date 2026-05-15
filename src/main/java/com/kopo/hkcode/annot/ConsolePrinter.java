package com.kopo.hkcode.annot;

import org.springframework.stereotype.Component;

@Component("consolePrinter")
public class ConsolePrinter implements Printer {

	@Override
	public void print(String messge) {
		System.out.println("console call" + messge);
		// TODO Auto-generated method stub

	}

}
