package com.book.Repository;

import com.book.data.entity.UserDocument;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jotaiwan on 20/08/2017.
 */
@Repository
public class UserDocumentRepository extends AbstractRepository {
    @SuppressWarnings("unchecked")
    public List<UserDocument> findAll() {
        Criteria criteria = getSession().createCriteria(UserDocument.class);
        return (List<UserDocument>)criteria.list();
    }

    public void save(UserDocument document) {
        persist(document);
    }

    public UserDocument findById(int id) {
        Criteria criteria = getSession().createCriteria(UserDocument.class);
        criteria.add(Restrictions.eq("id",id));
        return (UserDocument) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserDocument> findAllByUserId(int userId){
        Criteria criteria = getSession().createCriteria(UserDocument.class);
        Criteria userCriteria = criteria.createCriteria("user");
        userCriteria.add(Restrictions.eq("id", userId));
        return (List<UserDocument>)criteria.list();
    }


    public void deleteById(int id) {
        Query query = getSession().createSQLQuery("delete from user_document where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }
}
