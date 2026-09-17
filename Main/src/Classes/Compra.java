package Classes;

public class Compra {
    private Usuario usuario;
    private Bilhete bilhetes[];
    private String itens[];
    private int qtdBilhetes;
    private int qtdItens;

    public Compra(Usuario usuario, int maxBilhetes, int maxItens) {
        this.usuario = usuario;
        this.bilhetes = new Bilhete[maxBilhetes];
        this.itens = new String[maxItens];
        this.qtdBilhetes = 0;
        this.qtdItens = 0;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Bilhete[] getBilhetes() {
        return bilhetes;
    }

    public String[] getItens() {
        return itens;
    }

    public void adicionarBilhete(Bilhete bilhete) {
        if (qtdBilhetes < bilhetes.length) {
            bilhetes[qtdBilhetes] = bilhete;
            qtdBilhetes++;
        }
    }

    public void adicionarItem(String item) {
        if (qtdItens < itens.length) {
            itens[qtdItens] = item;
            qtdItens++;
        }
    }
}