package com.jspshop.domain;

import lombok.Data;

@Data
public class Color {
	private int color_idx;
	private Product product; //부모인 Product을 assocation으로 가져옴
	private String color_name;
}
