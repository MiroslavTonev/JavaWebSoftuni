package org.softuni.residentevil.domain.service;

import org.softuni.residentevil.domain.models.service.CapitalServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CapitalService {

    List<CapitalServiceModel> findAllCapitals();

}
