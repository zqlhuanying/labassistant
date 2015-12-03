package com.labassistant.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.SysUserEntity;
import com.labassistant.constants.AppConfig;
import com.labassistant.context.MySystemContext;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.email.EmailService;
import com.labassistant.email.SendMailInfo;
import com.labassistant.exception.MyRuntimeException;
import com.labassistant.utils.EncryptUtil;
import com.labassistant.utils.Uploader;

/**
 * 用户登录服务
 * 
 * @author zql
 * @date 2015/09/07
 */
@Service
public class SysUserServiceImpl extends BaseAbstractService<SysUserEntity>
		implements SysUserService {
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public SysUserEntity login(String username, String pwd) {
		String hql = "from SysUserEntity where nickName = ? or telNo = ? or eMail = ?";
		SysUserEntity sysUser = findOneByHql(hql, username, username, username);		// 此方法 若查询不到相应的记录 会返回NULL
		if (sysUser != null
				&& sysUser.getPwd().equals(EncryptUtil.MD5Digest(pwd))) {
			return sysUser;
		}
		return null;
	}

	// 第三方登录
	@Override
	public SysUserEntity thirdLogin(String old_token, String new_token, int source) {
		String hql = "from SysUserEntity where access_token = ? and nSource = ?";
		SysUserEntity sysUser = findOneByHql(hql, old_token, source);		
		if (sysUser != null) {
			sysUser.setAccess_token(new_token);
			update(sysUser);
			return sysUser;
		} else {
			SysUserEntity newUser = new SysUserEntity();
			newUser.setNickName(uuid());
			newUser.setPwd(EncryptUtil.MD5Digest(uuid()));
			newUser.seteMail("");
			newUser.setnState(0);
			newUser.setnSource(source);
			newUser.setAccess_token(new_token);
			Serializable pk = save(newUser);
			newUser.setUserID((String)pk);
			return newUser;
		}
	}
	
	@Override
	public void register(SysUserEntity sysUser) {
		if (validUsername(sysUser.getNickName())) {
			throw new MyRuntimeException("用户名已存在");
		}
		if (validEmail(sysUser.geteMail())) {
			throw new MyRuntimeException("邮箱已被占用");
		}
		if (validTelephone(sysUser.getTelNo())) {
			throw new MyRuntimeException("手机号已被使用");
		}
		sysUser.setPwd(EncryptUtil.MD5Digest(sysUser.getPwd()));
		Serializable pk = save(sysUser);
		// 对用户头像进行处理
		if(StringUtils.isNotBlank(sysUser.getIconStream())){
			sysUser.setUserID((String)pk);
			sysUser.setIcon(processIcon(sysUser));
			super.update(sysUser);
		}
	}

	@Override
	public void sendFindPwdMail(SysUserEntity user) throws MyRuntimeException, MessagingException{
		String validCode = uuid();
		// 发送前，先写入数据库
		String hql = "from SysUserEntity where eMail = ?";
		SysUserEntity sysUser = findOneByHql(hql, user.geteMail());
		sysUser.setF_validCode(validCode);
		sysUser.setF_timestamp(new Timestamp(new Date().getTime()));
		update(sysUser);
		
		String validUrl = AppConfig.DOMAIN_PAGE + "/getLost?un=" + EncryptUtil.encode(sysUser.getNickName()) + "&ser=" + EncryptUtil.encode(validCode);
		
		// 发送邮件
		SendMailInfo sendMailInfo = new SendMailInfo();
		sendMailInfo.setType(SendMailInfo.TYPE_HTML);
		sendMailInfo.setToUser(user.geteMail());
		sendMailInfo.setSubject("取回密码");
		sendMailInfo.setContent(findPwdHtmlTemplate(validUrl));
		emailService.send(sendMailInfo);
	}
	
	@Override
	public void resetPwd(SysUserEntity user, String ser){
		String hql = "from SysUserEntity where f_validCode = ?";		
		SysUserEntity sysUser = findOneByHql(hql, EncryptUtil.decode(ser));
		if(sysUser == null){
			throw new MyRuntimeException("链接无效，请重新找回密码");
		}
		// 链接有效期三天
		if(new Date().getTime() > sysUser.getF_timestamp().getTime() + 3 * 24 * 60 * 60 * 1000){
			throw new MyRuntimeException("链接无效，请重新找回密码");
		}
		sysUser.setPwd(EncryptUtil.MD5Digest(user.getPwd()));
		update(sysUser);
	}
	
	@Override
	public void update(SysUserEntity user){
		SysUserEntity sysUser = get(user.getUserID());
		if(StringUtils.isNotBlank(user.getNickName())){
			sysUser.setNickName(user.getNickName());
		}
		if(StringUtils.isNotBlank(user.geteMail())){
			sysUser.seteMail(user.geteMail());
		}
		if(StringUtils.isNotBlank(user.getTelNo())){
			sysUser.setTelNo(user.getTelNo());
		}
		if(StringUtils.isNotBlank(user.getProvinceID())){
			sysUser.setProvinceID(user.getProvinceID());
		}
		if(StringUtils.isNotBlank(user.getCityID())){
			sysUser.setCityID(user.getCityID());
		}
		if(StringUtils.isNotBlank(user.getCollegeID())){
			sysUser.setCollegeID(user.getCollegeID());
		}
		if(StringUtils.isNotBlank(user.getMajorID())){
			sysUser.setMajorID(user.getMajorID());
		}
		if(StringUtils.isNotBlank(user.getEducationID())){
			sysUser.setEducationID(user.getEducationID());
		}
		if(StringUtils.isNotBlank(user.getIconStream())){
			sysUser.setIcon(processIcon(user));
		}
		super.update(sysUser);
	}
	
	@Override
	public boolean validUsername(String username) {
		String hql = "from SysUserEntity where nickName = ?";
		int total = getCount(hql, true, username);
		return total > 0;
	}

	@Override
	public boolean validEmail(String email) {
		String hql = "from SysUserEntity where eMail = ?";
		int total = getCount(hql, true, email);
		return total > 0;
	}

	@Override
	public boolean validTelephone(String telephone) {
		if(StringUtils.isBlank(telephone)){
			return false;
		}
		String hql = "from SysUserEntity where telNo = ?";
		int total = getCount(hql, true, telephone);
		return total > 0;
	}
	
	/**
	 * 对用户头像处理
	 * @param sysUser
	 * @return
	 * @throws Exception 
	 */
	private String processIcon(SysUserEntity sysUser){
		InputStream inputStream = null;
		try{
			byte[] img = EncryptUtil.BASE64Decode(sysUser.getIconStream());
			inputStream = new ByteArrayInputStream(img);
		} catch (IOException e) {
			System.out.println("处理图片二进制流失败");
			e.printStackTrace();
		}
		
		Uploader upload = new Uploader(MySystemContext.getMyRequest());
		String iconName = sysUser.getIconName();
		if(StringUtils.isBlank(iconName)){
			iconName = "1.jpg";
		}
		upload.setOriginalName(iconName);
		upload.setSavePath("upload/icon/" + sysUser.getUserID());
		upload.setInputStream(inputStream);
		try {
			upload.upload();
		} catch (Exception e) {
			System.out.println("上传头像失败");
			e.printStackTrace();
		}
		
		return upload.getUrl();
	}
	
	private String findPwdHtmlTemplate(String validUrl){
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>找回密码</title>");  
		sb.append("</head>");    
		sb.append("<body>");  
		sb.append("取回密码说明</br>");
		sb.append("您收到这封邮件，是由于这个邮箱地址在 " + AppConfig.COMPANY + " 被登记为用户邮箱， 且该用户请求使用 Email 密码重置功能所致。");
		sb.append("<p>------------------------------------------------------------------------</p>");
		sb.append("<p><b>重要！！</b></p>");
		sb.append("<p>------------------------------------------------------------------------</p>");
		sb.append("<p>如果您没有提交密码重置的请求或不是 " + AppConfig.COMPANY + " 的注册用户，请立即忽略并删除这封邮件。只有在您确认需要重置密码的情况下，才需要继续阅读下面的内容。</p>");
		sb.append("<p>------------------------------------------------------------------------</p>");
		sb.append("<p><b>重置密码说明！！</b></p>");
		sb.append("<p>------------------------------------------------------------------------</p>");
		sb.append("您只需在提交请求后的三天内，通过点击下面的链接重置您的密码：</br>");
		sb.append(validUrl + "</br>");  
		sb.append("(如果上面不是链接形式，请将该地址手工粘贴到浏览器地址栏再访问)");
		sb.append("在上面的链接所打开的页面中输入新的密码后提交，您即可使用新的密码登录网站了。您可以在用户控制面板中随时修改您的密码。</br>"); 
		sb.append("<p>此致:</br>");
		sb.append("" + AppConfig.COMPANY + " 管理团队 ");
		sb.append("www.hualang-it.com.cn</p>");
		sb.append("</body>");  
		sb.append("</html>");  	
		return sb.toString();
	}
	
	/**
	 * 生成uuid
	 * @return
	 */
	private String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
