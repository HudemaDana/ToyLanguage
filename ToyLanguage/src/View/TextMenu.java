package View;

import View.Command.ACommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    Map<String, ACommand> commands;

    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(ACommand c) {
        commands.put(c.getKey(), c);
    }

    void printMenu() {
        for (ACommand com : commands.values()) {
            String line = String.format("%4s:%s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            ACommand com = commands.get(key);
            if (com == null) {
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}
