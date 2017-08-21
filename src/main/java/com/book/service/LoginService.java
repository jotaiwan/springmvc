package com.book.service;

import com.book.Repository.LoginRepository;
import com.book.data.dto.LoginDto;
import com.book.data.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public List<LoginDto> findAll() {
        return loginRepository.findAll().stream()
                .map(l -> new LoginDto(l.getId(), l.getUsername())).collect(Collectors.toList());
    }

    public LoginDto findById(int id) {
        Login login = loginRepository.findById(id);
        return new LoginDto(login.getId(), login.getUsername());
    }

    public void save(LoginDto loginDto) {
        // convert loginDto to login
        Login login = new Login();
        login.setUsername(loginDto.getUsername());
        login.setPassword(loginDto.getPassword());
        login.setEmailAddress(loginDto.getEmailAddress());
        loginRepository.saveLogin(login);
    }

    public void update(LoginDto loginDto) {
        // convert loginDto to login
        Login login = new Login();
        login.setId(loginDto.getId());
        login.setUsername(loginDto.getUsername());
        login.setPassword(loginDto.getPassword());
        loginRepository.update(login);
    }

    public int delete(int id) {
        return loginRepository.delete(id);
    }
}
