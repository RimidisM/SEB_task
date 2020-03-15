package iban_valid;

/**
 *
 * @author rimid
 */
public class IbanData {

    //IBAN Countries codes
    private final String[] COUNTRY_NAME = {
        "AL",
        "DZ",
        "AD",
        "AO",
        "AT",
        "AZ",
        "BH",
        "BY",
        "BE",
        "BJ",
        "BA",
        "BR",
        "VG",
        "BG",
        "BF",
        "BI",
        "CM",
        "CV",
        "FR",
        "CG",
        "CR",
        "HR",
        "CY",
        "CZ",
        "DK",
        "DO",
        "EG",
        "EE",
        "FO",
        "FI",
        "FR",
        "FR",
        "FR",
        "GA",
        "GE",
        "DE",
        "GI",
        "GR",
        "GL",
        "FR",
        "GT",
        "GG",
        "HU",
        "IS",
        "IR",
        "IQ",
        "IE",
        "IM",
        "IL",
        "IT",
        "CI",
        "JE",
        "JO",
        "KZ",
        "XK",
        "KW",
        "LV",
        "LB",
        "LI",
        "LT",
        "LU",
        "MK",
        "MG",
        "ML",
        "MT",
        "FR",
        "MR",
        "MU",
        "MD",
        "MC",
        "ME",
        "MZ",
        "NL",
        "FR",
        "NO",
        "PK",
        "PS",
        "PL",
        "PT",
        "QA",
        "FR",
        "RO",
        "LC",
        "FR",
        "SM",
        "ST",
        "SA",
        "SN",
        "RS",
        "SC",
        "SK",
        "SI",
        "ES",
        "SE",
        "CH",
        "TL",
        "TN",
        "TR",
        "UA",
        "AE",
        "GB",
        "VA",
        "FR"
    };

//IBAN lengths
    private final Integer[] IBAN_LENGTH = {
        28,
        24,
        24,
        25,
        20,
        28,
        22,
        28,
        16,
        28,
        20,
        29,
        24,
        22,
        27,
        16,
        27,
        25,
        27,
        27,
        21,
        21,
        28,
        24,
        18,
        28,
        27,
        20,
        18,
        18,
        27,
        27,
        27,
        27,
        22,
        22,
        23,
        27,
        18,
        27,
        28,
        22,
        28,
        26,
        26,
        23,
        22,
        22,
        23,
        27,
        28,
        22,
        30,
        20,
        20,
        30,
        21,
        28,
        21,
        20,
        20,
        19,
        27,
        28,
        31,
        27,
        27,
        30,
        24,
        27,
        22,
        25,
        18,
        27,
        15,
        24,
        29,
        28,
        25,
        29,
        27,
        24,
        32,
        27,
        27,
        25,
        24,
        28,
        22,
        31,
        24,
        19,
        24,
        24,
        21,
        23,
        24,
        26,
        29,
        23,
        22,
        22,
        27
    };

    public String[] getCOUNTRY_NAME() {
        return COUNTRY_NAME;
    }

    public Integer[] getIBAN_LENGTH() {
        return IBAN_LENGTH;
    }
}
