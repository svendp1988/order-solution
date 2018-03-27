package com.switchfully.order.api.customers;

import com.switchfully.order.ControllerIntegrationTest;
import com.switchfully.order.api.customers.addresses.AddressDto;
import com.switchfully.order.api.customers.emails.EmailDto;
import com.switchfully.order.api.customers.phonenumbers.PhoneNumberDto;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerControllerIntegrationTest extends ControllerIntegrationTest {

    @Test
    public void createCustomer() {
        CustomerDto customerToCreate = new CustomerDto()
                .withFirstname("Bruce")
                .withLastname("Wayne")
                .withEmail(new EmailDto()
                        .withLocalPart("brucy")
                        .withDomain("bat.net")
                        .withComplete("brucy@bat.net"))
                .withPhoneNumber(new PhoneNumberDto()
                        .withNumber("485212121")
                        .withCountryCallingCode("+32"))
                .withAddress(new AddressDto()
                        .withStreetName("Secretstreet")
                        .withHouseNumber("841")
                        .withPostalCode("1238")
                        .withCountry("GothamCountry"));

        CustomerDto createdCustomer = new TestRestTemplate()
                .postForObject(format("http://localhost:%s/%s", getPort(), CustomerController.RESOURCE_NAME), customerToCreate, CustomerDto.class);

        assertCustomerIsEqualIgnoringId(customerToCreate, createdCustomer);
    }

    private void assertCustomerIsEqualIgnoringId(CustomerDto customerToCreate, CustomerDto createdCustomer) {
        assertThat(createdCustomer.getId()).isNotNull().isNotEmpty();
        assertThat(createdCustomer.getAddress()).isEqualToComparingFieldByField(customerToCreate.getAddress());
        assertThat(createdCustomer.getPhoneNumber()).isEqualToComparingFieldByField(customerToCreate.getPhoneNumber());
        assertThat(createdCustomer.getEmail()).isEqualToComparingFieldByField(customerToCreate.getEmail());
        assertThat(createdCustomer.getFirstname()).isEqualTo(customerToCreate.getFirstname());
        assertThat(createdCustomer.getLastname()).isEqualTo(customerToCreate.getLastname());
    }

}