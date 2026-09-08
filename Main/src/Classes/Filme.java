package Classes;

public class Filme {
    private String nome;
    private String duracao;
    private String sinopse;
    private float valor;

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDuracao(){
        return duracao;
    }
    public void setDuracao(String duracao){
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

    public Filme(String nome, String duracao, String sinopse, float valor){
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
    }
}