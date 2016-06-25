package com.aktheknight.solarvillageplus.util;

import com.aktheknight.solarvillageplus.SolarVillagePlus;
import net.minecraft.client.resources.I18n;

public enum LanguageHelper {
    MESSAGE("message"),
    LABEL("label"),
    BLOCK("tile"),
    ITEMGROUP("itemGroup"),
    ITEM("item"),
    DESCRIPTION("description"),
    JEI("jei"),
    NONE(""),;

    private String name;

    LanguageHelper(String name) {
        this.name = name;
    }

    public String translateMessage(String message) {
        if (this == NONE)
            return I18n.format(message);
        return I18n.format(String.format("%s.%s.%s", this.name, SolarVillagePlus.MODID, message));
    }

}