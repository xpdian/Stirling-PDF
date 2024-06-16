package stirling.software.SPDF.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import stirling.software.SPDF.config.security.handler.CustomLogoutSuccessHandler;

/**
 * @author 29443
 * @date 2022/4/4
 */
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class TokenSecurityConfig {

    @Autowired private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired private AccessDeniedHandler accessHandler;

    @Autowired private AuthenticationEntryPoint authenticationException;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter();
        return jwtTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (authorize) ->
                                authorize
                                        // 放行view页面请求
                                        .requestMatchers(
                                                "/**",
                                                "/",
                                                "/book-to-pdf",
                                                "/pdf-to-book",
                                                "/img-to-pdf",
                                                "/html-to-pdf",
                                                "/markdown-to-pdf",
                                                "/url-to-pdf",
                                                "/pdf-to-img",
                                                "/file-to-pdf",
                                                "/pdf-to-html",
                                                "/pdf-to-presentation",
                                                "/pdf-to-text",
                                                "/pdf-to-word",
                                                "/pdf-to-xml",
                                                "/pdf-to-csv",
                                                "/pdf-to-pdfa",
                                                "/pipeline",
                                                "/merge-pdfs",
                                                "/split-pdf-by-sections",
                                                "/view-pdf",
                                                "/multi-tool",
                                                "/remove-pages",
                                                "/pdf-organizer",
                                                "/extract-page",
                                                "/pdf-to-single-page",
                                                "/rotate-pdf",
                                                "/split-pdfs",
                                                "/sign",
                                                "/multi-page-layout",
                                                "/scale-pages",
                                                "/split-by-size-or-count",
                                                "/overlay-pdf",
                                                "/crop",
                                                "/auto-split-pdf",
                                                "/about",
                                                "/licenses",
                                                "/home",
                                                "/robots.txt",
                                                "/compress-pdf",
                                                "/extract-image-scans",
                                                "/show-javascript",
                                                "/stamp",
                                                "/add-page-numbers",
                                                "/fake-scan",
                                                "/extract-images",
                                                "/flatten",
                                                "/change-metadata",
                                                "/compare",
                                                "/print-file",
                                                "/ocr-pdf",
                                                "/add-image",
                                                "/adjust-contrast",
                                                "/repair",
                                                "/remove-blanks",
                                                "/remove-annotations",
                                                "/auto-crop",
                                                "/auto-rename",
                                                "/auto-redact",
                                                "/add-password",
                                                "/change-permissions",
                                                "/remove-password",
                                                "/add-watermark",
                                                "/cert-sign",
                                                "/remove-cert-sign",
                                                "/sanitize-pdf",
                                                "/get-info-on-pdf")
                                        .permitAll()
                                        // 放行view页面接口
                                        .requestMatchers(
                                                "/api/v1/convert/**",
                                                "/api/v1/filter/**",
                                                "/api/v1/misc/**",
                                                "/api/v1/pipeline/**",
                                                "/api/v1/security/**",
                                                "/api/v1/general/**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.POST, "/login")
                                        .permitAll()
                                        .requestMatchers("/register", "/captcha", "/sms")
                                        .permitAll()
                                        .requestMatchers("/ws/**")
                                        .permitAll()
                                        .requestMatchers(
                                                "/css/**",
                                                "/js/**",
                                                "/pdfjs/**",
                                                "/img/**",
                                                "/favicon.ico",
                                                "/webjars/**",
                                                "/doc.html",
                                                "/webjars/**",
                                                "/img.icons/**",
                                                "/swagger-resources/**",
                                                "/v2/api-docs")
                                        .permitAll())
                .authorizeHttpRequests( // 放行原有的静态文件
                        authz ->
                                authz.requestMatchers(
                                                req -> {
                                                    String uri = req.getRequestURI();
                                                    String contextPath = req.getContextPath();

                                                    // Remove the context path from the URI
                                                    String trimmedUri =
                                                            uri.startsWith(contextPath)
                                                                    ? uri.substring(
                                                                            contextPath.length())
                                                                    : uri;

                                                    return trimmedUri.endsWith(".svg")
                                                            || trimmedUri.startsWith("/error")
                                                            || trimmedUri.startsWith("/images/")
                                                            || trimmedUri.startsWith("/public/")
                                                            || trimmedUri.startsWith("/css/")
                                                            || trimmedUri.startsWith("/fonts/")
                                                            || trimmedUri.startsWith("/js/")
                                                            || trimmedUri.startsWith(
                                                                    "/api/v1/info/status");
                                                })
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        // 退出登录
        http.logout().logoutUrl("/logout").logoutSuccessHandler(customLogoutSuccessHandler);

        // 使用自定义异常处理
        http.exceptionHandling()
                .accessDeniedHandler(accessHandler)
                .authenticationEntryPoint(authenticationException);

        // 暂时先关闭跨站请求伪造
        http.csrf().disable();
        // 开启跨域
        http.cors();

        // 不会创建session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 添加jwt过滤器
        http.addFilterAfter(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
