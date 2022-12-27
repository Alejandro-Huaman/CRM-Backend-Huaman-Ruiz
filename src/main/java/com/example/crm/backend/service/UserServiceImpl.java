package com.example.crm.backend.service;

import com.example.crm.backend.domain.activityAggregate.service.TaskService;
import com.example.crm.backend.domain.salesAggregate.persistence.SalesRepository;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.entity.User;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import com.example.crm.backend.domain.userAggregate.persistence.UserRepository;
import com.example.crm.backend.domain.userAggregate.service.RolService;
import com.example.crm.backend.domain.userAggregate.service.UserService;
import com.example.crm.backend.image.ImageModel;
import com.example.crm.backend.image.ImageUtility;
import com.example.crm.backend.resource.User.UpdateRolUserResource;
import com.example.crm.backend.resource.User.UpdateUserResource;
import com.example.crm.shared.exception.Message;
import com.example.crm.shared.exception.ResourceNotFoundException;
import com.example.crm.shared.exception.ResourcePerzonalized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String ENTITY = "User";

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RolService rolService;
    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserServiceImpl() {

    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public Optional<User> getbyNombreUsuarioOrEmail(String nombreOremail) {
        return userRepository.findByUsernameOrEmail(nombreOremail,nombreOremail);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return userRepository.existsByUsername(nombreUsuario);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public ResponseEntity<Object> updatephoto(Long userId, MultipartFile file) throws IOException {
        Optional<User> value = userRepository.findById(userId);
        if (value.isPresent()){

            try{
                value.get().setContent(ImageUtility.compressImage(file.getBytes()));
                value.get().setImageprofiletype(file.getContentType());
                userRepository.save(value.get());
                return ResponseEntity.ok().build();
            }catch (IOException e) {
                logger.info("context", e);
                return null;
            }

        }

        return null;
    }

    @Override
    public ResponseEntity<byte[]> getprofileimage(Long userId) throws Message {
        Optional<User> db = userRepository.findById(userId);
        if(db.isPresent()) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(db.get().getImageprofiletype()))
                    .body(ImageUtility.decompressImage(db.get().getContent()));
        }
        throw new Message("Error");
    }

    @Override
    public ImageModel getImageDetails(Long MultimediaId) throws Message {
        Optional<User> db = userRepository.findById(MultimediaId);
        if(db.isPresent()){
            ImageModel imageModel= new ImageModel(db.get().getId(),db.get().getImageprofiletype(),ImageUtility.decompressImage(db.get().getContent()));
            return imageModel;
        }

        throw new Message("Error");
    }


    @Override
    public User updateRolUser(Long userId, User request) {
        return userRepository.findById(userId).map(post->{
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.findByName(request.getRolName()).get());
            post.setRoles(roles);
            post.setRolName(request.getRolName());
            userRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail()))
            throw  new ResourcePerzonalized("Ya existe este email");

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User request) {
        return userRepository.findById(userId).map(post->{
            post.setName(request.getName());
            post.setLastname(request.getLastname());
            post.setEmail(request.getEmail());
            post.setUsername(request.getUsername());
            userRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
    @Override
    public User updatePasswordUserCodified(Long userId, User request) {
        return userRepository.findById(userId).map(post->{
            post.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepository.findById(userId).map(post -> {
            userRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
