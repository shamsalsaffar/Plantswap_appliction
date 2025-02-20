package com.java24.plantswap.models;

public enum LightRequirement {
    FULL_SUN,          // Direkt solljus hela dagen
    BRIGHT_DIRECT,     // Stark direkt sol under vissa timmar
    BRIGHT_INDIRECT,   // Mycket ljus men inte direkt solljus
    PARTIAL_SHADE,     // Några timmars sol, resten skugga
    LOW_LIGHT
}
