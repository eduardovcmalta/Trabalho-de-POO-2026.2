package Classes;

public class Sessao {
    private Filme filme;
    private boolean[][] cadeiras;
    private boolean horarioPassado = false;

    public Filme getFilme(){
        return filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public void setCadeiras(boolean[][] cadeiras) {
        this.cadeiras = cadeiras;
    }
    public boolean isHorarioPassado(){
        return horarioPassado;
    }
    public void setHorarioPassado(boolean horarioPassado){
        this.horarioPassado = horarioPassado;
    }

    public void cancelarReserva(String cadeira) {
        int[] coordenadas = converterCadeira(cadeira);
        if (coordenadas != null) {
            int linha = coordenadas[0];
            int coluna = coordenadas[1];
            if (linha >= 0 && linha < cadeiras.length && coluna >= 0 && coluna < cadeiras[0].length) {
                cadeiras[linha][coluna] = false; // Libera o assento
            }
        }
    }

    public java.util.List<String> sugerirCadeiras(int qtdBilhetes) {
        java.util.List<String> sugestao = new java.util.ArrayList<>();
        
        for (int linha = 0; linha < cadeiras.length; linha++) {
            int consecutivas = 0;
            sugestao.clear();
            
            for (int coluna = 0; coluna < cadeiras[linha].length; coluna++) {
                if (!cadeiras[linha][coluna]) { // Se estiver livre
                    consecutivas++;
                    char letra = (char) ('A' + linha);
                    sugestao.add(letra + String.valueOf(coluna + 1));
                    
                    if (consecutivas == qtdBilhetes) {
                        return sugestao; // Retorna a lista quando achar as cadeiras juntas
                    }
                } else {
                    consecutivas = 0; // Zera a contagem se achar uma ocupada
                    sugestao.clear();
                }
            }
        }
        return null; // Retorna null se não houver assentos juntos suficientes
    }

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

    public void reservarCadeira(String cadeira) throws VendasException {
        int[] coordenadas = converterCadeira(cadeira);

        if (coordenadas == null) {
            throw new VendasException("Código de assento inválido!");
        }

        int linha = coordenadas[0];
        int coluna = coordenadas[1];

        if (linha < 0 || linha >= cadeiras.length || coluna < 0 || coluna >= cadeiras[0].length) {
            throw new VendasException("Assento fora dos limites da sala!");
        }

        if (cadeiras[linha][coluna]) {
            throw new VendasException("ERRO: A poltrona " + cadeira + " já foi selecionada e está ocupada!");
        }

        cadeiras[linha][coluna] = true; // Reserva a cadeira com sucesso
    }

}
