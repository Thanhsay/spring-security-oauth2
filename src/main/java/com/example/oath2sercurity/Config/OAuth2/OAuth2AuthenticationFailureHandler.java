package com.example.oath2sercurity.Config.OAuth2;

import com.example.oath2sercurity.Config.Util.CookieUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.oath2sercurity.Config.OAuth2.HttpCookieOAuth2AuthorizationRequest.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final HttpCookieOAuth2AuthorizationRequest httpCookieOAuth2AuthorizationRequest;


    public OAuth2AuthenticationFailureHandler(
            HttpCookieOAuth2AuthorizationRequest httpCookieOAuth2AuthorizationRequest) {
        this.httpCookieOAuth2AuthorizationRequest = httpCookieOAuth2AuthorizationRequest;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
        String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue).orElse(("/"));
        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();
        httpCookieOAuth2AuthorizationRequest.removeAuthorizationRequestCookie(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
