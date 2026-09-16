import Classes.*;
import Enums.Produtos;

public class Main {

    public static void main (String[] args) {
        Sala sala1 = new Sala(1);
        Sala sala2 = new Sala(2);

        // 2. Cria os filmes
        Filme filme1 = new Filme("Interstelar", "120", "Filme de um cara no espaço", 20);
        Filme filme2 = new Filme("Batman", "200","Filme de um cara morcego", 20);

        Sessao sessao1 = new Sessao(filme1);
        Sessao sessao2 = new Sessao(filme2);

        sala1.agendarSessao(0, sessao1);
        sala1.agendarSessao(2, sessao2);

        sala1.exibirSessoesDisponiveis();
        sala2.exibirSessoesDisponiveis();

    }
}