package rbekyarov.project.web.controllers;

import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.entity.*;


import java.util.List;

public abstract class BaseController {

    public ModelAndView view(String view) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);


        return modelAndView;
    }

    //BehaviorController, BreedController,CellController,CityController,priceService
    public ModelAndView view(String view, String objectName, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);

        return modelAndView;
    }

    //reservationAdd
    public ModelAndView view(String view, String objectName, Object object, String clients, List<Client> clientsList, String allEmptyCells, List<Cell> allEmptyCellsList, String allPrices, Price allPricesList , String allDogsOnClient, List<Dog>allDogsOnClientList) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(clients, clientsList);
        modelAndView.addObject(allEmptyCells, allEmptyCellsList);
        modelAndView.addObject(allPrices, allPricesList);


        return modelAndView;
    }

    //dogAdd
    public ModelAndView view(String view, String objectName, Object object, String allBehaviors, List<Behavior> allBehaviorsList, String allBreeds, List<Breed> allBreedsList, String allClients, List<Client> allClientsList) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(allBehaviors, allBehaviorsList);
        modelAndView.addObject(allBreeds, allBreedsList);
        modelAndView.addObject(allClients, allClientsList);

        return modelAndView;
    }

    //clientAdd
    public ModelAndView view(String view, String objectName, Object object, String allCity, List allCityList) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(allCity, allCityList);


        return modelAndView;
    }


    public ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }
}
