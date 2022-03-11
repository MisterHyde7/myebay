package it.prova.myebay.dao;

import java.util.Optional;

import it.prova.myebay.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{
	
	public Optional<Utente> findByUsernameAndPassword(String username,String password);

	public Optional<Utente> login(String loginInput, String passwordInput);

	public Optional<Utente> findOneEagerAnnunci(Long id);

	public Optional<Utente> findOneEagerAcquisti(Long id);

}
