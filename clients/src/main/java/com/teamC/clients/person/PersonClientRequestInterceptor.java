//package com.teamC.clients.person;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import feign.auth.BasicAuthRequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Component
////@Configuration
//public class PersonClientRequestInterceptor implements RequestInterceptor {
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (requestAttributes == null) {
//            return;
//        }
//        HttpServletRequest request = requestAttributes.getRequest();
//        if (request == null) {
//            return;
//        }
//        String authorization = request.getHeader(AUTHORIZATION_HEADER);
//        if (authorization == null) {
//            return;
//        }
//        requestTemplate.header(AUTHORIZATION_HEADER, authorization);
//    }
//
////    @Bean
////    public RequestInterceptor requestInterceptor() {
////
////        return requestTemplate -> {
////            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////            if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
////                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
////                requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", details.getTokenValue()));
////            }
////        };
////    }
//
////    @Bean
////    public RequestInterceptor basicAuthRequestInterceptor() {
////        return new BasicAuthRequestInterceptor("User", "{noop}Password");
////    }
//
//
//}
