package projet.exception;

public class TransactionException extends RuntimeException{

	private static final long serialVersionUID = -7251039640873788829L;

	
	public TransactionException(String message,Throwable e) {
		super(message,e);
		this.setStackTrace(e.getStackTrace());
	}
	
	
	
	
}