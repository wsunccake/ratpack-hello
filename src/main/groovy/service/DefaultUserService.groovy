package service

import data.User

class DefaultUserService implements UserService {
    final List<User> demoUsers = new ArrayList<User>() {{
        User user1 = new User()
        user1.name = "kitty"
        user1.id = 1
        add(user1)

        User user2 = new User()
        user2.name = "daniel"
        user2.id = 2
        add(user2)
    }}

    @Override
    List<User> list() {
        return demoUsers
    }

    @Override
    void add(User user) {
        demoUsers << user
    }

    @Override
    User show(int id) {
        println "Show ${id}"
        println demoUsers[id]
        return demoUsers[id]
    }
}
