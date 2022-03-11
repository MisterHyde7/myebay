package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	@Override
	public List<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Utente findOne(Long id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Utente input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Utente input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Utente> findByUsernameAndPassword(String username, String password) {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u  " + "where u.username = :username and u.password=:password ", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<Utente> login(String loginInput, String passwordInput) {
		
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u join fetch u.ruoli r "
						+ "where u.username = :username and u.password=:password and u.stato=:statoUtente",
				Utente.class);
		query.setParameter("username", loginInput);
		query.setParameter("password", passwordInput);
		query.setParameter("statoUtente", StatoUtente.ATTIVO);
		System.out.println(query.getResultStream().findFirst());
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<Utente> findOneEager(Long id) {
		return entityManager.createQuery("from Utente u left join fetch u.acquisti a where a.id=:idUtente", Utente.class)
				.setParameter("idUtente", id).getResultList().stream().findFirst();
	}

}
