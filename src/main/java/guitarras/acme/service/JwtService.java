package guitarras.acme.service;

public interface JwtService {

    String generateJwt(String username, String perfil);

}
