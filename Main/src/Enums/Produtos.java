package Enums;

public enum Produtos {
    PIPOCA("Pipoca", 12),
    REFRIGERANTE("Refrigerante", 8),
    NACHOS("Nachos", 20),
    CACHORRO_QUENTE("Cachorro quente", 8),
    CHOCOLATE("Chocolate", 5);

    private String nome;
    private double valor;

    Produtos(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

}
