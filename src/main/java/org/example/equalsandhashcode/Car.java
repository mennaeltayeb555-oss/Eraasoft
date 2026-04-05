package org.example.equalsandhashcode;

public class Car {
        String plateNumber;
        String color;

        public Car(String plateNumber, String color) {
            this.plateNumber = plateNumber;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Car) {
                Car c = (Car) o;
                return this.plateNumber.equals(c.plateNumber);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return plateNumber.hashCode();
        }
    }

