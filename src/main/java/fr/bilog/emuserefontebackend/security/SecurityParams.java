package fr.bilog.emuserefontebackend.security;

public interface SecurityParams {
    String JWT_HEADER_NAME = "Authorization";
    String SECRET = "narfk";
    long EXPIRATION = 1000 * 3600 * 24;
    String HEADER_PREFIX = "Bearer ";
}
