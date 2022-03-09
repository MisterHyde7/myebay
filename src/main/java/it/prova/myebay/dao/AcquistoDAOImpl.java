package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.myebay.model.Acquisto;

public class AcquistoDAOImpl implements AcquistoDAO {
	
	private EntityManager entityManager;

	@Override
	public List<Acquisto> list() throws Exception {
		return entityManager.createQuery("from Acquisto", Acquisto.class).getResultList();
	}

	@Override
	public Acquisto findOne(Long id) throws Exception {
		return entityManager.find(Acquisto.class, id);
	}

	@Override
	public void update(Acquisto input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Acquisto input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Acquisto input) throws Exception {
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
	public Optional<Acquisto> findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Acquisto a left join fetch a.utente where a.id=:idAcquisto", Acquisto.class)
				.setParameter("idAcquisto", id).getResultList().stream().findFirst();
	}

}
