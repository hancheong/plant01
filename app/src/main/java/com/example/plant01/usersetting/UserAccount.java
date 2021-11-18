package com.example.plant01.usersetting;

public class UserAccount {
    private String idToken;
    public String UserNick;
    public String UserPh;
    public String UserGender;
    public String UserImg;
    public String UserBirth;
    public String UserEmail;
    public String UserPassword;
    public String UserPostalCode;
    public String UserAdr;

    public UserAccount(){}
//    public UserAccount(String idToken, String userNick, String userPh, String userGender
//            , String userImg, String userBirth, String userEmail, String userPassword, String userPostalCode, String userAdr) {
//        this.idToken = idToken;
//        UserNick = userNick;
//        UserPh = userPh;
//        UserGender = userGender;
//        UserImg = userImg;
//        UserBirth = userBirth;
//        UserEmail = userEmail;
//        UserPassword = userPassword;
//        UserPostalCode = userPostalCode;
////        UserAdr = userAdr;
//    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getUserNick() {
        return UserNick;
    }

    public void setUserNick(String userNick) {
        UserNick = userNick;
    }

    public String getUserPh() {
        return UserPh;
    }

    public void setUserPh(String userPh) {
        UserPh = userPh;
    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(String userGender) {
        UserGender = userGender;
    }

    public String getUserImg() {
        return UserImg;
    }

    public void setUserImg(String userImg) {
        UserImg = userImg;
    }

    public String getUserBirth() {
        return UserBirth;
    }

    public void setUserBirth(String userBirth) {
        UserBirth = userBirth;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserPostalCode() {
        return UserPostalCode;
    }

    public void setUserPostalCode(String userPostalCode) {
        UserPostalCode = userPostalCode;
    }

    public String getUserAdr() {
        return UserAdr;
    }

    public void setUserAdr(String userAdr) {
        UserAdr = userAdr;
    }
}
