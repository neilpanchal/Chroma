package com.chroma;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChromaUtil {

	public static double clamp(double value, double lower, double upper) {
		return Math.max(lower, Math.min(upper, value));
	}

	public static double max3(double a, double b, double c) {
		return Math.max(a, Math.max(b, c));
	}

	public static double min3(double a, double b, double c) {
		return Math.min(a, Math.min(b, c));
	}

	public static double lab_xyz(double x) {

		if (x > 0.206893034) {
			return x * x * x;
		} else {
			return (x - 4.0F / 29.0) / 7.787037;
		}
	}

	public static double xyz_rgb(double r) {

		return Math.round(255.0 * (r <= 0.00304 ? 12.92 * r : 1.055 * Math.pow(
				r, 1 / 2.4) - 0.055));

	}

	public static double rgb_xyz(double r) {

		if ((r /= 255.0) <= 0.04045) {
			return r / 12.92;
		} else {
			return Math.pow((r + 0.055) / 1.055, 2.4);
		}
	}

	public static double xyz_lab(double x) {

		if (x > 0.008856) {
			return Math.pow(x, 1.0 / 3.0);
		} else {
			return 7.787037 * x + 4.0 / 29.0;
		}
	}

	public static boolean validateHex(final String hex) {
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	public static String[] colorBrewer(String scale) {

		HashMap<String, String[]> hash_ = new HashMap<>();

        hash_.put("OrRd",
            new String[]{"#fff7ec", "#fee8c8", "#fdd49e", "#fdbb84", "#fc8d59", "#ef6548", "#d7301f", "#b30000", "#7f0000"});
        hash_.put("PuBu",
            new String[]{"#fff7fb", "#ece7f2", "#d0d1e6", "#a6bddb", "#74a9cf", "#3690c0", "#0570b0", "#045a8d", "#023858"});
        hash_.put("BuPu",
            new String[]{"#f7fcfd", "#e0ecf4", "#bfd3e6", "#9ebcda", "#8c96c6", "#8c6bb1", "#88419d", "#810f7c", "#4d004b"});
        hash_.put("Oranges",
            new String[]{"#fff5eb", "#fee6ce", "#fdd0a2", "#fdae6b", "#fd8d3c", "#f16913", "#d94801", "#a63603", "#7f2704"});
        hash_.put("BuGn",
            new String[]{"#f7fcfd", "#e5f5f9", "#ccece6", "#99d8c9", "#66c2a4", "#41ae76", "#238b45", "#006d2c", "#00441b"});
        hash_.put("YlOrBr",
            new String[]{"#ffffe5", "#fff7bc", "#fee391", "#fec44f", "#fe9929", "#ec7014", "#cc4c02", "#993404", "#662506"});
        hash_.put("YlGn",
            new String[]{"#ffffe5", "#f7fcb9", "#d9f0a3", "#addd8e", "#78c679", "#41ab5d", "#238443", "#006837", "#004529"});
        hash_.put("Reds",
            new String[]{"#fff5f0", "#fee0d2", "#fcbba1", "#fc9272", "#fb6a4a", "#ef3b2c", "#cb181d", "#a50f15", "#67000d"});
        hash_.put("RdPu",
            new String[]{"#fff7f3", "#fde0dd", "#fcc5c0", "#fa9fb5", "#f768a1", "#dd3497", "#ae017e", "#7a0177", "#49006a"});
        hash_.put("Greens",
            new String[]{"#f7fcf5", "#e5f5e0", "#c7e9c0", "#a1d99b", "#74c476", "#41ab5d", "#238b45", "#006d2c", "#00441b"});
        hash_.put("YlGnBu",
            new String[]{"#ffffd9", "#edf8b1", "#c7e9b4", "#7fcdbb", "#41b6c4", "#1d91c0", "#225ea8", "#253494", "#081d58"});
        hash_.put("Purples",
            new String[]{"#fcfbfd", "#efedf5", "#dadaeb", "#bcbddc", "#9e9ac8", "#807dba", "#6a51a3", "#54278f", "#3f007d"});
        hash_.put("GnBu",
            new String[]{"#f7fcf0", "#e0f3db", "#ccebc5", "#a8ddb5", "#7bccc4", "#4eb3d3", "#2b8cbe", "#0868ac", "#084081"});
        hash_.put("Greys",
            new String[]{"#ffffff", "#f0f0f0", "#d9d9d9", "#bdbdbd", "#969696", "#737373", "#525252", "#252525", "#000000"});
        hash_.put("YlOrRd",
            new String[]{"#ffffcc", "#ffeda0", "#fed976", "#feb24c", "#fd8d3c", "#fc4e2a", "#e31a1c", "#bd0026", "#800026"});
        hash_.put("PuRd",
            new String[]{"#f7f4f9", "#e7e1ef", "#d4b9da", "#c994c7", "#df65b0", "#e7298a", "#ce1256", "#980043", "#67001f"});
        hash_.put("Blues",
            new String[]{"#f7fbff", "#deebf7", "#c6dbef", "#9ecae1", "#6baed6", "#4292c6", "#2171b5", "#08519c", "#08306b"});
        hash_.put("PuBuGn",
            new String[]{"#fff7fb", "#ece2f0", "#d0d1e6", "#a6bddb", "#67a9cf", "#3690c0", "#02818a", "#016c59", "#014636"});
        hash_.put("Spectral",
            new String[]{"#9e0142", "#d53e4f", "#f46d43", "#fdae61", "#fee08b", "#ffffbf", "#e6f598", "#abdda4", "#66c2a5", "#3288bd", "#5e4fa2"});
        hash_.put("RdYlGn",
            new String[]{"#a50026", "#d73027", "#f46d43", "#fdae61", "#fee08b", "#ffffbf", "#d9ef8b", "#a6d96a", "#66bd63", "#1a9850", "#006837"});
        hash_.put("RdBu",
            new String[]{"#67001f", "#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#f7f7f7", "#d1e5f0", "#92c5de", "#4393c3", "#2166ac", "#053061"});
        hash_.put("PiYG",
            new String[]{"#8e0152", "#c51b7d", "#de77ae", "#f1b6da", "#fde0ef", "#f7f7f7", "#e6f5d0", "#b8e186", "#7fbc41", "#4d9221", "#276419"});
        hash_.put("PRGn",
            new String[]{"#40004b", "#762a83", "#9970ab", "#c2a5cf", "#e7d4e8", "#f7f7f7", "#d9f0d3", "#a6dba0", "#5aae61", "#1b7837", "#00441b"});
        hash_.put("RdYlBu",
            new String[]{"#a50026", "#d73027", "#f46d43", "#fdae61", "#fee090", "#ffffbf", "#e0f3f8", "#abd9e9", "#74add1", "#4575b4", "#313695"});
        hash_.put("BrBG",
            new String[]{"#543005", "#8c510a", "#bf812d", "#dfc27d", "#f6e8c3", "#f5f5f5", "#c7eae5", "#80cdc1", "#35978f", "#01665e", "#003c30"});
        hash_.put("RdGy",
            new String[]{"#67001f", "#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#ffffff", "#e0e0e0", "#bababa", "#878787", "#4d4d4d", "#1a1a1a"});
        hash_.put("PuOr",
            new String[]{"#7f3b08", "#b35806", "#e08214", "#fdb863", "#fee0b6", "#f7f7f7", "#d8daeb", "#b2abd2", "#8073ac", "#542788", "#2d004b"});
        hash_.put("Set2",
            new String[]{"#66c2a5", "#fc8d62", "#8da0cb", "#e78ac3", "#a6d854", "#ffd92f", "#e5c494", "#b3b3b3"});
        hash_.put("Accent",
            new String[]{"#7fc97f", "#beaed4", "#fdc086", "#ffff99", "#386cb0", "#f0027f", "#bf5b17", "#666666"});
        hash_.put("Set1",
            new String[]{"#e41a1c", "#377eb8", "#4daf4a", "#984ea3", "#ff7f00", "#ffff33", "#a65628", "#f781bf", "#999999"});
        hash_.put("Set3",
            new String[]{"#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5", "#d9d9d9", "#bc80bd", "#ccebc5", "#ffed6f"});
        hash_.put("Dark2",
            new String[]{"#1b9e77", "#d95f02", "#7570b3", "#e7298a", "#66a61e", "#e6ab02", "#a6761d", "#666666"});
        hash_.put("Paired",
            new String[]{"#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c", "#fdbf6f", "#ff7f00", "#cab2d6", "#6a3d9a", "#ffff99", "#b15928"});
        hash_.put("Pastel2",
            new String[]{"#b3e2cd", "#fdcdac", "#cbd5e8", "#f4cae4", "#e6f5c9", "#fff2ae", "#f1e2cc", "#cccccc"});
        hash_.put("Pastel1",
            new String[]{"#fbb4ae", "#b3cde3", "#ccebc5", "#decbe4", "#fed9a6", "#ffffcc", "#e5d8bd", "#fddaec", "#f2f2f2"});

        return hash_.get(scale);
	}

	public static String colorName(String name) {

		HashMap<String, String> hash_ = new HashMap<>();

		hash_.put("indigo",               "#4b0082");
        hash_.put("gold",                 "#ffd700");
        hash_.put("hotpink",              "#ff69b4");
        hash_.put("firebrick",            "#b22222");
        hash_.put("indianred",            "#cd5c5c");
        hash_.put("yellow",               "#ffff00");
        hash_.put("mistyrose",            "#ffe4e1");
        hash_.put("darkolivegreen",       "#556b2f");
        hash_.put("olive",                "#808000");
        hash_.put("darkseagreen",         "#8fbc8f");
        hash_.put("pink",                 "#ffc0cb");
        hash_.put("tomato",               "#ff6347");
        hash_.put("lightcoral",           "#f08080");
        hash_.put("orangered",            "#ff4500");
        hash_.put("navajowhite",          "#ffdead");
        hash_.put("lime",                 "#00ff00");
        hash_.put("palegreen",            "#98fb98");
        hash_.put("darkslategrey",        "#2f4f4f");
        hash_.put("greenyellow",          "#adff2f");
        hash_.put("burlywood",            "#deb887");
        hash_.put("seashell",             "#fff5ee");
        hash_.put("mediumspringgreen",    "#00fa9a");
        hash_.put("fuchsia",              "#ff00ff");
        hash_.put("papayawhip",           "#ffefd5");
        hash_.put("blanchedalmond",       "#ffebcd");
        hash_.put("chartreuse",           "#7fff00");
        hash_.put("dimgray",              "#696969");
        hash_.put("black",                "#000000");
        hash_.put("peachpuff",            "#ffdab9");
        hash_.put("springgreen",          "#00ff7f");
        hash_.put("aquamarine",           "#7fffd4");
        hash_.put("white",                "#ffffff");
        hash_.put("orange",               "#ffa500");
        hash_.put("lightsalmon",          "#ffa07a");
        hash_.put("darkslategray",        "#2f4f4f");
        hash_.put("brown",                "#a52a2a");
        hash_.put("ivory",                "#fffff0");
        hash_.put("dodgerblue",           "#1e90ff");
        hash_.put("peru",                 "#cd853f");
        hash_.put("lawngreen",            "#7cfc00");
        hash_.put("chocolate",            "#d2691e");
        hash_.put("crimson",              "#dc143c");
        hash_.put("forestgreen",          "#228b22");
        hash_.put("darkgrey",             "#a9a9a9");
        hash_.put("lightseagreen",        "#20b2aa");
        hash_.put("cyan",                 "#00ffff");
        hash_.put("mintcream",            "#f5fffa");
        hash_.put("silver",               "#c0c0c0");
        hash_.put("antiquewhite",         "#faebd7");
        hash_.put("mediumorchid",         "#ba55d3");
        hash_.put("skyblue",              "#87ceeb");
        hash_.put("gray",                 "#808080");
        hash_.put("darkturquoise",        "#00ced1");
        hash_.put("goldenrod",            "#daa520");
        hash_.put("darkgreen",            "#006400");
        hash_.put("floralwhite",          "#fffaf0");
        hash_.put("darkviolet",           "#9400d3");
        hash_.put("darkgray",             "#a9a9a9");
        hash_.put("moccasin",             "#ffe4b5");
        hash_.put("saddlebrown",          "#8b4513");
        hash_.put("grey",                 "#808080");
        hash_.put("darkslateblue",        "#483d8b");
        hash_.put("lightskyblue",         "#87cefa");
        hash_.put("lightpink",            "#ffb6c1");
        hash_.put("mediumvioletred",      "#c71585");
        hash_.put("slategrey",            "#708090");
        hash_.put("red",                  "#ff0000");
        hash_.put("deeppink",             "#ff1493");
        hash_.put("limegreen",            "#32cd32");
        hash_.put("darkmagenta",          "#8b008b");
        hash_.put("palegoldenrod",        "#eee8aa");
        hash_.put("plum",                 "#dda0dd");
        hash_.put("turquoise",            "#40e0d0");
        hash_.put("lightgrey",            "#d3d3d3");
        hash_.put("lightgoldenrodyellow", "#fafad2");
        hash_.put("darkgoldenrod",        "#b8860b");
        hash_.put("lavender",             "#e6e6fa");
        hash_.put("maroon",               "#800000");
        hash_.put("yellowgreen",          "#9acd32");
        hash_.put("sandybrown",           "#f4a460");
        hash_.put("thistle",              "#d8bfd8");
        hash_.put("violet",               "#ee82ee");
        hash_.put("navy",                 "#000080");
        hash_.put("magenta",              "#ff00ff");
        hash_.put("dimgrey",              "#696969");
        hash_.put("tan",                  "#d2b48c");
        hash_.put("rosybrown",            "#bc8f8f");
        hash_.put("olivedrab",            "#6b8e23");
        hash_.put("blue",                 "#0000ff");
        hash_.put("lightblue",            "#add8e6");
        hash_.put("ghostwhite",           "#f8f8ff");
        hash_.put("honeydew",             "#f0fff0");
        hash_.put("cornflowerblue",       "#6495ed");
        hash_.put("slateblue",            "#6a5acd");
        hash_.put("linen",                "#faf0e6");
        hash_.put("darkblue",             "#00008b");
        hash_.put("powderblue",           "#b0e0e6");
        hash_.put("seagreen",             "#2e8b57");
        hash_.put("darkkhaki",            "#bdb76b");
        hash_.put("snow",                 "#fffafa");
        hash_.put("sienna",               "#a0522d");
        hash_.put("mediumblue",           "#0000cd");
        hash_.put("royalblue",            "#4169e1");
        hash_.put("lightcyan",            "#e0ffff");
        hash_.put("green",                "#008000");
        hash_.put("mediumpurple",         "#9370db");
        hash_.put("midnightblue",         "#191970");
        hash_.put("cornsilk",             "#fff8dc");
        hash_.put("paleturquoise",        "#afeeee");
        hash_.put("bisque",               "#ffe4c4");
        hash_.put("slategray",            "#708090");
        hash_.put("darkcyan",             "#008b8b");
        hash_.put("khaki",                "#f0e68c");
        hash_.put("wheat",                "#f5deb3");
        hash_.put("teal",                 "#008080");
        hash_.put("darkorchid",           "#9932cc");
        hash_.put("deepskyblue",          "#00bfff");
        hash_.put("salmon",               "#fa8072");
        hash_.put("darkred",              "#8b0000");
        hash_.put("steelblue",            "#4682b4");
        hash_.put("palevioletred",        "#db7093");
        hash_.put("lightslategray",       "#778899");
        hash_.put("aliceblue",            "#f0f8ff");
        hash_.put("lightslategrey",       "#778899");
        hash_.put("lightgreen",           "#90ee90");
        hash_.put("orchid",               "#da70d6");
        hash_.put("gainsboro",            "#dcdcdc");
        hash_.put("mediumseagreen",       "#3cb371");
        hash_.put("lightgray",            "#d3d3d3");
        hash_.put("mediumturquoise",      "#48d1cc");
        hash_.put("lemonchiffon",         "#fffacd");
        hash_.put("cadetblue",            "#5f9ea0");
        hash_.put("lightyellow",          "#ffffe0");
        hash_.put("lavenderblush",        "#fff0f5");
        hash_.put("coral",                "#ff7f50");
        hash_.put("purple",               "#800080");
        hash_.put("aqua",                 "#00ffff");
        hash_.put("whitesmoke",           "#f5f5f5");
        hash_.put("mediumslateblue",      "#7b68ee");
        hash_.put("darkorange",           "#ff8c00");
        hash_.put("mediumaquamarine",     "#66cdaa");
        hash_.put("darksalmon",           "#e9967a");
        hash_.put("beige",                "#f5f5dc");
        hash_.put("blueviolet",           "#8a2be2");
        hash_.put("azure",                "#f0ffff");
        hash_.put("lightsteelblue",       "#b0c4de");
        hash_.put("oldlac",               "#fdf5e6");

        return hash_.get(name.toLowerCase());

	}



}
