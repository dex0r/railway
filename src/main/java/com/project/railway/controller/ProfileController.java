package com.project.railway.controller;

import com.project.railway.config.SecurityAES;
import com.project.railway.data.entity.Client;
import com.project.railway.data.entity.DiscountType;
import com.project.railway.data.entity.Ticket;
import com.project.railway.data.entity.TicketStatus;
import com.project.railway.data.repository.DiscountTypeRepository;
import com.project.railway.data.repository.TicketRepository;
import com.project.railway.data.repository.TicketStatusRepository;
import com.project.railway.service.ClientService;
import com.project.railway.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
public class ProfileController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketStatusRepository ticketStatusRepository;

    @Autowired
    DiscountTypeRepository discountTypeRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    TicketService ticketService;

    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal Client client) {
        return "redirect:/profile/information";
    }

    @GetMapping("/profile/information")
    public String getProfileInfoPage(Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("client", client);
        return "info";
    }

    @GetMapping("/profile/address")
    public String getProfileAddressPage(Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("client", client);
        return "address";
    }

    @GetMapping("/profile/discount")
    public String getProfileDiscountPage(Model model){
        List<DiscountType> discountTypes = discountTypeRepository.findAllDiscountTypes();
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("discount_types", discountTypes);
        model.addAttribute("client", client);
        return "discount";
    }

    @GetMapping("/profile/history")
    public String getProfileHistoryPage(Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Ticket> activeTickets = ticketService.findTicketByClientAndTicketStatus(client.getId(), "unverified");
        activeTickets.addAll(ticketService.findTicketByClientAndTicketStatus(client.getId(), "verified"));
        //System.out.println(activeTickets);
        model.addAttribute("active_tickets", activeTickets);

        List<Ticket> expiredTickets = ticketRepository.findTicketByClientIdAndTicketStatus(client.getId(), ticketStatusRepository.findTicketStatusByStatus("expired"));
        //System.out.println(expiredTickets);
        model.addAttribute("expired_tickets", expiredTickets);
        return "history";
    }

    @GetMapping("/profile/payment")
    public String getProfilePaymentPage(Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("client", client);
        return "creditcard";
    }

    @GetMapping("/profile/information/update-user-information")
    public String errorUpdatePassword(@RequestParam(name = "error") String errorMessage, Model model){
        errorMessage = "error";
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("client",SecurityContextHolder.getContext().getAuthentication().getPrincipal());


        return "info";
    }

    @PostMapping("/profile/information/update-user-information")
    public String updateClientPassword(@ModelAttribute(name = "currentPassword") String currentPassword,
                                       @ModelAttribute(name = "newPassword") String newPassword,
                                       @ModelAttribute(name = "confirmPassword") String confirmPassword,
                                       Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!new BCryptPasswordEncoder().matches(currentPassword, client.getPassword())){
            return "redirect:/profile/information/update-user-information?error=true";
        }

        if(clientService.validateNewPassword(newPassword, confirmPassword)){
            clientService.updateClientPassword(client, newPassword);
        }
        System.out.println(client.getPassword());
        System.out.println(new BCryptPasswordEncoder().matches(currentPassword, client.getPassword()));
        System.out.println(newPassword);
        System.out.println(confirmPassword);
        model.addAttribute("client",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "info";
    }

    @PostMapping("/profile/information/update-user-phone")
    public String updateClientPhoneNumber(@ModelAttribute(name = "phoneNumber") String phoneNumber,
                                          Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(phoneNumber);

        if (clientService.validateClientPhoneNumber(phoneNumber)) {
            clientService.updateClientPhoneNumber(client, phoneNumber);
        }
        model.addAttribute("client", client);
        return "redirect:/profile/information";
    }

    @PostMapping("/profile/information/update-address")
    public String updateClientAddress(@ModelAttribute(name = "address") String newAddress,
                                      @ModelAttribute(name = "additionalAddress") String newAdditionalAddress,
                                      @ModelAttribute(name = "city") String newCity,
                                      @ModelAttribute(name = "zip") String newZip,
                                      Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(client.toString());
        if(clientService.validateClientAddress(newAddress, newAdditionalAddress, newCity, newZip)){
            clientService.updateClientAddress(client, newAddress, newAdditionalAddress, newCity, newZip);
        }
        model.addAttribute("client", client);
        return "redirect:/profile/address";
    }

    @PostMapping("/profile/information/update-credit-card")
    public String updateClientCreditCard(@ModelAttribute(name = "creditCardName") String newCreditCardName,
                                         @ModelAttribute(name = "creditCardNumber") String newCreditCardNumber,
                                         @ModelAttribute(name = "creditCardExpirationMonth") String newCreditCardExpirationMonth,
                                         @ModelAttribute(name = "creditCardExpirationYear") String newCreditCardExpirationYear,
                                         @ModelAttribute(name = "creditCardCVV") String newCreditCardCVV,
                                         Model model){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(clientService.validateCreditCard(newCreditCardName, newCreditCardNumber, newCreditCardExpirationMonth, newCreditCardExpirationYear, newCreditCardCVV)){
            clientService.updateClientCreditCard(client, newCreditCardName, newCreditCardNumber, newCreditCardExpirationMonth, newCreditCardExpirationYear, newCreditCardCVV);
        }

        System.out.println(client.getFirstName() + client.getLastName());
        System.out.println(client.getCreditCardCVV());
        System.out.println(SecurityAES.decrypt(client.getCreditCardCVV(), client.getId()+client.getFirstName()+client.getLastName()+client.getEmail()));
        model.addAttribute("client", client);
        return "redirect:/profile/payment";
    }

    @PostMapping("/profile/information/update-discount")
    public String updateClientDiscountDocument(@ModelAttribute(name = "documentNumber") String documentNumber,
                                               @ModelAttribute(name = "discountType") String discountTypeId,
                                               Model model){

        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(clientService.validateClientDiscount(Long.parseLong(discountTypeId), documentNumber)){
            clientService.updateClientDiscount(client, Long.parseLong(discountTypeId), documentNumber);
        }

        model.addAttribute("discount_types", discountTypeRepository.findAllDiscountTypes());
        model.addAttribute("client", client);
        return "redirect:/profile/discount";
    }

    @PostMapping("/profile/history/cancel-ticket")
    public String cancelTicket(@ModelAttribute(name = "ticket") String ticketid){

        System.out.println(ticketid);
        return "redirect:/profile/information";
    }
}
