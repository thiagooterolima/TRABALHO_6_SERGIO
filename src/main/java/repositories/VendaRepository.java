package repositories;

import entities.Produto;
import entities.Venda;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {



    private final String INSERIR = String.join(
            "\n",
            "insert into vendas(horario,quantidade_comprada,valor_total,produto_codigo)",
            "values(?,?,?,?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select v.codigo, v.horario, v.quantidade_comprada,v.valor_total,v.produto_codigo," +
                     "p.codigo, p.nome, p.preco, p.quantidade, p.categoria_codigo",
                    "from vendas v",
                    "order by v.nome"
    );

    public void salvar(Venda venda) {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setDate(1, venda.getHorario());
            comando.setInt(2, venda.getQuantiade_comprada());
            comando.setDouble(3, venda.getValor_total());
            comando.setInt(4, venda.getProduto().getCodigo());

            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Venda> listar(){
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Venda> vendas = new ArrayList<>();

            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCodigo(resultado.getInt("p.codigo"));
                produto.setNome(resultado.getString("p.nome"));
                produto.setPreco(resultado.getDouble("p.preco"));
                produto.setQuantidade(resultado.getInt("p.quantidade"));

                Venda venda = new Venda();
                venda.setCodigo(resultado.getInt("v.codigo"));
                venda.setHorario(resultado.getDate("v.horario"));
                venda.setQuantiade_comprada(resultado.getInt("v.quantidade"));
                venda.setValor_total(resultado.getDouble("v.valor_total"));
                venda.setProduto(produto);

                System.out.println(venda);

                vendas.add(venda);
            }

            return vendas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[]args){



        Produto produto = new Produto();
        produto.setCodigo(1);

        Venda venda = new Venda();
       // venda.setHorario('12/03/2012');
        venda.setQuantiade_comprada(10);
        venda.setValor_total(40.4);

        VendaRepository vendaRepository = new VendaRepository();
        vendaRepository.salvar(venda);

        vendaRepository.listar();

    }
    
}
