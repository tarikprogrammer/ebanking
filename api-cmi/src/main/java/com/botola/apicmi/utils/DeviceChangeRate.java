package com.botola.apicmi.utils;

import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.TemporaryCrad;

import java.util.Objects;

public class DeviceChangeRate {

    public static Double ConvertAccount(Account Sender, Account Receiver, double amount) {
        //rates with MAD
        double MAD=1;
        double EUR=0.1;

        double USD=0.1;
        double BTC=0.000001;
        double ETH=0.000027;
        double LTC=0.0006666;


        if (Objects.equals(Sender.getDevice(), Receiver.getDevice())) {
            return amount; // No conversion needed
        }

        switch (Sender.getDevice()) {
            case "MAD":
                switch (Receiver.getDevice()) {
                    case "EUR":
                        return amount * EUR;
                    case "USD":
                        return amount * USD;
                    case "BTC":
                        return amount * BTC;
                    case "ETH":
                        return amount * ETH;
                    case "LTC":
                        return amount * LTC;
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "EUR":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / EUR;
                    case "USD":
                        return (amount / EUR) * USD; // EUR to MAD, then MAD to USD
                    case "BTC":
                        return (amount / EUR) * BTC; // EUR to MAD, then MAD to BTC
                    case "ETH":
                        return (amount / EUR) * ETH; // EUR to MAD, then MAD to ETH
                    case "LTC":
                        return (amount / EUR) * LTC; // EUR to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "USD":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / USD;
                    case "EUR":
                        return (amount / USD) * EUR; // USD to MAD, then MAD to EUR
                    case "BTC":
                        return (amount / USD) * BTC; // USD to MAD, then MAD to BTC
                    case "ETH":
                        return (amount / USD) * ETH; // USD to MAD, then MAD to ETH
                    case "LTC":
                        return (amount / USD) * LTC; // USD to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "BTC":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / BTC;
                    case "EUR":
                        return (amount / BTC) * EUR; // BTC to MAD, then MAD to EUR
                    case "USD":
                        return (amount / BTC) * USD; // BTC to MAD, then MAD to USD
                    case "ETH":
                        return (amount / BTC) * ETH; // BTC to MAD, then MAD to ETH
                    case "LTC":
                        return (amount / BTC) * LTC; // BTC to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "ETH":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / ETH;
                    case "EUR":
                        return (amount / ETH) * EUR; // ETH to MAD, then MAD to EUR
                    case "USD":
                        return (amount / ETH) * USD; // ETH to MAD, then MAD to USD
                    case "BTC":
                        return (amount / ETH) * BTC; // ETH to MAD, then MAD to BTC
                    case "LTC":
                        return (amount / ETH) * LTC; // ETH to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "LTC":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / LTC;
                    case "EUR":
                        return (amount / LTC) * EUR; // LTC to MAD, then MAD to EUR
                    case "USD":
                        return (amount / LTC) * USD; // LTC to MAD, then MAD to USD
                    case "BTC":
                        return (amount / LTC) * BTC; // LTC to MAD, then MAD to BTC
                    case "ETH":
                        return (amount / LTC) * ETH; // LTC to MAD, then MAD to ETH
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            default:
                throw new IllegalArgumentException("Unsupported currency: " + Sender.getDevice());
        }
    }

    public static Double ConvertTemp(TemporaryCrad Sender, Account Receiver, double amount) {
        //rates with MAD
        double MAD=1;
        double EUR=0.1;

        double USD=0.1;
        double BTC=0.01;
        double ETH=0.02;
        double LTC=0.003;


        if (Objects.equals(Sender.getChoixCompte(), Receiver.getDevice())) {
            return amount; // No conversion needed
        }

        switch (Sender.getChoixCompte()) {
            case "MAD":
                switch (Receiver.getDevice()) {
                    case "EUR":
                        return amount * EUR;
                    case "USD":
                        return amount * USD;
                    case "BTC":
                        return amount * BTC;
                    case "ETH":
                        return amount * ETH;
                    case "LTC":
                        return amount * LTC;
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "EUR":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / EUR;
                    case "USD":
                        return (amount / EUR) * USD; // EUR to MAD, then MAD to USD
                    case "BTC":
                        return (amount / EUR) * BTC; // EUR to MAD, then MAD to BTC
                    case "ETH":
                        return (amount / EUR) * ETH; // EUR to MAD, then MAD to ETH
                    case "LTC":
                        return (amount / EUR) * LTC; // EUR to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "USD":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / USD;
                    case "EUR":
                        return (amount / USD) * EUR; // USD to MAD, then MAD to EUR
                    case "BTC":
                        return (amount / USD) * BTC; // USD to MAD, then MAD to BTC
                    case "ETH":
                        return (amount / USD) * ETH; // USD to MAD, then MAD to ETH
                    case "LTC":
                        return (amount / USD) * LTC; // USD to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "BTC":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / BTC;
                    case "EUR":
                        return (amount / BTC) * EUR; // BTC to MAD, then MAD to EUR
                    case "USD":
                        return (amount / BTC) * USD; // BTC to MAD, then MAD to USD
                    case "ETH":
                        return (amount / BTC) * ETH; // BTC to MAD, then MAD to ETH
                    case "LTC":
                        return (amount / BTC) * LTC; // BTC to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "ETH":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / ETH;
                    case "EUR":
                        return (amount / ETH) * EUR; // ETH to MAD, then MAD to EUR
                    case "USD":
                        return (amount / ETH) * USD; // ETH to MAD, then MAD to USD
                    case "BTC":
                        return (amount / ETH) * BTC; // ETH to MAD, then MAD to BTC
                    case "LTC":
                        return (amount / ETH) * LTC; // ETH to MAD, then MAD to LTC
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            case "LTC":
                switch (Receiver.getDevice()) {
                    case "MAD":
                        return amount / LTC;
                    case "EUR":
                        return (amount / LTC) * EUR; // LTC to MAD, then MAD to EUR
                    case "USD":
                        return (amount / LTC) * USD; // LTC to MAD, then MAD to USD
                    case "BTC":
                        return (amount / LTC) * BTC; // LTC to MAD, then MAD to BTC
                    case "ETH":
                        return (amount / LTC) * ETH; // LTC to MAD, then MAD to ETH
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + Receiver.getDevice());
                }

            default:
                throw new IllegalArgumentException("Unsupported currency: " + Sender.getChoixCompte());
        }
    }
}
