package fr.bilog.emuserefontebackend.services.impl;

import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.entities.userProfile.FCMToken;
import fr.bilog.emuserefontebackend.models.FcmIdRequest;
import fr.bilog.emuserefontebackend.repositories.AppUserRepository;
import fr.bilog.emuserefontebackend.repositories.FCMTokenRepository;
import fr.bilog.emuserefontebackend.services.FCMTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class FCMTokenServiceImpl implements FCMTokenService {
    private FCMTokenRepository fcmTokenRepository;
    private AppUserRepository appUserRepository;

    @Override
    public FCMToken setFcmToken(FcmIdRequest fcmIdRequest) {
        Optional<FCMToken> fcmTokenTmp = fcmTokenRepository.findByToken(fcmIdRequest.getFcmId());
        if (fcmTokenTmp.isPresent()) {
            Optional<AppUser> appUserOld = appUserRepository.findByFcmTokensContains(fcmTokenTmp.get());
            if (appUserOld.isPresent()) {
                appUserOld.get().getFcmTokens().remove(fcmTokenTmp.get());
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());
            if (appUser.isPresent()) {
                appUser.get().getFcmTokens().add(fcmTokenTmp.get());
                return fcmTokenTmp.get();
            }
            return null;
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());
            if (appUser.isPresent()) {
                FCMToken fcmToken = new FCMToken();
                fcmToken.setToken(fcmIdRequest.getFcmId());
                fcmToken.setDeviceType(fcmIdRequest.getDeviceType());
                fcmToken.setCreationDate(new Date());
                fcmToken = fcmTokenRepository.save(fcmToken);
                appUser.get().getFcmTokens().add(fcmToken);
                return fcmToken;
            }
            return null;
        }
    }
}
