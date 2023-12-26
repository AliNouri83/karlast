import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String message) {
        super(message);
    }
}

class CourseNotFoundException extends Exception {
    public CourseNotFoundException(String message) {
        super(message);
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        System.out.print("Enter the number of courses: ");
        int numberOfCourses = scanner.nextInt();

        University<Student, Course> university = new University<>(numberOfStudents, numberOfCourses);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    university.addPerson(addStudent());
                    break;
                case 2:
                    university.addCourse(addCourse());
                    break;
                case 3:
                    addGrade(university);
                    break;
                case 4:
                    getPersonWithHighestGrade(university);
                    break;
                case 5:
                    displayAllGrades(university);
                    break;
                case 6:
                    displayHighestGrade(university);
                    break;
                case 7:
                    displayAllPeople(university);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Add Grade");
        System.out.println("4. Get Student with Highest Grade");
        System.out.println("5. Display All Grades");
        System.out.println("6. Display Highest Grade");
        System.out.println("7. Display All Students");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static Student addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        return new Student(name, studentId, age);
    }

    private static Course addCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course name: ");
        String courseName = scanner.next();
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        return new Course(courseName, courseId);
    }

    private static void addGrade(University<Student, Course> university) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        try {
            university.addGrade(studentId, courseId, grade);
            System.out.println("Grade added successfully.");
        } catch (PersonNotFoundException | CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getPersonWithHighestGrade(University<Student, Course> university) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        try {
            Student student = university.getPersonWithHighestGrade(courseId);
            System.out.println("Person with the highest grade: " + student);
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAllGrades(University<Student, Course> university) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        try {
            List<Double> grades = university.getAllGrades(courseId);
            System.out.println("Grades for the course:");
            for (double grade : grades) {
                System.out.println(grade);
            }
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayHighestGrade(University<Student, Course> university) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        try {
            double highestGrade = university.getHighestGrade(courseId);
            System.out.println("Highest grade for the course: " + highestGrade);
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAllPeople(University<Student, Course> university) {
        List<Student> students = university.getAllPeople();
        System.out.println("List of all students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

class University<T extends Person, U extends Course> {
    private int numberOfPeople;
    private int numberOfCourses;
    private List<T> people;
    private List<U> courses;

    public University(int numberOfPeople, int numberOfCourses) {
        this.numberOfPeople = numberOfPeople;
        this.numberOfCourses = numberOfCourses;
        this.people = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void addPerson(T person) {
        if (people.size() < numberOfPeople) {
            people.add(person);
        } else {
            System.out.println("Cannot add more people. Maximum limit reached.");
        }
    }

    public void addCourse(U course) {
        if (courses.size() < numberOfCourses) {
            courses.add(course);
        } else {
            System.out.println("Cannot add more courses. Maximum limit reached.");
        }
    }

    public void addGrade(int personId, int courseId, double grade) throws PersonNotFoundException, CourseNotFoundException {
        T person = getPersonById(personId);
        U course = getCourseById(courseId);

        if (person != null && course != null) {
        } else {
            throw new PersonNotFoundException("Person not found with ID " + personId);
        }
    }

    public T getPersonWithHighestGrade(int courseId) throws CourseNotFoundException {
        U course = getCourseById(courseId);
        if (course != null) {
            return null;
        } else {
            throw new CourseNotFoundException("Course not found with ID " + courseId);
        }
    }

    public List<Double> getAllGrades(int courseId) throws CourseNotFoundException {
        U course = getCourseById(courseId);
        if (course != null) {
            return null;
        } else {
            throw new CourseNotFoundException("Course not found with ID " + courseId);
        }
    }

    public double getHighestGrade(int courseId) throws CourseNotFoundException {
        U course = getCourseById(courseId);
        if (course != null) {
            return 0.0;
        } else {
            throw new CourseNotFoundException("Course not found with ID " + courseId);
        }
    }

    public List<T> getAllPeople() {
        return new ArrayList<>(people);
    }

    private T getPersonById(int personId) {
        return null;
    }

    private U getCourseById(int courseId) {
        return null;
    }
}
