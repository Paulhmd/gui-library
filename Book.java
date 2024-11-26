public class Book {
    private int id;
    private String name;
    private String type;
    private String language;

    public Book(int id, String name, String type, String language) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Type: " + type + ", Language: " + language;
    }
}
