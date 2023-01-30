package com.jspshop.domain;

import lombok.Data;

@Data
public class Cart{
	private Product product; //has a 관계
	//상품에는 존재하지않는 속성인 개수를 추가하자
	private int ea;
	private Member member;
}
