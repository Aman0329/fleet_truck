import java.util.*;

public class Truck_charging {
    public static void main(String[] args) {
        Truck[] trucks = {
                new Truck(1, 100, 40),
                new Truck(2, 120, 30),
                new Truck(3, 110, 50),
                new Truck(4, 125, 30),
                new Truck(5, 150, 10),
                new Truck(6, 80, 20),
                new Truck(7, 120, 20)
        };
        Charger[] chargers = {
                new Charger(1, 20),
                new Charger(2, 30),
                new Charger(3, 42)
        };
        int chargingTime = 5;
        Map<Integer,Integer> map=new HashMap<>();

        int[] capacityToCharge = new int[trucks.length];
        for (int i = 0; i < trucks.length; i++) {
            capacityToCharge[i] = (int) (trucks[i].getCapacity() * (1.0 - trucks[i].getChargeLevel() / 100.0));
            map.put(trucks[i].getId(), capacityToCharge[i]);
        }
// Sort capacity to charge in descending order
        List<Integer> sortedCapacity = new ArrayList<>();
        for (int capacity : capacityToCharge) {
            sortedCapacity.add(capacity);
        }
        sortedCapacity.sort(Collections.reverseOrder());

        List<Integer> chargingCapacity = new ArrayList<>();
        for (int i = 0; i < chargers.length; i++) {
            chargingCapacity.add(chargers[i].getRate() * chargingTime);
        }
        Collections.sort(chargingCapacity);


        //eliminate trucks which i dont want to charge
        for (int i = 0; i < sortedCapacity.size(); i++) {
            if (sortedCapacity.get(i) > chargingCapacity.get(0)) {
                sortedCapacity.remove(i);
                continue;
            }
        }
        List<Integer> c1 = new ArrayList<>();
        //ist condition map c1 with largest possible element
        int value = chargingCapacity.get(0);
        for (int i = 0; i < sortedCapacity.size(); i++) {
            if (sortedCapacity.get(i) <= value) {
                value = value - sortedCapacity.get(i);
                c1.add(sortedCapacity.get(i));
                sortedCapacity.remove(i);

            } else {
                continue;
            }
        }
        List<Integer> c2 = new ArrayList<>();
        int value2 = chargingCapacity.get(1);
        for (int i = 0; i < sortedCapacity.size(); i++) {
            if (sortedCapacity.get(i) <= value2) {
                value2 = value2 - sortedCapacity.get(i);
                c2.add(sortedCapacity.get(i));
                sortedCapacity.remove(i);
            } else {
                continue;
            }
        }
        List<Integer> c3 = new ArrayList<>();
        int value3 = chargingCapacity.get(2);
        for (int i = 0; i <= sortedCapacity.size(); i++) {
            if (sortedCapacity.get(i)<=value3) {
                value3 = value3 - sortedCapacity.get(i);
                c3.add(sortedCapacity.get(i));
                sortedCapacity.remove(i);
            } else {
                continue;
            }
        }
        for(int i = 0; i <= sortedCapacity.size(); i++) {
            if (sortedCapacity.get(i)<=value3){
                value3 = value3 - sortedCapacity.get(i);
                c3.add(sortedCapacity.get(i));
                sortedCapacity.remove(i);

            }
            else if(sortedCapacity.get(i)<=value2){
                value2 = value2 - sortedCapacity.get(i);
                c2.add(sortedCapacity.get(i));
                sortedCapacity.remove(i);
            }
            else if(sortedCapacity.get(i)<=value){
                value = value - sortedCapacity.get(i);
                c1.add(sortedCapacity.get(i));
                sortedCapacity.remove(i);
            }
            else{
                continue;
            }
        }
        for(int i = 0; i <c1.size(); i++) {
            System.out.println("C1-> ");
        }

            System.out.println("total no of trucks charged "+ (c3.size()+ c2.size()+ c1.size()));



    }
    }


    class Truck {
        int id;
        int capacity; // capacity in kwh
        int chargeLevel;

        public Truck(int id, int capacity, int chargeLevel) {
            this.id = id;
            this.capacity = capacity;
            this.chargeLevel = chargeLevel;
        }

        public int getId() {
            return id;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getChargeLevel() {
            return chargeLevel;
        }
    }

    class Charger {
        int id;
        int rate; // Charging rate in kW

        public Charger(int id, int rate) {
            this.id = id;
            this.rate = rate;
        }

        public int getId() {
            return id;
        }

        public int getRate() {
            return rate;
        }
    }

