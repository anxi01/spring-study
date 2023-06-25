package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);

		ApplicationContext context = ApplicationContextProvider.getContext();

/*		// @Component로 만들어진 bean들을 꺼내온다.
		Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
		UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

		// 우선 Base64를 주입한 Encoder
		Encoder encoder = new Encoder(base64Encoder);
		String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

		String result = encoder.encode(url);
		System.out.println(result);

		// 이후 setter를 이용한 DI를 통해 url를 주입한 Encoder
		encoder.setiEncoder(urlEncoder);
		result = encoder.encode(url);
		System.out.println(result);*/


		// Encoder이 Component인 경우 -> new를 통한 객체 생성 X
		// Spring container에서 관리하는 객체 = Bean
		Encoder encoder = context.getBean("urlEncode", Encoder.class);
		String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
		String result = encoder.encode(url);
		System.out.println(result);

	}

}

@Configuration // 한개의 클래스에서 여러개의 bean을 등록(== @Component와 비슷)
class AppConfig{

	@Bean("base64Encode")
	public Encoder encoder(Base64Encoder base64Encoder){
		return new Encoder(base64Encoder);
	}
	@Bean("urlEncode")
	public Encoder encoder(UrlEncoder urlEncoder){
		return new Encoder(urlEncoder);
	}
}
