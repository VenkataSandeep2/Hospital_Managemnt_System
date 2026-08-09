package serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import dao.AppointmentDAO;
import dao.BillDAO;
import daoimpl.AppointmentDAOImpl;
import daoimpl.BillDAOImpl;
import exception.InvalidBillException;
import model.Appointment;
import model.Bill;
import service.BillService;
import validation.BillValidator;

public class BillServiceImpl implements BillService 
{

    private BillDAO billDAO;
    private AppointmentDAO appointmentDAO;

    public BillServiceImpl() 
    {

        billDAO = new BillDAOImpl();
        appointmentDAO = new AppointmentDAOImpl();
    }

    @Override
    public boolean addBill(Bill bill) 
    {

        BillValidator.validate(bill);
        Appointment appointment =appointmentDAO.getAppointmentById(bill.getAppointmentId());

        if (appointment == null) 
        {
        	throw new InvalidBillException("Appointment not found with ID: " + bill.getAppointmentId());
        }

        if ("CANCELLED".equalsIgnoreCase(appointment.getStatus())) {

            throw new InvalidBillException("Cannot generate bill for a cancelled appointment.");
        }

        // 4. Check whether appointment already has a bill
        Bill existingBill =billDAO.getBillByAppointmentId( bill.getAppointmentId());

        if (existingBill != null) 
        {

            throw new InvalidBillException("Bill already exists for appointment ID: "+ bill.getAppointmentId());
        }

        // 5. Calculate total amount
        BigDecimal totalAmount = bill.getConsultationFee().add(bill.getMedicineCharge())
        						.add(bill.getLabCharge());

        bill.setTotalAmount(totalAmount);

        // 6. Default payment status
        if (bill.getPaymentStatus() == null || bill.getPaymentStatus().trim().isEmpty())
        {

            bill.setPaymentStatus("PENDING");
        }

        // 7. Save bill
        return billDAO.addBill(bill);
    }

    @Override
    public List<Bill> getAllBills() 
    {

        return billDAO.getAllBills();
    }

    @Override
    public Bill getBillById(int billId) 
    {

        return billDAO.getBillById(billId);
    }

    @Override
    public Bill getBillByAppointmentId(
            int appointmentId) {

        return billDAO.getBillByAppointmentId(
                appointmentId);
    }

    @Override
    public boolean updatePaymentStatus(int billId,String paymentStatus) 
    {

        if (billId <= 0) 
        {

            throw new InvalidBillException("Bill ID must be greater than zero.");
        }

        if (paymentStatus == null|| paymentStatus.trim().isEmpty()) 
        {

            throw new InvalidBillException("Payment status cannot be empty.");
        }

        if (!paymentStatus.equalsIgnoreCase("PENDING") && !paymentStatus.equalsIgnoreCase("PAID")) 
        {

            throw new InvalidBillException("Payment status must be PENDING or PAID.");
        }

        return billDAO.updatePaymentStatus(billId, paymentStatus.toUpperCase());
    }
}