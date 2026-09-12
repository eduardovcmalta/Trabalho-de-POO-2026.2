package Classes;

public class Sessao {
    private Filme filme;
    private boolean[][] lugares = new boolean[10][15];

    public Sessao(Filme filme, boolean[][] lugares) {
        this.filme = filme;
        this.lugares = lugares;
    }

    public Sessao(Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        return filme;
    }

    public boolean[][] getLugares() {
        return lugares;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
