package Classes;

public class Bilhete {
    private Usuario usuario;
    private String cpf;
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private float valor;
    private String cadeira;

    public Bilhete(Usuario usuario, String cpf, Sala sala, Sessao sessao, Filme filme, float valor, String cadeira) {
        this.usuario = usuario;
        this.cpf = cpf;
        this.sala = sala;
        this.sessao = sessao;
        this.filme = filme;
        this.valor = valor;
        this.cadeira = cadeira;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Sessao getSessao() {
        return sessao;
    }
    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Filme getFilme() {
        return filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCadeira() {
        return cadeira;
    }
    public void setCadeira(String cadeira) {
        this.cadeira = cadeira;
    }
}