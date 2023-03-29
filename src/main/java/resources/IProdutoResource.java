package resources;


import entities.Produto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IProdutoResource {

    @WebMethod
    void salvarProduto(@WebParam(name = "produto") Produto produto);

    @WebMethod
    List<Produto> listarProduto();

}
