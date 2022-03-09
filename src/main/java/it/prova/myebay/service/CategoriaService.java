package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.CategoriaDAO;
import it.prova.myebay.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listAllElements() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categoria CategoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria CategoriaInstance) throws Exception;

	public void rimuovi(Long idCategoriaToRemove) throws Exception;
	
	public void setCategoriaDAO(CategoriaDAO CategoriaDAO);
	
}
