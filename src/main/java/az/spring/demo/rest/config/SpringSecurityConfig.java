//package az.spring.demo.rest.config;
//
//
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@EnableWebSecurity
//@Deprecated
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Authentication
//        auth.inMemoryAuthentication()
//                .withUser("rashid")
//                .password("1234")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Authorization
//        http.formLogin();
//        http.authorizeRequests()
//                .antMatchers("/welcome", "/").permitAll()
//                .antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .and().csrf().disable();
//    }
//
//
//
//
//
//
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        // Authorization
////        http.formLogin();
////        http.authorizeRequests()
////                .antMatchers("/welcome", "/").permitAll()
////                .antMatchers(HttpMethod.GET,"/all").hasRole("ADMIN")
////                .antMatchers(HttpMethod.POST, "/add").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
////                .anyRequest().authenticated()
////                .and().httpBasic()
////                .and().csrf().disable();
////    }
//
//
//
//
//
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
//
//
