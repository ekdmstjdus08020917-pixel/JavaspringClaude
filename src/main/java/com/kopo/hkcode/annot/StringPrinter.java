package com.kopo.hkcode.annot;

import org.springframework.stereotype.Component;

@Component("stringPrinter")
public class StringPrinter implements Printer {

	@Override
	public  void print(String messge) {
		System.out.println("string call" + messge);
		// TODO Auto-generated method stub

	}

}
