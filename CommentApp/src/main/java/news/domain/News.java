package news.domain;

import java.util.List;

import lombok.Data;

@Data
public class News {
	private int news_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	
	//하나의 뉴스기사는 여러명의 자식글을 보유할 수 있다
	//mybatis에서는 이런 관계를 가리켜 컬렉션이라 부른다
	private List<Comments> commentsList;
}
