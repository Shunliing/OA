package com.office.dao;

import java.sql.ResultSet;
import com.office.dao.Content;
/*
 * 分页相关的类
 */
public class Page {
	private  ResultSet rs=null;
	private  int num_rs=0;
	//每页记录数
	private  int num_per=Content.NUM_PER;
	//页数
	private  int num_pages=0;
	//当前页
	private  int current_page=1;
	
	public Page(ResultSet rs,int current_page){
	    this.rs=rs;
		setNumrs(rs);                //�����ܼ�¼��
	       System.out.println("Page:�ܼ�¼��:"+getNumrs());
		setNumPages();               //������ҳ��
		   System.out.println("Page:��ҳ��:"+getNumPages());
		setCurrentPage(current_page);//���õ�ǰҳ��
	       System.out.println("Page:��ǰҳ��:"+getCurrentPage());
	}
	
	public  void setCurrentPage(int current_page){
		if(current_page<=0)
			current_page=1;
		if(current_page>getNumPages())
			current_page=getNumPages();
		this.current_page=current_page;
	}
    public  int getCurrentPage(){
		
		return this.current_page;
	}
    
    
    public  void setNumPages(){
    	this.num_pages=((num_rs%num_per==0)?(num_rs/num_per):(num_rs/num_per)+1);
    }
    public  int getNumPages(){
		return this.num_pages; 
	}
    
    
    public  void setNumrs(ResultSet rs){
		try{
		 // rs.last();
		  this.num_rs=rs.getRow();
		}
		catch(Exception e){e.printStackTrace();}
	}
	public  int getNumrs(){
		return this.num_rs;
	}
	
	
	public ResultSet getRs(){
		int rspos=(this.current_page-1)*this.num_per+1;
		try{
		    this.rs.absolute(rspos);
		    this.rs.previous();
		}
		catch(Exception e){e.printStackTrace();}
	    return this.rs;
	}
	
	public int getNumper(){
		return this.num_per;
	}
}
