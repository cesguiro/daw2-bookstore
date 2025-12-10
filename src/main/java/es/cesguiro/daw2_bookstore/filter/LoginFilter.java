package es.cesguiro.daw2_bookstore.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class LoginFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Objeto para leer el cuerpo JSON

    // Constructor para inyección de dependencia
    public LoginFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 1. Verificar si la URL y el método coincide (ya gestionado por FilterRegistrationBean, pero buena práctica)
        if (!request.getRequestURI().equals("/api/login") || !request.getMethod().equals("POST")) {
            // Si NO es el POST a /api/login, seguir al siguiente filtro
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Ejecutar la Lógica del Login
        try {
            // A. Extraer credenciales del cuerpo de la petición (JSON)
            LoginCredentials credentials = objectMapper.readValue(request.getInputStream(), LoginCredentials.class);

            // B. Autenticar y Generar Token (Llamada al Servicio)
            String token = authService.authenticateAndGenerateToken(
                    credentials.getUsername(),
                    credentials.getPassword()
            );

            // C. Respuesta Exitosa
            sendSuccessResponse(response, token);

        } catch (AuthenticationException e) {
            // D. Manejo de Error (Credenciales inválidas)
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Credenciales inválidas.");
        } catch (Exception e) {
            // E. Manejo de otros errores (ej. JSON mal formado)
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Formato de petición inválido.");
        }

        // **IMPORTANTE:** Aquí NO llamamos a filterChain.doFilter(), ya que la petición
        // de login ha sido resuelta y no debe pasar a los siguientes filtros ni al endpoint.
    }

    // --- Métodos Auxiliares ---

    private void sendSuccessResponse(HttpServletResponse response, String token) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        // Devolver el token, y la expiración si quieres que el cliente lo sepa
        String jsonResponse = String.format("{\"success\": true, \"token\": \"%s\"}", token);
        response.getWriter().write(jsonResponse);
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status);
        String jsonResponse = String.format("{\"success\": false, \"message\": \"%s\"}", message);
        response.getWriter().write(jsonResponse);
    }
}
