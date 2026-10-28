package Classes;

import Interfaces.GerenciaFilmes;

public class Administrador extends Base implements GerenciaFilmes {
    private double salario;
    private int id; 

    public Administrador(String nome, int idade, String email, double salario, int id) {
        super(nome, idade, email);
        this.salario = salario;
        this.id = id;
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // --- MÉTODOS DA CLASSE ABSTRATA BASE ---
    @Override
    public void adicionarUsuario(Usuario usuario) {
        System.out.println("Admin: Lógica de adicionar usuário pendente (Guilherme)");
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        System.out.println("Admin: Lógica de alterar usuário pendente");
    }

    // --- MÉTODO EXCLUSIVO DO ADMINISTRADOR ---
    public void excluirUsuario(Usuario usuario) {
        System.out.println("Admin: Lógica de excluir usuário pendente (Guilherme)");
    }

    // --- MÉTODOS DA INTERFACE GERENCIAFILMES ---
    @Override
    public void incluirFilme(Filme filme) {
        System.out.println("Admin: Lógica de incluir filme pendente (Guilherme)");
    }

    @Override
    public void excluirFilme(Filme filme) {
        System.out.println("Admin: Lógica de excluir filme pendente (Guilherme)");
    }

    @Override
    public void alterarFilme(Filme filme) {
        System.out.println("Admin: Lógica de alterar filme pendente (Guilherme)");
    }
}