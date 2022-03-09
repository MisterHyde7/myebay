package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.model.Acquisto;

public interface AcquistoService {
	
	public List<Acquisto> listAllElements() throws Exception;

	public Acquisto caricaSingoloElemento(Long id) throws Exception;

	public Acquisto caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Acquisto AcquistoInstance) throws Exception;

	public void inserisciNuovo(Acquisto AcquistoInstance) throws Exception;

	public void rimuovi(Long idAcquistoToRemove) throws Exception;
	
	public void setAcquistoDAO(AcquistoDAO AcquistoDAO);
	
}
