package com.mytechuncle.acnh.configurations.whitelist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("whitelist")
@Component
@ConfigurationProperties(prefix="whitelist")
public class Whitelist {
    private List<String> emails = new ArrayList<>();

    public List<String> getEmails() {
        return emails;
    }
}
