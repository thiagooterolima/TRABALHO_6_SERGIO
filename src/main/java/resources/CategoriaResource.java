package resources;


import entities.Categoria;
import jakarta.jws.WebService;
import repositories.CategoriaRepository;

import java.util.List;

@WebService(endpointInterface = "resources.ICategoriaResource")
public class CategoriaResource implements ICategoriaResource {

    private CategoriaRepository categoriaRepository = new CategoriaRepository();

    public void salvarCategoria(Categoria categoria)
    {categoriaRepository.salvar(categoria);
    }

    @Override
    public List<Categoria> listarCategoria(){
        List<Categoria> categorias = categoriaRepository.listar();
        return  categorias;
    }
}
