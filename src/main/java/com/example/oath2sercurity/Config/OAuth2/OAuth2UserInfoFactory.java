package com.example.oath2sercurity.Config.OAuth2;

import com.example.oath2sercurity.Exception.OAuth2AuthenticationProcessingException;
import com.example.oath2sercurity.Model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + "failed. Please try again later!");
        }
    }
}
