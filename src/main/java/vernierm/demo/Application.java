package vernierm.demo;

import vernierm.dependency.injection.ApplicationContext;

public class Application {
    public static void main(String[] args) throws Exception {
        var applicationContext = new ApplicationContext();
        DataService dataService = applicationContext.getInstance(DataService.class);
        System.out.println(dataService.getById(1));
    }
}