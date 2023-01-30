package domain;

import lombok.Data;

@Data
public class ReactNotice {
	private int reactnotice_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
