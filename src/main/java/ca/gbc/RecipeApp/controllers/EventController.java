package ca.gbc.RecipeApp.controllers;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Implementing the functionalities and endpoints for events
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Event;
import ca.gbc.RecipeApp.services.EventService;
import ca.gbc.RecipeApp.services.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping("/events")
    public String showEventList(Model model){
        List<Event> eventList = service.listAll();
        model.addAttribute("eventList",eventList);

        return "event/events";
    }

    @GetMapping("/events/new")
    public String showNewForm(Model model){
        model.addAttribute("pageTitle","Add New Event");

        List<Event> events = new ArrayList<>();
        model.addAttribute("events", events);
        List<Event> listOfEvent = service.listAll();
        model.addAttribute("listOfEvent", listOfEvent);
        model.addAttribute("event",new Event());

        return "event/event_form";
    }
    @PostMapping("/events/save")
    public String saveMeal(Event event , RedirectAttributes redirectAttributes){
        service.save(event);
        redirectAttributes.addFlashAttribute("message", "The event had been added");
        //redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/events";
    }
    @GetMapping("/events/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Event event=service.get(id);
            model.addAttribute("pageTitle","Edit Event(ID:"+id+")");
            model.addAttribute("event",event);
            return "event/event_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            //redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/events";
        }
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","Event has been deleted");

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            //redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        }
        return "redirect:/events";
    }
}


