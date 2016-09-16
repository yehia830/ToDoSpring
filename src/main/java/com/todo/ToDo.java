package com.todo;

import javax.persistence.*;


@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @ManyToOne
    User user;

    public ToDo() {
    }

    public ToDo(String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}