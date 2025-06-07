package com.sivalabs.ft.users.domain;

import com.sivalabs.ft.users.ApplicationProperties;
import java.util.List;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Keycloak keycloak;
    private final ApplicationProperties properties;

    public UserService(Keycloak keycloak, ApplicationProperties properties) {
        this.keycloak = keycloak;
        this.properties = properties;
    }

    public List<User> findAllUsers() {
        RealmResource realm = keycloak.realm(properties.realmName());
        boolean emailVerified = true;
        List<UserRepresentation> users =
                realm.users().search(null, null, null, null, emailVerified, null, null, null, true);
        return users.stream()
                .map(u -> new User(
                        u.getId(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getFirstName() + ' ' + u.getLastName(),
                        "ROLE_USER"))
                .toList();
    }
}
