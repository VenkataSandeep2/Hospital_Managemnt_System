package model;

import java.math.BigDecimal;

public class Bill {

    private int billId;
    private int appointmentId;
    private BigDecimal consultationFee;
    private BigDecimal medicineCharge;
    private BigDecimal labCharge;
    private BigDecimal totalAmount;
    private String paymentStatus;

    public Bill() {
    }

    public Bill(int billId,int appointmentId, BigDecimal consultationFee,
                BigDecimal medicineCharge, BigDecimal labCharge, BigDecimal totalAmount,
                String paymentStatus) {

        this.billId = billId;
        this.appointmentId = appointmentId;
        this.consultationFee = consultationFee;
        this.medicineCharge = medicineCharge;
        this.labCharge = labCharge;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(BigDecimal consultationFee) {
        this.consultationFee = consultationFee;
    }

    public BigDecimal getMedicineCharge() {
        return medicineCharge;
    }

    public void setMedicineCharge(BigDecimal medicineCharge) {
        this.medicineCharge = medicineCharge;
    }

    public BigDecimal getLabCharge() {
        return labCharge;
    }

    public void setLabCharge(BigDecimal labCharge) {
        this.labCharge = labCharge;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {

        return "Bill [billId=" + billId
                + ", appointmentId=" + appointmentId
                + ", consultationFee=" + consultationFee
                + ", medicineCharge=" + medicineCharge
                + ", labCharge=" + labCharge
                + ", totalAmount=" + totalAmount
                + ", paymentStatus=" + paymentStatus + "]";
    }
}