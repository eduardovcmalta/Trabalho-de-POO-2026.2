import Classes.*;
import Enums.Produtos;
import Enums.TipoSala;
import Enums.CupomPromocional;
import Persistencia.Repositorio;

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

        // Antes de escolher a poltrona, o cliente vê o mapa da sessão
        System.out.println(">>> Sessões da sala e mapa de cadeiras da sessão 1");
        sala1.exibirSessoesDisponiveis();
        sessao1.exibirMapa();
        System.out.println();

        // ==========================================
        // COMPRA 1: João
        // ==========================================
        Usuario joao = new Usuario("joao", "11122233344", "123", 20, "M", "joao@gmail.com", "joao cartao", "525242", 455);
        Compra compraJoao = new Compra(joao);
        
        try {
            compraJoao.comprarBilhete(sala1, sessao1, "A3");
            compraJoao.comprarProduto(Produtos.AGUA);
            joao.realizarCompra(compraJoao);
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

        System.out.println(">>> Mapa depois das compras (X = ocupada)");
        sessao1.exibirMapa();

        // ==========================================
        // ETAPA 1: vários bilhetes, alterar e cancelar compra
        // ==========================================
        System.out.println("\n--- VÁRIOS BILHETES (sistema sugere cadeiras juntas) ---");
        Usuario maria = new Usuario("maria", "55566677788", "123", 35, "F", "maria@gmail.com", "maria cartao", "4000123412341234", 321);
        Compra compraMaria = new Compra(maria);
        try {
            compraMaria.comprarVariosBilhetes(sala1, sessao1, 4, CupomPromocional.BEMVINDO10);
            maria.realizarCompra(compraMaria);
            compraMaria.exibirResumoCompra();

            System.out.println("--- ALTERAR COMPRA (troca por uma de 2 bilhetes) ---");
            Compra compraMaria2 = new Compra(maria);
            java.util.List<String> sugestao = sessao1.sugerirCadeiras(2);
            System.out.println("Sugestão para 2 pessoas: " + sugestao);
            compraMaria2.comprarBilhetes(sala1, sessao1, sugestao);
            maria.alterarCompra(compraMaria2);
            compraMaria2.exibirResumoCompra();

            System.out.println("--- CANCELAR COMPRA (libera as cadeiras) ---");
            System.out.println(sugestao.get(0) + " livre antes do cancelamento? " + sessao1.isCadeiraLivre(sugestao.get(0)));
            maria.cancelarCompra();
            System.out.println(sugestao.get(0) + " livre depois do cancelamento? " + sessao1.isCadeiraLivre(sugestao.get(0)));
        } catch (VendasException e) {
            System.out.println(e.getMessage());
        }

        // ==========================================
        // ETAPA 3: os 3 erros de VendasException
        // ==========================================
        System.out.println("\n--- ERRO 2: HORÁRIO DA SESSÃO JÁ PASSOU ---");
        sessao2.setHorarioPassado(true);
        try {
            new Compra(joao).comprarBilhete(sala1, sessao2, "F1");
        } catch (VendasException e) {
            System.out.println(e.getMessage());
        }
        sessao2.setHorarioPassado(false);

        System.out.println("\n--- ERRO 3: FILME FORA DE CARTAZ ---");
        filme2.setEmCartaz(false);
        try {
            new Compra(joao).comprarBilhete(sala1, sessao2, "F1");
        } catch (VendasException e) {
            System.out.println(e.getMessage());
        }
        filme2.setEmCartaz(true);

        // ==========================================
        // ETAPA 3: Administrador, Funcionário e persistência em arquivo
        // ==========================================
        System.out.println("\n--- PERSISTÊNCIA (Administrador / Funcionário) ---");
        apagarPasta("dados_demo");
        Repositorio repo = new Repositorio("dados_demo");
        Administrador admin = new Administrador("Carlos", 40, "carlos@cinema.com", 5000, 1, repo);
        Funcionario func = new Funcionario("Marina", 25, "marina@cinema.com", 2500, repo);

        admin.incluirFilme(filme1);
        admin.incluirFilme(filme2);
        admin.adicionarUsuario(joao);
        admin.adicionarUsuario(joao);        // repetido: o sistema recusa
        admin.adicionarUsuario(fatima);
        admin.adicionarUsuario(ana);
        func.incluirFilme(new Filme("Duna", 155, "Areia; e especiaria", 25));   // fica em memória
        admin.salvarDados();                 // o administrador grava tudo em arquivo

        System.out.println("\n>>> Simulando reinício do sistema: novo repositório lendo os arquivos");
        Repositorio repo2 = new Repositorio("dados_demo");
        Administrador admin2 = new Administrador("Carlos", 40, "carlos@cinema.com", 5000, 1, repo2);
        admin2.carregarDados();
        for (Usuario u : repo2.getUsuarios()) {
            System.out.println("  usuário: " + u.getUser() + " (" + u.getClass().getSimpleName() + ")");
        }
        for (Filme f : repo2.getFilmes()) {
            System.out.println("  filme: " + f.getNome() + " | em cartaz: " + f.isEmCartaz() + " | nota: " + f.getNota());
        }
        System.out.println("  crítica de Interstelar: " + repo2.buscarFilme("Interstelar").getCritica()[0].getMensagem());

        admin2.excluirUsuario(joao);
        Filme duna = repo2.buscarFilme("Duna");
        duna.setValor(30);
        admin2.alterarFilme(duna);
        admin2.excluirFilme(filme2);
    }

    private static void apagarPasta(String nome) {
        java.io.File pasta = new java.io.File(nome);
        java.io.File[] arquivos = pasta.listFiles();
        if (arquivos != null) {
            for (java.io.File a : arquivos) a.delete();
        }
        pasta.delete();
    }
}
