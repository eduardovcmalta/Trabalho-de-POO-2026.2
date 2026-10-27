import Classes.*;
import Enums.Produtos;
import Enums.TipoSala;
import Enums.CupomPromocional;

public class Main {

    public static void main (String[] args) {
        Sala sala1 = new Sala(1, TipoSala.COMUM);

        // 2. Cria os filmes
        Filme filme1 = new Filme("Interstelar", 120, "Filme de um cara no espaço", 20);
        Filme filme2 = new Filme("Batman", 200,"Filme de um cara morcego", 20);

        Sessao sessao1 = new Sessao(filme1);
        Sessao sessao2 = new Sessao(filme2);

        sala1.agendarSessao(0, sessao1);
        sala1.agendarSessao(2, sessao2);

        Usuario joao = new Usuario("joao", "11122233344", "123", 20, "M", "joao@gmail.com", "joao cartao", "525242", 455);
        Compra compraJoao = new Compra(joao);
        compraJoao.comprarBilhete(sala1,  sessao1, "A3");
        compraJoao.comprarProduto(Produtos.AGUA);
        compraJoao.exibirResumoCompra();

        Estudante fatima = new Estudante("fatima", "222233334444", "123", 16, "F", "fatima@gmail.com", "fatima cartao", "40028922", 421);
        Compra compraFatima = new Compra(fatima);
        compraFatima.comprarBilhete(sala1, sessao1, "A4");
        compraFatima.exibirResumoCompra();


        compraJoao.comprarProduto(Produtos.PIPOCA, CupomPromocional.BEMVINDO10);
        compraJoao.exibirResumoCompra();

        Critico ana = new Critico("ana", "33344455566", "123", 30, "F", "ana@gmail.com",
                "ana cartao", "1234567890123456", 123, "Folha de Pernambuco");
        ana.atribuirNota(9.0, filme1);
        ana.atribuirNota(7.0, filme1);
        ana.atribuirCritica("Ótimo filme", filme1);
        System.out.println("Nota do filme: " + filme1.getNota() + " (" + filme1.getQuantidade_criticos() + " críticos)");
            
        Compra compraAna = new Compra(ana);
        compraAna.comprarBilhete(sala1, sessao1, "B1");
        compraAna.exibirResumoCompra();

    }
}
