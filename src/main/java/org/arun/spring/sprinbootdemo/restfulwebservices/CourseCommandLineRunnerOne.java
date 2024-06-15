package org.arun.spring.sprinbootdemo.restfulwebservices;

import org.arun.spring.sprinbootdemo.restfulwebservices.configuration.YamlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CourseCommandLineRunnerOne implements CommandLineRunner {

//	@Autowired
//Any component youwant
@Autowired
private YamlConfig yamlConfig;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Arun this is run at startup, you can create Cache objects");
        System.out.println("YAML Properties " + yamlConfig.toString());



    }

}
