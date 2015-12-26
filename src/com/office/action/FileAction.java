//package com.office.action;
//
//import java.sql.ResultSet;
//import java.util.Date;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.struts.action.Action;
//import org.apache.struts.action.ActionMessage;
//import org.apache.struts.action.ActionMessages;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//
//import com.office.actionform.FileForm;
//import com.office.actionform.LogonForm;
//import com.office.dao.Content;
//import com.office.dao.DB;
//import com.office.tools.Change;
//import com.office.tools.CheckUserAble;
//
//public class FileAction extends Action {
//	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//		
//		/* �ж��û��Ƿ��¼ */
//		if(logonuser==null){
//			ActionMessages errors=new ActionMessages();
//			errors.add("notAllow",new ActionMessage("office.logon.notallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallowlogon");
//		}
//		
//		String mark=mapping.getParameter();
//		ActionForward forward=new ActionForward();
//		if(mark.equals("file_look"))
//			forward=executeFileLook(mapping,form,request,response);
//		if(mark.equals("file_download"))
//			forward=executeFileDownLoad(mapping,form,request,response);
//		if(mark.equals("file_up"))
//			forward=executeFileUp(mapping,form,request,response);
//		if(mark.equals("file_del"))
//			forward=executeFileDel(mapping,form,request,response);
//	    return forward;
//	}
//	
//	public ActionForward executeFileLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu1", "file");
//		session.setAttribute("selectmenu2", "file_look");
//	    String sql="select * from "+Content.TB_FILE;
//	   
//	    String str_currentpage=request.getParameter("page");
//	    System.out.println("ҳ������"+str_currentpage);
//        if(str_currentpage==null||str_currentpage.equals(""))
//			 str_currentpage="0";
//		int currentpage=Change.strtoint(str_currentpage);
//		if(currentpage<=0)
//			currentpage=1;
//
//	    DB db=new DB();
//	    ArrayList filelist=db.getallfilelist(sql,currentpage);
//	    
//	    session.setAttribute("numrs",Integer.toString(db.getNumrs()));
//	    session.setAttribute("numper", Integer.toString(db.getNumper()));
//	    session.setAttribute("currentpage",Integer.toString(db.getCurrentPage()));
//	    session.setAttribute("numpages", Integer.toString(db.getNumPages()));
//	    session.setAttribute("filelist", filelist);
//	    
//		return mapping.findForward("success");
//	}
//	public ActionForward executeFileDownLoad(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu2", "file_look");
//		ActionMessages errors=new ActionMessages();
//		String fileid=request.getParameter("id");
//		String filename=request.getParameter("filename");
//		String endname=filename.substring(filename.lastIndexOf("."));
//		String filepath=session.getServletContext().getRealPath("");
//		filepath+="\\file\\allfile\\";
//		byte b[]=new byte[100];
//		File file=new File(filepath,fileid+endname);
//		if(!file.exists()){
//			errors.add("downloadR",new ActionMessage("office.file.noexists"));
//			saveErrors(request,errors);
//			return mapping.findForward("false");
//		}
//		
//		response.setHeader("Content-disposition","attachment;filename="+filename);
//		try{
//            OutputStream outstream=response.getOutputStream();
//			FileInputStream in=new FileInputStream(file);
//		    int n=0;
//		    while((n=in.read(b))!=-1){
//			   outstream.write(b,0,n);
//		    }
//			in.close();
//			outstream.close();
//		}
//		catch(Exception e){e.printStackTrace();}
//		return mapping.findForward("success");
//	}
//	public ActionForward executeFileUp(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu2", "file_up");
//		ActionMessages errors=new ActionMessages();
//		FileForm fileform=(FileForm)form;
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//		if(!CheckUserAble.check(logonuser)){
//			session.setAttribute("selectmenu2", "");
//			errors.add("upR",new ActionMessage("office.user.noallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallow");
//		}
//		
//		String uptype=request.getParameter("uptype");
//		if(uptype==null||uptype.equals(""))
//			uptype="link";
//		if(uptype.equals("link"))
//			return mapping.findForward("up");
//		
//		if(uptype.equals("up")){
//		  String filename = fileform.getUpfile().getFileName();
//	       if(filename==null||filename.equals("")){
//	    	   errors.add("upR",new ActionMessage("office.file.nofile"));
//	    	   saveErrors(request,errors);
//	    	   return mapping.findForward("false");
//	       }
//	       if(filename.lastIndexOf(".")==-1){
//	    	   errors.add("upR",new ActionMessage("office.file.filename.error"));
//	    	   saveErrors(request,errors);
//	    	   return mapping.findForward("false");
//	       }
//	      String uper = ((LogonForm)session.getAttribute("logonuser")).getUsername();
//	      int size = fileform.getUpfile().getFileSize();
//	       if(size>Content.MAXLENGTH){
//	    	   errors.add("uprR",new ActionMessage("office.file.filesize.error"));
//	    	   saveErrors(request,errors);
//	    	   return mapping.findForward("false");
//	       }
//	      
//	      String filesize = Change.inttostr(size)+" �ֽ�";
//	      String uptime = Change.datetostr(new Date());
//	      String fileinfo = fileform.getFileinfo();
//        
//	      String sql="insert into "+Content.TB_FILE+" values('"+filename+"','"+uper+"','"+filesize+"','"+uptime+"','"+fileinfo+"')";    
//	      DB db=new DB();
//	      db.inseart(sql);	     
//	      
//	      sql="select * from "+Content.TB_FILE+" where "+Content.FILEUPTIME+"='"+uptime+"'";
//	      String subfilename="";
//	      String endfilename="";
//	      String upfilename="";
//	      ResultSet rs=db.getRs(sql);
//	      try{
//	    	  if(rs.next()){
//	    		  subfilename=Change.inttostr(rs.getInt(Content.FILEID));
//	              endfilename=filename.substring(filename.lastIndexOf("."));
//	    	      upfilename=subfilename+endfilename;
//	    	  }
//	      }
//	      catch(Exception e){e.printStackTrace();}
//	      
//	      String path=session.getServletContext().getRealPath("/");
//	      path=path+"file\\allfile\\";
//	        System.out.println(path);
//	      
//	      try{
//	         InputStream in=fileform.getUpfile().getInputStream();
//	         File file=new File(path,upfilename);
//	         FileOutputStream file_stream=new FileOutputStream(file);
//	         byte a[]=new byte[size];
//	         int read=0;
//	         int allread=0;
//	         while(allread<size)
//	          {
//	  	         read=in.read(a,allread,size);
//	  	         allread+=read;
//	  	         System.out.println("read:"+read);
//	  	         System.out.println("allread:"+allread);
//	          }
//              file_stream.write(a);
//              in.close();
//              file_stream.close();
//	      }
//	      catch(Exception e){e.printStackTrace();}
//		}
//		fileform.clear();
//		errors.add("uprR",new ActionMessage("office.file.up.success"));
//		saveErrors(request,errors);
//		return mapping.findForward("success");
//	}
//	public ActionForward executeFileDel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu2", "file_look");
//		ActionMessages errors=new ActionMessages();
//		String filepath=session.getServletContext().getRealPath("");
//		filepath+="\\file\\allfile\\";
//
//		FileForm fileform=(FileForm)form;
//		int id=fileform.getFileid();
//		String delete=fileform.getDelete();
//		if(delete==null||delete.equals(""))
//			delete="false";
//	   
//		if(delete.equals("true")){
//			String filename=fileform.getFilename();
//			String endname=filename.substring(filename.lastIndexOf("."));
//			File delfile=new File(filepath,id+endname);
//			if(delfile.exists())
//				delfile.delete();
//			else{
//				  fileform.setDelete(delete);
//				  errors.add("deleteR",new ActionMessage("office.file.already.delet.error"));
//				  saveErrors(request,errors);
//				  return mapping.findForward("false");
//			} 
//		}
//		
//		String sql="delete from "+Content.TB_FILE+" where "+Content.FILEID+"="+id;
//		DB db=new DB();
//		int i=db.del(sql);
//		if(i==0){
//			errors.add("deleteR",new ActionMessage("office.del.false"));
//			saveErrors(request,errors);
//			return mapping.findForward("false");
//		}
//		return mapping.findForward("success");
//	}
//}