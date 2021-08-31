package fr.bilog.emuserefontebackend.services;

import fr.bilog.emuserefontebackend.entities.userProfile.FCMToken;
import fr.bilog.emuserefontebackend.models.FcmIdRequest;

public interface FCMTokenService {
    FCMToken setFcmToken(FcmIdRequest fcmIdRequest);
}
