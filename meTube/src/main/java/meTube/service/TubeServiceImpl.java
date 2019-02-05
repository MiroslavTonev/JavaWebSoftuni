package meTube.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import meTube.domain.entities.Tube;
import meTube.models.TubeServiceModel;
import meTube.repository.TubeRepository;
import util.ModelMapper;

public class TubeServiceImpl implements TubeService{
	
	private final TubeRepository tubeRepository;
	private final ModelMapper modelMapper;
	
	@Inject
	public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {	
		this.tubeRepository = tubeRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void saveTube(TubeServiceModel tubeServiceModel) {
		this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));		
	}

	@Override
	public TubeServiceModel findTubeByName(String name) {
		Tube tube = this.tubeRepository.findByName(name).orElse(null);
		
		return this.modelMapper.map(tube, TubeServiceModel.class);
		
		
	}

	@Override
	public List<TubeServiceModel> findAllTubes() {
		
		return this.tubeRepository.findAll()
				.stream().map(t -> this.modelMapper.map(t, TubeServiceModel.class)).collect(Collectors.toList());
	}

}
