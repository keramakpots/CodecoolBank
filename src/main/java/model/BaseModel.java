package model;

public class BaseModel {

    private Integer id;
    private String name;
    private String description;

    public BaseModel(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
