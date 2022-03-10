package it.prova.myebay.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import it.prova.myebay.model.Categoria;

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

}
