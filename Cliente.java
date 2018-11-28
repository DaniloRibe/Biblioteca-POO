package model;

import java.util.ArrayList;
import java.util.List;
import repositories.RepositorioLivro;
public class Cliente extends Usuario {

    private List<Livro> livros = new ArrayList();
    public int quantidadeLivros;
    RepositorioLivro r = new RepositorioLivro();

    public List<Livro> getLivros() {
        return livros;
    }

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);

    }

    @Override
    public String toString() {
        return super.toString();
    }

}
