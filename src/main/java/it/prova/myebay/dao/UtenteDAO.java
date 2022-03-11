package it.prova.myebay.dao;

import java.util.Optional;

import it.prova.myebay.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{
	
	public Optional<Utente> findByUsernameAndPassword(String username,String password);

	public Optional<Utente> login(String loginInput, String passwordInput);

	public Optional<Utente> findOneEager(Long id);

}
