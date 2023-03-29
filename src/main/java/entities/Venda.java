package entities;


import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

@Data
public class Venda {

    private Integer codigo;

    private Date horario;

    private Integer quantiade_comprada;

    private Double valor_total;

    private Produto produto;
}
