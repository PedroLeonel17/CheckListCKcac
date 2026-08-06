package dev.pedro.CodigoKidChecklist.Config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Checklist",
        version = "1.0",
        description = "API do sistema de checklist"
    )
)
public class OpenApiConfig {
}
