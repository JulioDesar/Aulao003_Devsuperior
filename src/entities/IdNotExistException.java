package entities;

public class IdNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public IdNotExistException(String msg) {
		
		super(msg);
		
	}

}
