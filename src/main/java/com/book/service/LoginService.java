package com.book.service;

import com.book.Repository.LoginRepository;
import com.book.Repository.UserAccountRepository;
import com.book.Repository.UserDocumentRepository;
import com.book.data.dto.LoginDto;
import com.book.data.entity.Login;
import com.book.data.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

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

    @Autowired
    UserAccountRepository userAccountRepository;

    public List<LoginDto> findAll() {
        return loginRepository.findAll().stream()
                .map(l -> new LoginDto(l.getId(), l.getUsername(), l.getUser())).collect(Collectors.toList());
    }

    public LoginDto findById(int id) {
        Login login = loginRepository.findById(id);
        return new LoginDto(login.getId(), login.getUsername(), login.getUser());
    }

    public void save(LoginDto loginDto) {
        Login login = new Login();
        login.setUsername(loginDto.getUsername());
        login.setPassword(loginDto.getPassword());
        loginRepository.update(login);
    }

    public void update(LoginDto loginDto) {
        if (!StringUtils.isEmpty(loginDto.getPassword())) {
            // get login detail by user (user account)
            Login loginDetail = loginRepository.findByUser(loginDto.getUser());
            Login login = new Login();
            login.setId(loginDetail.getId());
            login.setUsername(loginDetail.getUsername());
            login.setPassword(loginDto.getPassword());
            loginRepository.update(login);
        }
        userAccountRepository.update(loginDto.getUser());
    }

    public int delete(int id) {
        return loginRepository.delete(id);
    }

    public boolean isCurrentPasswordCorrect(LoginDto login) {
        return StringUtils.equalsIgnoreCase(login.getCurrentPassword(),
                loginRepository.findById(login.getId()).getPassword());
    }
}
