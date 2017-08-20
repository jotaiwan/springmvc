package com.book.Repository;

import com.book.data.entity.UserAccount;
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

}
