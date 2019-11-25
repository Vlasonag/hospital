package model.entity;

import java.sql.Timestamp;


public class Note {
	
	private int id;
	private int worker_id;
	private int diagnosis_id;
	private String commentary;
	private Timestamp date;
	private Diagnosis diagnosis;
	private User user;
	
	public Note(int worker_id, int diagnosis_id, String commentary, Timestamp date) {
		super();
		this.worker_id = worker_id;
		this.diagnosis_id = diagnosis_id;
		this.commentary = commentary;
		this.date = date;
	}
	
	public Note(int id, int worker_id, int diagnosis_id, String commentary,
			Timestamp date) {
		super();
		this.id = id;
		this.worker_id = worker_id;
		this.diagnosis_id = diagnosis_id;
		this.commentary = commentary;
		this.date = date;
	}

	

	public Note() {
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(int worker_id) {
		this.worker_id = worker_id;
	}

	public int getDiagnosis_id() {
		return diagnosis_id;
	}

	public void setDiagnosis_id(int diagnosis_id) {
		this.diagnosis_id = diagnosis_id;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Note [worker_id=" + worker_id + ", commentary=" + commentary
				+ ", date=" + date + "]";
	}
	
	
}
