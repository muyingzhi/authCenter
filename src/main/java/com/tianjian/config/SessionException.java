package com.tianjian.config;

/**
 * @author muyz
 *         Created on 2018/9/26
 */
public class SessionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SessionException(String message){
        super(message);
    }
}
