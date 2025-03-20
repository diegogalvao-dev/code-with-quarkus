package guitarras.acme.service;

import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.model.Guitarra;

import java.util.List;

public interface GuitarraService {

    Guitarra create(GuitarrasDTO guitarra);
    void update(long id, Guitarra guitarra);
    void delete(long id);
    Guitarra findById(long id);
    Guitarra findByTipo(String tipo);
    List<Guitarra> findAll();

}
