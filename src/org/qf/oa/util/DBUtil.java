package org.qf.oa.util;

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

public class DBUtil {
	private static Properties props = new Properties();

	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	// 定义线程本地变量，每个线程访问它都会获得不同的对象. 使用ThreadLocal使一个连接绑定到一个线程上
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		InputStream is = DBUtil.class.getResourceAsStream("db.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			System.out.println("加载数组库配置文件失败");
			e.printStackTrace();
		}
	}

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

	public static ResultSet getResult(String sql, Object... params) {
		Connection conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
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

	public boolean getExecute(String sql) {
		boolean flag = false;
		Connection conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			flag = ps.executeUpdate() > 00 ? true : false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return flag;
	}

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

	public static void main(String[] args) {
		DBUtil.getResult("select * from oa_user", "");
	}
}
