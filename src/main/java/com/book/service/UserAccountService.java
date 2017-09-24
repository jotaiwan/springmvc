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

    public UserAccountDto findById(int id) {
        return new UserAccountDto(userAccountRepository.findById(id));
    }

    public UserAccountForm findUserById(int id) {
        return new UserAccountForm(userAccountRepository.findById(id));
    }

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

    public int deleteUserById(int id) {
        return userAccountRepository.deleteById(id);
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

    public List<UserInfo> findAllUsers() {
        List<UserAccount> users = userAccountRepository.findAllUsers();
        return userAccountAdapter.convertToUserViews(users);
    }

    public UserInfo findUser(int id) {
        UserAccount user = userAccountRepository.findById(id);
        return userAccountAdapter.convertToUserView(user);
    }
}
