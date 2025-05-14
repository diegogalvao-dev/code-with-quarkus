package guitarras.acme.service;

import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class HashServiceImpl implements HashService{

    private String salt = "##224%g";
    private Integer iterationCount = 403;
    private Integer keyLength = 512;

    @Override
    public String getHashSenha(String senha) throws Exception{

        byte[] result;

        try {

            result = SecretKeyFactory
                    .getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(senha.toCharArray(),
                            salt.getBytes(),
                            iterationCount,
                            keyLength))
                    .getEncoded();

        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new Exception("Problema ao gerar o hash");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("Problema ao gerar o hash");
        }

        return Base64.getEncoder().encodeToString(result);

    }

    public static void main(String ... args) {
        HashServiceImpl hash = new HashServiceImpl();
        try {
            System.out.println(hash.getHashSenha("123456"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
