package com.book.Repository;

import com.book.data.entity.Login;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jotaiwan on 22/07/2017.
 */
@Repository
public class LoginRepository extends AbstractRepository {

    public void saveLogin(Login login) {
        persist(login);
    }

    @SuppressWarnings("unchecked")
    public List<Login> findAll() {
        Criteria criteria = getSession().createCriteria(Login.class);
        return (List<Login>) criteria.list();
    }

    public void deleteLogin(int id) {
        Query query = getSession().createSQLQuery("delete from login where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    public Login findById(int id){
        Criteria criteria = getSession().createCriteria(Login.class);
        criteria.add(Restrictions.eq("id", id));
        return (Login) criteria.uniqueResult();
    }
}
