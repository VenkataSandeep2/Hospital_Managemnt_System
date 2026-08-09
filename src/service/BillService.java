package service;

import java.util.List;

import model.Bill;

public interface BillService 
{

    boolean addBill(Bill bill);

    List<Bill> getAllBills();

    Bill getBillById(int billId);

    Bill getBillByAppointmentId(int appointmentId);

    boolean updatePaymentStatus(int billId, String paymentStatus);
}