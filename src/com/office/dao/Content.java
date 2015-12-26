package com.office.dao;
/*************************************

版权所有: 	吉林省明日科技有限公司

完成日期：	2010-09-15

项目出处：	编程词典

服务社区：	www.mingribook.com

学习社区：	www.mrbccd.com

*************************************/

public class Content {
	public static final int MAXLENGTH=2*1024*1024;
	
	public static final int NUM_PER=4;
	
	public static final String CLASSNAME="net.sourceforge.jtds.jdbc.Driver";
	public static final String URL="jdbc:jtds:sqlserver://localhost:1433/db_office";
	
	public static final String CLASSNAME1="sun.jdbc.odbc.JdbcOdbcDriver";
	public static final String URL1="jdbc:odbc:db_office";
	
	public static final String TB_USER="tb_user";
	public static final String TB_MENU="tb_menu";
	public static final String TB_TEXT="tb_text";
	public static final String TB_MEET="tb_meet";
	public static final String TB_PCARD="tb_pcard";
	public static final String TB_LABEL="tb_label";
	public static final String TB_THING="tb_thing";
	public static final String TB_CAR="tb_car";
	public static final String TB_FILE="tb_file";
	public static final String TB_MAIL="tb_mail";
	public static final String TB_ADVICE="tb_advice";
		
	public static final String LABELNAME="label_name";
	public static final String LABELVALUE="label_value";
	public static final String LABELTYPE="label_type";
	public static final String LABELORDER="label_order";
	
	public static final String USERID="user_id";
	public static final String USERNAME="user_name";
	public static final String USERPASSWORD="user_password";
	public static final String USERTRUENAME="true_name";
	public static final String USERABLE="user_able";
	public static final String USERSEX="user_sex";
	public static final String USEREMAIL="user_email";
	public static final String USERACCESSTIMES="user_accessTimes";
	public static final String USERADDRESS="user_address";
	public static final String USERJOB="user_job";
	public static final String USERTEL="user_tel";
	public static final String USERBRANCH="user_branch";
	public static final String USERGOOD="user_good";
	public static final String USERFOUNDTIME="user_foundTime";
	
	public static final String MENUID="menu_id";
	public static final String MENUNAME="menu_name";
	public static final String MENUPARENTID="menu_parentid";
	public static final String MENUACTION="menu_action";
	public static final String MENUJIBIE="menu_jibie";
	public static final String MENUORDER="menu_order";
	
	public static final String TEXTID="text_id";
	public static final String TEXTSUBJECT="text_subject";
	public static final String TEXTSENDER="text_sender";
	public static final String TEXTGETER="text_geter";
	public static final String TEXTMARK="text_mark";
	public static final String TEXTCONTENT="text_content";
	public static final String TEXTSENDTIME="text_sendtime";
	public static final String TEXTDELGETER="del_geter";
	public static final String TEXTDELSENDER="del_sender";
	
	public static final String MEETID="meet_id";
	public static final String MEETSUBJECT="meet_subject";
	public static final String MEETSPEAKER="meet_speaker";
	public static final String MEETLIST="meet_listener";
	public static final String MEETTIME="meet_time";
	public static final String MEETADDRESS="meet_address";
	public static final String MEETCONTENT="meet_content";
	
	public static final String PCARDID="pcard_id";
	public static final String PCARDSUBJECT="pcard_subject";
	public static final String PCARDAUTHOR="pcard_author";
	public static final String PCARDTIME="pcard_time";
	public static final String PCARDCONTENT="pcard_content";
	
	public static final String THINGID="thing_id";
	public static final String THINGTYPE="thing_type";
	public static final String THINGMOD="thing_model";
	public static final String THINGNUM="thing_number";
	public static final String THINGCOST="thing_cost";
	public static final String THINGTIME="thing_buytime";
	public static final String THINGBRANCH="thing_buybranch";
	
	public static final String CARID="car_id";
	public static final String CARNUM="car_num";
	public static final String CARMODEL="car_model";
	public static final String CARFDJ="car_fdj";
	public static final String CARDRIVER="car_driver";
	public static final String CARMAKER="car_maker";
	public static final String CARBUYTIME="car_buytime";
	public static final String CARNJ="car_nj";
	public static final String CARINFO="car_info";
	public static final String CARSTATUS="car_status";
	
	public static final String FILEID="file_id";
	public static final String FILENAME="file_name";
	public static final String FILEUPER="file_uper";
	public static final String FILESIZE="file_size";
	public static final String FILEUPTIME="file_uptime";
	public static final String FILEINFO="file_info";
	
	public static final String MAILID="mail_id";
	public static final String MAILSUBJECT="mail_subject";
	public static final String MAILGETER="mail_geter";
	public static final String MAILSENDER="mail_sender";
	public static final String MAILCONTENT="mail_content";
	public static final String MAILSBRANCH="mail_sendbranch";
	public static final String MAILSTIME="mail_sendtime";
	public static final String MAILMARK="mail_mark";
	public static final String MAILDELGETER="del_geter";
	public static final String MAILDELSENDER="del_sender";
	
	public static final String ADVICEID="advice_id";
	public static final String ADVICESUBJECT="advice_subject";
	public static final String ADVICESENDER="advice_sender";
	public static final String ADVICESTIME="advice_sendtime";
	public static final String ADVICECONTENT="advice_content";
	public static final String ADVICESBRANCH="advice_sendbranch";
	
	public Content(){}
}
