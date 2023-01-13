package com.edu.noticeapp.repository;

import java.sql.*;
import java.util.ArrayList;

import com.edu.noticeapp.domain.Notice;

//Notice ���̺� ���� CRUD�� �����ϴ� ��ü
//javaSE, javaEE���� ���������� ��밡���� ���ر��� �����غ���
public class NoticeDAO {
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="jsp";
	String password="1234";

	//�� ����ϱ�
	public int insert(Notice notice){
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			String sql="insert into notice(notice_idx, title, writer, content)";
			sql+=" values(seq_notice.nextval, ?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			result=pstmt.executeUpdate(); //DML����

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}	
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		}
		System.out.println("insert() ȣ��");
		return result;
	}

	//��� ���ڵ� ��������
	public ArrayList selectAll(){
		ArrayList list=new ArrayList();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			String sql="select * from notice order by notice_idx desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery(); //select ����
			while(rs.next()){
				Notice notice=new Notice();
				notice.setNotice_idx(rs.getInt("notice_idx"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));

				list.add(notice);
			}

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}	
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}	
		}
		return list;
	}

	//�󼼺���_�Ѱ� ��ȸ
	public Notice select(int notice_idx){
		Notice notice=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			String sql="select * from notice where notice_idx=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			rs=pstmt.executeQuery();
			if(rs.next()){
				notice=new Notice();
				
				notice.setNotice_idx(rs.getInt("notice_idx"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}	
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return notice;
	}

	//�Ѱ� ����
	public int delete(int notice_idx){
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			String sql="delete notice where notice_idx=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			result=pstmt.executeUpdate(); //��������

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}	
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	//�Ѱ� ���� --> �۾���� �����
	public int update(Notice notice){
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			String sql="update notice set title=?, writer=?, content=?";
			sql+=" where notice_idx=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNotice_idx());

			result=pstmt.executeUpdate(); //DML����
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}	
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	//�� ��ȸ�� ����
	public int updateHit(int notice_idx){
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);

			String sql="update notice set hit=hit+1 where notice_idx=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			result=pstmt.executeUpdate(); //��������

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}	
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
