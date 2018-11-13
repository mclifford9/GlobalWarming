package net.porillo.util;

import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create a table where the columns align correctly
 *  - Assumes the default Minecraft font which is not monospaced
 *  - Customizable options:
 *   - Title
 *   - Headers: color and width in pixels
 *   - Default text colors
 *   - Row and column styles
 */
public class ChatTable {
    public enum Section {
        HEADER(0),
        BODY(1);
        public int get() {return value;}
        private final int value;
        Section(int value) {
            this.value = value;
        }
    }

    private enum Alignment {LEFT, CENTER, RIGHT}
    private final Map<Character, Integer> charWidth;
    @Setter private String title;
    private List<String> headers;
    private List<Integer> headerWidth;
    private List<List<String>> body;
    @Setter private ChatColor defaultColor;
    private ChatColor textColor[];
    private Character decoration[];
    private Character delimiter[];
    private Character onePixelPad[];
    private Character twoPixelPad[];

    public ChatTable(String title) {
        this.title = title;

        headers = new ArrayList<>();
        headerWidth = new ArrayList<>();
        body = new ArrayList<>();

        defaultColor = ChatColor.WHITE;

        textColor = new ChatColor[2];
        textColor[Section.HEADER.get()] = ChatColor.WHITE;
        textColor[Section.BODY.get()] = ChatColor.WHITE;

        decoration = new Character[2];
        decoration[Section.HEADER.get()] = '-';
        decoration[Section.BODY.get()] = ' ';

        delimiter = new Character[2];
        delimiter[Section.HEADER.get()] = ' ';
        delimiter[Section.BODY.get()] = ' ';

        twoPixelPad = new Character[2];
        twoPixelPad[Section.HEADER.get()] = '\u00B7';
        twoPixelPad[Section.BODY.get()] = '\u00B7';

        onePixelPad = new Character[2];
        onePixelPad[Section.HEADER.get()] = '\u17f2';
        onePixelPad[Section.BODY.get()] = '\u17f2';

        //ASCII character sizes:
        charWidth = new HashMap();
        charWidth.put(' ', 4);
        charWidth.put('!', 2);
        charWidth.put('"', 4);
        charWidth.put('#', 6);
        charWidth.put('$', 6);
        charWidth.put('%', 6);
        charWidth.put('&', 6);
        charWidth.put('\'', 2);
        charWidth.put('(', 4);
        charWidth.put(')', 4);
        charWidth.put('*', 4);
        charWidth.put('+', 6);
        charWidth.put(',', 2);
        charWidth.put('-', 6);
        charWidth.put('.', 2);
        charWidth.put('/', 6);
        charWidth.put('0', 6);
        charWidth.put('1', 6);
        charWidth.put('2', 6);
        charWidth.put('3', 6);
        charWidth.put('4', 6);
        charWidth.put('5', 6);
        charWidth.put('6', 6);
        charWidth.put('7', 6);
        charWidth.put('8', 6);
        charWidth.put('9', 6);
        charWidth.put(':', 2);
        charWidth.put(';', 2);
        charWidth.put('<', 5);
        charWidth.put('=', 6);
        charWidth.put('>', 5);
        charWidth.put('?', 6);
        charWidth.put('@', 8);
        charWidth.put('A', 6);
        charWidth.put('B', 6);
        charWidth.put('C', 6);
        charWidth.put('D', 6);
        charWidth.put('E', 6);
        charWidth.put('F', 6);
        charWidth.put('G', 6);
        charWidth.put('H', 6);
        charWidth.put('I', 4);
        charWidth.put('J', 6);
        charWidth.put('K', 6);
        charWidth.put('L', 6);
        charWidth.put('M', 6);
        charWidth.put('N', 6);
        charWidth.put('O', 6);
        charWidth.put('P', 6);
        charWidth.put('Q', 6);
        charWidth.put('R', 6);
        charWidth.put('S', 6);
        charWidth.put('T', 6);
        charWidth.put('U', 6);
        charWidth.put('V', 6);
        charWidth.put('W', 6);
        charWidth.put('X', 6);
        charWidth.put('Y', 6);
        charWidth.put('Z', 6);
        charWidth.put('[', 4);
        charWidth.put('\\', 6);
        charWidth.put(']', 4);
        charWidth.put('^', 6);
        charWidth.put('_', 6);
        charWidth.put('`', 3);
        charWidth.put('a', 6);
        charWidth.put('b', 6);
        charWidth.put('c', 6);
        charWidth.put('d', 6);
        charWidth.put('e', 6);
        charWidth.put('f', 5);
        charWidth.put('g', 6);
        charWidth.put('h', 6);
        charWidth.put('i', 2);
        charWidth.put('j', 6);
        charWidth.put('k', 5);
        charWidth.put('l', 3);
        charWidth.put('m', 6);
        charWidth.put('n', 6);
        charWidth.put('o', 6);
        charWidth.put('p', 6);
        charWidth.put('q', 6);
        charWidth.put('r', 6);
        charWidth.put('s', 6);
        charWidth.put('t', 4);
        charWidth.put('u', 6);
        charWidth.put('v', 6);
        charWidth.put('w', 6);
        charWidth.put('x', 6);
        charWidth.put('y', 6);
        charWidth.put('z', 6);
        charWidth.put('{', 4);
        charWidth.put('|', 2);
        charWidth.put('}', 4);
        charWidth.put('~', 7);
        charWidth.put('°', 4);
        charWidth.put(twoPixelPad[Section.HEADER.get()], 2);
        charWidth.put(twoPixelPad[Section.BODY.get()], 2);
        charWidth.put(onePixelPad[Section.HEADER.get()], 1);
        charWidth.put(onePixelPad[Section.BODY.get()], 1);
    }

    public void setTextColor(Section section, ChatColor color) {
        textColor[section.get()] = color;
    }

    public void setOnePixedPad(Section section, Character pad) {
        onePixelPad[section.get()] = pad;
    }

    public void setTwoPixedPad(Section section, Character pad) {
        twoPixelPad[section.get()] = pad;
    }

    public void setDecoration(Section section, Character decoration) {
        this.decoration[section.get()] = decoration;
    }

    public void setDelimiter(Section section, Character delimiter) {
        this.delimiter[section.get()] = delimiter;
    }

    public void addHeader(String header, int headerPixels) {
        headers.add(format(header, headerPixels, Section.HEADER, Alignment.CENTER));
        headerWidth.add(headerPixels);
    }

    public void addRow(List<String> values) {
        List<String> row = new ArrayList<>();
        for (int i = 0; i < values.size() && i < headers.size() && i < headerWidth.size(); i++) {
            String value = format(String.format(" %s", values.get(i)), headerWidth.get(i), Section.BODY, Alignment.LEFT);
            row.add(value);
        }

        body.add(row);
    }

    private int getPixelWidth(String value) {
        int width = 0;
        String cleanValue = value.replaceAll("§\\w", "");
        for (Character chr : cleanValue.toCharArray()) {
            Integer size = charWidth.get(chr);
            width += size == null ? 0 : size;
        }

        return width;
    }

    private String getPadding(int pixelWidth, Section section) {
        //Pad:
        StringBuilder padding = new StringBuilder();
        Integer padPixels = charWidth.get(decoration[section.get()]);
        if (padPixels != null && padPixels > 0) {
            int count = pixelWidth / padPixels;
            for (int i = 0; i < count; i++) {
                padding.append(decoration[section.get()]);
            }
        }

        //Alignment issues:
        int remainingPixels = pixelWidth - getPixelWidth(padding.toString());
        if (remainingPixels > 0) {
            //2-pixel character padding:
            while (remainingPixels > 1) {
                padding.append(twoPixelPad[section.get()]);
                remainingPixels -= 2;
            }

            //Single-pixel character padding:
            if (remainingPixels > 0) {
                padding.append(onePixelPad[section.get()]);
            }
        }

        return padding.toString();
    }

    private String pad(String value, int pixelWidth, Section section, Alignment align) {
        StringBuilder paddedValue = new StringBuilder();
        int remainingPixels = pixelWidth - getPixelWidth(value);
        switch (align) {
            case LEFT:
                paddedValue.append(value);
                paddedValue.append(getPadding(remainingPixels, section));
                break;
            case CENTER:
                int leftHalf = remainingPixels / 2;
                int rightHalf = remainingPixels - leftHalf;
                paddedValue.append(getPadding(leftHalf, section));
                paddedValue.append(value);
                paddedValue.append(getPadding(rightHalf, section));
                break;
            case RIGHT:
                paddedValue.append(getPadding(remainingPixels, section));
                paddedValue.append(value);
                paddedValue.append(value);
                break;
        }

        return paddedValue.toString();
    }

    private String format(String value, int columnPixels, Section section, Alignment align) {
        String formattedValue = value;
        while (formattedValue.length() > 3 && getPixelWidth(formattedValue) >= columnPixels) {
            formattedValue = String.format(
                  "%s...",
                  formattedValue.substring(0, formattedValue.length() - 4));
        }

        formattedValue = String.format(
              "%s%s%s",
              textColor[section.get()],
              formattedValue,
              defaultColor);

        return pad(formattedValue, columnPixels, section, align);
    }

    @Override
    public String toString() {
        //Delimiters:
        int headerDelimiterWidth = charWidth.get(delimiter[Section.HEADER.get()]);
        int bodyDelimiterWidth = charWidth.get(delimiter[Section.BODY.get()]);
        String delimiters[] = new String[2];
        if (headerDelimiterWidth > bodyDelimiterWidth) {
            delimiters[Section.HEADER.get()] = delimiter[Section.HEADER.get()].toString();
            delimiters[Section.BODY.get()] = pad(
                  delimiter[Section.BODY.get()].toString(),
                  headerDelimiterWidth,
                  Section.BODY,
                  Alignment.CENTER);
        } else {
            delimiters[Section.HEADER.get()] = pad(
                  delimiter[Section.HEADER.get()].toString(),
                  bodyDelimiterWidth,
                  Section.HEADER,
                  Alignment.CENTER);

            delimiters[Section.BODY.get()] = delimiter[Section.BODY.get()].toString();
        }

        //Header:
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append(defaultColor);
        tableBuilder.append(delimiters[Section.HEADER.get()]);
        tableBuilder.append(String.join(delimiters[Section.HEADER.get()], headers));
        tableBuilder.append(delimiters[Section.HEADER.get()]);

        //Title (uses full header width to center):
        if (title.length() > 0) {
            int tableWidth = getPixelWidth(tableBuilder.toString());
            String formattedValue = String.format(
                  "%s%s%s",
                  textColor[Section.HEADER.get()],
                  title,
                  defaultColor);

            tableBuilder.insert(0, String.format(
                  "\n%s%s\n",
                  defaultColor,
                  pad(formattedValue, tableWidth, Section.HEADER, Alignment.CENTER)));
        }

        //Body:
        for (List<String> row : body) {
            tableBuilder.append("\n");
            tableBuilder.append(defaultColor);
            tableBuilder.append(delimiters[Section.BODY.get()]);
            tableBuilder.append(String.join(delimiters[Section.BODY.get()], row));
            tableBuilder.append(delimiters[Section.BODY.get()]);
        }

        //Footer:
        tableBuilder.append("\n");
        tableBuilder.append(defaultColor);
        tableBuilder.append(delimiters[Section.HEADER.get()]);
        List<String> footers = new ArrayList<>();
        for (int width: headerWidth) {
            footers.add(pad("", width, Section.HEADER, Alignment.LEFT));
        }

        tableBuilder.append(String.join(delimiters[Section.HEADER.get()], footers));
        tableBuilder.append(delimiters[Section.HEADER.get()]);
        tableBuilder.append("\n\n");

        return tableBuilder.toString();
    }
}