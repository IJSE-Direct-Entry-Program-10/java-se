package lk.ijse.dep10.serialization.model;

public class Address {
    String no;
    String city = "Panadura";

    public Address() {
    }

    public Address(String no, String city) {
        this.no = no;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "no='" + no + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
