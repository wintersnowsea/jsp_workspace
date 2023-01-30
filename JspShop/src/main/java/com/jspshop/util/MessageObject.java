package com.jspshop.util;

import lombok.Data;

//out.print로 Message뿌릴때 사용할 util
@Data
public class MessageObject {
	private int code; //성공 또는 실패를 식별하는 식별코드 1, 0
	private String msg; //클라이언트에게 전송하고싶은 말
}
