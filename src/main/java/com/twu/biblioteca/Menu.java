package com.twu.biblioteca;

public class Menu {
    private int[] optionId = new int[2];
    private String[] options = new String[2];

    public Menu(){
        optionId[0] = 1;
        options[0] = "List of books";
        optionId[1] = 2;
        options[1] = "Quit the application";
    }

    public void display() {
        for(int i = 0; i< 2 ; i++){
            System.out.println(optionId[i] + " " + options[i]);
        }
    }

    public boolean isValidOption(int option) {
        for(int i = 0; i < options.length; i++){
            if(option == optionId[i])
                return true;
        }
        return false;
    }
}
