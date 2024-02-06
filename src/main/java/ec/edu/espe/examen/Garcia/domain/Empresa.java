package ec.edu.espe.examen.Garcia.domain;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "empresa")
public class Empresa {

    @Id
    private String codEmpresa;
    private String ruc;
    private String razonSocial;
    private Date fechaCreacion;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empresa other = (Empresa) obj;
        if (ruc == null) {
            if (other.ruc != null)
                return false;
        } else if (!ruc.equals(other.ruc))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ruc == null) ? 0 : ruc.hashCode());
        return result;
    }
}
