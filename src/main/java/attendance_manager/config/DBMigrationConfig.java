package attendance_manager.config;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */
//@Configuration
public class DBMigrationConfig {

    @Autowired
    DataSource dataSource;


    //    @Bean
//    public FlywayMigrationStrategy cleanMigrateStrategy() {
//        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
//            @Override
//            public void migrate(Flyway flyway) {
//                flyway.setIgnoreMissingMigrations(true);
//                flyway.setValidateOnMigrate(false);
//                flyway.repair();
//                flyway.migrate();
//            }
//        };
//
//        return strategy;
//    }
//
//    @Bean(initMethod = "migrate")
//    public Flyway flyway() {
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(dataSource);
//        flyway.repair();
//        flyway.setIgnoreMissingMigrations(true);
//        flyway.setValidateOnMigrate(false);
//        flyway.setBaselineOnMigrate(true);
//        flyway.setLocations(String.format("classpath:/db/migration"));
//        return flyway;
//    }
//
//    @Bean
//    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway, (f) -> {
//        });
//    }
//
//    @Bean
//    @DependsOn("entityManagerFactory")
//    FlywayMigrationInitializer delayedFlywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway);
//    }


}
