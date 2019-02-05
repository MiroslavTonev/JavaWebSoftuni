package meTube.service;

import java.util.List;

import meTube.models.TubeServiceModel;

public interface TubeService {
	
	void saveTube(TubeServiceModel tubeServiceModel);
	
	TubeServiceModel findTubeByName(String name);
	
	List<TubeServiceModel> findAllTubes();
}
