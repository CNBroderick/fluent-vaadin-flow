/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.LumoStyles
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.lumo;

public class LumoStyles {

    public static class BorderRadius {
        public static final String S = "border-radius-s";
        public static final String M = "border-radius-m";
        public static final String L = "border-radius-l";
        public static final String _50 = "border-radius-50";
    }

    public static class Color {
        public static final String BASE_COLOR = "var(--lumo-base-color)";

        public static class Primary {
            public static final String _10 = "var(--lumo-primary-color-10pct)";
            public static final String _50 = "var(--lumo-primary-color-50pct)";
            public static final String _100 = "var(--lumo-primary-color)";
        }

        public static class Error {
            public static final String _10 = "var(--lumo-error-color-10pct)";
            public static final String _50 = "var(--lumo-error-color-50pct)";
            public static final String _100 = "var(--lumo-error-color)";
        }

        public static class Success {
            public static final String _10 = "var(--lumo-success-color-10pct)";
            public static final String _50 = "var(--lumo-success-color-50pct)";
            public static final String _100 = "var(--lumo-success-color)";
        }

        public static class Tint {
            public static final String _5 = "var(--lumo-tint-5pct)";
            public static final String _10 = "var(--lumo-tint-10pct)";
            public static final String _20 = "var(--lumo-tint-20pct)";
            public static final String _30 = "var(--lumo-tint-30pct)";
            public static final String _40 = "var(--lumo-tint-40pct)";
            public static final String _50 = "var(--lumo-tint-50pct)";
            public static final String _60 = "var(--lumo-tint-60pct)";
            public static final String _70 = "var(--lumo-tint-70pct)";
            public static final String _80 = "var(--lumo-tint-80pct)";
            public static final String _90 = "var(--lumo-tint-90pct)";
            public static final String _100 = "var(--lumo-tint)";
        }

        public static class Shade {
            public static final String _5 = "var(--lumo-shade-5pct)";
            public static final String _10 = "var(--lumo-shade-10pct)";
            public static final String _20 = "var(--lumo-shade-20pct)";
            public static final String _30 = "var(--lumo-shade-30pct)";
            public static final String _40 = "var(--lumo-shade-40pct)";
            public static final String _50 = "var(--lumo-shade-50pct)";
            public static final String _60 = "var(--lumo-shade-60pct)";
            public static final String _70 = "var(--lumo-shade-70pct)";
            public static final String _80 = "var(--lumo-shade-80pct)";
            public static final String _90 = "var(--lumo-shade-90pct)";
            public static final String _100 = "var(--lumo-shade)";
        }

        public static class Contrast {
            public static final String _5 = "var(--lumo-contrast-5pct)";
            public static final String _10 = "var(--lumo-contrast-10pct)";
            public static final String _20 = "var(--lumo-contrast-20pct)";
            public static final String _30 = "var(--lumo-contrast-30pct)";
            public static final String _40 = "var(--lumo-contrast-40pct)";
            public static final String _50 = "var(--lumo-contrast-50pct)";
            public static final String _60 = "var(--lumo-contrast-60pct)";
            public static final String _70 = "var(--lumo-contrast-70pct)";
            public static final String _80 = "var(--lumo-contrast-80pct)";
            public static final String _90 = "var(--lumo-contrast-90pct)";
            public static final String _100 = "var(--lumo-contrast)";
        }
    }

    public static class FontFamily {
        public static final String MONOSPACE = "monospace";
    }

    public static class Heading {
        public static final String H1 = "h1";
        public static final String H2 = "h2";
        public static final String H3 = "h3";
        public static final String H4 = "h4";
        public static final String H5 = "h5";
        public static final String H6 = "h6";
    }

    public static class IconSize {
        public static final String S = "var(--lumo-icon-size-s)";
        public static final String M = "var(--lumo-icon-size-m)";
        public static final String L = "var(--lumo-icon-size-l)";
    }

    public static class Margin {
        public static class Bottom {
            public static final String AUTO = "margin-b-a";
            public static final String XS = "margin-b-xs";
            public static final String S = "margin-b-s";
            public static final String M = "margin-b-m";
            public static final String L = "margin-b-l";
            public static final String XL = "margin-b-xl";
        }

        public static class Horizontal {
            public static final String AUTO = "margin-h-a";
            public static final String XS = "margin-h-xs";
            public static final String S = "margin-h-s";
            public static final String M = "margin-h-m";
            public static final String L = "margin-h-l";
            public static final String XL = "margin-h-xl";
        }

        public static class Left {
            public static final String AUTO = "margin-l-a";
            public static final String XS = "margin-l-xs";
            public static final String S = "margin-l-s";
            public static final String M = "margin-l-m";
            public static final String L = "margin-l-l";
            public static final String XL = "margin-l-xl";
        }

        public static class Responsive {
            public static class Horizontal {
                public static final String M = "margin-r-h-m";
                public static final String L = "margin-r-h-l";
            }

            public static class Vertical {
                public static final String M = "margin-r-v-m";
                public static final String L = "margin-r-v-l";
            }
        }

        public static class Right {
            public static final String AUTO = "margin-r-a";
            public static final String XS = "margin-r-xs";
            public static final String S = "margin-r-s";
            public static final String M = "margin-r-m";
            public static final String L = "margin-r-l";
            public static final String XL = "margin-r-xl";
        }

        public static class Tall {
            public static final String XS = "margin-tall-xs";
            public static final String S = "margin-tall-s";
            public static final String M = "margin-tall-m";
            public static final String L = "margin-tall-l";
            public static final String XL = "margin-tall-xl";
        }

        public static class Top {
            public static final String AUTO = "margin-t-a";
            public static final String XS = "margin-t-xs";
            public static final String S = "margin-t-s";
            public static final String M = "margin-t-m";
            public static final String L = "margin-t-l";
            public static final String XL = "margin-t-xl";
        }

        public static class Uniform {
            public static final String AUTO = "margin-a";
            public static final String XS = "margin-xs";
            public static final String S = "margin-s";
            public static final String M = "margin-m";
            public static final String L = "margin-l";
            public static final String XL = "margin-xl";
        }

        public static class Vertical {
            public static final String AUTO = "margin-v-a";
            public static final String XS = "margin-v-xs";
            public static final String S = "margin-v-s";
            public static final String M = "margin-v-m";
            public static final String L = "margin-v-l";
            public static final String XL = "margin-v-xl";
        }

        public static class Wide {
            public static final String XS = "margin-wide-xs";
            public static final String S = "margin-wide-s";
            public static final String M = "margin-wide-m";
            public static final String L = "margin-wide-l";
            public static final String XL = "margin-wide-xl";
        }
    }

    public static class Padding {
        public static class Bottom {
            public static final String XS = "padding-b-xs";
            public static final String S = "padding-b-s";
            public static final String M = "padding-b-m";
            public static final String L = "padding-b-l";
            public static final String XL = "padding-b-xl";
        }

        public static class Horizontal {
            public static final String XS = "padding-h-xs";
            public static final String S = "padding-h-s";
            public static final String M = "padding-h-m";
            public static final String L = "padding-h-l";
            public static final String XL = "padding-h-xl";
        }

        public static class Left {
            public static final String XS = "padding-l-xs";
            public static final String S = "padding-l-s";
            public static final String M = "padding-l-m";
            public static final String L = "padding-l-l";
            public static final String XL = "padding-l-xl";
        }

        public static class Responsive {
            public static class Horizontal {
                public static final String M = "padding-r-h-m";
                public static final String L = "padding-r-h-l";
            }

            public static class Vertical {
                public static final String M = "padding-r-v-m";
                public static final String L = "padding-r-v-l";
            }
        }

        public static class Right {
            public static final String XS = "padding-r-xs";
            public static final String S = "padding-r-s";
            public static final String M = "padding-r-m";
            public static final String L = "padding-r-l";
            public static final String XL = "padding-r-xl";
        }

        public static class Tall {
            public static final String XS = "padding-tall-xs";
            public static final String S = "padding-tall-s";
            public static final String M = "padding-tall-m";
            public static final String L = "padding-tall-l";
            public static final String XL = "padding-tall-xl";
        }

        public static class Top {
            public static final String XS = "padding-t-xs";
            public static final String S = "padding-t-s";
            public static final String M = "padding-t-m";
            public static final String L = "padding-t-l";
            public static final String XL = "padding-t-xl";
        }

        public static class Uniform {
            public static final String XS = "padding-xs";
            public static final String S = "padding-s";
            public static final String M = "padding-m";
            public static final String L = "padding-l";
            public static final String XL = "padding-xl";
        }

        public static class Vertical {
            public static final String XS = "padding-v-xs";
            public static final String S = "padding-v-s";
            public static final String M = "padding-v-m";
            public static final String L = "padding-v-l";
            public static final String XL = "padding-v-xl";
        }

        public static class Wide {
            public static final String XS = "padding-wide-xs";
            public static final String S = "padding-wide-s";
            public static final String M = "padding-wide-m";
            public static final String L = "padding-wide-l";
            public static final String XL = "padding-wide-xl";
        }
    }

    public static class Shadow {
        public static final String XS = "shadow-xs";
        public static final String S = "shadow-s";
        public static final String M = "shadow-m";
        public static final String L = "shadow-l";
        public static final String XL = "shadow-xl";
    }

    public static class Size {
        public static final String XS = "var(--lumo-size-xs)";
        public static final String S = "var(--lumo-size-s)";
        public static final String M = "var(--lumo-size-m)";
        public static final String L = "var(--lumo-size-l)";
        public static final String XL = "var(--lumo-size-xl)";
    }

    public static class Space {
        public static final String XS = "var(--lumo-space-xs)";
        public static final String S = "var(--lumo-space-s)";
        public static final String M = "var(--lumo-space-m)";
        public static final String L = "var(--lumo-space-l)";
        public static final String XL = "var(--lumo-space-xl)";
    }

    public static class Spacing {
        public static class Bottom {
            public static final String XS = "spacing-b-xs";
            public static final String S = "spacing-b-s";
            public static final String M = "spacing-b-m";
            public static final String L = "spacing-b-l";
            public static final String XL = "spacing-b-xl";
        }

        public static class Horizontal {
            public static final String XS = "spacing-h-xs";
            public static final String S = "spacing-h-s";
            public static final String M = "spacing-h-m";
            public static final String L = "spacing-h-l";
            public static final String XL = "spacing-h-xl";
        }

        public static class Left {
            public static final String XS = "spacing-l-xs";
            public static final String S = "spacing-l-s";
            public static final String M = "spacing-l-m";
            public static final String L = "spacing-l-l";
            public static final String XL = "spacing-l-xl";
        }

        public static class Right {
            public static final String XS = "spacing-r-xs";
            public static final String S = "spacing-r-s";
            public static final String M = "spacing-r-m";
            public static final String L = "spacing-r-l";
            public static final String XL = "spacing-r-xl";
        }

        public static class Tall {
            public static final String XS = "spacing-tall-xs";
            public static final String S = "spacing-tall-s";
            public static final String M = "spacing-tall-m";
            public static final String L = "spacing-tall-l";
            public static final String XL = "spacing-tall-xl";
        }

        public static class Top {
            public static final String XS = "spacing-t-xs";
            public static final String S = "spacing-t-s";
            public static final String M = "spacing-t-m";
            public static final String L = "spacing-t-l";
            public static final String XL = "spacing-t-xl";
        }

        public static class Uniform {
            public static final String XS = "spacing-xs";
            public static final String S = "spacing-s";
            public static final String M = "spacing-m";
            public static final String L = "spacing-l";
            public static final String XL = "spacing-xl";
        }

        public static class Vertical {
            public static final String XS = "spacing-v-xs";
            public static final String S = "spacing-v-s";
            public static final String M = "spacing-v-m";
            public static final String L = "spacing-v-l";
            public static final String XL = "spacing-v-xl";
        }

        public static class Wide {
            public static final String XS = "spacing-wide-xs";
            public static final String S = "spacing-wide-s";
            public static final String M = "spacing-wide-m";
            public static final String L = "spacing-wide-l";
            public static final String XL = "spacing-wide-xl";
        }
    }

}
