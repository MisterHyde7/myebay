package it.prova.myebay.exceptions;

public class CreditoResiduoInsufficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CreditoResiduoInsufficienteException(String message) {
		super(message);
	}

}
