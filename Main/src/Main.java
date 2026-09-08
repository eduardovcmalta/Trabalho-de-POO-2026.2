import Classes.*;

public class Main {

    public static void main (String[] args) {
        Sala sala1 = new Sala(1);

        // 2. Cria os filmes
        Filme filme1 = new Filme("Interestelar");
        Filme filme2 = new Filme("Batman");

        Sessao sessao1 = new Sessao(filme1);
        Sessao sessao2 = new Sessao(filme2);

        sala1.agendarSessao(0, sessao1);
        sala1.agendarSessao(2, sessao2);
    }
}
