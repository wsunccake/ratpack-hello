package service

import data.User

interface UserService {
    List<User> list()

    void add(User user)

    User show (int id)
}
