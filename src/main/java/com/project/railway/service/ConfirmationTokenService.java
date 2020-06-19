package com.project.railway.service;

import com.project.railway.data.entity.ConfirmationToken;
import com.project.railway.data.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    void deleteConfirmationToken(Long id){
        confirmationTokenRepository.deleteById(id);
    }

    public Optional<ConfirmationToken> findConfirmationTokenByToken(String confirmationToken){
        return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(confirmationToken);
    }
}
