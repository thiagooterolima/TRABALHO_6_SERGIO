package entities;


import lombok.Data;

@Data
public class Produto {

    private Integer codigo;

    private String nome;

    private Double preco;

    private Integer quantidade;

    private Categoria categoria;
}
