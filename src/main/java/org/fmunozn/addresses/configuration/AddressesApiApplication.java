package org.fmunozn.addresses.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
@EnableCaching
@ComponentScan("org.fmunozn.addresses")
public class AddressesApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AddressesApiApplication.class, args);
	}	
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {

		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();

		ObjectMapper result = new ObjectMapper();
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		b.configure(result);
		
		return b;
		
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
	
}
