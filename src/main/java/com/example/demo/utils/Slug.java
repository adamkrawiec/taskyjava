package com.example.demo.utils;

import java.util.regex.Pattern;
import java.text.Normalizer;
import java.util.Locale;

public class Slug {
  private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
  private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
  private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

  public static String toSlug(String input) {
    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
    String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
    String slug = NONLATIN.matcher(normalized).replaceAll("");
    slug = EDGESDHASHES.matcher(slug).replaceAll("");
    return slug.toLowerCase(Locale.ENGLISH);
  }
}
