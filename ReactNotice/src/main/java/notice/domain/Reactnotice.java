package notice.domain;

import lombok.Data;

@Data
public class Reactnotice {
	private int reactnotice_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
