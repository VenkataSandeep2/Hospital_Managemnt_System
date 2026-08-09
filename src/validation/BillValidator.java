package validation;

import java.math.BigDecimal;

import exception.InvalidBillException;
import model.Bill;

public class BillValidator 
{

    public static void validate(Bill bill) 
    {

        if (bill == null) 
        {
            throw new InvalidBillException("Bill cannot be null.");
        }

        if (bill.getAppointmentId() <= 0) 
        {
        	
            throw new InvalidBillException("Appointment ID must be greater than zero.");
        }

        if (bill.getConsultationFee() == null || bill.getConsultationFee().compareTo(BigDecimal.ZERO) < 0) {

            throw new InvalidBillException("Consultation fee cannot be negative.");
        }

        if (bill.getMedicineCharge() == null || bill.getMedicineCharge().compareTo(BigDecimal.ZERO) < 0) {

            throw new InvalidBillException("Medicine charge cannot be negative.");
        }

        if (bill.getLabCharge() == null || bill.getLabCharge() .compareTo(BigDecimal.ZERO) < 0) {

            throw new InvalidBillException("Lab charge cannot be negative.");
        }

        if (bill.getPaymentStatus() == null || bill.getPaymentStatus().trim().isEmpty()) {

            throw new InvalidBillException("Payment status cannot be empty.");
        }
    }
}