package Classes;

public class Sessao {
    private Filme filme;
    private boolean[][] cadeiras;

    public Sessao(Filme filme) {
        this.cadeiras = new boolean[10][15];
        this.filme = filme;

    }

    public int[] converterCadeira(String cadeira) {

        if (cadeira == null || cadeira.trim().length() < 2) {
            return null;
        }

        cadeira = cadeira.trim().toUpperCase();

        char letra = cadeira.charAt(0);
        int linha = letra - 'A';

        try {
            int coluna = Integer.parseInt(cadeira.substring(1)) - 1;
            return new int[]{linha, coluna};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean reservarCadeira(String cadeira) {
        int[] coordenadas = converterCadeira(cadeira);


        if (coordenadas == null) {
            System.out.println("Código de assento inválido!");
            return false;
        }

        int linha = coordenadas[0];
        int coluna = coordenadas[1];


        if (linha < 0 || linha >= cadeiras.length || coluna < 0 || coluna >= cadeiras[0].length) {
            System.out.println("Assento fora dos limites da sala!");
            return false;
        }

        if (cadeiras[linha][coluna]) {
            System.out.println("O assento " + cadeira + " já está ocupado!");
            return false;
        }

        cadeiras[linha][coluna] = true;
        return true;
    }

    public boolean[][] getCadeiras() {
        return cadeiras;
    }

    public Filme getFilme() {
        return filme;
    }

}
