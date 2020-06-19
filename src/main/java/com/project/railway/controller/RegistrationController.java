package com.project.railway.controller;

import com.project.railway.data.entity.Client;
import com.project.railway.data.entity.ConfirmationToken;
import com.project.railway.data.entity.DiscountType;
import com.project.railway.data.repository.ClientRepository;
import com.project.railway.data.repository.DiscountTypeRepository;
import com.project.railway.service.ClientService;
import com.project.railway.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DiscountTypeRepository discountTypeRepository;

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "confirmation", required = false) String confirmation,
                               Model model){
        if(confirmation != null){
            model.addAttribute("confirmation", confirmation);
        }
        return "login";
    }

    @PostMapping("/processingLogin")
    public String processLogin(@AuthenticationPrincipal Client client){
        return "info";
    }

    @GetMapping("/login/failure")
    public String getLoginErrorPage(@RequestParam(name = "error") String errorMessage, Model model){
        errorMessage = "error";
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        List<DiscountType> discountTypes = discountTypeRepository.findAllDiscountTypes();
        model.addAttribute("discount_types", discountTypes);
        model.addAttribute("client", new Client());
        return "register";
    }

    @GetMapping("/register/fail")
    public String getRegisterFailPage(@RequestParam(name = "error") String error,
                                      Model model){
        List<DiscountType> discountTypes = discountTypeRepository.findAllDiscountTypes();
        model.addAttribute("errorMessage", error);
        model.addAttribute("discount_types", discountTypes);
        model.addAttribute("client", new Client());
        return "register";
    }

    @PostMapping("/register")
    public String registerClient(@ModelAttribute Client client){
        System.out.println(client.toString());
        if(clientService.checkIfClientExists(client)){
            return "redirect:/register/fail?error=bademail";
        }else{
            if(clientService.validateUser(client)){
                System.out.println("user will be registered.");
                clientService.signUpUser(client);
            }
            return "success";
        }
    }

    @GetMapping("/register/confirm")
    public String confirmMail(@RequestParam("token") String token){
        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(clientService::confirmUser);

        return "redirect:/login?confirmation=success";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }
}
