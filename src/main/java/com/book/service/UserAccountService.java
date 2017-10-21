package com.book.service;

import com.book.Repository.UserAccountRepository;
import com.book.adapter.UserAccountAdapter;
import com.book.data.dto.UserAccountDto;
import com.book.data.entity.UserAccount;
import com.book.data.entity.UserAccountJson;
import com.book.data.form.UserAccountForm;
import com.book.data.view.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jotaiwan on 20/08/2017.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAccountService {

    @Autowired
    UserAccountAdapter userAccountAdapter;

    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccountForm findFormById(int userId) {
        return new UserAccountForm(userAccountRepository.findById(userId));
    }

    public List<UserInfo> findAllUsers() {
        List<UserAccount> users = userAccountRepository.findAll();
        return userAccountAdapter.convertToUserViews(users);
    }

    public UserInfo findInfoById(int userId) {
        UserAccount user = userAccountRepository.findById(userId);
        return userAccountAdapter.convertToUserView(user);
    }

    @Deprecated
    public int saveUser(UserAccountDto user) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(user.getId());
        userAccount.setFirstName(user.getFirstName());
        userAccount.setLastName(user.getLastName());
        userAccount.setEmailAddress(user.getEmailAddress());
        return userAccountRepository.save(userAccount);
    }

    public int saveUser(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public int deleteAccountById(int userId) {
        return userAccountRepository.deleteById(userId);
    }

    public int saveUserAccountFormAsJson(UserAccountForm accountForm)
            throws Exception {
        UserAccountJson userAccountJson =
                userAccountAdapter.convertUserAccountFormViewToJson(accountForm);
        return userAccountRepository.saveUserAccountFormViewToJson(userAccountJson);
    }

    public boolean findUserAccountJsonByJson(UserAccountForm accountForm) {
        UserAccountJson userAccountJson =
                userAccountAdapter.convertUserAccountFormViewToJson(accountForm);
        UserAccountJson result = userAccountRepository.findUserAccountJsonByJson(userAccountJson.getJson());
        return result != null;
    }

    public UserAccount finUserAccountJsonById(int accountJsonId) {
        UserAccountJson result = userAccountRepository.findUserAccountJsonById(accountJsonId);
        // convert result to json
        return userAccountAdapter.convertUserAccountJsonToUserAccount(result);
    }
}
