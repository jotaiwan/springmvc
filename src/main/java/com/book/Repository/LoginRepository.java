package com.book.Repository;

import com.book.data.dto.LoginDto;
import com.book.data.entity.Login;
import com.book.data.entity.UserAccount;
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

    public int delete(int id) {
        Query query = getSession().createSQLQuery("delete from login where id = :id");
        query.setInteger("id", id);
        return query.executeUpdate();
    }

    public Login findById(int id) {
        Criteria criteria = getSession().createCriteria(Login.class);
        criteria.add(Restrictions.eq("id", id));
        return (Login) criteria.uniqueResult();
    }

    public Login findByUser(UserAccount user) {
        Criteria criteria = getSession().createCriteria(Login.class);
        criteria.add(Restrictions.eq("user", user));
        return (Login) criteria.uniqueResult();
    }

    public int update(Login login) {
        Query query = getSession().createSQLQuery(
                "update user_login set username = :username, password = :password where id = :id");

        query.setString("username", login.getUsername());
        query.setString("password", login.getPassword());
        query.setInteger("id", login.getId());

        int result = query.executeUpdate();
        return result;
    }

}
