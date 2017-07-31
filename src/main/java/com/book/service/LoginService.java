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
@Transactional(readOnly = true, rollbackFor = Exception.class)
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
}
