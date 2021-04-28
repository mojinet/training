package fr.modji.location;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // array of vehicule
        Vehicule[] allVehicule = new Vehicule[6];

        // set vehicule
        Velo v1 = new Velo("Lapierre","speed 400",LocalDate.now() ,27);
        allVehicule[0] = v1;
        Velo v2 = new Velo("Btwin", "riverside 900", LocalDate.now(), 10);
        allVehicule[1] = v2;
        Gyropode gp1 = new Gyropode("Segway", "Ninebot Elite",LocalDate.now(),40, 150);
        allVehicule[2] = gp1;
        Gyropode gp2 = new Gyropode("Weebot", "Echo",LocalDate.now(),35, 160);
        allVehicule[3] = gp2;
        Gyroroue gr1 = new Gyroroue("Immotion", "v8",LocalDate.now(),40);
        allVehicule[4] = gr1;
        Gyroroue gr2 = new Gyroroue("Segway", "Ninebot One E+",LocalDate.now(),30);
        allVehicule[5] = gr2;

        for (Vehicule v: allVehicule) {
            System.out.println(v.toString());
        }
    }
}
