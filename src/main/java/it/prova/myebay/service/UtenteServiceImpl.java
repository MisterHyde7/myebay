package it.prova.myebay.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.exceptions.CreditoResiduoInsufficienteException;
import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDAO;
	private AnnuncioDAO annuncioDAO;
	private AcquistoDAO acquistoDAO;

	@Override
	public List<Utente> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Utente caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findOne(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente caricaSingoloElementoEagerAcquisti(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findOneEagerAcquisti(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Utente UtenteInstance, Long idInput) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			Utente utenteDaModificare = utenteDAO.findOne(idInput);
			utenteDaModificare.setNome(UtenteInstance.getNome());
			utenteDaModificare.setCognome(UtenteInstance.getCognome());
			utenteDaModificare.setUsername(UtenteInstance.getUsername());
			utenteDaModificare.setRuoli(UtenteInstance.getRuoli());
			utenteDaModificare.setStato(UtenteInstance.getStato());

			utenteDAO.update(utenteDaModificare);

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
	public void inserisciNuovo(Utente UtenteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			utenteDAO.insert(UtenteInstance);

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
	public void rimuovi(Long idUtenteToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			utenteDAO.setEntityManager(entityManager);
			Utente UtenteToRemove = utenteDAO.findOne(idUtenteToRemove);
			if (UtenteToRemove == null)
				throw new ElementNotFoundException("Utente con id: " + idUtenteToRemove + " non trovato.");
			UtenteToRemove.setStato(StatoUtente.DISABILITATO);
			entityManager.merge(UtenteToRemove);
			
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
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	@Override
	public Utente findByUsernameAndPassword(String username, String password) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			Optional<Utente> result = utenteDAO.findByUsernameAndPassword(username, password);
			return result.isPresent() ? result.get() : null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			utenteEsistente = entityManager.merge(utenteEsistente);
			ruoloInstance = entityManager.merge(ruoloInstance);

			utenteEsistente.getRuoli().add(ruoloInstance);

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
	public Utente accedi(String loginInput, String passwordInput) {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			Optional<Utente> result = utenteDAO.login(loginInput, passwordInput);
			return result.isPresent() ? result.get() : null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;
	}

	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO = acquistoDAO;
	}

	@Override
	public boolean procediAllAcquisto(long parseLong, Long idAnnuncioInput) {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			utenteDAO.setEntityManager(entityManager);
			acquistoDAO.setEntityManager(entityManager);
			annuncioDAO.setEntityManager(entityManager);

			Utente utenteCaricato = utenteDAO.findOne(parseLong);
			Annuncio annuncioCaricato = annuncioDAO.findOne(idAnnuncioInput);
			Acquisto nuovoAcquisto = new Acquisto(annuncioCaricato.getTestoAnnuncio(), new Date(),annuncioCaricato.getPrezzo());

			if (utenteCaricato == null)
				throw new ElementNotFoundException("Utente con id: " + utenteCaricato + " non trovato.");
			if (utenteCaricato.getCredito() < annuncioCaricato.getPrezzo())
				throw new CreditoResiduoInsufficienteException(
						"Credito residuo: " + utenteCaricato.getCredito() + " non sufficiente per l'acquisto.");

			utenteCaricato.setCredito(utenteCaricato.getCredito() - annuncioCaricato.getPrezzo());
			annuncioCaricato.setAperto(false);
			if (utenteCaricato.getAcquisti() == null)
				utenteCaricato.setAcquisti(null);
			utenteCaricato.getAcquisti().add(nuovoAcquisto);
			nuovoAcquisto.setUtente(utenteCaricato);

			utenteDAO.update(utenteCaricato);
			annuncioDAO.update(annuncioCaricato);
			acquistoDAO.insert(nuovoAcquisto);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		return true;
	}

	@Override
	public Utente caricaSingoloElementoEagerAnnunci(Long id) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findOneEagerAnnunci(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente caricaSingoloElementoConRuoli(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findOneEagerRuoli(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
