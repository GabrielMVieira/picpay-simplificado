package jose.gabriel.picpaysimplificado.infra.springdoc;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PicPay simplificado")
                        .description("This project is an API built using Java, Java Spring, PostgreSql as the database.")
                        .contact(new Contact()
                                .name("Gabriel Vieira")
                                .email("gabrielmvieira1296@gmail.com"))
                        .license(new License()
                                .name("PicPay Backend Challenge")
                                .url("https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file")));
    }
}
