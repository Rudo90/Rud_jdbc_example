package jdbc;

import jdbc.database.DBConnectionProvider;
import jdbc.manager.PersonManager;
import jdbc.model.Person;
import java.util.Scanner;

public class JdbcExample {

    static Scanner scanner = new Scanner(System.in);
    static PersonManager personManager = new PersonManager();

    public static void main(String[] args) {

        boolean isRun = true;

        while (isRun) {
            System.out.println("Input 0 to exit");
            System.out.println("To add a person into database insert 1");
            System.out.println("To update datum in database insert 2");
            System.out.println("To find a person insert 3");
            System.out.println("To retrieve all data from database insert 4");
            System.out.println("To delete a datum from database insert 5");
            System.out.println("To delete all data from database insert 6");

            try {
                String command = String.valueOf(scanner.nextLine());

                switch (command) {

                    case "0":
                        System.exit(0);
                        break;
                    case "1":
                        addPerson();
                        break;
                    case "2":
                        upDatePerson();
                        break;
                    case "3":
                        getPerson();
                        break;
                    case "4":
                        personManager.getAllData();
                        break;
                    case "5":
                        deleteDatum();
                        break;
                    case "6":
                        personManager.deleteAllData();
                        break;
                    default:
                        System.out.println("Wrong command was inserted!");
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addPerson () {
        System.out.println("Input person's name, surname, email and password");
        String name = scanner.nextLine();
        String surname = scanner.nextLine();
        String email = scanner.nextLine();
        String password = scanner.nextLine();
        DBConnectionProvider.getProvider();
        Person person = Person.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build();
        personManager.addPerson(person);

    }

    public static void upDatePerson (){

        System.out.println("Input person's email for further modifications");
        String email = scanner.nextLine();
        System.out.println("Input person's new name, surname and password");
        String name = scanner.nextLine();
        String surname = scanner.nextLine();
        String password = scanner.nextLine();
        DBConnectionProvider.getProvider();
        personManager.upDatePerson(Person.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build());
    }

    public static void getPerson (){

        System.out.println("Input keyword to search a person");
        String keyword = scanner.nextLine();
        DBConnectionProvider.getProvider();
        personManager.getPerson(keyword);


    }

    public static void deleteDatum (){

        System.out.println("Input person's email to delete");
        String email = scanner.nextLine();
        DBConnectionProvider.getProvider();
        personManager.deleteData(email);

    }





    }