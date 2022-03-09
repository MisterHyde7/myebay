package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.myebay.model.Annuncio;

public class AnnuncioDAOImpl implements AnnuncioDAO {
	
	private EntityManager entityManager;

	@Override
	public List<Annuncio> list() throws Exception {
		return entityManager.createQuery("from Annuncio", Annuncio.class).getResultList();
	}

	@Override
	public Annuncio findOne(Long id) throws Exception {
		return entityManager.find(Annuncio.class, id);
	}

	@Override
	public void update(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Annuncio input) throws Exception {
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
	public Optional<Annuncio> findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Annuncio a left join fetch a.utente where a.id=:idAnnuncio", Annuncio.class)
				.setParameter("idAnnuncio", id).getResultList().stream().findFirst();
	}

}
