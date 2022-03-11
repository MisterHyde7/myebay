package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioDAO extends IBaseDAO<Annuncio>{

	public Optional<Annuncio> findOneEager(Long id) throws Exception;

	public List<Annuncio> findByExample(Annuncio annuncioInstance);

	public List<Annuncio> findByExampleConUtente(Annuncio annuncioDaCercare, long idInput);
	
}
