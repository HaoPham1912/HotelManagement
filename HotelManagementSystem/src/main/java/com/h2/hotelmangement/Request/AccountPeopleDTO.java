package com.h2.hotelmangement.Request;

public class AccountPeopleDTO {
    Integer numberAdult ;
    Integer numberChildren;

    public Integer getNumberOfAdust() {
        return numberAdult;
    }

    public void setNumberOfAdust(Integer numberOfAdust) {
        this.numberAdult = numberOfAdust;
    }

    public Integer getNumberOfChildren() {
        return numberChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberChildren = numberOfChildren;
    }
}
