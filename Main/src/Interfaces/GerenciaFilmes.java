package Interfaces;

import Classes.Filme;

public interface GerenciaFilmes {
    void incluirFilme(Filme filme);
    void excluirFilme(Filme filme);
    void alterarFilme(Filme filme);
}
