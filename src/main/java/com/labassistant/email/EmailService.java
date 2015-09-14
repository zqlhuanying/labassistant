package com.labassistant.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.labassistant.exception.MyRuntimeException;

/**
 * 邮件发送服务
 * 
 * @author zql
 * @date 2015/09/11
 */
public class EmailService {

	@Autowired
	private JavaMailSenderImpl sender;

	public void send(SendMailInfo mail) throws MessagingException,
			MyRuntimeException {
		if (SendMailInfo.TYPE_TEXT.equals(mail.getType())) {
			sendTextMail(mail);
		} else if (SendMailInfo.TYPE_HTML.equals(mail.getType())) {
			sendHtmlMail(mail);
		} else {
			throw new MyRuntimeException("邮件类型未确定");
		}
	}

	/**
	 * 发送文本邮件
	 * 
	 * @param mail
	 */
	private void sendTextMail(SendMailInfo mail) {
		// 注意SimpleMailMessage只能用来发送text格式的邮件
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(mail.getToUser());// 接受者
		simpleMailMessage.setFrom(sender.getUsername());
		simpleMailMessage.setSubject(mail.getSubject());// 主题
		simpleMailMessage.setText(mail.getContent());// 邮件内容
		sender.send(simpleMailMessage);
	}

	/**
	 * 发送html格式的邮件
	 * 
	 * @param mail
	 * @throws MessagingException
	 */
	private void sendHtmlMail(SendMailInfo mail) throws MessagingException {
		MimeMessage mailMessage = sender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);
		messageHelper.setTo(mail.getToUser());// 接受者
		messageHelper.setFrom(sender.getUsername());
		messageHelper.setSubject(mail.getSubject());// 主题
		// 邮件内容，注意加参数true，表示启用html格式
		messageHelper.setText(mail.getContent(), true);
		sender.send(mailMessage);
	}
}
