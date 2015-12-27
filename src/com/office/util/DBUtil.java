package com.office.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.office.dao.Content;
import com.office.dao.Page;
import com.office.vo.Document;
import com.office.vo.Meet;
import com.office.vo.Pcard;
/*
 * 数据库工具类
 */
public class DBUtil {
	//使用属性文件
	private static Properties props = new Properties();

	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	// 定义线程本地变量，每个线程访问它都会获得不同的对象. 使用ThreadLocal使一个连接绑定到一个线程上
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private int num_per = 0;
	private int num_rs = 0;
	private int num_currentpage = 1;
	private int num_pages = 1;
	static {
		InputStream is = DBUtil.class.getResourceAsStream("db.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			System.out.println("加载数组库配置文件失败");
			e.printStackTrace();
		}
	}
    //获取连接    
	public static Connection getConn() {
		conn = tl.get();
		if (null == conn) {
			try {
				Class.forName(props.getProperty("jdbc.driver"));
				conn = DriverManager.getConnection(
						props.getProperty("jdbc.url"),
						props.getProperty("jdbc.username"),
						props.getProperty("jdbc.password"));
				System.out.println("conn success!!!");
			} catch (ClassNotFoundException e) {
				System.out.println("驱动加载异常！");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("获取连接异常");
				e.printStackTrace();
			}

		}
		return conn;
	}
    //获取结果集
	public static ResultSet getResult(String sql) {
		Connection conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("sql查询异常!!");
			e.printStackTrace();
		}
		return rs;
	}	

	// 获取结果集，放入List中
	public static List<Map<String, Object>> getList(String sql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSetMetaData rmd = null;
		Connection conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rmd = rs.getMetaData();
			int columns = rmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columns; i++) {
					map.put(rmd.getColumnName(i), rs.getObject(i));
					list.add(map);
				}
			}
		} catch (SQLException e) {
			System.out.println("sql查询异常!!");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}
    //获取增删改的结果
	public static boolean getExecute(String sql) {
		boolean flag = false;
		Connection conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			flag = ps.executeUpdate() > 00 ? true : false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return flag;
	}
   //关闭结果
	public static void closeAll() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	// 获取公文相关信息
	public ArrayList<Document> getalltextlist(String sql, int pos) {
		ArrayList<Document> list = new ArrayList<Document>();
		try {
			rs = getResult(sql);
			Page page = new Page(rs, pos);
			setPage(page);
			int i = 1;
			rs = page.getRs();
			while (rs.next() && i <= num_per) {
				Document textform = new Document();
				textform.setTextid(rs.getInt(Content.TEXTID));
				textform.setSubject(rs.getString(Content.TEXTSUBJECT));
				textform.setSender(rs.getString(Content.TEXTSENDER));
				textform.setGeter(rs.getString(Content.TEXTGETER));
				textform.setMark(rs.getString(Content.TEXTMARK));
				textform.setContent(rs.getString(Content.TEXTCONTENT));
				textform.setSendtime(rs.getString(Content.TEXTSENDTIME));
				list.add(textform);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return list;
	}

	public void setPage(Page page) {
		this.num_per = page.getNumper();
		this.num_rs = page.getNumrs();
		this.num_pages = page.getNumPages();
		this.num_currentpage = page.getCurrentPage();
	}

	public int getNum_per() {
		return num_per;
	}

	public void setNum_per(int num_per) {
		this.num_per = num_per;
	}

	public int getNum_rs() {
		return num_rs;
	}

	public void setNum_rs(int num_rs) {
		this.num_rs = num_rs;
	}

	public int getNum_currentpage() {
		return num_currentpage;
	}

	public void setNum_currentpage(int num_currentpage) {
		this.num_currentpage = num_currentpage;
	}

	public int getNum_pages() {
		return num_pages;
	}

	public void setNum_pages(int num_pages) {
		this.num_pages = num_pages;
	}

	public ArrayList<Meet> getallmeetlist(String sql, int pos) {

		ArrayList<Meet> list = new ArrayList<>();
		try {
			rs = getResult(sql);
			Page page = new Page(rs, pos);
			setPage(page);
			int i = 1;
			rs = page.getRs();
			while (rs.next() && i <= num_per) {
				Meet meetform = new Meet();
				meetform.setMeetid(rs.getInt(Content.MEETID));
				meetform.setSubject(rs.getString(Content.MEETSUBJECT));
				meetform.setSpeaker(rs.getString(Content.MEETSPEAKER));
				meetform.setListener(rs.getString(Content.MEETLIST));
				meetform.setTime(rs.getString(Content.MEETTIME));
				meetform.setAddress(rs.getString(Content.MEETADDRESS));
				meetform.setContent(rs.getString(Content.MEETCONTENT));
				list.add(meetform);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	 //获取公告相关信息
	 public ArrayList<Pcard> getallpcardlist(String sql,int pos){
	    	System.out.println(sql);
	    	ArrayList<Pcard> list=new ArrayList<Pcard>();	    	
	    	try{
	    		rs=getResult(sql);
	    		Page page=new Page(rs,pos);
	    		setPage(page);
	    		int i=1;
	    		//rs=page.getRs();
	    		while(rs.next()&&i<=num_per){
	    		   Pcard pcard=new Pcard();
	    		   pcard.setPcardid(rs.getInt(Content.PCARDID));
	    		   pcard.setSubject(rs.getString(Content.PCARDSUBJECT));
	    		   pcard.setAuthor(rs.getString(Content.PCARDAUTHOR));
	    		   pcard.setTime(rs.getString(Content.PCARDTIME));
	    		   pcard.setContent(rs.getString(Content.PCARDCONTENT));
	    		   list.add(pcard);
	    		   i++;
	    	    }
	    	}
	    	catch(Exception e){e.printStackTrace();}
	        return list;
	    }
}
