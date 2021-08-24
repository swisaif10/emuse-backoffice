package fr.bilog.emuse;

import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import antlr.collections.List;
import fr.bilog.emuse.model.Article;
import fr.bilog.emuse.model.Role;
import fr.bilog.emuse.model.RoleName;
import fr.bilog.emuse.model.User;
import fr.bilog.emuse.repos.RoleRepo;
import fr.bilog.emuse.repos.UserRepo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class EmuseApplication implements CommandLineRunner {
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RepositoryRestConfiguration configuration;
	public static void main(String[] args) {
		SpringApplication.run(EmuseApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("defaultt").select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();

	}

	@SuppressWarnings("deprecation")
	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration("test-app-client-id", "test-app-client-secret", "test-app-realm", "test-app",
				"", ApiKeyVehicle.HEADER, "Authorization", "," /* scope separator */);
	}

	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring REST Sample with Swagger")
				.description("Spring REST Sample with Swagger").version("2.0").build();
	}

	@Override
	public void run(String... args) throws Exception {
		configuration.exposeIdsFor(Article.class);
		configuration.exposeIdsFor(User.class);

		Role admin = new Role();
		admin.setName(RoleName.ROLE_ADMIN);
		admin = roleRepo.save(admin);
		Role user = new Role();
		user.setName(RoleName.ROLE_USER);
		user = roleRepo.save(user);
		
		User userC = new User();
		userC.setRoles(Set.of(admin));
		userC.setEmail("souilmi@bilog.fr");
		userC.setPassword(bCryptPasswordEncoder.encode("12345678"));
		userC.setName("admin");
		userRepo.save(userC) ;

	}
	
	
	@Bean 
	BCryptPasswordEncoder bCryptPasswordEncoder2() {
		return new BCryptPasswordEncoder() ;
	}

}
