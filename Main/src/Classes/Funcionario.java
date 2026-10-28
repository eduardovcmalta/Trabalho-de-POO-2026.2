package Classes;

import Interfaces.GerenciaFilmes;

public class Funcionario extends Base implements GerenciaFilmes {
    private double salario;

    public Funcionario(String nome, int idade, String email, double salario) {
        super(nome, idade, email);
        this.salario = salario;
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        System.out.println("Funcionário: Lógica de adicionar usuário pendente (Guilherme)");
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        System.out.println("Funcionário: Lógica de alterar usuário pendente");
    }

    @Override
    public void incluirFilme(Filme filme) {
        System.out.println("Funcionário: Lógica de incluir filme pendente");
    }

    @Override
    public void excluirFilme(Filme filme) {
        System.out.println("Funcionário: Lógica de excluir filme pendente");
    }

    @Override
    public void alterarFilme(Filme filme) {
        System.out.println("Funcionário: Lógica de alterar filme pendente");
    }
}