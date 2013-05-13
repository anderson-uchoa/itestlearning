package br.ufc.si.itest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import br.ufc.si.itest.dao.AlunoDao;
import br.ufc.si.itest.dao.AlunoTurmaDao;
import br.ufc.si.itest.dao.JogoDao;
import br.ufc.si.itest.dao.ProfessorDao;
import br.ufc.si.itest.dao.TurmaDao;
import br.ufc.si.itest.dao.UsuarioDao;
import br.ufc.si.itest.dao.impl.AlunoDaoImpl;
import br.ufc.si.itest.dao.impl.AlunoTurmaDaoImpl;
import br.ufc.si.itest.dao.impl.JogoDaoImpl;
import br.ufc.si.itest.dao.impl.ProfessorDaoImpl;
import br.ufc.si.itest.dao.impl.TurmaDaoImpl;
import br.ufc.si.itest.dao.impl.UsuarioDaoImpl;
import br.ufc.si.itest.model.Administrador;
import br.ufc.si.itest.model.Aluno;
import br.ufc.si.itest.model.AlunoTurma;
import br.ufc.si.itest.model.AlunoTurma.AlunoTurmaPK;
import br.ufc.si.itest.model.Jogo;
import br.ufc.si.itest.model.Professor;
import br.ufc.si.itest.model.Turma;
import br.ufc.si.itest.model.Usuario;
import br.ufc.si.itest.utils.SendMail;

public class ProfessorBean {

	private Professor professor;
	private ProfessorDao profDao;
	private List<Professor> professores;
	private Turma turma;
	private List<Turma> turmas;
	private List<Aluno> alunos;
	private Aluno aluno;
	private List<Integer> ids_alunos;
	private Jogo jogo;
	private List<Jogo> jogos;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public ProfessorDao getProfDao() {
		return profDao;
	}

	public void setProfDao(ProfessorDao profDao) {
		this.profDao = profDao;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Integer> getIds_alunos() {
		return ids_alunos;
	}

	public void setIds_alunos(List<Integer> ids_alunos) {
		this.ids_alunos = ids_alunos;
	}
	
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public ProfessorBean() {
		super();
		professor = new Professor();
		profDao = new ProfessorDaoImpl();
		professores = new ArrayList<Professor>();
		turma = new Turma();
		turmas = new ArrayList<Turma>();
		alunos = new ArrayList<Aluno>();
		aluno = new Aluno();
		ids_alunos = new ArrayList<Integer>();
		jogo = new Jogo();
		jogos = new ArrayList<Jogo>();
		
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);

		professor = (Professor) session.getAttribute("prof");

		carregarTurmas();

	}

	public String cadastrarProfessor() {
		String msg = "Bem vindo ao Itest " + this.professor.getNome()
				+ ", sua senha: " + this.professor.getSenha();
		try {
			SendMail.enviarEmail(this.professor.getLogin(),
					"Criação de Conta no Itest", msg);
			profDao.save(professor);
			return "/admin_add_professor.xhtml";
		} catch (Exception e) {
			return "/admin_add_professor.xhtml";
		}

	}

	public String visualizarProfessores() {
		professores = profDao.list();
		return "admin_visual_professores.xhtml";
	}

	public String atualizarProfessor() {
		profDao.update(professor);
		professor = new Professor();
		professores = profDao.list();
		return "admin_visual_professores.xhtml";
	}

	public String removerProfessor() {
		profDao.delete(professor);
		professor = new Professor();
		professores = profDao.list();
		return "admin_visual_professores.xhtml";
	}

	public List<SelectItem> getTurmasSeleção() {
		List<SelectItem> turmaItens = new ArrayList<SelectItem>();
		TurmaDao turmaDao = new TurmaDaoImpl();
		turmas = turmaDao.list();
		for (Turma t : turmas) {
			turmaItens.add(new SelectItem(t.getId(), t.getNome()));
		}

		return turmaItens;
	}

	public List<SelectItem> getAlunosSelecao() {
		List<SelectItem> alunoItens = new ArrayList<SelectItem>();

		AlunoDao alunoDao = new AlunoDaoImpl();
		alunos = alunoDao.listarAlunos();

		for (Aluno a : alunos) {
			alunoItens.add(new SelectItem(a.getId(), a.getNome()));
		}

		return alunoItens;
	}

	public String AdicionarTurma() {
		TurmaDao turmaDao = new TurmaDaoImpl();
		turma.setProfessor(getProfessor());
		turmaDao.save(turma);

		turma = new Turma();
		turmas = turmaDao.carregarTurmasProfessor(professor.getId());
		return "prof_visual_turmas.jsf";
	}

	public void carregarTurmas() {
		TurmaDao turmaDao = new TurmaDaoImpl();
		turmas = turmaDao.carregarTurmasProfessor(professor.getId());
	}

	public String editarTurma() {
		TurmaDao turmaDao = new TurmaDaoImpl();
		turma.setProfessor(getProfessor());
		turmaDao.update(turma);
		turma = new Turma();
		return "prof_index.jsf";
	}

	public String removerTurma() {
		TurmaDao turmaDao = new TurmaDaoImpl();
		turmaDao.delete(turma);
		turmas = turmaDao.carregarTurmasProfessor(professor.getId());
		turma = new Turma();
		return "prof_index.jsf";
	}

	public String carregarAlunosCadastados() {

		AlunoDao alunoDao = new AlunoDaoImpl();
		alunos = alunoDao.listarAlunos();

		return "prof_visual_alunos.jsf";
	}

	public String buscarAlunoPorEmail() {
		AlunoDao alunoDao = new AlunoDaoImpl();
		aluno = (Aluno) alunoDao.getUsuarioByEmail(aluno.getLogin());
		return "prof_add_aluno_turma.jsf";
	}

	public String buscarAlunosTurma() {

		alunos.clear();

		AlunoTurmaDao aluno_turma_dao = new AlunoTurmaDaoImpl();

		List<AlunoTurma> alunosTurma = new ArrayList<AlunoTurma>();

		alunosTurma = aluno_turma_dao.getAlunoTurmaByIdTurma(turma.getId());

		for (AlunoTurma at : alunosTurma) {
			alunos.add(at.getPk().getAluno());
		}

		return "prof_visual_aluno_turma.jsf";
	}

	public String AddAlunoTurma() {
		TurmaDao turmaDao = new TurmaDaoImpl();
		turma = turmaDao.getTurmaById(turma.getId());

		AlunoTurma aluno_turma = new AlunoTurma();
		AlunoTurmaPK pk = new AlunoTurmaPK();

		AlunoTurmaDao aluno_turma_dao = new AlunoTurmaDaoImpl();

		pk.setAluno(aluno);
		pk.setTurma(turma);

		aluno_turma.setPk(pk);

		aluno_turma_dao.save(aluno_turma);

		List<AlunoTurma> alunosTurma = new ArrayList<AlunoTurma>();

		alunosTurma = aluno_turma_dao.getAlunoTurmaByIdTurma(turma.getId());

		alunos.clear();

		for (AlunoTurma at : alunosTurma) {
			alunos.add(at.getPk().getAluno());
		}

		return "prof_visual_alunos.jsf";
	}

	public String AddAlunosTurma() {
		TurmaDao turmaDao = new TurmaDaoImpl();
		turma = turmaDao.getTurmaById(turma.getId());

		AlunoDao alunoDao = new AlunoDaoImpl();

		AlunoTurma aluno_turma = new AlunoTurma();
		AlunoTurmaPK pk = new AlunoTurmaPK();

		AlunoTurmaDao aluno_turma_dao = new AlunoTurmaDaoImpl();

		for (int i = 0; i < ids_alunos.size(); i++) {

			aluno = (Aluno) alunoDao.getUsuarioById(Integer.parseInt(""
					+ ids_alunos.get(i)));

			pk.setAluno(aluno);
			pk.setTurma(turma);
			aluno_turma.setPk(pk);
			aluno_turma_dao.save(aluno_turma);

		}

		alunos.clear();

		List<AlunoTurma> alunosTurma = new ArrayList<AlunoTurma>();

		alunosTurma = aluno_turma_dao.getAlunoTurmaByIdTurma(turma.getId());

		for (AlunoTurma at : alunosTurma) {
			alunos.add(at.getPk().getAluno());
		}

		return "prof_visual_alunos.jsf";

	}

	public String removerAlunoTurma() {
		
		alunos.clear();

		AlunoTurmaDao aluno_turma_dao = new AlunoTurmaDaoImpl();

		List<AlunoTurma> alunosTurma = new ArrayList<AlunoTurma>();

		AlunoTurmaPK pk = new AlunoTurmaPK();
		AlunoTurma aluno_turma = new AlunoTurma();
		
		pk.setAluno(getAluno());
		pk.setTurma(getTurma());
		
		aluno_turma.setPk(pk);
		
		aluno_turma_dao.remove(aluno_turma);
		
		alunosTurma = aluno_turma_dao.getAlunoTurmaByIdTurma(turma.getId());

		for (AlunoTurma at : alunosTurma) {
			alunos.add(at.getPk().getAluno());
		}

		return "prof_visual_aluno_turma.jsf";
	}
	
	
	
	public String visualizarJogosAluno(){
		JogoDao jogo_dao = new JogoDaoImpl();
		jogos = jogo_dao.getJogoByUsuario(aluno.getId());
		return "prof_visual_jogos_aluno.jsf";
	}

}
