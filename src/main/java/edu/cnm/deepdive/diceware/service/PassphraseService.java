package edu.cnm.deepdive.diceware.service;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PassphraseService implements AbstractPassphraseService {

  @Override
  public List<String> generate(int length) {
    return List.of("about", "above", "absent", "absorb", "abstract");
  }

}
