package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;

public interface UtenteService {
	
	public List<Utente> listAllElements() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente UtenteInstance) throws Exception;

	public void inserisciNuovo(Utente UtenteInstance) throws Exception;

	public void rimuovi(Long idUtenteToRemove) throws Exception;
	
	public void setUtenteDAO(UtenteDAO utenteDAO);
	
	public Utente findByUsernameAndPassword(String username, String password) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;
	
}
