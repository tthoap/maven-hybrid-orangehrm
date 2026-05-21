package javaOOP;

public class Topic_03_Method {
    private void showCarname(){
        System.out.println("Honda");
    }

    static void showCarColor(){
        System.out.println("White");
    }

    public static void main(String[] args) {
        showCarColor();
        Topic_03_Method obj = new Topic_03_Method();
        obj.showCarname();

        Topic_03_Method.showCarColor();

    }
}
