package fr.bilog.emuserefontebackend;

import fr.bilog.emuserefontebackend.entities.article.Region;
import fr.bilog.emuserefontebackend.models.DepartementRequest;
import fr.bilog.emuserefontebackend.models.RegionRequest;
import fr.bilog.emuserefontebackend.models.RoleDesignation;
import fr.bilog.emuserefontebackend.services.AccountService;
import fr.bilog.emuserefontebackend.services.AppDataService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
public class EMuseRefonteBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EMuseRefonteBackendApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(EMuseRefonteBackendApplication.class);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    CommandLineRunner start(AppDataService appDataService, AccountService accountService) {
        return args -> {
/*
           appDataService.addRole(RoleDesignation.USER.toString());
            appDataService.addRole(RoleDesignation.ADMIN.toString());
            appDataService.addRole(RoleDesignation.SUPERADMIN.toString());
            accountService.addAppSuperAdmin("superAdmin", "superAdmin", "superAdmin", "superAdmin", "superAdmin");
            accountService.addAppAdmin("admin", "admin", "admin", "admin", "admin");

            appDataService.addDureeActivitesModeresType("- de 2.5 h");
            appDataService.addDureeActivitesModeresType("2.5 - 5 h");
            appDataService.addDureeActivitesModeresType("+ 5 h");
            appDataService.addDureeActivitesSoutenuesType("- de 2.5 h");
            appDataService.addDureeActivitesSoutenuesType("2.5 - 5 h");
            appDataService.addDureeActivitesSoutenuesType("+ 5 h");
            appDataService.addActivitesPreferencesType("En intérieur");
            appDataService.addActivitesPreferencesType("En extérieur");
            appDataService.addActivitesPreferencesType("En seul");
            appDataService.addActivitesPreferencesType("À plusieurs");
            appDataService.addApplicationType("F.enceinte");
            appDataService.addApplicationType("Asthmatique");
            appDataService.addApplicationType("Fumeur");
            appDataService.addApplicationType("Standard");

            Region regionHF = appDataService.addRegion(new RegionRequest("Haut-de-France", true));
            Region regionProvance = appDataService.addRegion(new RegionRequest("Provence-Alpes-Côte d'Azur", true));


            appDataService.addDepartement(new DepartementRequest("Aisne", regionHF.getId()));
            appDataService.addDepartement(new DepartementRequest("Nord", regionHF.getId()));
            appDataService.addDepartement(new DepartementRequest("Oise", regionHF.getId()));
            appDataService.addDepartement(new DepartementRequest("Pas-de-Calais", regionHF.getId()));
            appDataService.addDepartement(new DepartementRequest("Somme", regionHF.getId()));


            appDataService.addDepartement(new DepartementRequest("les Bouches-du-Rhône", regionProvance.getId()));
            appDataService.addDepartement(new DepartementRequest(" le Var", regionProvance.getId()));
            appDataService.addDepartement(new DepartementRequest("le Vaucluse", regionProvance.getId()));
            appDataService.addDepartement(new DepartementRequest(" les Alpes-Maritimes", regionProvance.getId()));
            appDataService.addDepartement(new DepartementRequest(" les Hautes-Alpes ", regionProvance.getId()));
            appDataService.addDepartement(new DepartementRequest("les Alpes de Haute-Provence", regionProvance.getId()));
*/

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
