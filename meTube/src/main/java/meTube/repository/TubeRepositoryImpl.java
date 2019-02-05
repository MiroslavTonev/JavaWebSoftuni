package meTube.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import meTube.domain.entities.Tube;

public class TubeRepositoryImpl implements TubeRepository{
	
	private final EntityManager entityManage; 
	
	public TubeRepositoryImpl() {
		entityManage = Persistence.createEntityManagerFactory("meTube").createEntityManager();
	}		
			
	@Override
	public Tube save(Tube entity) {
		entityManage.getTransaction().begin();		
		entityManage.persist(entity);
		entityManage.getTransaction().commit();
		return entity;
	}

	@Override
	public List<Tube> findAll() {		
		return this.entityManage.createQuery("SELECT t FROM tubes t", Tube.class).getResultList();
	}

	@Override
	public Optional<Tube> findById(String id) {
		Optional<Tube> tube = Optional.of(entityManage.createQuery("SELECT t FROM tubes t WHERE t.id = :id", Tube.class)
				.setParameter("id", id)
				.getSingleResult());
		return tube;
	}

	@Override
	public Optional<Tube> findByName(String name) {
		Optional<Tube> tube = Optional.of(entityManage.createQuery("SELECT t FROM tubes t WHERE t.name = :name", Tube.class)
				.setParameter("name", name)
				.getSingleResult());
		return tube;
	}

}
