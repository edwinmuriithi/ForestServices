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









    }
}
