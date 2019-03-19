package org.softuni.residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.models.binding.VirusAddBindingModel;
import org.softuni.residentevil.domain.models.service.VirusServiceModel;
import org.softuni.residentevil.domain.models.view.AllVirusesViewModel;
import org.softuni.residentevil.domain.models.view.CapitalListViewModel;
import org.softuni.residentevil.domain.service.CapitalService;
import org.softuni.residentevil.domain.service.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel){
        modelAndView.addObject("bindingModel", bindingModel);
        modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList()));
        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel, BindingResult bindingResult, ModelAndView modelAndView){
         if(bindingResult.hasErrors()){
            modelAndView.addObject("bindingModel", bindingModel);
            modelAndView.addObject("capitals", this.capitalService.findAllCapitals());
            return super.view("add-virus", modelAndView);
        }

         this.virusService.saveVirus(this.modelMapper.map(bindingModel, VirusServiceModel.class));
        return super.redirect("/");
    }

    @GetMapping("/show")
    public ModelAndView showViruses(ModelAndView modelAndView){
        List<AllVirusesViewModel> viruses = this.virusService.showAllViruses().stream().map(v -> this.modelMapper.map(v, AllVirusesViewModel.class)).collect(Collectors.toList());
        modelAndView.addObject("viruses", viruses);
        return super.view("show-viruses", modelAndView);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel){

        VirusAddBindingModel virusAddBindingModel = this.modelMapper.map(this.virusService.findById(id), VirusAddBindingModel.class);
        virusAddBindingModel.setId(id);

        modelAndView.addObject("bindingModel", virusAddBindingModel);
        modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList()));
        return super.view("edit-virus", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editConfirm(@PathVariable("id") String id, @Valid @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel, BindingResult bindingResult, ModelAndView modelAndView){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("bindingModel", bindingModel);
            modelAndView.addObject("capitals", this.capitalService.findAllCapitals());
            return super.view("edit-virus", modelAndView);
        }

        bindingModel.setId(id);
        this.virusService.saveVirus(this.modelMapper.map(bindingModel, VirusServiceModel.class));
        return super.redirect("/");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id, ModelAndView modelAndView) throws Exception {
        this.virusService.deleteVirus(id);
        return super.redirect("/");
    }

}
