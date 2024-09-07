public class Customer extends Clients{

    public Customer() {
    }
    public Customer(int clientID, String account, String password,String phoneNumber, String emailAddress) {
        super( clientID,account, password, phoneNumber, emailAddress);
    }
}
