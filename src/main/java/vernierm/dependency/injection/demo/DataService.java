package vernierm.dependency.injection.demo;

import vernierm.dependency.injection.annotations.Autowired;

public class DataService {
    @Autowired
    private final DataRepository dataRepository;

    public DataService() {
        this.dataRepository = new DataRepository();
    }

    public Data getById(int id) {
        return dataRepository.getById(id);
    }
}
