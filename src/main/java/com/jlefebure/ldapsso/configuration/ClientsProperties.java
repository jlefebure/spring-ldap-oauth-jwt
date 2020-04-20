package com.jlefebure.ldapsso.configuration;

import com.jlefebure.ldapsso.model.sso.Client;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "auth")
public class ClientsProperties {
    private Map<String, Client> consumers;
    private boolean degrade = false;

    public Map<String, Client> getConsumers() {
        return consumers;
    }

    public void setConsumers(Map<String, Client> consumers) {
        this.consumers = consumers;
    }

    public Client getClient(String consumer) {
        return consumers.get(consumer);
    }

    public boolean isDegrade() {
        return degrade;
    }

    public void setDegrade(boolean degrade) {
        this.degrade = degrade;
    }
}
