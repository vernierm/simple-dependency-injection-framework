package vernierm.dependency.injection.demo;

public class Data {
    private final int id;
    private final String name;

    public Data(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
