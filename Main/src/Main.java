import Classes.*;
import Enums.Produtos;
import Enums.TipoSala;
import Enums.CupomPromocional;

public class Main {

    public static void main (String[] args) {
        Sala sala1 = new Sala(1, TipoSala.COMUM);

        // Cria os filmes
        Filme filme1 = new Filme("Interstelar", 120, "Filme de um cara no espaço", 20);
        Filme filme2 = new Filme("Batman", 200,"Filme de um cara morcego", 20);

        Sessao sessao1 = new Sessao(filme1);
        Sessao sessao2 = new Sessao(filme2);

        sala1.agendarSessao(0, sessao1);
        sala1.agendarSessao(2, sessao2);

        // ==========================================
        // COMPRA 1: João
        // ==========================================
        Usuario joao = new Usuario("joao", "11122233344", "123", 20, "M", "joao@gmail.com", "joao cartao", "525242", 455);
        Compra compraJoao = new Compra(joao);
        
        try {
            compraJoao.comprarBilhete(sala1, sessao1, "A3");
            compraJoao.comprarProduto(Produtos.AGUA);
            compraJoao.exibirResumoCompra();
        } catch (VendasException e) {
            System.out.println(e.getMessage());
        }

        // ==========================================
        // COMPRA 2: Fátima (Estudante)
        // ==========================================
        Estudante fatima = new Estudante("fatima", "222233334444", "123", 16, "F", "fatima@gmail.com", "fatima cartao", "40028922", 421);
        Compra compraFatima = new Compra(fatima);
        
        try {
            compraFatima.comprarBilhete(sala1, sessao1, "A4");
            compraFatima.exibirResumoCompra();
        } catch (VendasException e) {
            System.out.println(e.getMessage());
        }

        compraJoao.comprarProduto(Produtos.PIPOCA, CupomPromocional.BEMVINDO10);
        compraJoao.exibirResumoCompra();

        // ==========================================
        // AVALIAÇÃO E COMPRA 3: Ana (Crítica)
        // ==========================================
        Critico ana = new Critico("ana", "33344455566", "123", 30, "F", "ana@gmail.com",
                "ana cartao", "1234567890123456", 123, "Folha de Pernambuco");
        ana.atribuirNota(9.0, filme1);
        ana.atribuirNota(7.0, filme1);
        ana.atribuirCritica("Ótimo filme", filme1);
        System.out.println("Nota do filme: " + filme1.getNota() + " (" + filme1.getQuantidade_criticos() + " críticos)");
            
        Compra compraAna = new Compra(ana);
        try {
            compraAna.comprarBilhete(sala1, sessao1, "B1");
            compraAna.exibirResumoCompra();
        } catch (VendasException e) {
            System.out.println(e.getMessage());
        }

        // ==========================================
        // TESTE DA EXCEÇÃO DE VENDAS (Erro propositado)
        // ==========================================
        System.out.println("\n--- TESTANDO A EXCEÇÃO DE CADEIRA OCUPADA ---");
        try {
            // Tentando comprar a cadeira A3 que o João já comprou e reservou lá em cima
            compraAna.comprarBilhete(sala1, sessao1, "A3"); 
        } catch (VendasException e) {
            System.out.println(e.getMessage()); // Vai imprimir o erro formatado!
        }
    }
}