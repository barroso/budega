package br.com.barroso.exception;

@SuppressWarnings("serial")
public class DBException extends Exception {

	private String msg;
	
	public DBException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
}