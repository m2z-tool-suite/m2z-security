package com.m2z.tools.security.config;

import com.m2z.tools.security.service.JwkService;
import com.m2z.tools.security.service.JwkServiceNimbusImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Slf4j
public class JwkConfig {

    @Bean(name = "cognitoJwkServiceImpl")
    public JwkService jwkServiceCognito(@Value("${ms.aws.region}")String config, @Value("${ms.aws.cognito.userPoolId}") String userPoolId) {
        log.info("Creating cognito token processor");
        final String jwksUrl = String.format("https://cognito-idp.%s.amazonaws.com/%s%s", config, userPoolId, "/.well-known/jwks.json");
        HashSet<String> requiredClaims = new HashSet<String>(Arrays.asList("sub", "iat", "exp", "jti", "scope", "username", "token_use")); //cognito:groups
        return new JwkServiceNimbusImpl(jwksUrl, requiredClaims,"RS256", null);
    }
}
