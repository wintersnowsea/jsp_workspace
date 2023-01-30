package com.jspshop.exception;

//우리가 만드는 Exception
public class ProductException extends RuntimeException{
	
	//개발자가 전달하고픈 에러 메세지...
	//Exception을 이용하여 우리만의 예외를 만들어서 일으키자 --> 우리가 만든건 강요하지 않음
	//외부에 알리는게 목적!
	public ProductException(String msg) {
		super(msg);
	}
}
