package vernierm.dependency.injection;

import vernierm.dependency.injection.demo.DataService;

public class Application {
    public static void main(String[] args) {
        var dataService = new DataService();
        System.out.println(dataService.getById(1));
    }
}
