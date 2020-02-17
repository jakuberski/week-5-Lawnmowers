package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItems;

public class ListItemHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Lawnmowers");

	public void insertItem(ListItems li) {

		EntityManager em = emfactory.createEntityManager();

		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();

	}

	
	public List<ListItems> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<ListItems> allItems = em.createQuery("SELECT i FROM ListItems i").getResultList();
		return allItems;
	}

	public void deleteItem(ListItems toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItems> typedQuery = em.createQuery("select	li	from ListItems	li	where	li.brand	=	:selectedBrand	and	li.color	=	:selectedColor",
				ListItems.class);
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedBrand", toDelete.getBrand());
		typedQuery.setParameter("selectedColor", toDelete.getColor());
		// we only want one result
		typedQuery.setMaxResults(1);
		// get the result and save it into a new list item
		ListItems result = typedQuery.getSingleResult();
		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListItems> searchForItemByBrand(String brand) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItems> typedQuery = em.createQuery(
				"select	li	from ListItems	li	where	li.brand	=	:selectedBrand", ListItems.class);
		typedQuery.setParameter("selectedBrand", brand);
		List<ListItems> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ListItems> searchForItemByItem(String color) {
//		TODO	Auto-generated	method	stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItems> typedQuery = em
				.createQuery("select	li	from ListItems	li	where	li.color	=	:selectedColor", ListItems.class);
		typedQuery.setParameter("selectedColor", color);
		List<ListItems> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public ListItems searchForItemById(int idToEdit) {
//		TODO	Auto-generated	method	stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItems found = em.find(ListItems.class, idToEdit);
		em.close();
		return found;
	}

	public void updateItem(ListItems toEdit) {
//		TODO	Auto-generated	method	stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public void cleanUp() {
		emfactory.close();
	}

}
