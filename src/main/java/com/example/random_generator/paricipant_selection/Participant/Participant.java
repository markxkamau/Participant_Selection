package com.example.random_generator.paricipant_selection.Participant;

import javax.persistence.*;

@Entity
@Table
public class Participant {
    @Id
    @SequenceGenerator(
            sequenceName = "participant_generator",
            name = "participant_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "participant_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private Gender gender;

    public Participant() {
    }

    public Participant(Long id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

