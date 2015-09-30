package com.labassistant.service;

import org.springframework.stereotype.Service;

import com.labassistant.beans.SysUserEntity;
import com.labassistant.dao.service.BaseAbstractService;
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
}
