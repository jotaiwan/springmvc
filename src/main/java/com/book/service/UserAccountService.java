package com.book.service;

import com.book.Repository.UserAccountRepository;
import com.book.data.dto.UserAccountDto;
import com.book.data.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jotaiwan on 20/08/2017.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccountDto findById(int id) {
        return new UserAccountDto(userAccountRepository.findById(id));
    }

    public int saveUser(UserAccountDto user) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(user.getId());
        userAccount.setFirstName(user.getFirstName());
        userAccount.setLastName(user.getLastName());
        userAccount.setEmailAddress(user.getEmailAddress());
        return userAccountRepository.save(userAccount);
    }

    public void deleteUserById(int id) {
        userAccountRepository.deleteById(id);
    }

    public List<UserAccountDto> findAllUsers() {
        List<UserAccount> userAccounts = userAccountRepository.findAllUsers();
        return userAccounts.stream().map(u -> new UserAccountDto(u))
                .collect(Collectors.toList());
    }

}
