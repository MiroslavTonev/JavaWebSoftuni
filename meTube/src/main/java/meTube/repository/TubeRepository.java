package meTube.repository;

import java.util.Optional;

import meTube.domain.entities.Tube;

public interface TubeRepository extends GenericRepository<Tube, String>{
	
	Optional<Tube> findByName(String name);

}
