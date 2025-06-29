import app.*;
import conveyor.*;
import factory.*;
import item.*;
import utils.*;
import warehouse.*;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.print("loading...\n");

        //로그출력객체정의, 현재시간출력
        final Logger logger = LogConfig.createLogger(Main.class.getName());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = now.format(formatter);
        logger.info("started on " + formatted);
        
        //메소드객체불러오기
        Scanner scanner = new Scanner(System.in);
        CommandHandler commandHandler = new CommandHandler();
        TokenCheck tokenCheck = new TokenCheck();

        while (true) {
            System.out.print("=======================\n");
            System.out.print("** Factory Simulator **\n");
            System.out.print("=======================\n");
            System.out.print("=COMMANDS=  [read Readme.md for details]\n");
            System.out.print("> create    > view       > info\n");
            System.out.print("> linkin    > linkout    > exit\n");
            System.out.print("=OBJECTS=  [ex)'create f']\n");
            System.out.print("(f)factory    (w)warehouse\n");
            System.out.print("(c)conveyor   (d)distributor\n");
            System.out.print("\n");
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            String command = tokens[0];

            switch (command){
                case "exit":
                    System.exit(0);
                    break;
                case "create":
                    if (tokens.length < 2) {
                        System.out.println("Insufficient arguments... (press Enter)");
                        scanner.nextLine();
                        break;
                    }
                    String type = tokens[1];
                    int n;
                    switch (type){
                        case "f":
                            System.out.println("Set production time (seconds), ex: 10\n");
                            System.out.println("production time: ");
                            n = tokenCheck.validIntScanner();
                            commandHandler.handleCreateF(n, logger);
                            System.out.println("Another factory was created successfully! (press Enter)");
                            scanner.nextLine();
                            break;
                        case "w":
                            System.out.println("Set max size (ea), ex: 40\n");
                            System.out.println("max size: ");
                            n = tokenCheck.validIntScanner();
                            commandHandler.handleCreateW(n, logger);
                            System.out.println("Another warehouse was created successfully! (press Enter)");
                            scanner.nextLine();
                            break;
                        case "c":
                            System.out.println("Set length of conveyor (ea), ex: 10\n");
                            System.out.println("length: ");
                            n = tokenCheck.validIntScanner();
                            commandHandler.handleCreateC(n, logger);
                            System.out.println("Another conveyor belt was created successfully! Use linkin/linkout to conncect object's output/input into this input/output. (press Enter)");
                            scanner.nextLine();
                            break;
                        case "d":
                            commandHandler.handleCreateD(logger);
                            System.out.println("Another distribution belt was created successfully! (press Enter)");
                            scanner.nextLine();
                            break;
                    }
                    break;
                default:
                    System.out.println("Unknown command... (press Enter)");
                    scanner.nextLine();
            }
        }

    }
}