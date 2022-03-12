package it.prova.myebay.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;

public class UtilityForm {

	public static Map<Categoria, Boolean> buildCheckedCategorieForPages(List<Categoria> listaTotaleCategorie,
			String[] categorieFromParams) {
		TreeMap<Categoria, Boolean> result = new TreeMap<>();

		// converto array di string in List di Long
		List<Long> categorieIdConvertiti = new ArrayList<>();
		for (String stringItem : categorieFromParams != null ? categorieFromParams : new String[] {}) {
			categorieIdConvertiti.add(Long.valueOf(stringItem));
		}

		for (Categoria CategoriaItem : listaTotaleCategorie) {
			result.put(CategoriaItem, categorieIdConvertiti.contains(CategoriaItem.getId()));
		}

		return result;
	}

	public static Map<Categoria, Boolean> buildCheckedCategorieFromCategorieAlreadyInAnnuncio(
			List<Categoria> listaTotaleCategorie, Set<Categoria> listaCategoriePosseduteDaAnnuncio) {
		TreeMap<Categoria, Boolean> result = new TreeMap<>();

		// converto array di ruoli in List di Long
		List<Long> categorieConvertiteInIds = new ArrayList<>();
		for (Categoria CategoriaDiAnnuncioItem : listaCategoriePosseduteDaAnnuncio != null
				? listaCategoriePosseduteDaAnnuncio
				: new ArrayList<Categoria>()) {
			categorieConvertiteInIds.add(CategoriaDiAnnuncioItem.getId());
		}

		for (Categoria CategoriaItem : listaTotaleCategorie) {
			result.put(CategoriaItem, categorieConvertiteInIds.contains(CategoriaItem.getId()));
		}

		return result;
	}

	public static Set<Categoria> buildSetCategorieForAnnuncio(List<Categoria> listaTotaleCategorie,
			String[] categorieFromParams) {
		Set<Categoria> result = new HashSet<>();

		// converto array di string in List di Long
		List<Long> categorieIdConvertiti = new ArrayList<>();
		for (String stringItem : categorieFromParams != null ? categorieFromParams : new String[] {}) {
			categorieIdConvertiti.add(Long.valueOf(stringItem));
		}

		for (Categoria CategoriaItem : listaTotaleCategorie) {
			for (Long longIdItem : categorieIdConvertiti) {
				if (CategoriaItem.getId() == longIdItem) {
					result.add(CategoriaItem);
				}
			}
		}

		return result;
	}

	public static Map<Ruolo, Boolean> buildCheckedRuoliForPages(List<Ruolo> listaTotaleRuoli,
			String[] ruoliFromParams) {
		Map<Ruolo, Boolean> result = new HashMap<>();

		// converto array di string in List di Long
		List<Long> ruoliIdConvertiti = new ArrayList<>();
		for (String stringItem : ruoliFromParams != null ? ruoliFromParams : new String[] {}) {
			ruoliIdConvertiti.add(Long.valueOf(stringItem));
		}

		for (Ruolo ruoloItem : listaTotaleRuoli) {
			result.put(ruoloItem, ruoliIdConvertiti.contains(ruoloItem.getId()));
		}

		return result;
	}

	public static Map<Ruolo, Boolean> buildCheckedRuoliFromUtenteAlreadyInUtente(List<Ruolo> listaTotaleCategorie,
			Set<Ruolo> listaRuoliPosseduteDaUtente) {
		Map<Ruolo, Boolean> result = new HashMap<>();

		// converto array di ruoli in List di Long
		List<Long> ruoliConvertiteInIds = new ArrayList<>();
		for (Ruolo ruoloDiUtenteItem : listaRuoliPosseduteDaUtente != null ? listaRuoliPosseduteDaUtente
				: new ArrayList<Ruolo>()) {
			ruoliConvertiteInIds.add(ruoloDiUtenteItem.getId());
		}

		for (Ruolo ruoloItem : listaTotaleCategorie) {
			result.put(ruoloItem, ruoliConvertiteInIds.contains(ruoloItem.getId()));
		}

		return result;
	}

	public static Set<Ruolo> buildSetRuoliForUtente(List<Ruolo> listaTotaleRuoli, String[] ruoliFromParams) {
		Set<Ruolo> result = new HashSet<>();

		// converto array di string in List di Long
		List<Long> ruoliIdConvertiti = new ArrayList<>();
		for (String stringItem : ruoliFromParams != null ? ruoliFromParams : new String[] {}) {
			ruoliIdConvertiti.add(Long.valueOf(stringItem));
		}

		for (Ruolo ruoloItem : listaTotaleRuoli) {
			for (Long longIdItem : ruoliIdConvertiti) {
				if (ruoloItem.getId() == longIdItem) {
					result.add(ruoloItem);
				}
			}
		}

		return result;
	}

}
