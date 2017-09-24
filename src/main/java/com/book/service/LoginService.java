package com.book.service;

import com.book.Repository.LoginRepository;
import com.book.Repository.UserAccountRepository;
import com.book.adapter.LoginDetailAdapter;
import com.book.data.dto.LoginDto;
import com.book.data.entity.Login;
import com.book.data.entity.UserAccount;
import com.book.data.form.LoginDetailForm;
import com.book.data.form.UserAccountForm;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    LoginDetailAdapter loginDetailAdapter;

    @Deprecated
    public List<LoginDto> findAll() {
        return loginRepository.findAll().stream()
                .map(l -> new LoginDto(l.getId(), l.getUsername(), l.getUser())).collect(Collectors.toList());
    }

    @Deprecated
    public LoginDto findById(int id) {
        Login login = loginRepository.findById(id);
        return new LoginDto(login.getId(), login.getUsername(), login.getUser());
    }

    public LoginDetailForm findFormByUserId(int userId) {
        UserAccount user = userAccountRepository.findById(userId);
        Login login = loginRepository.findById(user.getLogin().getId());
        if (login != null) {
            return new LoginDetailForm(login.getId(), login.getUsername(), null);
        }
        return null;
    }

    public void save(LoginDto loginDto) {
        Login login = new Login();
        login.setUsername(loginDto.getUsername());
        login.setPassword(loginDto.getPassword());
        loginRepository.update(login);
    }

    public int save(LoginDetailForm loginForm, UserAccount userAccount) {
        // convert loginform to login
        Login login = loginDetailAdapter.converFormToLogin(loginForm, userAccount);
        return loginRepository.save(login);
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

    public void update(LoginDetailForm loginForm) {
        if (!StringUtils.isEmpty(loginForm.getPassword())) {
            Login login = new Login();
            login.setId(loginForm.getId());
            login.setUsername(loginForm.getUsername());
            login.setPassword(loginForm.getPassword());
            loginRepository.update(login);
        }
    }

    public void update(UserAccountForm userForm) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userForm.getId());
        userAccount.setFirstName(userForm.getFirstName());
        userAccount.setLastName(userForm.getLastName());
        userAccount.setEmailAddress(userForm.getEmailAddress());

        userAccountRepository.update(userAccount);
    }


    public int delete(int id) {
        return loginRepository.delete(id);
    }

    public boolean isCurrentPasswordCorrect(LoginDetailForm login) {
        return StringUtils.equalsIgnoreCase(login.getCurrentPassword(),
                loginRepository.findById(login.getId()).getPassword());
    }
}
