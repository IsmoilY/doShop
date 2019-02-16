package com.apple.selfone.security;

import com.apple.selfone.entity.Role;
import com.apple.selfone.entity.User;
import com.apple.selfone.model.RegisterRequest;
import com.apple.selfone.model.ResultResponse;
import com.apple.selfone.model.UserPrincipal;
import com.apple.selfone.repository.RoleRepository;
import com.apple.selfone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException(usernameOrEmail));
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new UserPrincipal(
                usernameOrEmail,
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user,
                authorities
        );
    }

    public boolean checkAuth(){
        return (SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken);
    }

    public ResultResponse register(RegisterRequest request){
        ResultResponse response = new ResultResponse();

        try {
            User user = new User(
                    request.getUsername(),
                    request.getEmail(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getFullName(),
                    "",
                    true,
                    Arrays.asList(roleRepository.getOne(30))
            );

            userRepository.save(user);
            response.setSuccess(true);
            response.setMessage("New user is successfully registered");
        }catch (Exception e){
            response.setMessage("Registering new user failed");
            response.setSuccess(false);
            e.printStackTrace();
        }

        return response;
    }

}
