package ec.edu.espe.examen.Garcia.domain;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comentario {
    private Integer calificacion;
    private String comentario;
    private String usuario;
    private Date fechaComentario;
}
