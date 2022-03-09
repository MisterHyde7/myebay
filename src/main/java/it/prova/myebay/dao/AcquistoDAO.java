package it.prova.myebay.dao;

import java.util.Optional;

import it.prova.myebay.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto> {

	public Optional<Acquisto> findOneEager(Long id) throws Exception;
	
}
