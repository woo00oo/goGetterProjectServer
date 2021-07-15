package udodog.goGetterServer.service.social;

public class FacebookOauth implements SocialOauth{

    @Override
    public String getOauthRedirectURL() {
        return "";
    }

    @Override
    public String requestAccessToken(String code) {
        return null;
    }
}
