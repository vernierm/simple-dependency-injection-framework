package vernierm.demo;

import java.util.Map;

public class DataRepository {
    private final Map<Integer, Data> data = Map.of(
            1, new Data(1, "Fido"),
            2, new Data(1, "Bobi"),
            3, new Data(1, "Roki")
    );

    public Data getById(int id) {
        return data.get(id);
    }
}
