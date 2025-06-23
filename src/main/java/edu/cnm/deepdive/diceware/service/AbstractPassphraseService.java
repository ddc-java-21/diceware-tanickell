package edu.cnm.deepdive.diceware.service;

import java.util.List;

public interface AbstractPassphraseService {

  List<String> generate(int length);

}
