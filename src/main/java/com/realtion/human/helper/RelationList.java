package com.realtion.human.helper;

public enum RelationList {
    AAI("AAI"),
    PAPAA("BABA"),
    MULGA("MULGA"),
    MULGI("MULGI"),
    BHAVU("BHAVU"),
    BAHIN("BAHIN"),

    AAJI("AAJI"),
    AJOBA("AJOBA"),
    PANJOBA("PANJOBA"),
    PANJI("PANJI"),
    NAT("NAT"),
    NATU("NATU"),

    MAMA("MAMA"),
    MAMI("MAMI"),
    BHACHA("BHACHA"),
    BHACHI("BHACHI"),

    KAKA("KAKA"),
    MAVSHI("MAVSHI"),
    CHULTA("CHULTA"),
    CHULTI("CHULTI"),
    AATYA("AATYA"),
    DIR("DIR"),
    JAVU("JAVU"),
    NANAND("NANAND"),
    MEHVNA("MEHVNA"),
    MEHVNI("MEHVNI"),
    VAHINI("VAHINI"),
    PUTNYA("PUTNYA"),
    PUTNI("PUTNI"),
    JAVAI("JAVAI"),
    SUN("SUN"),
    SADU("SADU"),

    DAJI("DAJI"),
    SASRE("SASRE"),
    SASU("SASU");


    private String value;

    RelationList(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
