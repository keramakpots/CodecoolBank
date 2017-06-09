package model;

public class BaseModel implements BaseModelInterface{

    private Integer id;
    private String name;
    private String description;

    public BaseModel(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

}
