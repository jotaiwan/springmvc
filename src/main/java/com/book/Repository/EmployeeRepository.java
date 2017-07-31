package com.book.Repository;

import com.book.data.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository extends AbstractRepository {

	public void saveEmployee(Employee employee) {
		persist(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		Criteria criteria = getSession().createCriteria(Employee.class);
		return (List<Employee>) criteria.list();
	}

	public void deleteEmployeeBySsn(String ssn) {
		Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
		query.setString("ssn", ssn);
		query.executeUpdate();
	}

	
	public Employee findBySsn(String ssn){
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("ssn",ssn));
		return (Employee) criteria.uniqueResult();
	}

	public Employee findById(Integer id){
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id",id));
		return (Employee) criteria.uniqueResult();
	}

	public void updateEmployee(Employee employee){
		getSession().update(employee);
	}
	
}
