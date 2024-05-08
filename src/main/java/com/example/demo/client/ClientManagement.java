package com.example.demo.client;

import com.example.demo.user.User;

public class ClientManagement {

    public int createClient(Client client, User user) {
        if (user.getRole().getClientManager()){

            return 1;
        }
        return 0;
    }

}
