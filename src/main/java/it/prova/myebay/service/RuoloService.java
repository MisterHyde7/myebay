package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.RuoloDAO;
import it.prova.myebay.model.Ruolo;

public interface RuoloService {
	
	public List<Ruolo> listAllElements() throws Exception;

	public Ruolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ruolo RuoloInstance) throws Exception;

	public void inserisciNuovo(Ruolo RuoloInstance) throws Exception;

	public void rimuovi(Long idRuoloToRemove) throws Exception;
	
	public void setRuoloDAO(RuoloDAO RuoloDAO);
	
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception;
	
}
