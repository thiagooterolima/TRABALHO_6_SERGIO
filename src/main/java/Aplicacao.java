import jakarta.xml.ws.Endpoint;
import resources.CategoriaResource;
import resources.ProdutoResource;
import resources.VendaResource;

public class Aplicacao {

    public static void main(String[]args){
        Endpoint.publish(
                "http://127.0.0.1:8080/categoria-ws",
                new CategoriaResource()
        );

        Endpoint.publish(
                "http://127.0.0.1:8080/produto-ws",
                new ProdutoResource()
        );

        Endpoint.publish(
                "http://127.0.0.1:8080/venda-ws",
                new VendaResource()
        );

    }
}
