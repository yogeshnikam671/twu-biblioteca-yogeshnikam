public class Menu {
    int[] optionId = new int[2];
    String[] options = new String[2];

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
}
