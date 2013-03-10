package com.packt.springsecurity.backend.security;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.packt.springsecurity.backend.persistence.model.Authority;
import com.packt.springsecurity.backend.persistence.model.User;
import com.packt.springsecurity.backend.persistence.service.IUserService;

@Component
public class SecUserService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    public SecUserService() {
        super();
    }

    // API

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User foundByUsername = userService.findByName(username);
        final Set<Authority> authorities = foundByUsername.getAuthorities();
        final List<GrantedAuthority> authoritiesForSpring = convertAuthorityNamesIntoSpringAuthorities(authorities);

        return new org.springframework.security.core.userdetails.User(username, foundByUsername.getPassword(), authoritiesForSpring);
    }

    // util

    private final List<GrantedAuthority> convertAuthorityNamesIntoSpringAuthorities(final Set<Authority> authorities) {
        final Iterable<String> authorityNames = Iterables.transform(authorities, Functions.toStringFunction());
        final String[] arrayOfAuthorityNames = Iterables.toArray(authorityNames, String.class);
        final List<GrantedAuthority> authoritiesForSpring = AuthorityUtils.createAuthorityList(arrayOfAuthorityNames);
        return authoritiesForSpring;
    }

}
