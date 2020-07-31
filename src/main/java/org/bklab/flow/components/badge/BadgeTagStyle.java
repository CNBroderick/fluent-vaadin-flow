package org.bklab.flow.components.badge;

public enum BadgeTagStyle {
    GREEN("badge-tag-green"),
    MAGENTA("badge-tag-magenta"),
    ORANGE("badge-tag-orange"),
    RED("badge-tag-red"),
    VOLCANO("badge-tag-volcano"),
    GOLD("badge-tag-gold"),
    LIME("badge-tag-lime"),
    CYAN("badge-tag-cyan"),
    BLUE("badge-tag-blue"),
    GEEKBLUE("badge-tag-geekblue"),
    PURPLE("badge-tag-purple"),
    GREY("badge-tag-grey"),

    BG_GREEN("badge-tag-bg-green"),
    BG_ORANGE("badge-tag-bg-orange"),
    BG_RED("badge-tag-bg-red"),
    BG_MAGENTA("badge-tag-bg-magenta"),
    BG_GOLD("badge-tag-bg-gold"),
    BG_VOLCANO("badge-tag-bg-volcano"),
    BG_LIME("badge-tag-bg-lime"),
    BG_CYAN("badge-tag-bg-cyan"),
    BG_BLUE("badge-tag-bg-blue"),
    BG_GEEKBLUE("badge-tag-bg-geekblue"),
    BG_PURPLE("badge-tag-bg-purple"),
    BG_GREY("badge-tag-bg-grey"),
    ;

    public final String style;

    BadgeTagStyle(String style) {
        this.style = style;
    }
}
