package org.auk;

/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;*/
import org.auk.data.*;
import org.auk.models.Course;
import org.auk.models.Review;
import org.auk.models.Student;
import org.auk.models.StudentProfile;
import org.auk.utils.ConsoleColors;
import org.auk.utils.DbUtil;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.auk.utils.Logger.log;

/**
 * TechUp JAVA 2019
 *
 * @link Extra JavaFX GUI components http://www.jfoenix.com
 */
public class App {
    private final static String DB_URL = "jdbc:mariadb://localhost/techupdb?useSSL=false";
    private final static int X_TIMES = 65;

    /*
     * The Hidden Synchronized Keyword With a Static Block
     * https://dzone.com/articles/the-hidden-synchronized-keyword-with-a-static-bloc
     */
    private static final String currentTimeString;
    private static String lastUsedTimeString;

    static {
        currentTimeString = LocalDate.now().toString();
    }

    private static StudentRepository repository;
    private static Connection connection;

    @PersistenceContext
    static EntityManager entityManager;

    public static void main(String[] args) {/*
        SessionFactory sessionFactory = DbUtil.getSessionFactory();
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        log().info("Transaction Begin");*/

//        Student student = new Student();
//        student.setFirstName("Arian");
//        student.setLastName("Xhezairi");
//        student.setEmail("arian@legends.com");
//        student.setBirthday(new Date());
//        //em.persist(student);
//        StudentProfile studentProfile = new StudentProfile();
//        studentProfile.setAddress("st.prishtine 3423");
//        studentProfile.setBirthplace("Kosovo");
//        studentProfile.setStudent(student);
//      //  em.persist(studentProfile);
//        StudentDao studentDao = new StudentDao();
//        studentDao.save(student);

//        List<Student> studentList = em.createQuery("from Student ", Student.class).getResultList();
//        for (Student std : studentDao.getAll()) {
//            print(std.getFullName());
//        }

        //<=============  add a  profile to a Student  (OneToOne) =================>
  /*      StudentDao studentDao = new StudentDao();
        for (Student std : studentDao.getAll()) {
           if(std.getFirstName().equalsIgnoreCase("Arian")
           && std.getLastName().equalsIgnoreCase("Xhezairi")){
               Student student1 = std;
               StudentProfile studentProfile1 = new StudentProfile();
               studentProfile1.setAddress("st.prishtine 10000");
               studentProfile1.setBirthplace("Prizren");
               studentProfile1.setStudent(student1);
               StudentProfileDao studentProfileDao = new StudentProfileDao();
               studentProfileDao.save(studentProfile1);
           }else {
               System.out.println("Student does not exist");
           }
        }*/



         //<============     (OneToMany  add some reviws to a Course    ============>
     /*   Course course = new Course();
        course.setTitle("Hibernate");
        course.setDescription("learn ORM");
        course.setStartDate(new Date());
        course.setEndDate(new Date());
        CourseDao courseDao = new CourseDao();
        Review review1 = new Review();
        review1.setComment("best Course ever");
        course.addReview(review1);
        review1.setCourse(course);
        Review review2 = new Review();
        review2.setComment("Thought was better");
        course.addReview(review2);
        review2.setCourse(course);
        System.out.println("---------- before Course ------------");
        courseDao.save(course);
        ReviewDao reviewDao = new ReviewDao();
        System.out.println("---------- before Review1 ------------");
        reviewDao.save(review1);
        System.out.println("---------- after Review1 ------------");
        reviewDao.save(review2);*/


     // <=============  get reviews of a course ==============>

//        CourseDao courseDao = new CourseDao();
//        for (Course course:courseDao.getAll()) {
//                if(course.getReviewList() !=null){
//                    for (Review review:course.getReviewList()) {
//                        System.out.println(review);
//                    }
//                }
//
//        }
 //    <========================================================>



     // <=============== add a course to a student  ( MantyToMany)=================>
        CourseDao courseDao = new CourseDao();
        StudentDao studentDao = new StudentDao();
        Course course = courseDao.getById(12);
       Student student = studentDao.getById(4);

       student.addCourse(course);
       studentDao.update(student);




//        em.getTransaction().commit();
//        log().info("Transaction Commit");

        // Pure JDBC - no ORM
//        try (var ignore = getConnection()) {
//            getStudentList();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //launch(args);
        //launchCLI(args);
    }

    private static void getStudentList() {
        try (var stmt = connection.createStatement();
             var results = stmt.executeQuery("SELECT * FROM students")) {

            while (results.next()) {
                var metaData = results.getMetaData();

                for (var i = 1; i <= metaData.getColumnCount(); i++) {
                    var column = metaData.getColumnName(i).substring(0, 1).toUpperCase() + metaData.getColumnName(i).substring(1);
                    print(column + ": " + results.getString(i));
                }

                print(System.lineSeparator());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initiates and returns the Connection instance of the specified Database driver
     * <p>
     * Bonus: Find out more about Maps
     *
     * @link https://www.baeldung.com/java-initialize-hashmap
     */
   /* private static Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }

        // Class.forName("org.mariadb.jdbc.Driver");
        DriverManager.registerDriver(new org.mariadb.jdbc.Driver());

        Properties props = new Properties();
        // The Java 8 Way
        props.putAll(Stream.of(new String[][]{
                {"user", "root"},
                {"password", "root"},
                {"autoReconnect", "true"}
        }).collect(Collectors.toMap(entry -> entry[0], entry -> entry[1])));

        // The Java 9 Way
//        Map<String, String> propMap = Map.of("user","root", "password", "root", "autoReconnect", "true");
//        props.putAll(propMap);

        return connection = DriverManager.getConnection(DB_URL, props);
    }*/

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/layout_main.fxml"));
//        Scene scene = new Scene(root, 300, 275);
//        primaryStage.setTitle("Hello TechUp!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    private static void launchCLI(String... args) {
        print(ConsoleColors.PURPLE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + " ~ Welcome to Student Management System 2020 ~ " + ConsoleColors.RESET);
        print(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLUE_UNDERLINED + "Please fill in the data for the new student..." + ConsoleColors.RESET);
        print("=".repeat(50));

        repository = new StudentRepository(5);

        boolean receiving = true;

        for (; receiving; ) {
            String studentData = readNewStudentDataFromInput();
            repository.add(repository.getNextRecordId() + ", " + studentData, true);
            repository.refreshList();

            print("Do you want to create another student record? Y/n:");
            Scanner sc = new Scanner(System.in);
            char answer = sc.next().charAt(0);
//
//            switch (answer) {
//                case 'N', 'n' -> receiving = false;
//            }
        }

        print(ConsoleColors.RED_BOLD + "Thank you for your service, and see you soon again! ^_^" + ConsoleColors.RESET);

        // Table Headers
        printTableBanner();
        printTableColumns();
        printTableBody();
    }

    private static String readNewStudentDataFromInput() {
        String[] columns = {"Name", "Surname", "Birthday", "Phone", "Email"};

        // Create a Scanner instance and initialize with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // Initialize count of input elements
        int count = 0;

        // Check if an int value is available
        while (count < columns.length) {
            print(ConsoleColors.BLUE_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT + columns[count] + ":" + ConsoleColors.RESET);
            sb.append(sc.nextLine()).append(", ");
            count++;
        }

//        String[] newData = sb.toString().split(",");
//        Student newStudent = new Student();
//        newStudent.setFirstName(newData[0]);
//        newStudent.setLastName(newData[1]);
//        newStudent.setEmail(newData[3]);
//        newStudent.setPhone(newData[4]);

//        try {
//            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(newData[2]);
//            newStudent.setDob(dob);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        return sb.toString();
    }

    private static void printTableBody() {
        for (Student student : repository.getAll()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM, Y", Locale.getDefault());
            String output = "| {0} " + " ".repeat(7 - (String.valueOf(student.getId()).length()))
                    + "| {1} " + " ".repeat(13 - (student.getFirstName().length() - 1))
                    + "| {2}" + " ".repeat(16 - (simpleDateFormat.format(student.getBirthday()).length() - 1))
                    + "| {3}" + " ".repeat(15 - (student.getPhone().length() - 1))
                    + "|";
            MessageFormat mf = new MessageFormat(output);
            print(mf.format(new Object[]{student.getId(), student.getFirstName(),
                    simpleDateFormat.format(student.getBirthday()), student.getPhone()}));
            print("-".repeat(X_TIMES));
        }

        /*
          Get Student by id
         */
        Student theStudent = repository.getById(2);
//        Objects.requireNonNull(theStudent);

        /*
          Add student to the list
         */
        Student s = new Student();
        s.setId(11);
        s.setFirstName("Xhevat");
        s.setLastName("Xhezairi");
        s.setGender(Student.Gender.MALE);
//        repository.add(s);
    }

    private static void printTableColumns() {
        print("| ID " + " ".repeat(5) + "| NAME " + " ".repeat(10) + "| DoB " + " ".repeat(13) + "| TEL " + " ".repeat(12) + "|");
        print("-".repeat(X_TIMES));
    }

    private static void printTableBanner() {
        String banner =
                "=".repeat(X_TIMES) + System.getProperty("line.separator") +
                        "|                       Hello JAVA Gurus!                       |\n" +
                        "=".repeat(X_TIMES);
        print(banner);
    }

    /**
     * Prints a message
     *
     * @param message Message to print
     */
    private static void print(String message) {
        System.out.println(message);
    }

    public static synchronized void updateLastUsedTime() {
        lastUsedTimeString = LocalDate.now().toString();
    }
}
