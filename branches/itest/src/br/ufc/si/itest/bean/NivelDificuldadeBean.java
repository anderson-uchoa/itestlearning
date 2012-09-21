package br.ufc.si.itest.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.model.SelectItem;

import br.ufc.si.itest.dao.NivelDificuldadeDao;
import br.ufc.si.itest.dao.impl.NivelDificuldadeDaoImpl;
import br.ufc.si.itest.model.NivelDificuldade;

public class NivelDificuldadeBean {
	/* DAOs */
	private NivelDificuldadeDao nivelDificuldadeDao;

	/* Propriedades auxiliares */
	private List<SelectItem> niveisDificuldade;

	/* Construtor */
	public NivelDificuldadeBean() {
		nivelDificuldadeDao = new NivelDificuldadeDaoImpl();

		niveisDificuldade = new ArrayList<SelectItem>();

		carregaNiveisDificuldade();
	}

	/* ----M�todos auxiliares---- */

	/*
	 * M�todo para carregar os n�veis de dificuldade existentes quando a p�gina
	 * � carregada
	 */
	public void carregaNiveisDificuldade() {
		List<NivelDificuldade> listNiveis = nivelDificuldadeDao.list();
		Collections.reverse(listNiveis);
		for (NivelDificuldade nd : listNiveis) {
			niveisDificuldade.add(new SelectItem(nd.getId(), nd.getNome()));
		}
	}

	/* Getters e Setters */
	public List<SelectItem> getNiveisDificuldade() {
		return niveisDificuldade;
	}

	public void setNiveisDificuldade(List<SelectItem> niveisDificuldade) {
		this.niveisDificuldade = niveisDificuldade;
	}

}
