package ca.gbc.RecipeApp.services;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Defining functions for the events
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Event;
import ca.gbc.RecipeApp.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository repo;

    public List<Event> listAll(){
        return (List<Event>) repo.findAll();
    }


    public void save(Event event) {
        repo.save(event);
    }

    public Event get(Integer id) throws UserNotFoundException{
        Optional<Event> result = repo.findById(id);
        if(result.isPresent()){
            return  result.get();
        }
        throw new UserNotFoundException("Could not found any Event with id "+id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count= repo.countById(id);
        if(count==null||count==0){
            throw new UserNotFoundException("Could not found any Event with id "+id);

        }
        repo.deleteById(id);



    }
}
