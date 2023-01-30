package com.jspshop.exception;

//우리가 만드는 Exception
public class MemberException extends RuntimeException{
	
	//개발자가 전달하고픈 에러 메세지...
	public MemberException(String msg) {
		super(msg);
	}
}
