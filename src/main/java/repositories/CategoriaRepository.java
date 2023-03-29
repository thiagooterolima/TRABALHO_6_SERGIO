package repositories;

import entities.Categoria;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CategoriaRepository {

    private final String INSERIR = String.join(
            "\n",
            "insert into categoria (nome)",
            "values (?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select *",
            "from categoria",
            "order by nome"
    );

    public void salvar(Categoria categoria){
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setString(1, categoria.getNome());

            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categoria>listar(){
        try( Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Categoria> categorias = new ArrayList<>();

            while (resultado.next()) {
                Categoria categoria = new Categoria();
                categoria.setCodigo(resultado.getInt("codigo"));
                categoria.setNome(resultado.getString("nome"));

                categorias.add(categoria);
            }

            return categorias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        Categoria categoria = new Categoria();
        categoria.setNome("Refrigerante");

        CategoriaRepository categoriaRepository = new CategoriaRepository();
        categoriaRepository.salvar(categoria);

        categoriaRepository.listar();
    }
}
