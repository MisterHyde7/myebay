package it.prova.myebay.utility;

import it.prova.myebay.model.Annuncio;

public class UtilityAnnuncio {

	public static Annuncio createAnnuncioFromParam(String testoAnnuncioInput, String prezzoAnnuncioParam) {

		if (prezzoAnnuncioParam.isBlank()) {
			prezzoAnnuncioParam = "1";
		}
		Annuncio annuncioFromParam = new Annuncio(testoAnnuncioInput, Integer.parseInt(prezzoAnnuncioParam));
		return annuncioFromParam;
	}

	public static boolean validaAnnuncioBean(Annuncio annuncioBean) {

		if (annuncioBean.getTestoAnnuncio().isBlank() || annuncioBean.getTestoAnnuncio() == null
				|| annuncioBean.getPrezzo() <= 0) {

			return false;
		}
		return true;
	}

}
