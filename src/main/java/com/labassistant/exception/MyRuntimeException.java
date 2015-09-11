package com.labassistant.exception;

/**
 * 自定义异常
 * @author zql
 * @date 2015/09/07
 */
public class MyRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 4242197816103175130L;

	public MyRuntimeException(){}   
    
    public MyRuntimeException(String message) {   
        super(message);           
    }
    
    public MyRuntimeException(Throwable throwable){
        super(throwable);
    }
    
    public MyRuntimeException(String message, Throwable throwable){
        super(message, throwable);
    }
}
