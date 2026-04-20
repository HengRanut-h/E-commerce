package com.pos.ecommerce.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.pos.ecommerce.constant.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),

    MANAGER(Set.of(
            MANAGER_READ, MANAGER_WRITE, MANAGER_UPDATE, MANAGER_DELETE
    )),

    ADMIN(Set.of(
            ADMIN_READ,   ADMIN_WRITE,   ADMIN_UPDATE,   ADMIN_DELETE,
            MANAGER_READ, MANAGER_WRITE, MANAGER_UPDATE, MANAGER_DELETE
    ));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(
                getPermissions().stream()
                        .map(p -> new SimpleGrantedAuthority(p.name()))
                        .toList()
        );
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}