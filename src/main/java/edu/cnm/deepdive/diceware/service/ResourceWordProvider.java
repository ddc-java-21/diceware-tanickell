package edu.cnm.deepdive.diceware.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceWordProvider implements WordProvider {

  private static final Pattern WORD_EXTRACTOR = Pattern.compile("^\\s*\\d+\\s+(.*?)\\s*?$");

  private final List<String> words;

  @Autowired
  public ResourceWordProvider(@Value("${diceware.word-list}") String wordListResource) {
    Resource resource = new ClassPathResource(wordListResource);
    try (Stream<String> lines = Files.lines(Paths.get(resource.getURI()))) {
      words = lines
          .map(WORD_EXTRACTOR::matcher)
          .filter(Matcher::matches)
          .map((matcher) -> matcher.group(1))           //.map((matcher) -> matcher.replaceAll("$1"))
          .filter((word) -> !word.isBlank())
          .toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<String> provide() {
    return words;
  }

}
