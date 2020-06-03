package com.team.app.backend.persistance.model;

import java.sql.Date;

public class UserInvite {
    private Long id;
    private boolean activated;
    private String inviteText;
    private Date inviteDate;
    private Long userIdFrom;
    private Long userIdTo;
    private String usernameFrom;


    public UserInvite() {

    }

    public UserInvite(Long id, boolean activated, String inviteText, Date inviteDate, Long userIdFrom, Long userIdTo, String usernameFrom) {
        this.id = id;
        this.activated = activated;
        this.inviteText = inviteText;
        this.inviteDate = inviteDate;
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
        this.usernameFrom = usernameFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getInviteText() {
        return inviteText;
    }

    public void setInviteText(String inviteText) {
        this.inviteText = inviteText;
    }

    public Date getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(Date inviteDate) {
        this.inviteDate = inviteDate;
    }

    public Long getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(Long userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public Long getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(Long userIdTo) {
        this.userIdTo = userIdTo;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }
}
