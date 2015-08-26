package soccerteam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by roman_dolgoter on 24/08/2015.
 */

@Configuration
@EnableJpaRepositories(basePackages = {"soccerteam\\.data","soccerteam\\.model","soccerteam\\.web"})
public class JpaConfiguration {
}
