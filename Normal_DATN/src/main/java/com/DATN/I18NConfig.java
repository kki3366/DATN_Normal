package com.DATN;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;



@Configuration
public class I18NConfig implements WebMvcConfigurer{
@Bean("messageSource")
public MessageSource getMessageSource() {
ReloadableResourceBundleMessageSource ms
= new ReloadableResourceBundleMessageSource();
ms.setBasenames("classpath:i18n/layout", "classpath:i18n/home","classpath:i18n/tb");

ms.setDefaultEncoding("utf-8");
return ms;
}


@Bean("localeResolver")
public LocaleResolver getLocaleResolver() {
	CookieLocaleResolver resolver = new CookieLocaleResolver();
	resolver.setDefaultLocale(new Locale("vi"));
	resolver.setCookiePath("/");
	resolver.setCookieMaxAge(60 * 60); // 60 minutes
	return resolver;
}
@Override
public void addInterceptors(InterceptorRegistry registry) {
LocaleChangeInterceptor locale = new LocaleChangeInterceptor();
locale.setParamName("lang");
registry.addInterceptor(locale).addPathPatterns("/**");


}

}
