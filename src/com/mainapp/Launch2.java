package com.mainapp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.Employee;

public class Launch2 {

	// JPQL

	public static void main(String[] args) {

		EntityManagerFactory ef = Persistence.createEntityManagerFactory("config");
		EntityManager em = ef.createEntityManager();

//		insert(em);
//		read(em);
//		update(em);
		delete(em);

		em.close();
		ef.close();
	}

	private static void insert(EntityManager em) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee(900 + i, "jpqlname", "jpaladdr", 1000);
			em.persist(employee);
			if (i % 5 == 0) {
				em.flush();
			}

		}

		transaction.commit();
		em.close();
		System.out.println("BULK DATA INSERTED");
	}

	private static void read(EntityManager em) {

		String jpql = "select e from Employee e"; // COMPULSORY ALISING

		Query Query = em.createQuery(jpql, Employee.class);
		List<Employee> list = Query.getResultList();
		for (Employee e : list) {

			System.out.println(e + " ");
		}
	}

	private static void update(EntityManager em) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		String jpql = "update Employee set esalary=:esalary where id>=:eid";

		Query Query = em.createQuery(jpql);
		Query.setParameter("esalary", 60000);
		Query.setParameter("eid", 900);

		Query.executeUpdate();
		System.out.println("BULK DATA UPDATED");

		transaction.commit();
	}

	private static void delete(EntityManager em) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

//		String sql = "delete from Employee where eid>=:eid";

		Query Query = em.createNamedQuery("deleteSQL");
		Query.setParameter("id", 106);

		Query.executeUpdate();

		transaction.commit();
		System.out.println("BULK DATA DELETED");
		em.close();

	}
}
