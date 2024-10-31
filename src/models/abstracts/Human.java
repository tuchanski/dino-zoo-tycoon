package models.abstracts;

import models.enums.Gender;

public abstract class Human {

    private static Long idCounter = 0L;

    private Long id;

    private String name;
    private Integer age;
    private Gender gender;

    public Human(String name, Integer age, String gender) {
        idCounter++;
        this.id = idCounter;
        this.name = name;
        this.age = age;
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    // TO CHILD CLASSES
    protected abstract void stareAtDino(); // We should have some better ideas

}
