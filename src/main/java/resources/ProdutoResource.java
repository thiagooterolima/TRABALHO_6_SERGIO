package resources;


import entities.Produto;
import jakarta.jws.WebService;
import repositories.ProdutoRepository;

import java.util.List;

@WebService(endpointInterface = "resource.IProdutoResource")
public class ProdutoResource implements  IProdutoResource {

    private ProdutoRepository produtoRepository = new ProdutoRepository();

    @Override
    public void salvarProduto(Produto produto){produtoRepository.salvar(produto);}

    @Override
    public List<Produto> listarProduto(){
        List<Produto> produtos = produtoRepository.listar();
        return produtos;
    }

}
