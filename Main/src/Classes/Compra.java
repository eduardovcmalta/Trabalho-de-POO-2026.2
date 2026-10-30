package Classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import Enums.CupomPromocional;
import Enums.Produtos;

public class Compra {
    private Usuario usuario;
    private List<Bilhete> bilhetes = new ArrayList<>();
    private List<Produtos> produtos = new ArrayList<>();
    private List<Double> valoresProdutos = new ArrayList<>();

    public List<Double> getValoresProdutos() {
        return valoresProdutos;
    }

    public void setValoresProdutos(List<Double> valoresProdutos) {
        this.valoresProdutos = valoresProdutos;
    }

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
    
    public void setBilhetes(List<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    // --- COMPRA DE BILHETES ---

    // Comprar SEM cupom
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, String cadeira) throws VendasException {
        return comprarBilhete(sala, sessao, cadeira, null);
    }

    // Comprar COM cupom
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, String cadeira, CupomPromocional cupom) throws VendasException {
        Filme filme = sessao.getFilme();

        // 1 e 2. Filme em cartaz e horário ainda não passou
        validarSessao(sessao);

        // 3. Tenta reservar a cadeira (vai disparar VendasException se estiver ocupada)
        sessao.reservarCadeira(cadeira);

        double valor = usuario.calcularValorBilhete(sala, filme);

        if (cupom != null) {
            valor = cupom.aplicarDesconto(valor);
        }

        String horario = sala.getHorarioSessao(sessao);
        Bilhete bilhete = new Bilhete(usuario, usuario.getCpf(), sala, sessao, filme, valor, cadeira, horario);
        bilhetes.add(bilhete);

        return bilhete;
    }

    // Regras da venda: o filme precisa estar em cartaz e a sessão não pode ter passado
    public void validarSessao(Sessao sessao) throws VendasException {
        Filme filme = sessao.getFilme();
        if (!filme.isEmCartaz()) {
            throw new VendasException("ERRO: O filme '" + filme.getNome() + "' não está mais em cartaz!");
        }
        if (sessao.isHorarioPassado()) {
            throw new VendasException("ERRO: O horário desta sessão já passou!");
        }
    }

    // Comprar VÁRIOS escolhendo as cadeiras (ex.: as que o sistema sugeriu). Tudo ou nada.
    public java.util.List<Bilhete> comprarBilhetes(Sala sala, Sessao sessao, java.util.List<String> cadeiras) throws VendasException {
        return comprarBilhetes(sala, sessao, cadeiras, null);
    }

    public java.util.List<Bilhete> comprarBilhetes(Sala sala, Sessao sessao, java.util.List<String> cadeiras, CupomPromocional cupom) throws VendasException {
        java.util.List<Bilhete> comprados = new ArrayList<>();
        if (cadeiras == null || cadeiras.isEmpty()) {
            return comprados;
        }
        if (new HashSet<>(cadeiras).size() != cadeiras.size()) {
            throw new VendasException("ERRO: Há cadeiras repetidas na lista!");
        }

        // valida TUDO antes de reservar qualquer cadeira
        validarSessao(sessao);
        for (String c : cadeiras) {
            if (!sessao.isCadeiraValida(c)) {
                throw new VendasException("ERRO: Código de assento inválido: " + c);
            }
            if (!sessao.isCadeiraLivre(c)) {
                throw new VendasException("ERRO: A poltrona " + c + " já foi selecionada e está ocupada!");
            }
        }

        for (String c : cadeiras) {
            comprados.add(comprarBilhete(sala, sessao, c, cupom));
        }
        return comprados;
    }

    // Comprar VÁRIOS SEM cupom
    public void comprarVariosBilhetes(Sala sala, Sessao sessao, int quantidade) throws VendasException {
        comprarVariosBilhetes(sala, sessao, quantidade, null);
    }

    // Comprar VÁRIOS COM cupom
    public void comprarVariosBilhetes(Sala sala, Sessao sessao, int quantidade, CupomPromocional cupom) throws VendasException {
        java.util.List<String> cadeirasSugeridas = sessao.sugerirCadeiras(quantidade);

        if (cadeirasSugeridas == null) {
            throw new VendasException("Não há " + quantidade + " cadeiras juntas disponíveis nesta sessão.");
        }

        System.out.println("Sugestão de cadeiras juntas encontradas: " + cadeirasSugeridas);
        System.out.println("Efetuando a compra...");

        for (String cadeira : cadeirasSugeridas) {
            comprarBilhete(sala, sessao, cadeira, cupom);
        }
        
        System.out.println(quantidade + " bilhetes comprados com sucesso!");
    }


    // --- COMPRA DE PRODUTOS DA BOMBONIERE ---
    
    // Comprar produto SEM cupom
    public void comprarProduto(Produtos produto) {
        comprarProduto(produto, null);
    }   
    
    // Comprar produto COM cupom
    public void comprarProduto(Produtos produto, CupomPromocional cupom) {
        if (produto == null) return;    
        double valor = produto.getValor();
        if (cupom != null) {
            valor = cupom.aplicarDesconto(valor);
        }   
        produtos.add(produto);
        valoresProdutos.add(valor); // Adiciona o valor final (com ou sem desconto) na lista
        System.out.println(produto.getNome() + " adicionado à compra!");
    }

    // --- CÁLCULOS E RESUMO ---

    public double calcularValorTotal() {
        double total = 0.0;

        for (Bilhete b : bilhetes) {
            total += b.getValor();
        }

        for (double v : valoresProdutos) {
            total += v;
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
                        valoresProdutos.get(i));
            }
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("TOTAL A PAGAR: R$ %.2f%n", calcularValorTotal());
        System.out.println("==========================================================================\n");
    }
}