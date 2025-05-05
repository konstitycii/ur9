package quru.qa;

public enum User {
    VLAD_KLIMIN("Vlad Klimin", "klimas777@ya.ru", "Penza Mira 77", "Zarechny Zelenay 29A"),
    ANDREY_PRONKIN("Andrey Pronkin", "andrey12v@mail.ru", "Zarechny Lenina 66", "Zarechny Lenina 66"),
    PAVEL_RUDAKOV("Pavel Rudakov", "rudak@gmail.com", "Zarechny Ozerskay 2", "Zarechny Ozerskay 2");

    private final String fullName;
    private final String email;
    private final String currentAddress;
    private final String permanentAddress;

    User(String fullName, String email, String currentAddress, String permanentAddress) {
        this.fullName = fullName;
        this.email = email;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }
}