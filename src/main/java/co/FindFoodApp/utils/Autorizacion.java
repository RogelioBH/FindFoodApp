package co.FindFoodApp.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Autorizacion implements Filter{

    public static final String KEY="ColombiaEsGrandeFindFoodApp";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                String hash= "";
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;

                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpServletResponse.setHeader("Access-Control-Allow-Headers","Authorization, Content-Type");

                String url = httpServletRequest.getRequestURI();
                if(url.contains("/app/login")||url.contains("/app/user") || url.contains(".js")|| url.contains(".css")|| url.contains(".ico")|| url.contains("assets") || url.contains("#")){
                    chain.doFilter(request, response);
                }else{
                    hash = httpServletRequest.getHeader("Authorization");
                    if(hash==null || hash.trim().equals("")){
                        response.setContentType("application/json");
                        String body= "{\"autorizacion \":\"NO\"}";
                        response.getWriter().write(body);
                    }

                    try {
                        Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                        if(url.contains("/app/usuario") || url.contains("/app/donante") || url.contains("/app/beneficiario") || url.contains("/app/donacion") || url.contains("/api/verificar")){
                            chain.doFilter(request, response);
                        }
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
        
    }
    
   
    
}
