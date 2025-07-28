package com.example.java_play_ground.service;

import com.example.java_play_ground.service.app.*;
import com.example.java_play_ground.service.utils.*;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;
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
        CommandRegistry registry = new CommandRegistry();
        CommandHandler commandHandler = new CommandHandler(registry);
        TokenCheck tokenCheck = new TokenCheck();

        //입력변수들
        String type;
        String typeLink;
        int input_i;
        float input_f;

        while (true) {
            System.out.print("=======================\n");
            System.out.print("** Factory Simulator **\n");
            System.out.print("=======================\n");
            System.out.print("=COMMANDS=  [read Readme.md for details]\n");
            System.out.print("> create    > list       > field\n");
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
                    type = tokens[1];
                    switch (type){
                        case "f":
                            System.out.println("Set production time (seconds), ex: 9.5\n");
                            System.out.println("production time: ");
                            input_f = tokenCheck.validFloatScanner();
                            commandHandler.handle("createProducer", input_f, logger);
                            System.out.println("Another factory was created successfully!");
                            break;
                        case "w":
                            System.out.println("Set max size (ea), ex: 40\n");
                            System.out.println("max size: ");
                            input_i = tokenCheck.validIntScanner();
                            commandHandler.handle("createWarehouse", (float) input_i, logger);
                            System.out.println("Another warehouse was created successfully!");
                            break;
                        case "c":
                            System.out.println("Set length of conveyor (unit), ex: 10.5\n");
                            System.out.println("length: ");
                            input_f = tokenCheck.validFloatScanner();
                            commandHandler.handle("createConveyor", input_f, logger);
                            System.out.println("Another conveyor belt was created successfully!");
                            break;
                        case "d":
                            commandHandler.handle("createDistributor", 0, logger);
                            System.out.println("Another distribution belt was created successfully!");
                            break;
                        default:
                        		System.out.println("Invalid instance... (press Enter)");
                        		scanner.nextLine();
                        		break;
                    }
                    break;
                case "list":
                    if (tokens.length < 2) {
                        System.out.println("Insufficient arguments... (press Enter)");
                        scanner.nextLine();
                        break;
                    }
                    type = tokens[1];
                    switch (type) {
                        case "f":
                            commandHandler.handle("listProducer", 0, logger);
                            System.out.print("press Enter...");
                            scanner.nextLine();
                            break;
                        case "w":
                        		commandHandler.handle("listWarehouse", 0, logger);
                            System.out.print("press Enter...");
                            scanner.nextLine();
                            break;
                        case "c":
                        		commandHandler.handle("listConveyor", 0, logger);
                            System.out.print("press Enter...");
                            scanner.nextLine();
                            break;
                        case "d":
                        		commandHandler.handle("listDistributor", 0, logger);
                            System.out.print("press Enter...");
                            scanner.nextLine();
                            break;
                        default:
	                    		System.out.println("Invalid instance... (press Enter)");
	                    		scanner.nextLine();
	                    		break;
                    }
                    break;
                
                case "field" :
		            	break;
                case "linkin":
	                	if (tokens.length < 3) {
	                        System.out.println("Insufficient arguments... (press Enter)");
	                        scanner.nextLine();
	                        break;
	                    }
	                	type = tokens[1];
	                	typeLink = tokens[2];
	                		if(type.startsWith("f") && typeLink.startsWith("c")) {
	                			registry.getContext().setTempLinkFrom(type);
	                		    registry.getContext().setTempLinkTo(typeLink);
	                			commandHandler.handle("linkp2c", 0, logger);
	                			break;
	                		}
	                		if(type.startsWith("c") && typeLink.startsWith("d")) {
	                			break;
	                		}
	                		if(type.startsWith("c") && typeLink.startsWith("w")) {
	                			registry.getContext().setTempLinkFrom(type);
	                		    registry.getContext().setTempLinkTo(typeLink);
	                			commandHandler.handle("linkc2w", 0, logger);
	                			break;
	                		}
	                		if(type.startsWith("d") && typeLink.startsWith("c")) {
	                			break;
	                		}
		            		System.out.println("Invalid instance... (press Enter)");
		            		scanner.nextLine();
                		break;
                case "linkout":
                		break;
                	
                default:
                    System.out.println("Unknown command... (press Enter)");
                    scanner.nextLine();
            }
        }

    }
}