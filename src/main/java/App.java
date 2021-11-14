import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
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


    }
}
