package com.book.service;

import com.book.Repository.UserDocumentRepository;
import com.book.data.dto.UserDocumentForm;
import com.book.data.entity.UserAccount;
import com.book.data.entity.UserDocument;
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
public class UserDocumentService {

    @Autowired
    UserDocumentRepository userDocumentRepository;

    public UserDocument findById(int id) {
        return userDocumentRepository.findById(id);
    }

    public List<UserDocument> findAll() {
        return userDocumentRepository.findAll();
    }

    public List<UserDocumentForm> findAllByUserId(int userId) {
        List<UserDocument> userDocuments = userDocumentRepository.findAllByUserId(userId);
        return userDocuments.stream().map(d -> new UserDocumentForm(d)).collect(Collectors.toList());
    }

    public void saveDocument(UserDocumentForm document){
        UserDocument userDocument = new UserDocument();
        userDocument.setId(document.getId());
        userDocument.setName(document.getName());
        userDocument.setType(document.getType());
        userDocument.setContent(document.getContent());
        userDocument.setDescription(document.getDescription());

        UserAccount user = new UserAccount();
        user.setId(document.getUser().getId());
        user.setFirstName(document.getUser().getFirstName());
        user.setLastName(document.getUser().getLastName());
        user.setEmailAddress(document.getUser().getEmailAddress());
        userDocument.setUser(user);

        userDocumentRepository.save(userDocument);
    }

    public void deleteById(int id){
        userDocumentRepository.deleteById(id);
    }
}
