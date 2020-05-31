package kandk.springframework.sfkpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfkPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfkPetClinicApplication.class, args);
	}
//	@Bean
//	@Profile("springdatajpa")
//	public DataSource getDataSource(){
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		DataSource dataSource = dataSourceBuilder
//				.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
//				.url("jdbc:sqlserver://localhost;databaseName=petclinic")
//				.username("sa").password("Kornel120481")
//				.build();
//		return dataSource;
//	}

}
