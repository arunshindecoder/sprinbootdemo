package org.arun.spring.sprinbootdemo.restfulwebservices.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CurrencyServiceConfigurationOne {

    // this is coming from application.properoties based on active profile
    @Value("${some.other.configurationvalue}")
    private String someConfigurationValue;

    @Value("#{'${corsallowedlistarun}'.split(',')}")
    private List<String> corsAllowedList = new ArrayList<>();

    public String getSomeConfigurationValue() {
        return someConfigurationValue + " " +corsAllowedList;
    }

}
