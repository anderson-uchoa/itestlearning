package br.ufc.si.itest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.ufc.si.itest.dao.ItemTesteDao;
import br.ufc.si.itest.dao.impl.ItemTesteDaoImpl;
import br.ufc.si.itest.model.ItemTeste;
import br.ufc.si.itest.utils.Utils;

public class ItemTesteBean {
	
	/* DAOs */
	private ItemTesteDao itemTesteDao;
	
	/* Propriedades auxiliares */
	private List<SelectItem> itensTeste;
	private List<ItemTeste> itensTesteProjeto;
	private List<String> itensTesteSelecionados;
	private List<ItemTeste> respostas;
	private List<ItemTeste> respostasCorretas;
	private List<ItemTeste> respostasErradas;
	private Boolean respondido;
	
	/* Construtor */
	public ItemTesteBean() {
		itemTesteDao = new ItemTesteDaoImpl();
		itensTeste = new ArrayList<SelectItem>();
		itensTesteProjeto = new ArrayList<ItemTeste>();
		itensTesteSelecionados = new ArrayList<String>();
		respostas = new ArrayList<ItemTeste>();
		respostasCorretas = new ArrayList<ItemTeste>();
		respostasErradas = new ArrayList<ItemTeste>();
		respondido = false;
	}
	
	/* M�todos Auxiliares */
	public Integer validaResposta() {
		Integer pontuacao = 0;
		for (ItemTeste it : itensTesteProjeto) {
			if (it.getResposta()) {
				respostasCorretas.add(it);
			}
			else {
				respostasErradas.add(it);
			}
		}
		for (String i : itensTesteSelecionados) {
			ItemTeste it = getItemTesteById(new Integer(i));
			respostas.add(it);
			if(respostasCorretas.contains(it)) {
				pontuacao = pontuacao + Utils.PONTO_POSITIVO;
			}
			else {
				pontuacao = pontuacao + Utils.PONTO_NEGATIVO;
			}
		}
		for(ItemTeste it : respostasCorretas) {
			if(!respostas.contains(it)) {
				pontuacao = pontuacao + Utils.PONTO_NEGATIVO;
			}
		}
		
		respondido = true;
		return pontuacao;
	}

	public ItemTeste getItemTesteById(Integer id) {
		for (ItemTeste it : itensTesteProjeto) {
			if (it.getId().equals(id)) {
				return it;
			}
		}
		return null;
	}

	/* Getters e Setters*/
	public ItemTesteDao getItemTesteDao() {
		return itemTesteDao;
	}

	public void setItemTesteDao(ItemTesteDao itemTesteDao) {
		this.itemTesteDao = itemTesteDao;
	}

	public List<SelectItem> getItensTeste() {
		return itensTeste;
	}

	public void setItensTeste(List<SelectItem> itensTeste) {
		this.itensTeste = itensTeste;
	}

	public List<ItemTeste> getItensTesteProjeto() {
		return itensTesteProjeto;
	}

	public void setItensTesteProjeto(List<ItemTeste> itensTesteProjeto) {
		this.itensTesteProjeto = itensTesteProjeto;
	}

	public List<String> getItensTesteSelecionados() {
		return itensTesteSelecionados;
	}

	public void setItensTesteSelecionados(List<String> itensTesteSelecionados) {
		this.itensTesteSelecionados = itensTesteSelecionados;
	}

	public Boolean getRespondido() {
		return respondido;
	}

	public void setRespondido(Boolean respondido) {
		this.respondido = respondido;
	}

	public List<ItemTeste> getRespostasCorretas() {
		return respostasCorretas;
	}

	public void setRespostasCorretas(List<ItemTeste> respostasCorretas) {
		this.respostasCorretas = respostasCorretas;
	}

	public void setRespostas(List<ItemTeste> respostas) {
		this.respostas = respostas;
	}

	public List<ItemTeste> getRespostas() {
		return respostas;
	}

	public List<ItemTeste> getRespostasErradas() {
		return respostasErradas;
	}

	public void setRespostasErradas(List<ItemTeste> respostasErradas) {
		this.respostasErradas = respostasErradas;
	}
	
}
