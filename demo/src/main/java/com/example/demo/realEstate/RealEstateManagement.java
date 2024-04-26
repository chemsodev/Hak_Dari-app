package com.example.demo.realEstate;

import com.example.demo.user.User;

public class RealEstateManagement {
    public int createRealEstate(RealEstate realEstate,User user) {
        if(user.getRole().getRealEstateManager()){

            return 1;
        }
        return 0;
    }
    public int updateRealEstate(RealEstate realEstate,User user) {
        return 0;
    }

    public int deleteRealEstate(RealEstate realEstate,User user) {
        return 0;
    }
}
