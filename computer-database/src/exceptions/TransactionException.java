package exceptions;

public class TransactionException extends RuntimeException{

	private static final long serialVersionUID = -7251039640873788829L;

	
	public TransactionException(String message,Throwable e) {
		super();
		this.setStackTrace(e.getStackTrace());
	}
	
	
	
	
}