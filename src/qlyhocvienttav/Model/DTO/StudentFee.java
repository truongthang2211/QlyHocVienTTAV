package qlyhocvienttav.Model.DTO;

public class StudentFee extends Student{

    String idFee;
    double amountOfFeeIsComplete;
    String dateOfCompleteFee;
    String status;

   

    
    public String getIdFee() {
        return idFee;
    }

    public void setIdFee(String idFee) {
        this.idFee = idFee;
    }

    public StudentFee(String idFee,String student_id, double amountOfFeeIsComplete, String dateOfCompleteFee, String status ) {
        super(student_id);
        this.amountOfFeeIsComplete = amountOfFeeIsComplete;
        this.dateOfCompleteFee = dateOfCompleteFee;
        this.status = status;
         this.idFee = idFee;
    }

    public StudentFee(String idFee,String student_id,String fullName,String course_name,  double amountOfFeeIsComplete, double amountOfFee,String dateOfCompleteFee, String status) {
        super(student_id, course_name, amountOfFee, fullName);
        this.idFee = idFee;
        this.amountOfFeeIsComplete = amountOfFeeIsComplete;
        this.dateOfCompleteFee = dateOfCompleteFee;
        this.status = status;
    }
    
 

   

    public double getAmountOfFee() {
        return amountOfFee;
    }

    public void setAmountOfFee(double amountOfFee) {
        this.amountOfFee = amountOfFee;
    }

    public double getAmountOfFeeIsComplete() {
        return amountOfFeeIsComplete;
    }

    public void setAmountOfFeeIsComplete(double amountOfFeeIsComplete) {
        this.amountOfFeeIsComplete = amountOfFeeIsComplete;
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
