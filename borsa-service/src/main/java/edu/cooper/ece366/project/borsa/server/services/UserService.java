package edu.cooper.ece366.project.borsa.server.services;

import edu.cooper.ece366.project.borsa.server.RestApiServer;
import edu.cooper.ece366.project.borsa.server.model.User;
import edu.cooper.ece366.project.borsa.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUser() {
        List<User> listOfAllUsers = userRepository.findAll();
        LOGGER.info(String.format("all users = %s", listOfAllUsers.toString()));
        return listOfAllUsers;
    }

}
