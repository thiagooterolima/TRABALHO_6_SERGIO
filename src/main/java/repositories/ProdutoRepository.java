package repositories;

import entities.Categoria;
import entities.Produto;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private final String INSERIR = String.join(
            "\n",
            "insert into produtos (nome,preco,quantidade,categoria_codigo)",
            "values (?,?,?,?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select p.codigo, p.nome ,p.preco ,p.quantidade ,c.codigo, c.nome, ",
            "from produtos p ",
            "inner join categoria e on c.codigo = p.categoria_codigo",
            "order by c.nome"
    );

    public void salvar(Produto produto){
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setString(1, produto.getNome());
            comando.setInt(2, produto.getQuantidade());
            comando.setDouble(3,produto.getPreco());
            comando.setInt(4,produto.getCategoria().getCodigo());

            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto>listar() {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Produto> produtos = new ArrayList<>();

            while (resultado.next()) {
                Categoria categoria = new Categoria();
                categoria.setCodigo(resultado.getInt("c.codigo"));
                categoria.setNome(resultado.getString("c.nome"));

                Produto produto = new Produto();
                produto.setCodigo(resultado.getInt("p.codigo"));
                produto.setNome(resultado.getString("p.nome"));
                produto.setPreco(resultado.getDouble("p.preco"));
                produto.setQuantidade(resultado.getInt("p.quantidade"));
                produto.setCategoria(categoria);

                System.out.println(produto);

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[]args){

        Categoria categoria = new Categoria();
        categoria.setCodigo(1);

        Produto produto = new Produto();
        produto.setNome("Soda");
        produto.setPreco(7.00);
        produto.setQuantidade(1);
        produto.setCategoria(categoria);

        ProdutoRepository produtoRepository = new ProdutoRepository();
        produtoRepository.salvar(produto);

        produtoRepository.listar();

    }
}
