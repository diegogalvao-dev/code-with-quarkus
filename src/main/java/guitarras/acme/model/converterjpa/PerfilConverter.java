package guitarras.acme.model.converterjpa;

import guitarras.acme.model.Perfil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter<Perfil, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Perfil perfil) {
        return perfil == null ? null : perfil.getID();

    }

    @Override
    public Perfil convertToEntityAttribute(Integer id) {
        return Perfil.valueOf(id);
    }

}