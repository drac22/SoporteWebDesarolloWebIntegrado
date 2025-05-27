package pruebas.demo.service;

public class JwtService {

    // @Value("${jwt.secret}")
    // private String secretKey;

    // @Value("${jwt.expiration}")
    // private long expirationTime;

    // private Key getSigningKey() {
    //     return Keys.hmacShaKeyFor(secretKey.getBytes());
    // }

    // public String generateToken(UserDetails userDetails) {
    //     return Jwts.builder()
    //             .setSubject(userDetails.getUsername())
    //             .claim("roles", userDetails.getAuthorities().stream()
    //                     .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
    //             .signWith(getSigningKey(), SignatureAlgorithm.HS256)
    //             .compact();
    // }

    // public String extractUsername(String token) {
    //     return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
    //             .parseClaimsJws(token).getBody().getSubject();
    // }

    // public boolean isTokenValid(String token, UserDetails userDetails) {
    //     return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    // }

    // private boolean isTokenExpired(String token) {
    //     return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
    //             .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    // }
}
