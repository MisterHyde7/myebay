package it.prova.myebay.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

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
		return entityManager
				.createQuery("from Annuncio a left join fetch a.utente where a.id=:idAnnuncio", Annuncio.class)
				.setParameter("idAnnuncio", id).getResultList().stream().findFirst();
	}

	@Override
	public List<Annuncio> findByExample(Annuncio annuncioInstance) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select a from Annuncio a left join a.categorie c where a.aperto = 1 ");

		if (StringUtils.isNotEmpty(annuncioInstance.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + annuncioInstance.getTestoAnnuncio() + "%");
		}
		if (annuncioInstance.getPrezzo() >= 1) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", annuncioInstance.getPrezzo());
		}
		if (annuncioInstance.getCategorie() != null && annuncioInstance.getCategorie().size() > 0) {
			whereClauses.add(" c in :listCategorie ");
			paramaterMap.put("listCategorie", annuncioInstance.getCategorie());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

	@Override
	public List<Annuncio> findByExampleConUtente(Annuncio annuncioDaCercare, long idInput) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select a from Annuncio a left join a.categorie c where a.utente.id = a.utente");

		if (StringUtils.isNotEmpty(annuncioDaCercare.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + annuncioDaCercare.getTestoAnnuncio() + "%");
		}
		if (annuncioDaCercare.getPrezzo() >= 1) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", annuncioDaCercare.getPrezzo());
		}
		if (annuncioDaCercare.getCategorie() != null && annuncioDaCercare.getCategorie().size() > 0) {
			whereClauses.add(" c in :listCategorie ");
			paramaterMap.put("listCategorie", annuncioDaCercare.getCategorie());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
	
	@Override
	public Optional<Annuncio> findOneEagerCategorie(Long id) throws Exception {
		return entityManager
				.createQuery("from Annuncio a left join fetch a.categorie c where a.id=:idAnnuncio", Annuncio.class)
				.setParameter("idAnnuncio", id).getResultList().stream().findFirst();
	}

}
