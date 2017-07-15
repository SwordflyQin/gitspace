package com.tedu.cloudnote.util;

public class NoteException extends RuntimeException{
	
	public NoteException(String msg,Throwable t){
		super(msg,t);
	}
}
