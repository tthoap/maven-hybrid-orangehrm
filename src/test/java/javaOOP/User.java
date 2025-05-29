package javaOOP;

public class User {
    public static void main(String[] args) {
        //Khởi tạo
        Car car = new Car();
        car.setFullname();
        car.fullname= "Honda";
        //Animal animal = new Animal();

        Car secondCar = new Car();
        secondCar.setFullname();
        car.fullname = "Huyndai";


        Computer computer = new Computer();
        computer.setRAM();

        Person firstEmp = new Person("Nguyen Van Linh", "25", "12 Lelai");
        Person secondEmp = new Person("Nguyen Van An", "32", "12 To Ky");
    }

}
