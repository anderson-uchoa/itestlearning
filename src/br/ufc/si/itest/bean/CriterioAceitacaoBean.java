package br.ufc.si.itest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.ufc.si.itest.dao.CriterioAceitacaoDao;
import br.ufc.si.itest.dao.impl.CriterioAceitacaoDaoImpl;
import br.ufc.si.itest.model.CriterioAceitacao;
import br.ufc.si.itest.utils.Utils;

public class CriterioAceitacaoBean {

	/* Model */
	private CriterioAceitacao criterioAceitacao;

	/* DAOs */
	private CriterioAceitacaoDao criterioAceitacaoDao;

	/* Propriedades auxiliares */
	private List<SelectItem> criteriosAceitacao;
	private List<CriterioAceitacao> criteriosAceitacaoProjeto;
	private List<String> criteriosAceitacaoSelecionados;
	private List<CriterioAceitacao> respostas;
	private List<CriterioAceitacao> respostasCorretas;
	private List<CriterioAceitacao> respostasErradas;
	private Boolean respondido;

	/* Construtor */
	public CriterioAceitacaoBean() {
		criterioAceitacao = new CriterioAceitacao();
		criterioAceitacaoDao = new CriterioAceitacaoDaoImpl();
		criteriosAceitacao = new ArrayList<SelectItem>();
		criteriosAceitacaoProjeto = new ArrayList<CriterioAceitacao>();
		criteriosAceitacaoSelecionados = new ArrayList<String>();
		respostas = new ArrayList<CriterioAceitacao>();
		respostasCorretas = new ArrayList<CriterioAceitacao>();
		respostasErradas = new ArrayList<CriterioAceitacao>();
		respondido = false;
	}

	/* M�todos Auxiliares */
	public Integer validaResposta(int nivelDificuldade) {
		Integer pontuacao = 0;
		for (CriterioAceitacao ca : criteriosAceitacaoProjeto) {
			if (ca.getResposta()) {
				respostasCorretas.add(ca);
			} else {
				respostasErradas.add(ca);
			}
		}
		for (String i : criteriosAceitacaoSelecionados) {
			CriterioAceitacao ca = getCriterioAceitacaoById(new Integer(i));
			respostas.add(ca);
			if (respostasCorretas.contains(ca)) {
				if (nivelDificuldade == 1) {
					pontuacao = pontuacao + Utils.PONTO_POSITIVO;
				}

				if (nivelDificuldade == 2) {
					pontuacao = pontuacao + Utils.PONTO_POSITIVO_MEDIO;
				}

				if (nivelDificuldade == 3) {
					pontuacao = pontuacao + Utils.PONTO_POSITIVO_DIFICIL;
				}

			} else {
				if (nivelDificuldade == 1) {
					pontuacao = pontuacao + Utils.PONTO_NEGATIVO;
				}

				if (nivelDificuldade == 2) {
					pontuacao = pontuacao + Utils.PONTO_NEGATIVO_MEDIO;
				}

				if (nivelDificuldade == 3) {
					pontuacao = pontuacao + Utils.PONTO_NEGATIVO_DIFICIL;
				}

			}
		}
		for (CriterioAceitacao ca : respostasCorretas) {
			if (!respostas.contains(ca)) {
				if (nivelDificuldade == 1) {
					pontuacao = pontuacao + Utils.PONTO_NEGATIVO;
				}

				if (nivelDificuldade == 2) {
					pontuacao = pontuacao + Utils.PONTO_NEGATIVO_MEDIO;
				}

				if (nivelDificuldade == 3) {
					pontuacao = pontuacao + Utils.PONTO_NEGATIVO_DIFICIL;
				}

			}
		}

		respondido = true;
		return pontuacao;
	}

	public CriterioAceitacao getCriterioAceitacaoById(Integer id) {
		for (CriterioAceitacao ca : criteriosAceitacaoProjeto) {
			if (ca.getId().equals(id)) {
				return ca;
			}
		}
		return null;
	}

	/* Getters e Setters */
	public CriterioAceitacaoDao getCriterioAceitacaoDao() {
		return criterioAceitacaoDao;
	}

	public void setCriterioAceitacaoDao(
			CriterioAceitacaoDao criterioAceitacaoDao) {
		this.criterioAceitacaoDao = criterioAceitacaoDao;
	}

	public List<SelectItem> getCriteriosAceitacao() {
		return criteriosAceitacao;
	}

	public void setCriteriosAceitacao(List<SelectItem> criteriosAceitacao) {
		this.criteriosAceitacao = criteriosAceitacao;
	}

	public List<CriterioAceitacao> getCriteriosAceitacaoProjeto() {
		return criteriosAceitacaoProjeto;
	}

	public void setCriteriosAceitacaoProjeto(
			List<CriterioAceitacao> criteriosAceitacaoProjeto) {
		this.criteriosAceitacaoProjeto = criteriosAceitacaoProjeto;
	}

	public List<String> getCriteriosAceitacaoSelecionados() {
		return criteriosAceitacaoSelecionados;
	}

	public void setCriteriosAceitacaoSelecionados(
			List<String> criteriosAceitacaoSelecionados) {
		this.criteriosAceitacaoSelecionados = criteriosAceitacaoSelecionados;
	}

	public List<CriterioAceitacao> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<CriterioAceitacao> respostas) {
		this.respostas = respostas;
	}

	public List<CriterioAceitacao> getRespostasCorretas() {
		return respostasCorretas;
	}

	public void setRespostasCorretas(List<CriterioAceitacao> respostasCorretas) {
		this.respostasCorretas = respostasCorretas;
	}

	public Boolean getRespondido() {
		return respondido;
	}

	public void setRespondido(Boolean respondido) {
		this.respondido = respondido;
	}

	public List<CriterioAceitacao> getRespostasErradas() {
		return respostasErradas;
	}

	public void setRespostasErradas(List<CriterioAceitacao> respostasErradas) {
		this.respostasErradas = respostasErradas;
	}

	public CriterioAceitacao getCriterioAceitacao() {
		return criterioAceitacao;
	}

	public void setCriterioAceitacao(CriterioAceitacao criterioAceitacao) {
		this.criterioAceitacao = criterioAceitacao;
	}

}
