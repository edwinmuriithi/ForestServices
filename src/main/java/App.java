import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //home
        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //navigate to ranger creation form
        get("/create/ranger",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model,"ranger-form.hbs");
        },new HandlebarsTemplateEngine());

        //create a ranger.
        post("/create/ranger/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String badge_number = request.queryParams("badgeNumber");
            Ranger ranger = new Ranger(name, badge_number);
            ranger.save();
            return new ModelAndView(model,"ranger-form.hbs");
        },new HandlebarsTemplateEngine());

        // navigate to location creation form
        get("/create/location",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"location-form.hbs");
        },new HandlebarsTemplateEngine());

        //create a location.
        post("/create/location/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            Location location = new Location(name);
            try {
                location.save();
            }catch (IllegalArgumentException e){
                System.out.println(e);
            }
            return new ModelAndView(model,"location-form.hbs");
        },new HandlebarsTemplateEngine());

        // navigate to sighting creation form
        get("/create/sighting",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());

        //create a sighting.
        post("/create/sighting/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            int location_id= Integer.parseInt(request.queryParams("location"));
            int ranger_id= Integer.parseInt(request.queryParams("ranger"));
            int animal_id= Integer.parseInt(request.queryParams("animal"));
            Sighting sighting=new Sighting(location_id,ranger_id,animal_id);
            sighting.save();
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());

        // navigate to animal creation form
        get("/create/animal",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());

        //create an animal.
        post("/create/animal/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String name = request.queryParams("name");
            System.out.println(name);
            String type = request.queryParams("type");
            System.out.println(type);
            String health = request.queryParams("health");
            System.out.println(health);
            String age = request.queryParams("age");
            System.out.println(age);

            if(type.equals(EndangeredAnimal.ENDANGERED)){
                EndangeredAnimal endangered = new EndangeredAnimal(name, EndangeredAnimal.ENDANGERED, health, age);
                endangered.save();
            }
            else {
                Animal animal = new Animal(name, Animal.NORMAL);
                animal.save();
            }
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());

        //view rangers
        get("/view/rangers",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("rangers", Ranger.all());
            return new ModelAndView(model,"ranger-view.hbs");
        },new HandlebarsTemplateEngine());

        //view ranger's sightings
        get("/view/ranger/sightings/:id",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            int idOfRanger = Integer.parseInt(request.params(":id"));
            Ranger foundRanger = Ranger.find(idOfRanger);
            List<Sighting> sightings = foundRanger.getRangerSightings();
            ArrayList<String> animals = new ArrayList<String>();
            ArrayList<String> types = new ArrayList<String>();

            for (Sighting sighting : sightings){
                String animal_name = Animal.find(sighting.getAnimalId()).getName();
                String animal_type = Animal.find(sighting.getAnimalId()).getType();
                animals.add(animal_name);
                types.add(animal_type);
            }
            model.put("sightings", sightings);
            model.put("animals", animals);
            model.put("types", types);
            model.put("rangers", Ranger.all());
            return new ModelAndView(model,"ranger-view.hbs");
        },new HandlebarsTemplateEngine());

        //view locations
        get("/view/locations",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("locations", Location.all());
            return new ModelAndView(model,"location-view.hbs");
        },new HandlebarsTemplateEngine());

        //view locations' sightings
        get("/view/location/sightings/:id",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            int idOfLocation = Integer.parseInt(request.params(":id"));
            Location foundLocation = Location.find(idOfLocation);
            List<Sighting> sightings = foundLocation.getLocationSightings();
            ArrayList<String> animals = new ArrayList<String>();
            ArrayList<String> types = new ArrayList<String>();
            for (Sighting sighting : sightings){
                String animal_name = Animal.find(sighting.getAnimalId()).getName();
                String animal_type = Animal.find(sighting.getAnimalId()).getType();
                animals.add(animal_name);
                types.add(animal_type);
            }
            model.put("sightings", sightings);
            model.put("animals", animals);
            model.put("types", types);
            model.put("locations", Location.all());
            return new ModelAndView(model,"location-view.hbs");
        },new HandlebarsTemplateEngine());

        //DEBUG LATER
        //view sightings
        get("/view/sightings",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            List<Sighting> sightings = Sighting.all();
//            ArrayList<String> animals = new ArrayList<String>();
//            ArrayList<String> types = new ArrayList<String>();
//            for (Sighting sighting : sightings){
//                String animal_name = Animal.find(sighting.getAnimalId()).getName();
//                String animal_type = Animal.find(sighting.getAnimalId()).getType();
//                animals.add(animal_name);
//                types.add(animal_type);
//            }
//            model.put("sightings", sightings);
//            model.put("animals", animals);
//            model.put("types", types);
            return new ModelAndView(model,"sighting-view.hbs");
        },new HandlebarsTemplateEngine());

    }
}
