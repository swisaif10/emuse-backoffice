package fr.bilog.emuserefontebackend.services;

import fr.bilog.emuserefontebackend.entities.article.Highlight;
import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.models.*;

import java.util.List;

public interface AppUserService {
    List<AppUser> getAllUsers();

    AppUser updateAppUser(Long id, UserUpdateRequest userUpdateRequest);

    PageDTO getFilteredUsers(int page, String sortBy, FiltreUserRequest filtreUserRequest);

    PageDTO getArticles(int page, String sortBy);

    boolean updatActivitesPrefs(ActPrefUpdateRequest actPrefUpdateRequest);

    boolean updatePhysionomy(PhysionomyUpdateRequest physionomyUpdateRequest);

    boolean updatePassword(PasswordUpdateRequest passwordUpdateRequest);

    Highlight pushHighlight(PushHighlightRequest pushHighlightRequest);
}
