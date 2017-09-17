package com.book.Repository;

import com.book.data.entity.UserAccount;
import com.book.data.entity.UserAccountJson;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by jotaiwan on 20/08/2017.
 */
@Repository
public class UserAccountRepository extends AbstractRepository {

    public UserAccount findById(int id) {
        Criteria criteria = getSession().createCriteria(UserAccount.class);
        criteria.add(Restrictions.eq("id",id));
        return (UserAccount) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserAccount> findAllUsers() {
        Criteria criteria = getSession().createCriteria(UserAccount.class).addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<UserAccount> users = (List<UserAccount>) criteria.list();
        return users;
    }

    public int save(UserAccount user) {
        getSession().persist(user);
        return user.getId();
    }

    public void deleteById(int id) {
        Query query = getSession().createSQLQuery("delete from user_account where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    public void update(UserAccount user) {
        String qry = "update user_account set first_name = :firstname, last_name = :lastname, " +
                "email_address = :emailaddress where id = :id";
        Query query = getSession().createSQLQuery(qry);
        query.setInteger("id", user.getId());
        query.setString("firstname", user.getFirstName());
        query.setString("lastname", user.getLastName());
        query.setString("emailaddress", user.getEmailAddress());
        query.executeUpdate();
    }

    public int saveUserAccountFormViewToJson(UserAccountJson userAccountJson) {
        getSession().persist(userAccountJson);
        return userAccountJson.getId();
    }

    public UserAccountJson findUserAccountJsonByJson(String json) {
        Criteria criteria = getSession().createCriteria(UserAccountJson.class);
        criteria.add(Restrictions.eq("json", json));
        return (UserAccountJson) criteria.uniqueResult();
    }
}
