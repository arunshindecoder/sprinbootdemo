package org.arun.spring.sprinbootdemo.restfulwebservices.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConfigurationController {

    @Autowired
    private CurrencyServiceConfiguration configuration;
    @Autowired
    private CurrencyServiceConfigurationOne someOtherConfiguration;

    @RequestMapping(value = "/currency-configuration",method = RequestMethod.GET)
    public CurrencyServiceConfiguration retrieveAllCourses() {
        configuration.setHint(configuration.getHint() +" " + someOtherConfiguration.getSomeConfigurationValue());
        return configuration;

    }

}
