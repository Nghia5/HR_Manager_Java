package com.example.nhom3.user.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // BẬT CẤU HÌNH CORS TOÀN CỤC CHO SPRING SECURITY
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        
                        // 1. CHO PHÉP TRUY CẬP CÁC API AUTH (Đăng nhập, Quên mật khẩu)
                        .requestMatchers("/api/auth/**").permitAll()
                        
                        // 2. CHO PHÉP TRUY CẬP GIAO DIỆN TĨNH (HTML, CSS, JS, Images)
                        // Mở cửa cho trang chủ và các trang html phổ biến
                        .requestMatchers("/", "/index.html", "/login.html", "/forgot-password.html", "/register.html").permitAll()
                        // Mở cửa cho các thư mục chứa tài nguyên giao diện
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**", "/templates/**", "/assets/**").permitAll()
                        
                        // 3. CÁC API NGHIỆP VỤ YÊU CẦU PHẢI ĐĂNG NHẬP (CÓ TOKEN)
                        .requestMatchers("/api/employee-positions/**").authenticated()
                        .requestMatchers("/api/employees/**").authenticated()
                        .requestMatchers("/api/departments/**").authenticated()
                        .requestMatchers("/api/positions/**").authenticated()
                        
                        // TẤT CẢ CÁC REQUEST KHÁC PHẢI ĐƯỢC XÁC THỰC
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ĐỊNH NGHĨA CHI TIẾT CÁC LUẬT CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Cấp phép cho các nguồn truy cập (Frontend)
        configuration.setAllowedOrigins(Arrays.asList(
                "http://127.0.0.1:5500",
                "http://localhost:5500",
                "http://localhost:3000",
                "https://hr-manager-java-2.onrender.com" // Thêm chính link web của bạn trên Render
        ));

        // Cấp phép cho các method
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Cấp phép cho các Header (Quan trọng nhất là Authorization để gửi Token JWT)
        configuration.setAllowedHeaders(Arrays.asList(
                "Authorization", 
                "Content-Type", 
                "X-Requested-With", 
                "accept",
                "Origin", 
                "Access-Control-Request-Method", 
                "Access-Control-Request-Headers"
        ));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cho mọi API
        return source;
    }
}