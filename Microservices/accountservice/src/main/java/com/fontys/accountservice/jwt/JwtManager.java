package com.fontys.accountservice.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import net.minidev.json.JSONArray;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.ApplicationScope;


import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.text.ParseException;

@ApplicationScope
@Named
public class JwtManager {
    static {
        FileInputStream fis = null;
        char[] password = "secret".toCharArray();
        String alias = "alias";
        PrivateKey pk = null;
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            String configDir = System.getProperty("");
            String keystorePath = configDir + File.separator + "jwt.keystore";
            fis = new FileInputStream(keystorePath);
            ks.load(fis, password);
            Key key = ks.getKey(alias, password);
            if (key instanceof PrivateKey) {
                pk = (PrivateKey) key;
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        privateKey = pk;
    }


    private static final PrivateKey privateKey;
    private static final int TOKEN_VALIDITY = 14400;
    private static final String CLAIM_ROLES = "groups";
    private static final String ISSUER = "quickstart-jwt-issuer";
    private static final String AUDIENCE = "jwt-audience";

    public String createJwt(final String subject, final String role) throws Exception {
        JWSSigner signer = new RSASSASigner(privateKey);
        JsonArrayBuilder rolesBuilder = Json.createArrayBuilder();
        rolesBuilder.add(role);
        JsonObjectBuilder claimsBuilder = Json.createObjectBuilder()
                .add("sub", subject)
                .add("iss", ISSUER)
                .add("aud", AUDIENCE)
                .add(CLAIM_ROLES, rolesBuilder.build())
                .add("exp", ((System.currentTimeMillis() / 1000) + TOKEN_VALIDITY));

        JWSObject jwsObject = new JWSObject(new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(new JOSEObjectType("jwt")).build(),
                new Payload(claimsBuilder.build().toString()));

        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    public static final String BEARER = "Bearer";

    public static String decodeToken(String token) {
        System.out.println("decode token: " + token);
        if (token != null && token.startsWith(BEARER)) {
            try {
                JWT jwt = JWTParser.parse(token.substring(7));
                System.out.println("_-----------------------------------------");
                System.out.println(jwt.getJWTClaimsSet().getClaims());
                String username = jwt.getJWTClaimsSet().getClaims().get("sub").toString();
                JSONArray groups = (JSONArray) jwt.getJWTClaimsSet().getClaims().get("groups");
                System.out.println("uname: " + username);
                System.out.println("groups: " + groups.get(0).toString());
                System.out.println("created token: " + jwt.toString());
                return username;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
