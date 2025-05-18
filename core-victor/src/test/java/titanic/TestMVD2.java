package titanic;

import titanic.datamodel.Passenger;

public class TestMVD2 {
    public static void main(String[] args) {
        Passenger passenger = new Passenger("Allen, Miss Elisabeth Walton", "1st", 29, "female", true);
        System.out.println(passenger);
    }
}