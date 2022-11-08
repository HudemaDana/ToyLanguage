import Controller.Controller;
import Repository.IRepository;
import Repository.Repository;
import View.View;
import Exception.EmptyCollectionException;

public class Main{
    public static void main(String[] args) {
        IRepository repo = new Repository();
        Controller controller = new Controller(repo);
        View view = new View(controller);

        try {
            view.menu();
        }catch (EmptyCollectionException e){
            System.out.println(e);
        }
    }
}