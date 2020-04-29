package com.mytechuncle.acnh.configurations;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsentingOAuth2AuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private DefaultOAuth2AuthorizationRequestResolver defaultResolver;

    public ConsentingOAuth2AuthorizationRequestResolver(ClientRegistrationRepository repo) {
        defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, "/oauth2/authorization");
    }


    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest httpServletRequest) {
        OAuth2AuthorizationRequest req = defaultResolver.resolve(httpServletRequest);
        if(req != null) {
            req = customizeAuthorizationRequest(req);
        }
        return req;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest httpServletRequest, String clientRegistrationId) {
        OAuth2AuthorizationRequest req = defaultResolver.resolve(httpServletRequest, clientRegistrationId);
        if(req != null) {
            req = customizeAuthorizationRequest(req);
        }
        return req;
    }

    private OAuth2AuthorizationRequest customizeAuthorizationRequest(OAuth2AuthorizationRequest req) {
        Map<String, Object> additionalParameters =
                new LinkedHashMap<>(req.getAdditionalParameters());
        additionalParameters.put("prompt", "consent");

        return OAuth2AuthorizationRequest.from(req)
                .additionalParameters(additionalParameters)
                .build();
    }
}
