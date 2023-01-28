package rbekyarov.project.web.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.entity.*;


import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
        modelAndView.addObject(allDogsOnClient, allDogsOnClientList);


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
    public ModelAndView view(String view, String objectName, Object object, String allCity, List allCityList,String objectName1, String object1) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(allCity, allCityList);
        modelAndView.addObject(objectName1, object1);


        return modelAndView;
    }
    public ModelAndView view(String view, String objectName, Object object, String string1, List list1, String string2, List list2 ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(string1, list1);
        modelAndView.addObject(string2, list2);


        return modelAndView;
    }
     //ClientView
    public ModelAndView view(String view, String objectName, Object object, String string1, List list1, String stingBigDecimal, BigDecimal bigdecimal,String string2, int int1,String string3, int int2 ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(string1, list1);
        modelAndView.addObject(stingBigDecimal, bigdecimal);
        modelAndView.addObject(string2, int1);
        modelAndView.addObject(string3, int2);

        modelAndView.addObject(stingBigDecimal, bigdecimal);


        return modelAndView;
    }
    public ModelAndView view(String view, String objectName, Object object, String string1, List list1, String string2, BigDecimal bigDecimal ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(string1, list1);
        modelAndView.addObject(string2, bigDecimal);


        return modelAndView;
    }
    public ModelAndView view(String view, String objectName, Object object, String string1, List list1, String string2, List list2, String string3, List list3, String string4, List list4,String string5, List list5, String string6,BigDecimal bigdecimal1, String string7, BigDecimal bigdecimal2,String string8, List list6) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(string1, list1);
        modelAndView.addObject(string2, list2);
        modelAndView.addObject(string3, list3);
        modelAndView.addObject(string4, list4);
        modelAndView.addObject(string5, list5);
        modelAndView.addObject(string6, bigdecimal1);
        modelAndView.addObject(string7, bigdecimal2);
        modelAndView.addObject(string8, list6);



        return modelAndView;


    }
    //Reservation Table
    public ModelAndView view(String view, String string1, Page list1, String string2, List list2, String string3, List list3, String string4, int int1 , String string5, int int2, String string6, int int3,String string7, String str1) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(string1, list1);
        modelAndView.addObject(string2, list2);
        modelAndView.addObject(string3, list3);
        modelAndView.addObject(string4, int1);
        modelAndView.addObject(string5, int2);
        modelAndView.addObject(string6, int3);
        modelAndView.addObject(string7, str1);




        return modelAndView;


    }



    public ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }
}
