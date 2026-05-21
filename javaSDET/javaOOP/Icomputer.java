package javaOOP;

public interface Icomputer {
    // Nếu không gán từ khóa là abstract cho hàm thì tự hiểu đây vẫn là 1 hàm abstract
    public void showComputerPerformance();

    // Khung để cho các class con kế thừa nó phải override lại (implement lại)
    public abstract void displayComputerPerformance();
}
