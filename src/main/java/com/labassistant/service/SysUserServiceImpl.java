package com.labassistant.service;

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
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.email.EmailService;
import com.labassistant.email.SendMailInfo;
import com.labassistant.exception.MyRuntimeException;
import com.labassistant.utils.EncryptUtil;

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
		save(sysUser);
	}

	@Override
	public void sendFindPwdMail(SysUserEntity user) throws MyRuntimeException, MessagingException{
		if(!validEmail(user.geteMail())){
			throw new MyRuntimeException("邮箱不存在");
		}
		String validCode = uuid();
		String validUrl = AppConfig.DOMAIN_PAGE + "/getLost?ser=" + EncryptUtil.encode(validCode);
		// 发送前，先写入数据库
		String hql = "from SysUserEntity where eMail = ?";
		SysUserEntity sysUser = findOneByHql(hql, user.geteMail());
		sysUser.setF_validCode(validCode);
		sysUser.setF_timestamp(new Timestamp(new Date().getTime()));
		update(sysUser);
		// 发送邮件
		SendMailInfo sendMailInfo = new SendMailInfo();
		sendMailInfo.setType(SendMailInfo.TYPE_HTML);
		sendMailInfo.setToUser(user.geteMail());
		sendMailInfo.setSubject("找回密码");
		sendMailInfo.setContent(findPwdHtmlTemplate(validUrl));
		emailService.send(sendMailInfo);
	}
	
	@Override
	public void resetPwd(SysUserEntity user, String ser){
		String hql = "from SysUserEntity where f_validCode = ?";		
		SysUserEntity sysUser = findOneByHql(hql, EncryptUtil.decode(ser));
		if(sysUser == null){
			throw new MyRuntimeException("链接无效");
		}
		// 链接有效期30分钟
		if(new Date().getTime() > sysUser.getF_timestamp().getTime() + 30 * 60 * 1000){
			throw new MyRuntimeException("链接无效");
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
		String hql = "from SysUserEntity where telNo = ?";
		int total = getCount(hql, true, telephone);
		return total > 0;
	}
	
	private String findPwdHtmlTemplate(String validUrl){
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>找回密码</title>");  
		sb.append("</head>");    
		sb.append("<body>");  
		sb.append("您好，");
		sb.append("请点击以下链接找回密码：" + validUrl);  
		sb.append("<p>--------</p>");  
		sb.append("链接30分钟有效");
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
