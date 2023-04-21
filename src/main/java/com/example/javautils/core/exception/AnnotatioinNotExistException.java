package com.example.javautils.core.exception;

public class AnnotatioinNotExistException extends Exception{
    String msg = null;
    public AnnotatioinNotExistException(String msg) {
		this.msg = msg;
	}
    @Override
	public String getMessage() {
		return msg;
	}
}
