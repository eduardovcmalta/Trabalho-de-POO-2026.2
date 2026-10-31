package Classes;

public class Bilhete {
    private Usuario usuario;
    private String cpf;
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private double valor;
    private String cadeira;
    private String horario;

    public Bilhete(Usuario usuario, String cpf, Sala sala, Sessao sessao, Filme filme, double valor, String cadeira, String horario) {
        this.usuario = usuario;
        this.cpf = cpf;
        this.sala = sala;
        this.sessao = sessao;
        this.filme = filme;
        this.valor = valor;
        this.cadeira = cadeira;
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }
    public Sessao getSessao() { return sessao; }
    public void setSessao(Sessao sessao) { this.sessao = sessao; }
    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getCadeira() { return cadeira; }
    public void setCadeira(String cadeira) { this.cadeira = cadeira; }
}