//package com.office.tools;
//
//import org.apache.struts.action.RequestProcessor;
//import javax.servlet.http.*;
//import java.io.*;
//
//public class FormToChinese  extends RequestProcessor {
//    public FormToChinese() {
//   }
//   protected boolean processPreprocess(HttpServletRequest request,HttpServletResponse response){
//       try {
//           request.setCharacterEncoding("gb2312");
//       } catch (UnsupportedEncodingException e) {
//              e.printStackTrace();
//       }
//       return true;
//   }
//}
