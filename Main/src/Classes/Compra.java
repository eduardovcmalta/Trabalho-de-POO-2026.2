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

        Bilhete bilhete = new Bilhete(usuario.getUser(), usuario.getCpf(), sala, sessao, filme, valor, cadeira);
        bilhetes.add(bilhete);

        return bilhete;
    }

    public void adicionarProduto(Produtos produto) {
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
}