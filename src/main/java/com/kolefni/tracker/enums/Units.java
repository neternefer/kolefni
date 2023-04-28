package com.kolefni.tracker.enums;

public enum Units {

        KILOMETERS("km"),
        MILES("mi"),
        KWH("kWh"),
        MWH("mWh");

        private final String abbreviation;

        Units(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() {
            return abbreviation;
        }
}

