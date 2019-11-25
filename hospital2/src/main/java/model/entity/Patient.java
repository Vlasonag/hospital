package model.entity;

import java.util.Set;


public class Patient {
	
	private int id;
	private int room;
	private String name;
	private String surname;
	private int diagnosis_id;
	private int doctor_id;
	private int inHospital;
	private Set<User> listOfWorkers;
	
	
	
	public Patient(int id, int room, String name, String surname, int doctor_id,
				int diagnosis_id) {
		super();
		this.id = id;
		this.room = room;
		this.name = name;
		this.surname = surname;
		this.diagnosis_id = diagnosis_id;
		this.doctor_id = doctor_id;
	}
	
	public Patient(int id, int room, String name, String surname, int doctor_id) {
		this.id = id;
		this.room = room;
		this.name = name;
		this.surname = surname;
		this.doctor_id = doctor_id;
	}
	public Patient(int room, String name, String surname, int doctor_id) {
		this.room = room;
		this.name = name;
		this.surname = surname;
		this.doctor_id = doctor_id;
	}

	public Patient() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Set<User> getListOfWorkers() {
		return listOfWorkers;
	}

	public void setListOfWorkers(Set<User> listOfWorkers) {
		this.listOfWorkers = listOfWorkers;
	}
	public int getDiagnosis_id() {
		return diagnosis_id;
	}
	public void setDiagnosis_id(int diagnosis_id) {
		this.diagnosis_id = diagnosis_id;
	}

	public int getInHospital() {
		return inHospital;
	}

	public void setInHospital(int inHospital) {
		this.inHospital = inHospital;
	}

	@Override
	public String toString() {
		return "Patient, id=" + id + ", room=" + room + ", name=" + name 
				+ ",\n surname=" + surname + ", diagnosis_id=" + diagnosis_id
				+ ", doctor_id=" + doctor_id;
	}
	
	
	
}
