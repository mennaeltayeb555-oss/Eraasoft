package org.example.equalsandhashcode;

public class Product {
        String code;
        double price;

        public Product(String code, double price) {
            this.code = code;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Product) {
                Product p = (Product) o;
                return this.code.equals(p.code);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return code.hashCode();
        }
    }

