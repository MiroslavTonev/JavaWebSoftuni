package chyshka.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import chyshka.domain.entities.Product;

public class ProductRepositoryImpl implements ProductRepository{
	
	private EntityManager entityManager;
	
	public ProductRepositoryImpl() {
		this.entityManager = 
				Persistence.createEntityManagerFactory("chyshka").createEntityManager();
	}
	
	@Override
	public Product save(Product entity) {
		entityManager.getTransaction().begin();;		
		this.entityManager.persist(entity);		
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public Product findById(String id) {
		this.entityManager.getTransaction().begin();
		Product product = (Product) this.entityManager.createQuery("SELECT p FROM products p WHERE p.id = :id")
				.setParameter("id", id);
		
		this.entityManager.getTransaction().commit();
		return product;
	}

	@Override
	public List<Product> findAll() {
		this.entityManager.getTransaction().begin();
		List<Product> products = this.entityManager.createQuery("SELECT p FROM products p").getResultList();		
		
		this.entityManager.getTransaction().commit();
		return products;
	}

	@Override
	public Product findByName(String name) {
		this.entityManager.getTransaction().begin();
		Product product =  (Product) this.entityManager.createQuery("SELECT p FROM products p WHERE p.name = :name")
				.setParameter("name", name)
				.getSingleResult();
		
		this.entityManager.getTransaction().commit();	
		return product;
	}

}
