package com.doctor.doctorReview.Misc;

import com.doctor.doctorReview.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomerUserDetails extends User implements UserDetails {


    public CustomerUserDetails(final User user){
            super(user);
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getPassword() {
        return super.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
