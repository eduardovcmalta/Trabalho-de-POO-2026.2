package Enums;

public enum Produtos {

    PIPOCA("Pipoca", 10),
    NACHOS("Nachos", 20),
    REFRIGERANTE("Refrigerante", 8),
    CACHORRO_QUENTE("Cachorro quente", 8),
    AGUA("Agua", 5);

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    private String nome;
    private double valor;

    Produtos(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }


}
