package Classes;

public class Sessao {
    private Filme filme;
    private boolean[][] cadeiras;

    public Sessao(Filme filme) {
        this.cadeiras = new boolean[10][15];
        this.filme = filme;
    }

    public boolean[][] getCadeiras() {
        return cadeiras;
    }


}
