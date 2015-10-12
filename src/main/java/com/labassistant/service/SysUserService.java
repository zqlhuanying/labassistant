package com.labassistant.service;

import javax.mail.MessagingException;

import com.labassistant.beans.SysUserEntity;
import com.labassistant.dao.service.IBaseAbstractService;
import com.labassistant.exception.MyRuntimeException;

/**
 * 
 * @author zql
 * @date 2015/09/07
 */
public interface SysUserService extends IBaseAbstractService<SysUserEntity>{
	
	public SysUserEntity login(String username, String pwd);
	
	public void register(SysUserEntity sysUserEntity);
	
	public void sendFindPwdMail(SysUserEntity user) throws MyRuntimeException, MessagingException;
	
	public void resetPwd(SysUserEntity user, String ser);
	
	public boolean validUsername(String username);
	
	public boolean validEmail(String email);
	
	public boolean validTelephone(String telephone);
	
}
