package exception;

public class InvalidAppointmentException extends RuntimeException
       {

    public class InvalidBillException {

	}

	public InvalidAppointmentException(String message)
    {
        super(message);
    }
}