package fr.bilog.emuserefontebackend;

import fr.bilog.emuserefontebackend.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class EMuseRefonteBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EMuseRefonteBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
           /* accountService.addRole(RoleDesignation.USER.toString());
           	accountService.addRole(RoleDesignation.ADMIN.toString());
            accountService.addUser("Hela","farhati");
			accountService.addAppAdmin("admin","admin","admin","admin","admin");*/
		};
	}
	@Bean
	public Docket api() {
		HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme.JWT_BEARER_BUILDER
				.name("Authorization")
				.build();

		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("fr.bilog"))
				.paths(PathSelectors.any())
				.build()
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Collections.singletonList(authenticationScheme));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
	}
}
