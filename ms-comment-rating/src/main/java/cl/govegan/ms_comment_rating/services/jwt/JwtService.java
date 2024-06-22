package cl.govegan.ms_comment_rating.services.jwt;

public interface JwtService {
    String getUsernameFromToken(String token);
    String getRoleFromToken(String token);
    boolean validateToken(String token);
}