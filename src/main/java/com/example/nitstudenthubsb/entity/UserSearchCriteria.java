package com.example.nitstudenthubsb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserSearchCriteria {
    private String techStack;
    private String rollNo;
    private String hostel;
    private String languages;
    private String habits;
    private String otherDetails;
}
