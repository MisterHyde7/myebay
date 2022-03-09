package it.prova.myebay.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AcquistoServiceImpl implements AcquistoService {
	
	private AcquistoDAO acquistoDAO;

	@Override
	public List<Acquisto> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Acquisto caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findOne(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Acquisto caricaSingoloElementoEager(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findOneEager(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Acquisto AcquistoInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			acquistoDAO.update(AcquistoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Acquisto AcquistoInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			acquistoDAO.insert(AcquistoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idAcquistoToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			acquistoDAO.setEntityManager(entityManager);
			Acquisto AcquistoToRemove = acquistoDAO.findOne(idAcquistoToRemove);
			if (AcquistoToRemove == null)
				throw new ElementNotFoundException("Acquisto con id: " + idAcquistoToRemove + " non trovato.");

			acquistoDAO.delete(AcquistoToRemove);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO = acquistoDAO;
	}

}
