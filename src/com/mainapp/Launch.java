package com.mainapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.resource.transaction.internal.SynchronizationRegistryStandardImpl;

import com.entity.Employee;

public class Launch {

	public static void main(String[] args) {

		EntityManagerFactory ef = Persistence.createEntityManagerFactory("config");
		EntityManager em = ef.createEntityManager();
		 insert(ef);
		// read(em);
		//update(em);
		  delete(em);

		em.close();
		ef.close();
	}

	private static void delete(EntityManager em) {
		Employee employee = em.find(Employee.class, 33);
		if (employee != null) {
		
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(employee);
			transaction.commit();
			System.out.println("DATA DELETED SUCCESSFULLY");
		} else {
			System.out.println("DATA NOT  FOUND");
		}
		
	}

	private static void update(EntityManager em) {

		Employee employee = em.find(Employee.class, 33);
		if (employee != null) {
			employee.setEsalary(1500);
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.merge(employee);
			transaction.commit();
			System.out.println("DATA UPDATED SUCCESSFULLY");
		} else {
			System.out.println("DATA NOT FOUND");
		}

	}

	private static void read(EntityManager em) {

		Employee employee = em.find(Employee.class, 33);
		System.out.println(employee);
	}

	private static EntityManager insert(EntityManagerFactory ef) {
		EntityManager em = ef.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Employee employee = new Employee(4, "AbhishekSharma", "srh", 1000);
		em.persist(employee);
		transaction.commit();
		System.out.println("DATA INSERTED SUCCESSFULLY");
		return em;
	}

}
