package pruebas.demo.service;


public class JwtFilter {

    // @Autowired
    // private JwtService jwtService;

    // @Autowired
    // private CustomUserDetailsService userDetailsService;

    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    //         FilterChain filterChain) throws ServletException, IOException {

    //     final String authHeader = request.getHeader("Authorization");

    //     if (authHeader == null || !authHeader.startsWith("Bearer ")) {
    //         filterChain.doFilter(request, response);
    //         return;
    //     }

    //     String token = authHeader.substring(7);
    //     String correo = jwtService.extractUsername(token);

    //     if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //         UserDetails userDetails = userDetailsService.loadUserByUsername(correo);

    //         if (jwtService.isTokenValid(token, userDetails)) {
    //             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
    //                     null, userDetails.getAuthorities());

    //             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //             SecurityContextHolder.getContext().setAuthentication(authToken);
    //         }
    //     }

    //     filterChain.doFilter(request, response);
    // }
}
