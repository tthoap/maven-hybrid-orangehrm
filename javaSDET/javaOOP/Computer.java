package javaOOP;

public abstract class Computer {

    // Normal method
    public void showComputerPerformance(){
        System.out.println("Computer Performance");
    }

    // Khung để cho các class con kế thừa nó phải override lại (implement lại)
    public abstract void displayComputerPerformance();
}
