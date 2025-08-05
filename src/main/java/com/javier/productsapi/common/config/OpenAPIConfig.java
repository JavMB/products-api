package com.javier.productsapi.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Ecommerce API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Javi", email = "javiermengual@live.com", url = "https://www.javiemengual.me"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = @Server(
                url = "$http://localhost:8080",
                description = "Production"
        )
)


@Configuration
public class OpenAPIConfig {

}
