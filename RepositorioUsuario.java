
package repositories;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class RepositorioUsuario {
    private List <Usuario> usuarios = new ArrayList<>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
