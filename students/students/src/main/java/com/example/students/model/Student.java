package com.example.students.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;
    
	private int age;
	private String mail_id;
	private int number;
	public int getId() {
	return id;
	}

	public void setId(int id) {
	this.id = id;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail_id() {
		return mail_id;
	}

	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
	return "Student{" +
	"id=" + id +
	", name='" + name + '\'' +
	", age='" + age + '\'' +
	",mail_id='" + mail_id + '\'' +
	", number='" + number + '\'' +
	'}';
}
}