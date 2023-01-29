package vernierm.demo;

import vernierm.dependency.injection.Autowired;

public class DataService {
    @Autowired
    private DataRepository dataRepository;

    public Data getById(int id) {
        return dataRepository.getById(id);
    }
}
