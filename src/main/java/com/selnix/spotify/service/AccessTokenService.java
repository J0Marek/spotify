package com.selnix.spotify.service;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AccessTokenService {

    /**
     * TODO Use Spring Client to do same shit
     * TODO Dont send request every time, use refresh token instead
     */

    public String getAccessToken() throws OAuthSystemException, OAuthProblemException {

        String clientId = "a92335586fa64993a335e4ef614175a2";
        String clientSecret = "8c7fde1e19284682bca02950f2f66c33";
        String tokenURL = "https://accounts.spotify.com/api/token";

        String accessToken;
        String bearerValue;
        String encodedValue = getBase64Encoded(clientId, clientSecret);

        OAuthClient client = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest request = OAuthClientRequest
                .tokenLocation(tokenURL)
                .setGrantType(GrantType.CLIENT_CREDENTIALS)
                .buildBodyMessage();

        request.addHeader("Authorization", "Basic " + encodedValue);

        OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class);

        accessToken = oAuthResponse.getAccessToken();
        bearerValue = "Bearer " + accessToken;
        return bearerValue;
    }

    public String getBase64Encoded(String id, String password) {
        return Base64.getEncoder().encodeToString((id + ":" + password).getBytes(StandardCharsets.UTF_8));
    }
}
