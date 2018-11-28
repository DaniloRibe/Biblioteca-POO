package repositories;

import java.util.HashMap;
import java.util.Map;
import model.Livro;

public class RepositorioLivro {

    private Map<String, Livro> livros = new HashMap<>();

    public Map<String, Livro> getLivros() {
        return livros;
    }

    public void setLivros(Map<String, Livro> livros) {
        this.livros = livros;
    }

}
