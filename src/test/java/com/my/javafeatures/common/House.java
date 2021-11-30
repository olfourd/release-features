package com.my.javafeatures.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

public class House {

    public static final String DEFAULT_ADDRESS = "address not defined";

    @Getter
    private String address = DEFAULT_ADDRESS;

    public boolean isValidAddress() {
        @AllArgsConstructor
        class AddressSpliterator {
            private final String address;

            public String getCity() {
                String city = StringUtils.substringsBetween(address, "г. ", ",")[0];
                System.out.println(city);
                return city;
            }

            public String getStreet() {
                String street = StringUtils.substringsBetween(address, "ул. ", " ")[0];
                System.out.println(street);
                return street;
            }

            public String getNumber() {
                String number = StringUtils.strip(StringUtils.substring(address, address.length() - 2));
                System.out.println(number);
                return number;
            }
        }

        AddressSpliterator addressSpliterator = new AddressSpliterator(this.address);
        return !StringUtils.isAllBlank(
                addressSpliterator.getCity(),
                addressSpliterator.getStreet(),
                addressSpliterator.getNumber()
        );
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public class AddressDefiner {
        private String city;
        private String street;
        private String number;

        public void defineAddress() {
            address = String.format("г. %s, ул. %s %s", this.city, this.street, this.number);
            System.out.println(address);
        }

        public void forceChangeAddress(String newAddress) {
            House.this.address = newAddress;
        }
    }

    public static class AddressDefinerStatic {
        public String defineAddress(String city, String street, String number) {
            return StringUtils.isAllBlank(city, street, number)
                    ? String.format("One of passed parameter is blank. %s", DEFAULT_ADDRESS)
                    : String.format("г. %s, ул. %s %s", city, street, number);
        }
    }
}
