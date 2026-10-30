package Classes;

import Interfaces.GerenciaFilmes;
import Persistencia.Repositorio;

public class Administrador extends Base implements GerenciaFilmes {
    private double salario;
    private int id;
    private Repositorio repositorio;

    public Administrador(String nome, int idade, String email, double salario, int id, Repositorio repositorio) {
        super(nome, idade, email);
        this.salario = salario;
        this.id = id;
        this.repositorio = repositorio;
    }

    public Repositorio getRepositorio() { return repositorio; }
    public void setRepositorio(Repositorio repositorio) { this.repositorio = repositorio; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // ---------- persistência (responsabilidade do administrador) ----------

    public boolean salvarDados() {
        boolean ok = repositorio.salvar();
        if (ok) System.out.println("Admin " + getNome() + " salvou os dados em arquivo.");
        return ok;
    }

    public boolean carregarDados() {
        boolean ok = repositorio.carregar();
        if (ok) System.out.println("Admin " + getNome() + " carregou os dados do arquivo: "
                + repositorio.getUsuarios().size() + " usuário(s), " + repositorio.getFilmes().size() + " filme(s).");
        return ok;
    }

    // ---------- usuários ----------

    @Override
    public void adicionarUsuario(Usuario usuario) {
        if (repositorio.adicionarUsuario(usuario)) {
            repositorio.salvar();
            System.out.println("Admin " + getNome() + " persistiu o usuário " + usuario.getUser() + " com sucesso!");
        } else {
            System.out.println("Não foi possível adicionar: CPF " + usuario.getCpf() + " já existe.");
        }
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        if (repositorio.alterarUsuario(usuario)) {
            repositorio.salvar();
            System.out.println("Admin " + getNome() + " atualizou o usuário " + usuario.getUser() + ".");
        } else {
            System.out.println("Usuário não encontrado para alteração.");
        }
    }

    public void excluirUsuario(Usuario usuario) {
        if (repositorio.removerUsuario(usuario.getCpf())) {
            repositorio.salvar();
            System.out.println("Usuário " + usuario.getUser() + " excluído do banco de dados.");
        } else {
            System.out.println("Usuário não encontrado para exclusão.");
        }
    }

    // ---------- filmes ----------

    @Override
    public void incluirFilme(Filme filme) {
        if (repositorio.adicionarFilme(filme)) {
            repositorio.salvar();
            System.out.println("Admin " + getNome() + " persistiu o filme " + filme.getNome() + " com sucesso!");
        } else {
            System.out.println("Não foi possível incluir: o filme " + filme.getNome() + " já existe.");
        }
    }

    @Override
    public void excluirFilme(Filme filme) {
        if (repositorio.removerFilme(filme.getNome())) {
            repositorio.salvar();
            System.out.println("Filme " + filme.getNome() + " excluído do banco de dados.");
        } else {
            System.out.println("Filme não encontrado para exclusão.");
        }
    }

    @Override
    public void alterarFilme(Filme filme) {
        if (repositorio.alterarFilme(filme)) {
            repositorio.salvar();
            System.out.println("Admin " + getNome() + " atualizou o filme " + filme.getNome() + ".");
        } else {
            System.out.println("Filme não encontrado para alteração.");
        }
    }
}
