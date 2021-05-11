package qlyhocvienttav.Model.DTO;

public class StudentFee {

    String idFee;
    String idStudent;
    String nameStudent;
    Long amountOfFee;


    Long amountOfFeeIsComplete;
    String dateOfCompleteFee;
    String status;
    StudentFee(){};
    public StudentFee(String _idFee, String _idStudent, String _nameStudent, Long _amountOfStudent, String _status,String _dateOfCompleteFee){
        idFee=_idFee;
        idStudent=_idStudent;
        nameStudent=_nameStudent;
        amountOfFee=_amountOfStudent;
        dateOfCompleteFee=_dateOfCompleteFee;
        status=_status;
    }
    public Long getAmountOfFeeIsComplete() {
        return amountOfFeeIsComplete;
    }

    public void setAmountOfFeeIsComplete(Long amountOfFeeIsComplete) {
        this.amountOfFeeIsComplete = amountOfFeeIsComplete;
    }

    public String getIdFee() {
        return idFee;
    }

    public void setIdFee(String idFee) {
        this.idFee = idFee;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public Long getAmountOfFee() {
        return amountOfFee;
    }

    public void setAmountOfFee(Long amountOfFee) {
        this.amountOfFee = amountOfFee;
    }

    public String getDateOfCompleteFee() {
        return dateOfCompleteFee;
    }

    public void setDateOfCompleteFee(String dateOfCompleteFee) {
        this.dateOfCompleteFee = dateOfCompleteFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
