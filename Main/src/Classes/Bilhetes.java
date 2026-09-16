package Classes;

public class Bilhetes{
    private Usuario user;
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private float valor;
    private String cadeira;

    public Bilhetes(Usuario user, Sala sala, Sessao sessao,
                    Filme filme, float valor, String cadeira){
        this.user = user;
        this.sala = sala;
        this.sessao = sessao;
        this.filme = filme;
        this.valor = calcularValor();
        this.cadeira = cadeira;
    }

    public Usuario getUser(){
        return user;
    }
    public void setUser(Usuario  user){
        this.user = user;
    }

    public Sala getSala(){
        return sala;
    }
    public void setSala(Sala sala){
        this.sala = sala;
    }

    public Sessao getSessao(){
        return sessao;
    }
    public void setSessao(Sessao sessao){
        this.sessao = sessao;
    }

    public Filme getFilme(){
        return filme;
    }
    public void setFilme(Filme filme){
        this.filme = filme;
    }

    public float getValor(){
        return valor;
    }
    public void setValor(float valor){
        this.valor = calcularValor();
    }

    public String getCadeira(){
        return cadeira;
    }
    public void setCadeira(String cadeira){
        this.cadeira = cadeira;
    }
}