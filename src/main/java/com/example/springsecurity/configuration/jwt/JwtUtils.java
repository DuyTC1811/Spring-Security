package com.example.springsecurity.configuration.jwt;

import com.example.springsecurity.configuration.security.UserDetailsImpl;
import com.example.springsecurity.models.UserInfo;
import io.exceptions.models.TokenException;
import io.jsonwebtoken.*;
import io.utilities.cache.IRedisValueOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static io.utilities.converter.ConverterStringUntil.converterStringToObject;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    private final AuthenticationManager authenticationManager;
    private final IRedisValueOperation<String> redisValueOperation;
    @Value("${spring.security.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.security.jwtExpirationMs}")
    private int jwtExpirationMs;


    public JwtUtils(
            AuthenticationManager authenticationManager,
            IRedisValueOperation<String> redisValueOperation) {
        this.authenticationManager = authenticationManager;
        this.redisValueOperation = redisValueOperation;
    }

    /**
     * Được dùng để tạo ra mã Token và thêm các thông tin mà ta muốn thêm vào token
     * Bao gồm thời gian hiệu lực của Token và thuật toán generate Token
     *
     * @param username Thôn tin xác thực
     * @param password Thôn tin xác thực
     * @return Chuỗi String Token
     */
    public String generateJwtToken(String username, String password) {
        Date currentDate = new Date();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Thêm những thông tin vào trong token
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userPrincipal.getUsername());
        claims.put("userCode", userPrincipal.getUserCode());
        claims.put("roles", roles);
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().get("username").toString();
    }

    public String generateTokenFromUsername(String username) {
        Date currentDate = new Date();
        UserInfo userInfo = verifyExpiration(username);
        // Thêm những thông tin vào trong token
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userInfo.getUsername());
        claims.put("userCode", userInfo.getUserCode());
        claims.put("roles", userInfo.getRoles());

        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentDate.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Kiểm tra thời hạn của User
     * @param username username
     * @return UserInfo
     */
    public UserInfo verifyExpiration(String username) {
        String value = redisValueOperation.getValue(username);
        if (Objects.isNull(value)) {
            throw new TokenException(username, "This user has expired. Please make a new sign-in request");
        }
        return converterStringToObject(value, UserInfo.class);
    }


    /**
     * Phương thức này dùng để check Token
     * jwtSecret: mã key dùng để đối chiếu
     *
     * @param authToken một chuỗi Token
     * @return true hoặc false
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException exception) {
            LOGGER.error("Invalid JWT signature: {}", exception.getMessage());
        } catch (MalformedJwtException exception) {
            LOGGER.error("Invalid JWT token: {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            LOGGER.error("JWT token is expired: {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            LOGGER.error("JWT token is unsupported: {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            LOGGER.error("JWT claims string is empty: {}", exception.getMessage());
        }
        return false;
    }
}
