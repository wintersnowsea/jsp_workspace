package com.jspshop.exception;

//우리가 만드는 Exception
public class PsizeException extends RuntimeException{
	
	//개발자가 전달하고픈 에러 메세지...
	public PsizeException(String msg) {
		super(msg);
	}
}
