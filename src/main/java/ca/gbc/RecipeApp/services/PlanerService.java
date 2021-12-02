package ca.gbc.RecipeApp.services;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 1
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   November 7th, 2021
//        * Description:            Defining functions for the meal planner
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Planer;
import ca.gbc.RecipeApp.repositories.PlanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanerService {
    @Autowired
    private PlanerRepository repo;
    public List<Planer> listAll(){
        return (List<Planer>) repo.findAll();
    }


    public void save(Planer planer) {
        repo.save(planer);
    }

    public Planer get(Integer id) throws UserNotFoundException{
        Optional<Planer> result = repo.findById(id);
        if(result.isPresent()){
            return  result.get();
        }
        throw new UserNotFoundException("Could not found any meal with id "+id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count= repo.countById(id);
        if(count==null||count==0){
            throw new UserNotFoundException("Could not found any meal with id "+id);

        }
        repo.deleteById(id);
    }
}

