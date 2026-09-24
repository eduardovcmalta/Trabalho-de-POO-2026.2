package Classes;

public class Filme {
    private String nome;
    private int duracao;
    private String sinopse;
    private float valor;
    private float nota;
    private int quantidade_criticos;

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

    public Filme(String nome, int duracao, String sinopse, float valor){
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
    }
}