package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {
	
	public List<Annuncio> listAllElements() throws Exception;

	public Annuncio caricaSingoloElemento(Long id) throws Exception;

	public Annuncio caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Annuncio AnnuncioInstance) throws Exception;

	public void inserisciNuovo(Annuncio AnnuncioInstance) throws Exception;

	public void rimuovi(Long idAnnuncioToRemove) throws Exception;
	
	public void setAnnuncioDAO(AnnuncioDAO AnnuncioDAO);
	
	public List<Annuncio> findByExample(Annuncio annuncioInstance) throws Exception;

	public List<Annuncio> findByExampleConUtente(Annuncio annuncioDaCercare, long parseLong);
	
}
