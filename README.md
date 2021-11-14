# FOREST SERVICES


The Forest Service is considering a proposal from a timber company to clear-cut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application allows Rangers to track wildlife sightings in the area.



## Project Setup Instructions
### Setup Requirements for Production Database
* CREATE DATABASE wildlife_tracker;
* CREATE TABLE animals (id serial PRIMARY KEY, name varchar,type VARCHAR,health VARCHAR,age VARCHAR);
* CREATE TABLE locations (id serial PRIMARY KEY,name VARCHAR);
* CREATE TABLE locations_sightings (id serial PRIMARY KEY,location_id INT,sighting_id INT);
* CREATE TABLE rangers (id serial PRIMARY KEY,name VARCHAR,badge_number VARCHAR);
* CREATE TABLE rangers_sightings (id serial PRIMARY KEY,ranger_id INT,sighting_id INT);
* CREATE TABLE sightings (id serial PRIMARY KEY,animal_id INT,ranger_id INT,location_id INT,time TIMESTAMP);

### Create a test database
* CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
- Fork this repo
- Clone this repo
- Open  the project in your desired editor (I use IntelliJ)
- Run the tests and make sure they pass
- Run the app

## Development.

### Built with

- [Java] - 
- [Spark] - To create user interface.
- [Gradle] - To manage dependencies.
- [HTML / CSS] - Created and styled site.

## Want to contribute? Great!

### To fix a bug or enhance an existing module, follow these steps:

- Fork the repo
- Create a new branch (`git checkout -b improve-feature`)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (`git commit -am 'Improve feature'`)
- Push to the branch (`git push origin improve-feature`)
- Create a Pull Request

### Bug / Feature Request

If you find a bug or undesired results, or if you'd like to request a new function, feel free to reach out at dennis.muthiora@student.moringaschool.com.


## Team

[Dennis Muthiora ](https://github.com/wdmuthiora)

## [License](https://github.com/iharsh234/WebApp/blob/master/LICENSE.md)

MIT © [Dennis Muthiora ](https://github.com/wdmuthiora)