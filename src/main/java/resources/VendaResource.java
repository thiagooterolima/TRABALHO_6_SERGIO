package resources;

import entities.Venda;
import jakarta.jws.WebService;
import repositories.VendaRepository;

import java.util.List;

@WebService(endpointInterface = "resources.IVendaResource")
public class VendaResource implements IVendaResource {

    private VendaRepository vendaRepository = new VendaRepository();
    @Override
    public void salvarVenda(Venda venda) {
        vendaRepository.salvar(venda);
    }

    @Override
    public List<Venda> listarVenda() {
        List<Venda> vendas = vendaRepository.listar();
        return vendas;

    }

}
