package org.softuni.residentevil.domain.service;

import org.softuni.residentevil.domain.models.service.VirusServiceModel;

import java.util.List;

public interface VirusService {

    boolean saveVirus(VirusServiceModel virusServiceModel);

    List<VirusServiceModel> showAllViruses();

    VirusServiceModel findById(String id);

    boolean deleteVirus(String id) throws Exception;
}
