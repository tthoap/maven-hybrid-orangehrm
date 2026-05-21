package javaOOP;

public class Topic_01_Class_Object_Student {
    public int studentID;
    private String studentName;
    private float knowledPoint;
    private float practicePoint;

    public Topic_01_Class_Object_Student(){}
    public Topic_01_Class_Object_Student(int studentID, String studentName, float knowledPoint, float practicePoint) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.knowledPoint = knowledPoint;
        this.practicePoint = practicePoint;
    }

    private int getStudentID() {
        return studentID;
    }

    private void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    private String getStudentName() {
        return studentName;
    }

    private void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private float getKnowledPoint() {
        return knowledPoint;
    }

    private void setKnowledPoint(float knowledPoint) {
        this.knowledPoint = knowledPoint;
    }

    private float getPracticePoint() {
        return practicePoint;
    }

    private void setPracticePoint(float practicePoint) {
        this.practicePoint = practicePoint;
    }

    private float averagePoint() {
        return (this.knowledPoint + this.practicePoint*2) / 3;
    }

    private void showStudentInfo() {
        System.out.println("********************************************");
        System.out.println("Student ID: " + getStudentID());
        System.out.println("Student Name: " + getStudentName());
        System.out.println("Knowledge Point: " + getKnowledPoint());
        System.out.println("Pratice Point: " + getPracticePoint());
        System.out.println("Average point: " + averagePoint());
        System.out.println("********************************************");
    }

    public static void main(String[] args) {
        Topic_01_Class_Object_Student firstStd = new Topic_01_Class_Object_Student(10010,
                "Le Vy", 8.0f, 9.0f);
        firstStd.showStudentInfo();

        Topic_01_Class_Object_Student secondStd = new Topic_01_Class_Object_Student(10011,
                "Hoang Mai", 8.0f, 8.0f);
        secondStd.showStudentInfo();

        Topic_01_Class_Object_Student thirdStd = new Topic_01_Class_Object_Student(10012,
                "Tran Huy", 9.0f, 9.0f);
        thirdStd.showStudentInfo();

    }
}
