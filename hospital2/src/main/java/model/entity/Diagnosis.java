	package model.entity;

import java.util.List;

public class Diagnosis {
	
	private int id;
	private String description;
	private String conclusion;
	private String procedures;
	private String medicines;
	private String operations;
	private int patient_id;
	private int doctor_id;
	private Patient patient;
	private List<Note> listOfJournals;
	
	public Diagnosis(String description, String conclusion, String procedures,
			String medicines, String operations, int patient_id, int doctor_id) {
		super();
		this.description = description;
		this.conclusion = conclusion;
		this.procedures = procedures;
		this.medicines = medicines;
		this.operations = operations;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
	}	

	public Diagnosis(int id, String description, String conclusion,
			String procedures, String medicines, String operations,
			int patient_id, int doctor_id) {
		super();
		this.id = id;
		this.description = description;
		this.conclusion = conclusion;
		this.procedures = procedures;
		this.medicines = medicines;
		this.operations = operations;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
	}
	
	

	public Diagnosis(String description, String conclusion, String procedures,
			String medicines, String operations, int doctor_id) {
		super();
		this.description = description;
		this.conclusion = conclusion;
		this.procedures = procedures;
		this.medicines = medicines;
		this.operations = operations;
		this.doctor_id = doctor_id;
	}

	public Diagnosis() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}

	public String getMedicines() {
		return medicines;
	}

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public List<Note> getListOfJournals() {
		return listOfJournals;
	}

	public void setListOfJournals(List<Note> listOfJournals) {
		this.listOfJournals = listOfJournals;
	}	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Diagnosis [id=" + id + ", description=" + description
				+ ", conclusion=" + conclusion + ", procedures=" + procedures
				+ ", medicines=" + medicines + ", operations=" + operations
				+ ", patient_id=" + patient_id + ", doctor_id=" + doctor_id
				+ ", listOfJournals=" + listOfJournals + "]";
	}
	
	
	
}
