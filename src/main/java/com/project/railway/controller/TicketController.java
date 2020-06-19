package com.project.railway.controller;

import com.project.railway.data.entity.Client;
import com.project.railway.data.entity.Ticket;
import com.project.railway.data.repository.TicketRepository;
import com.project.railway.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Controller
@SessionAttributes("ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @GetMapping("/validation/ticket")
    public String getConfirmationTicket(@RequestParam(name = "id") String id,
                                        Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasConductorRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CONDUCTOR"));
        if(hasConductorRole){
            Optional<Ticket> ticket = ticketRepository.findById(Long.parseLong(id));
            if(ticket.isPresent()){
                System.out.println(ticket);
                model.addAttribute("ticket", ticket.get());
                return "/validation/validate-ticket";
            }else{
                return "/validation/no-ticket";
            }
        }else{
            return "redirect:/search";
        }
    }

    @PostMapping("/validation/validate")
    public String postValidateTicket(@ModelAttribute(name = "ticket") Ticket ticket,
                                     Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasConductorRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CONDUCTOR"));
        if(hasConductorRole){
            System.out.println(java.time.LocalDate.now());
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Calendar calendar = Calendar.getInstance();
            System.out.println(dateFormat.format(calendar.getTime()));
            System.out.println(ticket);
            ticketService.validateTicket(ticket.getId());
            return "redirect:/validation/validate?result=success";
        }else{
            return "redirect:/search";
        }
    }

    @GetMapping("/validation/validate")
    public String getValidateTicketPage(@RequestParam(name = "result") String result,
                                        Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasConductorRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CONDUCTOR"));
        if(hasConductorRole) {
            model.addAttribute("result", result);
            return "/validation/validate";
        }else{
            return "redirect:/search";
        }
    }
}
