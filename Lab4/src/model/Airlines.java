package model;


import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Airlines {

    private LocalDate date;
    private Date departureTime;
    private String airline;
    private String flightNum;
    private String destination;
    private int gate;
    private ArrayList<String> airlineNames = new ArrayList<>();
    private ArrayList<String> destinations = new ArrayList<>();

    private final static String AIRLINES_PATH = "src/data/airlines.txt";
    private final static String DESTINATIONS_PATH = "src/data/destinations.txt";

    public Airlines() {//Genera una aerolinea con datos aleatorios
        try {
            loadConstants();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random rng = new Random();
        int actualMonth = LocalDate.now().getMonth().getValue();
        this.date = LocalDate.of(2019, rng.nextInt(11 - actualMonth) + actualMonth + 1, rng.nextInt(27) + 1);
        Date randomDeparture = new Date();
        randomDeparture.setTime(randomDeparture.getTime() + 86400000L + (long) rng.nextInt(86400000));
        this.departureTime = randomDeparture;
        int airlineNamePosition = rng.nextInt(airlineNames.size() / 2 - 1);
        this.airline = airlineNames.get(airlineNamePosition);
        this.flightNum = airlineNames.get(airlineNamePosition + (airlineNames.size() / 2)) + " " + rng.nextInt(999);
        this.destination = destinations.get(rng.nextInt(destinations.size() - 1));
        this.gate = rng.nextInt(15) + 1;
        System.out.println();
    }

    private void loadConstants() throws IOException {
        FileReader fr = null;
        File objFile = new File(AIRLINES_PATH);
        try {
            fr = new FileReader(objFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
        BufferedReader br = null;
        if (fr != null) {
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                airlineNames.add(line);
                line = br.readLine();
            }
        }
        fr = null;
        objFile = new File(DESTINATIONS_PATH);
        try {
            fr = new FileReader(objFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
        if (fr != null) {
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                destinations.add(line);
                line = br.readLine();
            }
        }
        if (fr != null) {
            fr.close();
            br.close();
        }
    }

//    public String transformDate(LocalDate date) {
//        String[] dateString = date.toString().split("-");
//        return dateString[2] + "/" + dateString[1] + "/" + dateString[0];
//    }

    public String transformDepartureTime(Date departureTime) {
        String[] timeArray = departureTime.toString().split(" ");
        int hour = Integer.valueOf(timeArray[3].substring(0, 2));
        String minutes = timeArray[3].substring(2, 5);
        String am = "am";
        if (hour > 12) {
            am = "pm";
            hour -= 12;
        }
        return hour + minutes + am;
    }

    public int compareByDate(Airlines a) {
        int returnValue = 2;

        String[] thisDate = String.valueOf(this.date).split("-");
        String[] paramDate = String.valueOf(a.getDate()).split("-");

        if (Integer.valueOf(thisDate[0]) > Integer.valueOf(paramDate[0])) {
            returnValue = 1;
        } else if (Integer.valueOf(thisDate[0]) < Integer.valueOf(paramDate[0])) {
            returnValue = -1;
        } else if (Integer.valueOf(thisDate[1]) > Integer.valueOf(paramDate[1])) {
            returnValue = 1;
        } else if (Integer.valueOf(thisDate[1]) < Integer.valueOf(paramDate[1])) {
            returnValue = -1;
        } else if (Integer.valueOf(thisDate[2]) > Integer.valueOf(paramDate[2])) {
            returnValue = 1;
        } else if (Integer.valueOf(thisDate[2]) < Integer.valueOf(paramDate[2])) {
            returnValue = -1;
        } else {
            returnValue = 1;
        }
        return returnValue;
    }

    public int compareByTime(Airlines a) {
        return this.departureTime.compareTo(a.departureTime);
    }

    public int compareByAirline(Airlines a) {
        return airline.compareTo(a.airline);
    }

    public int compareByFlightNum(Airlines a) {
        int comparison = flightNum.compareTo(a.flightNum);
        if (flightNum.substring(0, 2).compareTo(a.flightNum.substring(0, 2)) == 0) {
            comparison = flightNum.substring(3).compareTo(a.flightNum.substring(3));
        }
        return comparison;
    }

    public int compareByDestination(Airlines a) {
        return destination.compareTo(a.destination);
    }

    public int compareByGate(Airlines a) {
        return Integer.compare(gate, a.getGate());
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

}