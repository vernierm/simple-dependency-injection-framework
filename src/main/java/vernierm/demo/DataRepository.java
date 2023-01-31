package vernierm.demo;

import vernierm.dependency.injection.annotations.Component;

import java.util.Map;

@Component
public class DataRepository {
    private final Map<Integer, Data> data = Map.of(
            1, new Data(1, "Fido"),
            2, new Data(2, "Bobi"),
            3, new Data(3, "Roki")
    );

    public Data getById(int id) {
        return data.get(id);
    }
}
