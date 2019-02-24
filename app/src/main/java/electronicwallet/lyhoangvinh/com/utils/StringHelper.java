package electronicwallet.lyhoangvinh.com.utils;

import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringHelper {

    public static boolean isNull(@Nullable CharSequence str) {
        return (str == null);
    }

    public static boolean isNullOrEmpty(@Nullable CharSequence str) {
        return (str == null || str.length() == 0);
    }

    public static Spannable applyUnderline(String source) {
        Spannable out = null;
        try {
            if (source != null) {
                SpannableString content = new SpannableString(source);
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                out = content;
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return out;
    }

    public static String replaceSpace(String source, String defValue) {
        if (source == null)
            return defValue;
        try {
            return source.replaceAll("\\s+", "");
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return defValue;
    }

    public static String removeDuplicateSpace(String source, String defValue) {
        if (source == null)
            return defValue;
        try {
            return source.replaceAll("\\s+", " ");
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return defValue;
    }

    public static String join(char delim, List<String> parts) {
        if (parts == null || parts.size() == 0) return null;
        return join(delim, parts.toArray(new String[parts.size()]));
    }

    public static String join(char delim, String... parts) {
        if (parts == null || parts.length == 0) return null;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i != 0) {
                result.append(delim);
            }
            result.append(parts[i]);
        }
        return result.toString();
    }

    public static Spanned fromHtml(String html) {
        if (isNullOrEmpty(html)) {
            return new SpannableString("");
        }
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    @Nullable
    public static String cleanRaw(@Nullable CharSequence source) {
        if (source == null)
            return null;
        String temp = source.toString();
        temp = temp.replaceAll("(\r\n|\n)", " ");
        temp = removeDuplicateSpace(temp, null);
        return temp;
    }

    public static class Slugify {

        private static final String default_slug_separator = "-"; //Hyphen-Minus U+002D '-'
        private static final Pattern NONLATIN = Pattern.compile("[^\\w" + default_slug_separator + "]"); //"[^\\w-]"
        private static final Pattern WHITESPACE = Pattern.compile("[\\s]+");
        private static final Pattern UN_ACCENT = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        private static final Pattern NON_ENDASH = Pattern.compile("[\\u2013]+");
        private static final Pattern ONE_SEPARATOR = Pattern.compile("[" + default_slug_separator + "]+");

        public static String makeSlug(@Nullable final String input) {
            String out = null;
            try {
                if (input != null) {

                    String nowhitespace = WHITESPACE.matcher(input).replaceAll(default_slug_separator);
                    String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
                    String un_accent = UN_ACCENT.matcher(normalized).replaceAll("");
                    // Oops, char 'Đ' or 'đ'
                    un_accent = un_accent.replaceAll("Đ", "d").replaceAll("đ", "d");

                    //non-Dash
                    String non_endash = NON_ENDASH.matcher(un_accent).replaceAll(default_slug_separator); //En Dash U+2013 '–'
                    //...more dash
                    String un_dash = non_endash;

                    String slug = NONLATIN.matcher(un_dash).replaceAll("").toLowerCase(Locale.ENGLISH);
                    String one_separator = ONE_SEPARATOR.matcher(slug).replaceAll(default_slug_separator);
                    out = one_separator;
                }
            } catch (Exception err1) {
                if (LogHelper.isLog()) err1.printStackTrace();
            }
            if (out == null) {
                out = input;
            }
            return out;
        }
    }

    public static class DetectHtml {
        // adapted from post by Phil Haack and modified to match better
        private static final String tagStart = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";
        private static final String tagEnd = "\\</\\w+\\>";
        private static final String tagSelfClosing = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";
        private static final String htmlEntity = "&[a-zA-Z][a-zA-Z0-9]+;";
        private static final Pattern htmlPattern = Pattern.compile("(" + tagStart + ".*" + tagEnd + ")|(" + tagSelfClosing + ")|(" + htmlEntity + ")", Pattern.DOTALL);

        public static boolean isHtml(String s) {
            boolean ret = false;
            if (s != null) {
                ret = htmlPattern.matcher(s).find();
            }
            return ret;
        }
    }

    //============================================================================================//


}