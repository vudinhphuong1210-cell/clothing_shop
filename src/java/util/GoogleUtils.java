package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleUtils {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = GoogleUtils.class.getClassLoader().getResourceAsStream("google-oauth.properties")) {
            if (is != null) {
                props.load(is);
            } else {
                System.err.println("CRITICAL: google-oauth.properties not found in classpath!");
            }
        } catch (IOException e) {
            System.err.println("CRITICAL: Could not load google-oauth.properties: " + e.getMessage());
        }
    }

    public static final String GOOGLE_CLIENT_ID = props.getProperty("google.client.id", "");
    public static final String GOOGLE_CLIENT_SECRET = props.getProperty("google.client.secret", "");
    public static final String GOOGLE_REDIRECT_URI = props.getProperty("google.redirect.uri", "");
    
    public static final String GOOGLE_GRANT_TYPE = "authorization_code";
    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    // Getter methods dùng bởi GoogleLoginServlet
    public static String getClientId() { return GOOGLE_CLIENT_ID; }
    public static String getRedirectUri() { return GOOGLE_REDIRECT_URI; }

    public static String getToken(final String code) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        
        String body = "client_id=" + URLEncoder.encode(GOOGLE_CLIENT_ID, StandardCharsets.UTF_8)
                    + "&client_secret=" + URLEncoder.encode(GOOGLE_CLIENT_SECRET, StandardCharsets.UTF_8)
                    + "&redirect_uri=" + URLEncoder.encode(GOOGLE_REDIRECT_URI, StandardCharsets.UTF_8)
                    + "&code=" + URLEncoder.encode(code, StandardCharsets.UTF_8)
                    + "&grant_type=" + URLEncoder.encode(GOOGLE_GRANT_TYPE, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GOOGLE_LINK_GET_TOKEN))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println("Google Token Response: " + json);
        
        if (json.contains("error")) {
            throw new IOException("Google Token Error: " + json);
        }
        return getValueFromJson(json, "access_token");
    }

    public static Map<String, String> getUserInfo(final String accessToken) throws IOException, InterruptedException {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IOException("Access token is null or empty");
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GOOGLE_LINK_GET_USER_INFO + accessToken))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println("Google UserInfo Response: " + json);
        
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("id", getValueFromJson(json, "id"));
        userInfo.put("email", getValueFromJson(json, "email"));
        userInfo.put("name", getValueFromJson(json, "name"));
        userInfo.put("picture", getValueFromJson(json, "picture"));
        
        return userInfo;
    }

    private static String getValueFromJson(String json, String key) {
        String pattern = "\"" + key + "\":\\s*\"([^\"]*)\"";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}