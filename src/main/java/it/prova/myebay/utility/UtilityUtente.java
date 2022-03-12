package it.prova.myebay.utility;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Utente;

public class UtilityUtente {

	public static Utente createUtenteFromParams(String nomeInputParam, String cognomeInputParam,
			String userNameInputParam, String password) {

		return new Utente(nomeInputParam, cognomeInputParam, userNameInputParam, password);
		
	}
	
	public static Utente createUtenteFromParams(String nomeInputParam, String cognomeInputParam,
			String userNameInputParam) {

		return new Utente(nomeInputParam, cognomeInputParam, userNameInputParam);
		
	}

	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome()) 
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername())
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateUtenteBeanSenzaPassword(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome()) 
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername())) {
			return false;
		}
		return true;
	}
	
}
