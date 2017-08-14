package com.book.Repository;

import com.book.data.entity.Employee;
import com.book.data.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository extends AbstractRepository {

	public void saveProduct(Product product) {
		persist(product);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAllProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		return (List<Product>) criteria.list();
	}

	public void deleteProductByCode(String code) {
		Query query = getSession().createSQLQuery("delete from product where code = :code");
		query.setString("code", code);
		query.executeUpdate();
	}

	
	public Product findByCode(String code){
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("code", code));
		return (Product) criteria.uniqueResult();
	}

	public Product findByCode(Integer code){
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("code", code));
		return (Product) criteria.uniqueResult();
	}

	public void updateProduct(Product product){
		getSession().update(product);
	}
	
}
