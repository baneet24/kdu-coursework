package org.example;

class Trader {
    private String firstName;
    private String lastName;
    private String phone;
    private String walletAddress;

    private double profit;

    public Trader(String firstName, String lastName, String phone, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.walletAddress = walletAddress;

    }

    @Override
    public String toString() {
        return "Trader{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public String getWalletAddress() {
        return walletAddress;
    }
    public double getProfit() {
        return profit;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}