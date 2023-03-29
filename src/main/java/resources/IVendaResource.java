package resources;


import entities.Venda;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IVendaResource {

    @WebMethod
    void salvarVenda(@WebParam(name = "venda")Venda venda);

    @WebMethod
    List<Venda> listarVenda();
}
