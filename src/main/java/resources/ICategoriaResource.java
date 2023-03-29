package resources;

import entities.Categoria;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ICategoriaResource {

    @WebMethod
    void salvarCategoria(@WebParam(name = "categoria") Categoria categoria);


    @WebMethod
    List<Categoria> listarCategoria();


}
