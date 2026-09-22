package Classes;

import java.util.ArrayList;
import java.util.List;
import Enums.Cupons;
import Enums.Produtos;

public class Compra {
    private Usuario usuario;
    private List<Bilhete> bilhetes = new ArrayList<>();
    private List<Produtos> produtos = new ArrayList<>();

    public Compra(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public int getQtdBilhetes() {
        return bilhetes.size();
    }

    public int getQtdProdutos() {
        return produtos.size();
    }

    // Comprar SEM cupom
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, String cadeira) {
        return comprarBilhete(sala, sessao, cadeira, null);
    }

    // Comprar COM cupom
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, String cadeira, Cupons cupom) {
        if (!sessao.reservarCadeira(cadeira)) {
            System.out.println("Cadeira " + cadeira + " indisponível!");
            return null;
        }

        Filme filme = sessao.getFilme();
        double valor = usuario.calcularValorBilhete(sala, filme);

        if (cupom != null) {
            valor = cupom.aplicarDesconto(valor);
        }

        // Obtém a faixa de horário da sessão a partir da sala
        String horario = sala.getHorarioSessao(sessao);

        // Cria o bilhete passando o objeto usuario, (float) valor e o horario
        Bilhete bilhete = new Bilhete(usuario, usuario.getCpf(), sala, sessao, filme, (float) valor, cadeira, horario);
        bilhetes.add(bilhete);

        return bilhete;
    }

    public void comprarProduto(Produtos produto) {
        if (produto != null) {
            this.produtos.add(produto);
            System.out.println(produto.getNome() + " adicionado à compra!");
        }
    }

    public double calcularValorTotal() {
        double total = 0.0;

        for (Bilhete b : bilhetes) {
            total += b.getValor();
        }

        for (Produtos p : produtos) {
            total += p.getValor();
        }

        return total;
    }

    public void exibirResumoCompra() {
        System.out.println("==========================================================================");
        System.out.println("                             RESUMO DA COMPRA                             ");
        System.out.println("==========================================================================");

        if (usuario != null) {
            System.out.println("Cliente: " + usuario.getUser() + " (CPF: " + usuario.getCpf() + ")");
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("INGRESSOS (" + bilhetes.size() + "):");
        if (bilhetes.isEmpty()) {
            System.out.println("  Nenhum ingresso selecionado.");
        } else {
            for (int i = 0; i < bilhetes.size(); i++) {
                Bilhete b = bilhetes.get(i);
                System.out.printf("  %d. Filme: %-15s | Assento: %-4s | Horário: %-15s | Valor: R$ %.2f%n",
                        (i + 1),
                        b.getFilme().getNome(),
                        b.getCadeira(),
                        b.getHorario(),
                        b.getValor());
            }
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("BOMBONIERE (" + produtos.size() + "):");
        if (produtos.isEmpty()) {
            System.out.println("  Nenhum item da bomboniere selecionado.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                Produtos p = produtos.get(i);
                System.out.printf("  %d. %-20s | Valor: R$ %.2f%n",
                        (i + 1),
                        p.getNome(),
                        p.getValor());
            }
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("TOTAL A PAGAR: R$ %.2f%n", calcularValorTotal());
        System.out.println("==========================================================================\n");
    }
}