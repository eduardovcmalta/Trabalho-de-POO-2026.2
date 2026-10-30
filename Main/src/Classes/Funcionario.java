package Classes;

import Interfaces.GerenciaFilmes;
import Persistencia.Repositorio;

public class Funcionario extends Base implements GerenciaFilmes {
    private double salario;
    private Repositorio repositorio;

    public Funcionario(String nome, int idade, String email, double salario, Repositorio repositorio) {
        super(nome, idade, email);
        this.salario = salario;
        this.repositorio = repositorio;
    }

    public Repositorio getRepositorio() { return repositorio; }
    public void setRepositorio(Repositorio repositorio) { this.repositorio = repositorio; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    // O funcionário altera os dados em memória; quem grava em arquivo é o Administrador.

    @Override
    public void adicionarUsuario(Usuario usuario) {
        if (repositorio.adicionarUsuario(usuario)) {
            System.out.println("Funcionário " + getNome() + " cadastrou o usuário " + usuario.getUser());
        } else {
            System.out.println("Não foi possível cadastrar: CPF " + usuario.getCpf() + " já existe.");
        }
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        if (repositorio.alterarUsuario(usuario)) {
            System.out.println("Funcionário " + getNome() + " alterou o usuário " + usuario.getUser());
        } else {
            System.out.println("Usuário não encontrado (CPF " + usuario.getCpf() + ").");
        }
    }

    @Override
    public void incluirFilme(Filme filme) {
        if (repositorio.adicionarFilme(filme)) {
            System.out.println("Funcionário " + getNome() + " incluiu o filme " + filme.getNome());
        } else {
            System.out.println("Não foi possível incluir: o filme " + filme.getNome() + " já existe.");
        }
    }

    @Override
    public void excluirFilme(Filme filme) {
        if (repositorio.removerFilme(filme.getNome())) {
            System.out.println("Funcionário " + getNome() + " excluiu o filme " + filme.getNome());
        } else {
            System.out.println("Filme não encontrado para exclusão.");
        }
    }

    @Override
    public void alterarFilme(Filme filme) {
        if (repositorio.alterarFilme(filme)) {
            System.out.println("Funcionário " + getNome() + " alterou o filme " + filme.getNome());
        } else {
            System.out.println("Filme não encontrado para alteração.");
        }
    }
}
