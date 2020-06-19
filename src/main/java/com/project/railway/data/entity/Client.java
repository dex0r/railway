package com.project.railway.data.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientID")
    private Long id;

    @Column(name = "Client_FirstName")
    private String firstName;

    @Column(name = "Client_LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Builder.Default
    @Column(name = "Address")
    private String address = "";

    @Builder.Default
    @Column(name = "AdditionalAddress")
    private String additionalAddress = "";

    @Builder.Default
    @Column(name = "City")
    private String city = "";

    @Builder.Default
    @Column(name = "Zip")
    private String zip = "";

    @Builder.Default
    @Column(name = "Credit_Card_Name")
    private String creditCardName = "";

    @Builder.Default
    @Column(name = "Credit_Card_Number")
    private String creditCardNumber = "";

    @Builder.Default
    @Column(name = "Credit_Card_Expiration_Month")
    private String creditCardExpirationMonth = "";

    @Builder.Default
    @Column(name = "Credit_Card_Expiration_Year")
    private String creditCardExpirationYear = "";

    @Builder.Default
    @Column(name = "Credit_Card_CVV")
    private String creditCardCVV = "";

    private UserRole userRole = UserRole.USER;

    @Builder.Default
    private boolean locked = false;

    @Builder.Default
    private boolean enabled = false;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DiscountType_ID")
    private DiscountType discountType;

    @Builder.Default
    private String documentNumber = "";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    public Client(){
        super();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(final String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardExpirationMonth() {
        return creditCardExpirationMonth;
    }

    public void setCreditCardExpirationMonth(String creditCardExpirationMonth) {
        this.creditCardExpirationMonth = creditCardExpirationMonth;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getCreditCardExpirationYear() {
        return creditCardExpirationYear;
    }

    public void setCreditCardExpirationYear(String creditCardExpirationYear) {
        this.creditCardExpirationYear = creditCardExpirationYear;
    }

    public String getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setCreditCardCVV(String creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setAddressDetails(final String address,
                                  final String additionalAddress,
                                  final String city,
                                  final String zip){

        this.address = address;
        this.additionalAddress = additionalAddress;
        this.city = city;
        this.zip = zip;
    }

    public void setCreditCardDetails(final String creditCardName,
                                     final String creditCardNumber,
                                     final String creditCardExpirationMonth,
                                     final String creditCardExpirationYear,
                                     final String creditCardCVV){

        this.creditCardName = creditCardName;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpirationMonth = creditCardExpirationMonth;
        this.creditCardExpirationYear = creditCardExpirationYear;
        this.creditCardCVV = creditCardCVV;
    }

    public void setDiscountDetails(final DiscountType discountType,
                                   final String documentNumber){

        this.discountType = discountType;
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", FirstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", additionalAddress='" + additionalAddress + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", creditCardName='" + creditCardName + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardExpirationMonth='" + creditCardExpirationMonth + '\'' +
                ", creditCardExpirationYear='" + creditCardExpirationYear + '\'' +
                ", creditCardCVV='" + creditCardCVV + '\'' +
                ", userRole=" + userRole +
                ", locked=" + locked +
                ", enabled=" + enabled +
                ", discountType=" + discountType +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}
