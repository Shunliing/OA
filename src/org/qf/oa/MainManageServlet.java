package org.qf.oa;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/MainManageServlet")
public class MainManageServlet extends HttpServlet{
	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = -5705454088112530297L;
	//private SearchDB searchDB;//����SearchDB������
	private int zongjingli=0;//���ڱ�ʾ�ܾ����ҽ����������
	private int shichang=0;//���ڱ�ʾ�г��������������
	private int jishu=0;//���ڱ�ʾ�����������������
	private int gongguan=0;//���ڱ�ʾ���ز������������
	private int caiwu=0;//���ڱ�ʾ���񲿽����������
	private int houqin=0;//���ڱ�ʾ���ڲ������������
	
//	public int getTotal(){//total��getter����
//		return this.zongjingli+this.shichang+this.jishu+this.gongguan+this.caiwu+this.houqin;
//	}
//	public int getRight(){//right��getter����
//		return (Integer)ActionContext.getContext().getSession().get("right");
//	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String sql="select alarm_dept,count(*) from oa_alarm where alarm_state=0 group by alarm_dept";
		//List<User> list = DBUtil.getList(sql, "");
		
		
		
		
		req.getRequestDispatcher("mainManage.jsp").forward(req, resp);
	}
	public void execute(){
		
		List list =null;//=this.searchDB.getArrayList(sql);//����getArrayList����������б�
		int size=list.size();//����б��С
		for(int i=0;i<size;i++){//�����б�
			Object[] o=(Object[])list.get(i);//����б��е�һ������
			String deptnumber=(String)o[0];//��ò��ŵı��
			if(deptnumber.equals("10000")){//�����1000˵�����ܾ�����
				zongjingli=((java.math.BigDecimal)o[1]).intValue();
			}
			else if(deptnumber.equals("10001")){//�����10001˵�����г���
				shichang=((java.math.BigDecimal)o[1]).intValue();
			}
			else if(deptnumber.equals("10002")){//�����10002˵���Ǽ�����
				jishu=((java.math.BigDecimal)o[1]).intValue();
			}
			else if(deptnumber.equals("10003")){//�����10003˵���ǹ��ز�
				gongguan=((java.math.BigDecimal)o[1]).intValue();
			}
			else if(deptnumber.equals("10004")){//�����10004˵���ǲ���
				caiwu=((java.math.BigDecimal)o[1]).intValue();
			}
			else if(deptnumber.equals("10005")){//�����10005˵���Ǻ��ڲ�
				houqin=((java.math.BigDecimal)o[1]).intValue();
			}
		}
		;
	}
}