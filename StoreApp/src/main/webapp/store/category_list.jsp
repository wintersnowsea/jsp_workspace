<%@page import="store.domain.Category"%>
<%@page import="java.util.List"%>
<%@page import="store.repository.CategoryDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! CategoryDAO categoryDAO=new CategoryDAO(); %>
<%
	//비동기로 요청을 시도하는 클라이언트를 위해 html문서 전체를 응답할 필요없고,
	//클라이언트가 원하는 데이터만 골라서 보내주면 된다
	//왜? 클라이언트는 화면 전체를 원하지 않는다
	List<Category> categoryList = categoryDAO.selectAll();
	//out.print("카테고리 수는 "+categoryList.size());
	//out.print(categoryList);
	
	//고생하는 스타일
	//개발자가 json 문자열을 직접 처리
	StringBuilder sb = new StringBuilder();
	sb.append("[");
	for(int i=0;i<categoryList.size();i++){
		Category category = categoryList.get(i);		
		sb.append("{");
		sb.append("\"category_idx\":"+category.getCategory_idx()+",");
		sb.append("\"category_name\":\""+category.getCategory_name()+"\"");
		if(i<categoryList.size()-1){
			sb.append("},"); //사이즈 -1보다 작을때만		
		}else{
			sb.append("}");
		}
	}
		sb.append("]");		
	
	out.print(sb.toString());
%>

