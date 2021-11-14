import org.sql2o.Sql2o;

public class DB {
    //local
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "davide", "jw8s0F4");

    //remote
   public static String connectionString = "jdbc:postgresql://ec2-18-204-74-74.compute-1.amazonaws.com:5432/d8ie6bbjts7o40";

   public static Sql2o sql2o = new Sql2o(connectionString, "saditgxzvvjuzy", "fd1fda32eeb772315a0475986626297480509f647db23bf20805a34f8da742f4");

}

