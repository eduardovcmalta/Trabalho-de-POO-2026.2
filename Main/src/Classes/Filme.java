package Classes;

public class Filme {
    private String nome;
    private int duracao;
    private String sinopse;
    private float valor;
    private double nota;
    private int quantidade_criticos;
    private Critica[] critica = new Critica[100];

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public int getDuracao(){
        return duracao;
    }
    public void setDuracao(int duracao){
        this.duracao = duracao;
    }

    public String getSinopse(){
        return sinopse;
    }
    public void setSinopse(String sinopse){
        this.sinopse = sinopse;
    }

    public float getValor(){
        return valor;
    }
    public void setValor(float valor){
        this.valor = valor;
    }

    public double getNota(){ return nota; }
    public void setNota(double nota){ this.nota = nota;}

    public int getQuantidade_criticos(){ return quantidade_criticos; }
    public void setQuantidade_criticos(int quantidade_criticos){ this.quantidade_criticos = quantidade_criticos;}

    public Critica[] getCritica() { return critica; }
    public void setCritica(Critica[] critica) { this.critica = critica; }

    public Filme(String nome, int duracao, String sinopse, float valor){
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
    }
}