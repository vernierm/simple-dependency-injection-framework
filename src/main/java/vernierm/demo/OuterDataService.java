package vernierm.demo;

import vernierm.dependency.injection.annotations.Autowired;
import vernierm.dependency.injection.annotations.Component;

@Component
public class OuterDataService {
    @Autowired
    private DataService dataService;

    public Data getById(int id) {
        return dataService.getById(id);
    }
}
