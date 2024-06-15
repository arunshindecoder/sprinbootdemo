package org.arun.spring.sprinbootdemo.restfulwebservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CourseCommandLineRunnerTwo implements CommandLineRunner {

//	@Autowired
//Any component youwant
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Number 2 Arun this is run at startup, you can create Cache objects");


    }

}
