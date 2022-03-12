package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;

public interface UtenteService {
	
	public List<Utente> listAllElements() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;
	
	public Utente caricaSingoloElementoEagerAnnunci(Long id) throws Exception;

	public void inserisciNuovo(Utente UtenteInstance) throws Exception;

	public void rimuovi(Long idUtenteToRemove) throws Exception;
	
	public void setUtenteDAO(UtenteDAO utenteDAO);
	
	public Utente findByUsernameAndPassword(String username, String password) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;

	public Utente accedi(String loginInput, String passwordInput);

	public boolean procediAllAcquisto(long parseLong, Long input);

	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);

	public void setAcquistoDAO(AcquistoDAO acquistoDAO);

	Utente caricaSingoloElementoEagerAcquisti(Long id) throws Exception;

	Utente caricaSingoloElementoConRuoli(Long id) throws Exception;

	void aggiorna(Utente UtenteInstance, Long idInput) throws Exception;
	
}
