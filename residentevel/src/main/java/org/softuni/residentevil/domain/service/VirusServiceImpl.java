package org.softuni.residentevil.domain.service;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Virus;
import org.softuni.residentevil.domain.models.service.VirusServiceModel;
import org.softuni.residentevil.domain.repository.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveVirus(VirusServiceModel virusServiceModel) {
//        try{
            this.virusRepository.saveAndFlush(this.modelMapper.map(virusServiceModel, Virus.class));
            return true;
//        }catch (Exception e){
//            throw new InvalidParameterException("Virus could not be created");
//        }
    }

    @Override
    public List<VirusServiceModel> showAllViruses() {
        return this.virusRepository.findAll().stream().map( v -> this.modelMapper.map(v, VirusServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public VirusServiceModel findById(String id) {
        Virus virus = new Virus();
        virus = this.virusRepository.findById(id).orElse(null);
        return this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    public boolean deleteVirus(String id) throws Exception {
        try {
            this.virusRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("The virus could not be deleted");
        }
    }


}
