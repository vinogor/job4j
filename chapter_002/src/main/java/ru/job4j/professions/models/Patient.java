package ru.job4j.professions.models;

public class Patient {
	private String[] diseases;
	private String receiptDate;

	public Patient(String[] diseases, String receiptDate) {
		this.diseases = diseases;
		this.receiptDate = receiptDate;
	}

	public String[] getDiseases() {
		return diseases;
	}

	public String getReceiptDate() {
		return receiptDate;
	}
}