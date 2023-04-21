package com.example.javautils.core.exception;

public class MultiAnnotationException extends Exception{
    String msg = null;
    public MultiAnnotationException(String msg) {
		this.msg = msg;
	}
    @Override
	public String getMessage() {
		return msg;
	}

}
