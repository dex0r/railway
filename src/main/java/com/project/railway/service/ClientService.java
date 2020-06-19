package com.project.railway.service;

import com.project.railway.config.SecurityAES;
import com.project.railway.data.entity.Client;
import com.project.railway.data.entity.ConfirmationToken;
import com.project.railway.data.entity.DiscountType;
import com.project.railway.data.entity.UserRole;
import com.project.railway.data.repository.ClientRepository;
import com.project.railway.data.repository.DiscountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DiscountTypeRepository discountTypeRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailService emailService;

    private Pattern firstNamePattern = Pattern.compile("^([a-zA-Zа-яА-Я])+$");
    private Pattern lastNamePattern = Pattern.compile("^([a-zA-Zа-яА-Я])+$");
    private Pattern emailPattern = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    private Pattern phoneNumberPattern = Pattern.compile("^(?:\\+)(359)[ ]?(87|88|89|98)[0-9]{7}$");
    private Pattern passwordPattern = Pattern.compile("^(?=.*?[0-9]).{8,}$");
    private Pattern addressPattern = Pattern.compile("^[a-zA-Z0-9а-яА-Я- ,.]*$");
    private Pattern cityPattern = Pattern.compile("^[a-zA-Z0-9а-яА-Я]*$");
    private Pattern zipPattern = Pattern.compile("^[1-9][0-9]{3}$");
    private Pattern creditCardNamePattern = Pattern.compile("^[a-zA-Zа-яА-Я]+( )[a-zA-Zа-яА-Я]+$");
    private Pattern creditCardNumberVisaPattern = Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3})?)$");
    private Pattern creditCardNumberMasterCardPattern = Pattern.compile("^(?:5[1-5][0-9]{14})$");
    private Pattern creditCardExpirationMonthSDPattern = Pattern.compile("^[1-9]$");
    private Pattern creditCardExpirationMonthDDPattern = Pattern.compile("^[1][0-2]$");
    private Pattern creditCardExpirationYearPattern = Pattern.compile("^[2-9][0-9]$");
    private Pattern creditCardCVVPattern = Pattern.compile("^[0-9]{3}$");
    private Pattern documentNumberPattern = Pattern.compile("^[0-9]+");

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<Client> optionalUser = clientRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

    public void signUpUser(Client client){

        final String encryptedPassword = new BCryptPasswordEncoder().encode(client.getPassword());

        if(client.getCreditCardNumber().length() != 0){
            client.setCreditCardCVV(SecurityAES.encrypt(client.getCreditCardCVV(), client.getId()+client.getFirstName()+client.getLastName()+client.getEmail()));
        }

        client.setPassword(encryptedPassword);

        client.setUserRole(UserRole.USER);

        clientRepository.save(client);

        final ConfirmationToken confirmationToken = new ConfirmationToken(client);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        sendConfirmationEmail(client.getEmail(), confirmationToken.getConfirmationToken());
    }

    public void confirmUser(ConfirmationToken confirmationToken){
        final Client client = confirmationToken.getClient();

        client.setEnabled(true);

        clientRepository.save(client);

        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
    }

    private void sendConfirmationEmail(String userMail, String token){
        final SimpleMailMessage mailMessage = new SimpleMailMessage();

        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);

        mailMessage.setTo(userMail);
        mailMessage.setFrom("407c263f6c5f75");

        switch(locale.toString()){
            case "bg":
                mailMessage.setSubject("БДЖ Резервация онлайн - потвърдете профила си!");
                mailMessage.setText("Благодарим Ви за регистрацията в БДЖ резервация онлайн! За да завършите регистрацията си моля кликнете на линка: "
                        + "http://localhost:8080/register/confirm?token="
                        + token);
                break;
            case "en":
                mailMessage.setSubject("BDZ Reservation online - confirm your profile!");
                mailMessage.setText("Thank you for registering in the BDZ reservation online! To finish your account registraion please click on the following link: "
                        + "http://localhost:8080/register/confirm?token=" + token);
                break;
        }

        emailService.sendEmail(mailMessage);
    }

    public boolean validateClientAddress(final String address, final String additionalAddress, final String city, final String zip){
        Matcher addressMatcher = addressPattern.matcher(address);

        Matcher additionalAddressMatcher = addressPattern.matcher(additionalAddress);

        Matcher cityMatcher = cityPattern.matcher(city);

        Matcher zipMatcher = zipPattern.matcher(zip);

        if(addressMatcher.matches()
                && additionalAddressMatcher.matches()
                && cityMatcher.matches()
                && zipMatcher.matches()) {
            System.out.println("address validation passed!");
            return true;
        }else{
            return false;
        }
    }

    public void updateClientAddress(Client client,
                                    final String address,
                                    final String additionalAddress,
                                    final String city,
                                    final String zip){

        client.setAddressDetails(address, additionalAddress, city, zip);

        clientRepository.save(client);
    }

    public boolean validateCreditCard(final String creditCardName,
                                      final String creditCardNumber,
                                      final String creditCardExpirationMonth,
                                      final String creditCardExpirationYear,
                                      final String creditCardCVV){
        Matcher creditCardNameMatcher = creditCardNamePattern.matcher(creditCardName);

        Matcher creditCardNumberVisaMatcher = creditCardNumberVisaPattern.matcher(creditCardNumber);

        Matcher creditCardNumberMasterCardMatcher = creditCardNumberMasterCardPattern.matcher(creditCardNumber);

        Matcher creditCardExpirationMonthSDMatcher = creditCardExpirationMonthSDPattern.matcher(creditCardExpirationMonth);

        Matcher creditCardExpirationMonthDDMatcher = creditCardExpirationMonthDDPattern.matcher(creditCardExpirationMonth);

        Matcher creditCardExpirationYearMatcher = creditCardExpirationYearPattern.matcher(creditCardExpirationYear);

        Matcher creditCardCVVMatcher = creditCardCVVPattern.matcher(creditCardCVV);

        if(creditCardNameMatcher.matches()
                && (creditCardNumberVisaMatcher.matches() || creditCardNumberMasterCardMatcher.matches())
                && (creditCardExpirationMonthSDMatcher.matches() || creditCardExpirationMonthDDMatcher.matches())
                && creditCardExpirationYearMatcher.matches()
                && creditCardCVVMatcher.matches()) {
            System.out.println("credit card validation passed!");
            return true;
        }else{
            return false;
        }
    }

    public void updateClientCreditCard(Client client,
                                       final String creditCardName,
                                       final String creditCardNumber,
                                       final String creditCardExpirationMonth,
                                       final String creditCardExpirationYear,
                                       String creditCardCVV){

        creditCardCVV = SecurityAES.encrypt(creditCardCVV, client.getId()+client.getFirstName()+client.getLastName()+client.getEmail());

        client.setCreditCardDetails(creditCardName, creditCardNumber, creditCardExpirationMonth, creditCardExpirationYear, creditCardCVV);

        clientRepository.save(client);
    }

    public boolean validateClientDiscount(Long discountTypeID, String documentNumber){
        Matcher documentNumberMatcher = documentNumberPattern.matcher(documentNumber);

        if(discountTypeID != 1) {
            if (documentNumberMatcher.matches()) {
                System.out.println("document number successful");
                return true;
            } else {
                return false;
            }
        }else{
            if(documentNumber.length() == 0){
                System.out.println("document number successful");
                return true;
            }else{
                return false;
            }
        }
    }

    public void updateClientDiscount(Client client,
                                     final Long discountTypeId,
                                     final String discountNumber){

        DiscountType discountType = discountTypeRepository.findDiscountTypeById(discountTypeId);

        client.setDiscountDetails(discountType, discountNumber);

        clientRepository.save(client);
    }

    public boolean validateNewPassword(final String password, final String confirmPassword){

        Matcher newPasswordMatcher = passwordPattern.matcher(password);
        Matcher confirmPasswordMatcher = passwordPattern.matcher(confirmPassword);

        if((newPasswordMatcher.matches() && confirmPasswordMatcher.matches()) && (password.equals(confirmPassword))){
            System.out.println("password validation successful");
            return true;
        }else{
            return false;
        }
    }

    public void updateClientPassword(Client client, final String password){

        final String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        client.setPassword(encryptedPassword);

        clientRepository.save(client);
    }

    public boolean validateClientPhoneNumber(final String phoneNumber){
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);

        if(phoneNumberMatcher.matches()){
            System.out.println("phone number validation successful");
            return true;
        }else{
            return false;
        }
    }

    public void updateClientPhoneNumber(Client client, final String phoneNumber){

        client.setPhoneNumber(phoneNumber);

        clientRepository.save(client);
    }

    public boolean checkIfClientExists(final Client client){
        Optional<Client> foundClient = clientRepository.findByEmailAndUserRole(client.getEmail(), UserRole.USER);
        return foundClient.isPresent();
    }

    public boolean validateUser(final Client client){

        Matcher firstNameMatcher = firstNamePattern.matcher(client.getFirstName());
        Matcher lastNameMatcher = lastNamePattern.matcher(client.getLastName());
        Matcher emailMatcher = emailPattern.matcher(client.getEmail());
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(client.getPhoneNumber());
        Matcher passwordMatcher = passwordPattern.matcher(client.getPassword());
        Matcher addressMatcher = addressPattern.matcher(client.getAddress());
        Matcher additionalAddressMatcher = addressPattern.matcher(client.getAdditionalAddress());
        Matcher cityMatcher = cityPattern.matcher(client.getCity());
        Matcher zipMatcher = zipPattern.matcher(client.getZip());
        Matcher creditCardNameMatcher = creditCardNamePattern.matcher(client.getCreditCardName());
        Matcher creditCardNumberVisaMatcher = creditCardNumberVisaPattern.matcher(client.getCreditCardNumber());
        Matcher creditCardNumberMasterCardMatcher = creditCardNumberMasterCardPattern.matcher(client.getCreditCardNumber());
        Matcher creditCardExpirationMonthSDMatcher = creditCardExpirationMonthSDPattern.matcher(client.getCreditCardExpirationMonth());
        Matcher creditCardExpirationMonthDDMatcher = creditCardExpirationMonthDDPattern.matcher(client.getCreditCardExpirationMonth());
        Matcher creditCardExpirationYearMatcher = creditCardExpirationYearPattern.matcher(client.getCreditCardExpirationYear());
        Matcher creditCardCVVMatcher = creditCardCVVPattern.matcher(client.getCreditCardCVV());
        Matcher documentNumberMatcher = documentNumberPattern.matcher(client.getDocumentNumber());

        if(firstNameMatcher.matches()
        && lastNameMatcher.matches()
        && emailMatcher.matches()
        && phoneNumberMatcher.matches()
        && passwordMatcher.matches()){
            if(client.getAddress().length() != 0
            || client.getAdditionalAddress().length() != 0
            || client.getCity().length() != 0
            || client.getZip().length() != 0){
                if(addressMatcher.matches()
                && additionalAddressMatcher.matches()
                && cityMatcher.matches()
                && zipMatcher.matches()){
                    if(client.getCreditCardName().length() != 0
                    || client.getCreditCardNumber().length() != 0
                    || client.getCreditCardExpirationMonth().length() != 0
                    || client.getCreditCardExpirationYear().length() != 0
                    || client.getCreditCardCVV().length() != 0){
                        if(creditCardNameMatcher.matches()
                        && (creditCardNumberVisaMatcher.matches() || creditCardNumberMasterCardMatcher.matches())
                        && (creditCardExpirationMonthSDMatcher.matches() || creditCardExpirationMonthDDMatcher.matches())
                        && creditCardExpirationYearMatcher.matches()
                        && creditCardCVVMatcher.matches()){
                            if(client.getDiscountType().getId() != 1){
                                if(documentNumberMatcher.matches()){
                                    System.out.println("user validation successful");
                                    return true;
                                }else{
                                    return false;
                                }
                            }else{
                                System.out.println("user validation successful");
                                return true;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        System.out.println("user validation successful");
                        return true;
                    }
                }else{
                    return false;
                }
            }else{
                System.out.println("user validation successful");
                return true;
            }
        }else{
            return false;
        }
    }
}
