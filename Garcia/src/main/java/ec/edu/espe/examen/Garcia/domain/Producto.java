package ec.edu.espe.examen.Garcia.domain;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@Document(collection = "producto")
public class Producto {

    @Id
    private String codProducto;
    private String rudEmpresa;
    private String descripcion;
    private BigDecimal precio;
    private List<Comentario> comentarios;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (codProducto == null) {
            if (other.codProducto != null)
                return false;
        } else if (!codProducto.equals(other.codProducto))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codProducto == null) ? 0 : codProducto.hashCode());
        return result;
    }
}
