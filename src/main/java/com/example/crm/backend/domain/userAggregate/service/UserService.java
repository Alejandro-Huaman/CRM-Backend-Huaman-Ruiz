package com.example.crm.backend.domain.userAggregate.service;
import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import com.example.crm.backend.image.ImageModel;
import com.example.crm.backend.resource.User.UpdateRolUserResource;
import com.example.crm.backend.resource.User.UpdateUserResource;
import com.example.crm.shared.exception.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();
    Page<User> getUsers(Pageable pageable);
    User getUserById(Long userId);
    User updateRolUser(Long userId, User request);
    User createUser(User user);
    User updateUser(Long userId, User request);
    User updatePasswordUserCodified(Long userId, User request);
    ResponseEntity<?> deleteUser(Long userId);
    Optional<User> getbyNombreUsuarioOrEmail(String nombreOremail);
    boolean existsByNombreUsuario(String nombreUsuario);
    User getUserByEmail(String email);
    boolean existsByEmail(String nombreUsuario);
    ResponseEntity<Object> updatephoto(Long userId, MultipartFile file) throws IOException;
    ResponseEntity<byte[]> getprofileimage(Long userId) throws Message;
    ImageModel getImageDetails(Long MultimediaId) throws Message;
}
