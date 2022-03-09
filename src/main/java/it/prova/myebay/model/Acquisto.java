package it.prova.myebay.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acquisto")
public class Acquisto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "dataAcquisto")
	private Date dataAcquisto;
	@Column(name = "prezzo")
	private int prezzo;

	@ManyToOne
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;
}
