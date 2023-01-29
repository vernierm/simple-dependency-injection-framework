package vernierm.demo;

import vernierm.dependency.injection.annotations.Autowired;
import vernierm.dependency.injection.annotations.Component;

@Component
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    public Data getById(int id) {
        return dataRepository.getById(id);
    }
}
